package org.usfirst.frc.team5554.robot;

import org.usfirst.frc.team5554.CommandGroups.*;
import org.usfirst.frc.team5554.Controllers.Indicator;
import org.usfirst.frc.team5554.Controllers.Motor;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
		
	/****************************************Operator Objects**********************************************/
	
	private Driver driver;
	private Shooter shooter;
	private Feeder feeder;
	private Indicator gears;
	private Climb climber;
	private CameraThread streamer;
	
	/****************************************Joysticks********************************************/
	
	private Joystick joy;
	private Joystick xbox;
	
	/*****************************************Autonomous******************************************/
	
	private Command autonomousCommand;
	private SendableChooser<Command> autoChooser = new SendableChooser<Command>();
	
	/****************************************Flags************************************************/
	
	public static boolean isInShootingMode;
	public static boolean isInAutonomousMode;
	
	private boolean ignoreIncreaseSwitch = false; // for tests only!!!!!!!!!!!!!!
	private boolean ignoreDecreaseSwitch = false; // for tests only!!!!!!!!!!!!!!
	
	
	@Override
	public void robotInit() 
	{
		/****************************************Controllers********************************************/
		
		//Driving
		Victor LEDs = new Victor(RobotMap.LEDS_PORT);
		DigitalInput microSwitch = new DigitalInput(RobotMap.GEAR_MICROSWITCH_PORT);
		Motor left = new Motor(RobotMap.MOTOR_LEFT);
		Motor right = new Motor(RobotMap.MOTOR_RIGHT);
		Encoder leftEnc = new Encoder(RobotMap.LEFT_ENCODER_CHANNELA , RobotMap.LEFT_ENCODER_CHANNELB , true , CounterBase.EncodingType.k4X);
		Encoder rightEnc = new Encoder(RobotMap.RIGHT_ENCODER_CHANNELA , RobotMap.RIGHT_ENCODER_CHANNELB , true , CounterBase.EncodingType.k4X);
		leftEnc.setDistancePerPulse(RobotMap.DIAMETER_OF_6INCHWHEEL/RobotMap.ENCODER_ROUNDS_PER_REVOLUTION);
		rightEnc.setDistancePerPulse(RobotMap.DIAMETER_OF_6INCHWHEEL/RobotMap.ENCODER_ROUNDS_PER_REVOLUTION);
		ADXRS450_Gyro gyro = new ADXRS450_Gyro(RobotMap.GYRO_PORT);
		gyro.reset();
				
		/***********************************Declaring Operator Objects***********************************************/
		
		driver = new Driver(left,right ,leftEnc ,rightEnc ,gyro);
		shooter = new Shooter(RobotMap.MOTOR_SHOOTER_ZERO, RobotMap.MOTOR_SHOOTER_ONE, RobotMap.MOTOR_SCRAMBLE);
		Robot.isInShootingMode = false;
		feeder = new Feeder(RobotMap.MOTOR_FEEDER);
		climber = new Climb(RobotMap.MOTOR_CLIMBER_ONE, RobotMap.MOTOR_CLIMBER_TWO);
		gears = new Indicator(microSwitch);		
		gears.SetOutpotDevice(LEDs);;
		
		/**********************************Joysticks Declaration****************************************************/
		
		joy = new Joystick(RobotMap.DRIVER_JOYSTICK_PORT);
		xbox = new Joystick(RobotMap.DRIVER_XBOXJOYSTICK_PORT);
		
		/**********************************Cameras******************************************************************/
		
		streamer = new CameraThread(joy , xbox);
		streamer.start();
		
		/***********************************Autonomous Options***********************************************/
		
		autoChooser.addDefault("Empty", null);
		autoChooser.addObject("PassBseLine", new PassBaseLine(driver));
		autoChooser.addObject("PlaceFrontGear", new PlaceFrontGear(driver));
		autoChooser.addObject("AutoShoot", new ShootAuto(shooter));
		SmartDashboard.putData("Auto Selector" , autoChooser);

		
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
		autonomousCommand = autoChooser.getSelected();
		autonomousCommand.start();
				
	}

	@Override
	public void autonomousPeriodic() 
	{
		Scheduler.getInstance().run();
		isInAutonomousMode = isAutonomous();
	}
	
	@Override
	public void teleopInit()
	{
		CameraThread.toSwitch = true;
	}

	@Override
	public void teleopPeriodic() 
	{
		/****************************************** Driving ********************************************/

		driver.Moving(joy.getRawAxis(RobotMap.JOYSTICK_SLIDER_AXIS) , joy);
	
		/****************************************** Shooter&Scramble ********************************************/
		
		//Shooter
		if(xbox.getRawAxis(RobotMap.XBOX_JOYSTICK_AUTO_SHOOT) > 0.15)
		{
			shooter.PidShoot(RobotMap.PID_SPEED);
			Robot.isInShootingMode = true;
			driver.disable();
		}
		else if(xbox.getRawButton(RobotMap.XBOX_JOYSTICK_SHOOTER_BACKWARD))
		{
			shooter.shoot(0.5);
			Robot.isInShootingMode = false;
			driver.enable();
		}
		else
		{
			shooter.shoot(0);
			Robot.isInShootingMode = false;
			driver.enable();
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
		
		if(joy.getRawButton(RobotMap.JOYSTICK_FEEDER_BUTTON))
		{
			feeder.feed(-0.8);
		}
		else if(joy.getRawButton(RobotMap.JOYSTICK_FEEDER_REVERSE_BUTTON))
		{
			feeder.feed(0.8);
		}
		else
		{
			feeder.feed(0);
		}
    	
    	/**************************************** Gear Holder ******************************************/
	
		gears.SendOutput(0.3, 4);;
		
		/****************************************** Climbing *******************************************/
		
		if(xbox.getRawButton(RobotMap.XBOX_CLIMB_BUTTON))
		{
			climber.climb(1);
		}
		else if(xbox.getRawButton(RobotMap.XBOX_REVERSE_CLIMB_BUTTON))
		{
			climber.climb(-1);
		}
		else
		{
			climber.climb(0);
		}
		
		/*************************************** Dashboard Widgets *************************************/
		
		SmartDashboard.putNumber("Shooter PWM", shooter.GetSpeed());	
		SmartDashboard.putNumber("P of shooter:", shooter.getP());
		SmartDashboard.putNumber("I of shooter:", shooter.getI());
		SmartDashboard.putNumber("D of shooter:", shooter.getD());
		//**************TEST SECTION - INCREASING AND DECREASING VELOCITY*****************************//
		
    	if(joy.getRawButton(8) && ignoreIncreaseSwitch == false)
    	{
			ignoreIncreaseSwitch = true;
			
    		if(shooter.GetSpeed() <= 1)
    		{
    			shooter.SetSpeed(shooter.GetSpeed()+0.01);
    		}
    	}
    	else if(!joy.getRawButton(8))
    	{
    		ignoreIncreaseSwitch = false;
    	}
    	
		//decrease speed button
    	if(joy.getRawButton(7) && ignoreDecreaseSwitch == false)
    	{
    		ignoreDecreaseSwitch = true;
    		
    		if(shooter.GetSpeed() >= 0)
    		{
    			shooter.SetSpeed(shooter.GetSpeed()-0.01);
    		}

    	}
    	else if(!joy.getRawButton(7))
    	{
    		ignoreDecreaseSwitch = false;
    	}
    	
		if(joy.getRawButton(9))
		{
			shooter.tweakPID(0.01);
		}else if(joy.getRawButton(10)){
			shooter.tweakPID(-0.01);
		}
    	
    	//System.out.println("Speed is " + shooter.GetSpeed());
    	//shooter.setP((-joy.getRawAxis(3))/2 + 0.5);
	}
	
	@Override
	public void disabledPeriodic()
	{
		CameraThread.toSwitch = false;
		Robot.isInShootingMode = false;
	}

	@Override
	public void testPeriodic() 
	{
		driver.ResetGyro();
	}
	
}

