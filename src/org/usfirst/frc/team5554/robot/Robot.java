package org.usfirst.frc.team5554.robot;

import org.usfirst.frc.team5554.CommandGroups.*;
import org.usfirst.frc.team5554.Controllers.Motor;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
		
	/****************************************Operator Objects**********************************************/
	
	private Driver driver;
	private Shooter shooter;
	private Feeder feeder;
	private Climb climber;
	private CameraThread streamer;
	
	/****************************************Joysticks********************************************/
	
	private Joystick rightJoy;
	private Joystick leftJoy;
	private Joystick xbox;
	
	/*****************************************Autonomous******************************************/
	
	private Command autonomousCommand;
	private SendableChooser<Command> autoChooser = new SendableChooser<>();
	
	/****************************************Flags************************************************/
	
	public static boolean isInShootingMode;
	
	//private boolean ignoreButton4 = false; 
	
	
	
	@Override
	public void robotInit() 
	{
		/****************************************Controllers********************************************/
		
		
		//Driving
		Motor left = new Motor(RobotMap.MOTOR_LEFT);
		Motor right = new Motor(RobotMap.MOTOR_RIGHT);
		Encoder leftEnc = new Encoder(RobotMap.LEFT_ENCODER_CHANNELA , RobotMap.LEFT_ENCODER_CHANNELB , false);
		Encoder rightEnc = new Encoder(RobotMap.RIGHT_ENCODER_CHANNELA , RobotMap.RIGHT_ENCODER_CHANNELB , false);
		leftEnc.setDistancePerPulse(RobotMap.PERIMETER_OF_LEFT_WHEEL/RobotMap.ENCODER_ROUNDS_PER_REVOLUTION);
		rightEnc.setDistancePerPulse(RobotMap.PERIMETER_OF_RIGHT_WHEEL/RobotMap.ENCODER_ROUNDS_PER_REVOLUTION);
		ADXRS450_Gyro gyro = new ADXRS450_Gyro(RobotMap.GYRO_PORT);
				
		/***********************************Declaring Operator Objects***********************************************/
		
		driver = new Driver(left,right , leftEnc, rightEnc, gyro);
		driver.CalibrateGyro();
		shooter = new Shooter(RobotMap.MOTOR_SHOOTER_ZERO, RobotMap.MOTOR_SHOOTER_ONE, RobotMap.MOTOR_SCRAMBLE);
		Robot.isInShootingMode = false;
		feeder = new Feeder(RobotMap.MOTOR_FEEDER);
		climber = new Climb(RobotMap.MOTOR_CLIMBER_ONE, RobotMap.MOTOR_CLIMBER_TWO);
		
		/**********************************Joysticks Declaration****************************************************/
		
		leftJoy = new Joystick(RobotMap.DRIVER_LEFT_JOYSTICK_PORT);
		rightJoy = new Joystick(RobotMap.DRIVER_RIGHT_JOYSTICK_PORT);
		xbox = new Joystick(RobotMap.DRIVER_XBOXJOYSTICK_PORT);
		
		/**********************************Cameras******************************************************************/
		
		streamer = new CameraThread(xbox);
		streamer.start();
		
		/***********************************Autonomous Options***********************************************/
		
		autoChooser.addDefault("Empty", ((Command)new Empty()));
		autoChooser.addObject("PassBaseLine", ((Command)new PassBaseLine(driver)));
		autoChooser.addObject("PlaceFrontGear", ((Command)new PlaceFrontGear(driver)));
		autoChooser.addObject("PlaceLeftSideGear", ((Command)new PlaceLeftSideGear(driver)));
		autoChooser.addObject("PlaceRightSideGear", ((Command)new PlaceRightSideGear(driver)));
		SmartDashboard.putData("Autonomous Chooser" , autoChooser);

		
	}

	@Override
	public void autonomousInit() 
	{		
		/**This section determines that if the blue autonomous selected
		 *is empty then the autonoumous command selected will be from the 
		 *red chooser and the opposite.
		 *If both are selected empty then nothing will happen during the autonomous period.
		 *If both sides are picked then nothing will happen during the autonomous period.
		**/
		autonomousCommand = ((Command)autoChooser.getSelected());
		autonomousCommand.start();
				
	}

	@Override
	public void autonomousPeriodic() 
	{
		Scheduler.getInstance().run();
	}
	
	@Override
	public void teleopInit()
	{
		CameraThread.toSwitch = true;
		
		driver.enable();
		//if(driver.isInverted() == false)
		//{
			//driver.invert();
		//}
	}

	@Override
	public void teleopPeriodic() 
	{		
		/****************************************** Driving ********************************************/

		driver.TankDrive(leftJoy.getY() , rightJoy.getY() , rightJoy.getRawAxis(RobotMap.JOYSTICK_SLIDER_AXIS));  //lowers the sensitivity of the turns
		
    	//if(rightJoy.getRawButton(RobotMap.JOYSTICK_SWITCH_FRONT) && ignoreButton4 == false)
    	//{
    		//ignoreButton4 = true;
			
    		//driver.invert();
    	//}
    	//else if(!rightJoy.getRawButton(RobotMap.JOYSTICK_SWITCH_FRONT))
    	//{
    	//	ignoreButton4 = false;
    	//}
		
		/****************************************** Shooter&Scramble ********************************************/
		
		double shooterSlider = leftJoy.getRawAxis(RobotMap.JOYSTICK_SLIDER_AXIS); 
		
		shooterSlider = (-shooterSlider+1)/2;
		
		System.out.println(shooterSlider);
		
		//Shooter
		if(xbox.getRawAxis(RobotMap.XBOX_JOYSTICK_AUTO_SHOOT) > 0.15)
		{
			shooter.shoot(shooterSlider);
			
		}
		else if(xbox.getRawButton(RobotMap.XBOX_JOYSTICK_REVERSE_SHOOT))
		{
			shooter.shoot(-0.4); //was -0.4
		}
		else
		{
			shooter.shoot(0);
		}
		
		//Scramble
		if(xbox.getRawAxis(RobotMap.XBOX_JOYSTICK_SCRAMBLE_FORWARD) > 0.1 )
		{
			shooter.scramble(0.8);
		}
		else if(xbox.getRawButton(RobotMap.XBOX_JOYSTICK_SCRAMBLE_BACKWARD))
		{
			shooter.scramble(-0.8); 
		}
		else
		{
			shooter.scramble(0);
		}
    	
		/****************************************** Feeder *********************************************/
		
		if(rightJoy.getRawButton(RobotMap.JOYSTICK_FEEDER_BUTTON)||leftJoy.getRawButton(RobotMap.JOYSTICK_FEEDER_BUTTON))
		{
			feeder.feed(-0.8);
		}
		else if(rightJoy.getRawButton(RobotMap.JOYSTICK_FEEDER_REVERSE_BUTTON)||leftJoy.getRawButton(RobotMap.JOYSTICK_FEEDER_REVERSE_BUTTON))
		{
			feeder.feed(0.8);
		}
		else
		{
			feeder.feed(0);
		}
    	
    	/**************************************** Gear Holder ******************************************/
	
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		/****************************************** Climbing *******************************************/
		
		if(xbox.getRawButton(RobotMap.XBOX_CLIMB_BUTTON))
		{
			climber.climb(0.4);    //was 0.5
		}
		else if(xbox.getRawButton(RobotMap.XBOX_CLIMB_FAST_BUTTON))
		{
			climber.climb(0.6);   //was 0.8
		}
		else if(xbox.getRawButton(RobotMap.XBOX_REVERSE_CLIMB_BUTTON))
		{
			climber.climb(-0.4);  // was -0.5  
		}
		else if(xbox.getRawButton(RobotMap.XBOX_CLIMB_SUPERFAST_BUTTON))
		{
			climber.climb(0.8);   //was 1
		}
		else if(xbox.getRawButton(2))
		{
			climber.climb(0.15);   //was 1
		}
		else
		{
			climber.climb(0);
		}   
		


	}
	
	@Override
	public void disabledPeriodic()
	{
		CameraThread.toSwitch = false;
		Robot.isInShootingMode = false;
		driver.ResetEncoders();
	}

	@Override
	public void testPeriodic() 
	{
		driver.ResetGyro();
	}
	
}

