package org.usfirst.frc.team5554.robot;

import org.usfirst.frc.team5554.CommandGroups.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase;
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
	private GearHolder gears;
	private Climb climber;
	private CameraThread streamer;
	
	/****************************************Joysticks********************************************/
	
	private Joystick joy;
	private Joystick xbox;
	
	/*****************************************Autonomous******************************************/
	
	private Command autonomousCommand;
	private SendableChooser<Command> redChooser;	
	private SendableChooser<Command> blueChooser;
	private Command redSelected;
	private Command blueSelected;
	
	
	@Override
	public void robotInit() 
	{
		/***********************************Declaring Sensors********************************************************/
		
		Encoder leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_CHANNELA, RobotMap.LEFT_ENCODER_CHANNELB , true , CounterBase.EncodingType.k4X);
		Encoder rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_CHANNELA, RobotMap.RIGHT_ENCODER_CHANNELB , true , CounterBase.EncodingType.k4X);
		Encoder shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_CHANNELA, RobotMap.SHOOTER_ENCODER_CHANNELB , true , CounterBase.EncodingType.k4X);
		ADXRS450_Gyro gyro = new ADXRS450_Gyro();
		/***********************************Declaring Operator Objects***********************************************/
		
		driver = new Driver(RobotMap.MOTOR_LEFT , RobotMap.MOTOR_RIGHT,leftEncoder,rightEncoder,gyro);
		shooter = new Shooter(RobotMap.MOTOR_SHOOT_ONE, RobotMap.MOTOR_SCRAMBLE, shooterEncoder);
		feeder = new Feeder(RobotMap.MOTOR_FEEDER);
		climber = new Climb(RobotMap.MOTOR_CLIMBER);
		gears = new GearHolder(RobotMap.GEAR_MICROSWITCH_PORT_ONE, RobotMap.GEAR_MICROSWITCH_PORT_TWO,RobotMap.RELAY_PORT,2);		
		gears.SetLeds(true);
		
		/**********************************Joysticks Declaration****************************************************/
		
		joy = new Joystick(RobotMap.DRIVER_JOYSTICK_PORT);
		xbox = new Joystick(RobotMap.DRIVER_XBOXJOYSTICK_PORT);
		
		/**********************************Cameras******************************************************************/
		
		streamer = new CameraThread(joy , xbox);
		streamer.start();
		
		/***********************************Autonomous Options***********************************************/
		
		redChooser = new SendableChooser<Command>();
		redChooser.addDefault("Empty", new Autonomous_Empty());
		redChooser.addObject("A1", new Autonomous_A1(driver));
		redChooser.addObject("A2", new Autonomous_A2(driver));
		redChooser.addObject("B", new Autonomous_B(driver));
		redChooser.addObject("C1", new Autonomous_C1(driver, shooter));
		redChooser.addObject("C2", new Autonomous_C2(driver));
		redChooser.addObject("C3", new Autonomous_C3(driver, shooter));
		redChooser.addObject("C4", new Autonomous_C4(driver));
		SmartDashboard.putData("Autonomous" , redChooser);
		
		blueChooser = new SendableChooser<Command>();
		blueChooser.addDefault("Empty", new Autonomous_Empty());
		blueChooser.addObject("D1", new Autonomous_D1(driver));
		blueChooser.addObject("D2", new Autonomous_D2(driver));
		blueChooser.addObject("E", new Autonomous_E(driver));
		blueChooser.addObject("F1", new Autonomous_F1(driver, shooter));
		blueChooser.addObject("F2", new Autonomous_F2(driver));
		blueChooser.addObject("F3", new Autonomous_F3(driver, shooter));
		blueChooser.addObject("F4", new Autonomous_F4(driver));
		SmartDashboard.putData("Autonomous" , blueChooser);
		
	}

	@Override
	public void autonomousInit() 
	{		
		
	}

	@Override
	public void autonomousPeriodic() 
	{
		Scheduler.getInstance().run();
	}
	
	@Override
	public void teleopInit()
	{
		streamer.SetSwitch(true);
	}

	@Override
	public void teleopPeriodic() 
	{
		/****************************************** Driving ********************************************/
		
		driver.Moving(joy.getRawAxis(RobotMap.JOYSTICK_Y_AXIS), joy.getRawAxis(RobotMap.JOYSTICK_Z_AXIS),
				joy.getRawAxis(RobotMap.JOYSTICK_SLIDER_AXIS));
		
		/****************************************** Shooter&Scramble ********************************************/
		
		//Shooter
		if(xbox.getRawAxis(RobotMap.XBOX_JOYSTICK_AUTO_SHOOT) > 0.15)
		{
			shooter.autoShoot();
		}
		else if(xbox.getRawButton(RobotMap.XBOX_JOYSTICK_SCRAMBLE_BACKWARD))
		{
			shooter.shoot(-0.5);
		}
		else
		{
			shooter.shoot(0);
		}
		
		
		//Scramble
		if(xbox.getRawAxis(RobotMap.XBOX_JOYSTICK_SCRAMBLE_FORWARD) > 0.1)
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
			feeder.feed(0.8);
		}
		else
		{
			feeder.feed(0);
		}
    	
    	/**************************************** Gear Holder ******************************************/
	
		gears.isGearIn();
		
		/****************************************** Climbing *******************************************/
		
		if(xbox.getRawButton(RobotMap.XBOX_CLIMB_BUTTON))
		{
			climber.climb(1);
		}
		else
		{
			climber.climb(0);
		}
		
	}
	
	@Override
	public void disabledPeriodic()
	{
		streamer.SetSwitch(false);
	}

	@Override
	public void testPeriodic() 
	{
	}
}

