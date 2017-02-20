package org.usfirst.frc.team5554.robot;

import org.usfirst.frc.team5554.CommandGroups.*;
import org.usfirst.frc.team5554.Controllers.Motor;

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
	
	/****************************************Flags************************************************/
	
	public static boolean isInShootingMode;
	public static boolean isInAutonomousMode;
	
	
	
	int delayCount; // for tests only!!!!!!!!!!!!!!
	
	
	@Override
	public void robotInit() 
	{
		/****************************************Controllers********************************************/
		
		//Driving
		Motor left = new Motor(RobotMap.MOTOR_LEFT);
		Motor right = new Motor(RobotMap.MOTOR_RIGHT);
		Encoder leftEnc = new Encoder(RobotMap.LEFT_ENCODER_CHANNELA , RobotMap.LEFT_ENCODER_CHANNELB , true , CounterBase.EncodingType.k4X);
		Encoder rightEnc = new Encoder(RobotMap.RIGHT_ENCODER_CHANNELA , RobotMap.RIGHT_ENCODER_CHANNELB , true , CounterBase.EncodingType.k4X);
		leftEnc.setDistancePerPulse(RobotMap.DIAMETER_OF_6INCHWHEEL/RobotMap.ENCODER_ROUNDS_PER_REVOLUTION);
		rightEnc.setDistancePerPulse(RobotMap.DIAMETER_OF_6INCHWHEEL/RobotMap.ENCODER_ROUNDS_PER_REVOLUTION);
		ADXRS450_Gyro gyro = new ADXRS450_Gyro(RobotMap.GYRO_PORT);
		
		//Shooter
		Encoder shooterEnc = new Encoder(RobotMap.SHOOTER_ENCODER_CHANNELA , RobotMap.SHOOTER_ENCODER_CHANNELB , true , CounterBase.EncodingType.k4X);
		shooterEnc.setDistancePerPulse(RobotMap.DIAMETER_OF_6INCHWHEEL/RobotMap.ENCODER_ROUNDS_PER_REVOLUTION);
				
		/***********************************Declaring Operator Objects***********************************************/
		
		driver = new Driver(left,right ,leftEnc ,rightEnc ,gyro);
		shooter = new Shooter(RobotMap.MOTOR_SHOOTER, RobotMap.MOTOR_SCRAMBLE, shooterEnc);
		Robot.isInShootingMode = false;
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
		
		//Red Alliance
		redChooser = new SendableChooser<Command>();
		redChooser.addDefault("Empty", new Autonomous_Empty());
		redChooser.addObject("A1", new Autonomous_A1(driver));
		redChooser.addObject("A2", new Autonomous_A2(driver));
		redChooser.addObject("B", new Autonomous_B(driver));
		redChooser.addObject("C1", new Autonomous_C1(driver, shooter));
		redChooser.addObject("C2", new Autonomous_C2(driver));
		redChooser.addObject("C3", new Autonomous_C3(driver, shooter));
		redChooser.addObject("C4", new Autonomous_C4(driver));
		SmartDashboard.putData("RedAutonomous" , redChooser);
		
		//Blue Alliance
		blueChooser = new SendableChooser<Command>();
		blueChooser.addDefault("Empty", new Autonomous_Empty());
		blueChooser.addObject("D1", new Autonomous_D1(driver));
		blueChooser.addObject("D2", new Autonomous_D2(driver));
		blueChooser.addObject("E", new Autonomous_E(driver));
		blueChooser.addObject("F1", new Autonomous_F1(driver, shooter));
		blueChooser.addObject("F2", new Autonomous_F2(driver));
		blueChooser.addObject("F3", new Autonomous_F3(driver, shooter));
		blueChooser.addObject("F4", new Autonomous_F4(driver));
		SmartDashboard.putData("BlueAutonomous" , blueChooser);
		
	}

	@Override
	public void autonomousInit() 
	{		
		//This section determines that if the blue autonomous selected
		//is empty then the autonoumous command selected will be from the 
		//red chooser and the opposite.
		//If both are selected empty then nothing will happen during the autonomous period.
		//If both sides are picked then nothing will happen during the autonomous period.
		
		redSelected = redChooser.getSelected();
		blueSelected = blueChooser.getSelected();
		
		if(blueSelected.toString().equals("Autonomous_Empty") && redSelected.toString().equals("Autonomous_Empty"))
		{
			autonomousCommand = new Autonomous_Empty();
		}
		else if(blueSelected.toString().equals("Autonomous_Empty"))
		{
			autonomousCommand = redSelected;
		}
		else if(redSelected.toString().equals("Autonomous_Empty"))
		{
			autonomousCommand = blueSelected;
		}
		else
		{
			autonomousCommand = new Autonomous_Empty();
		}
		
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
			shooter.autoShoot();
			Robot.isInShootingMode = true;
			driver.disable();
		}
		else if(xbox.getRawButton(RobotMap.XBOX_JOYSTICK_SCRAMBLE_BACKWARD))
		{
			shooter.shoot(-0.5);
			Robot.isInShootingMode = false;
			shooter.disController();
			driver.enable();
		}
		else
		{
			shooter.shoot(0);
			Robot.isInShootingMode = false;
			shooter.disController();
			driver.enable();
		}
		
		
		//Scramble
		if(xbox.getRawAxis(RobotMap.XBOX_JOYSTICK_SCRAMBLE_FORWARD) > 0.1 || Robot.isInShootingMode)
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
		
		if(joy.getRawButton(RobotMap.JOYSTICK_FEEDER_BUTTON) || Robot.isInShootingMode)
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
		
		/*************************************** Dashboard Widgets *************************************/
		
		SmartDashboard.putNumber("Shooter Speed", shooter.GetSpeed());
		SmartDashboard.putNumber("Shooter PWM", shooter.GetPwmScalar());
		
		
		
		/**************TEST SECTION - INCREASING AND DECREASING VELOCITY*****************************/
		
		if(delayCount>0){
			delayCount--;
		}
		else
			delayCount=0;
		
		if(joy.getRawButton(3) && delayCount==0){
			shooter.increaseVelocity();
			delayCount=30;
		}
		if(joy.getRawButton(4) && delayCount==0){
			shooter.decreaseVelocity();
			delayCount=30;
		}		
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
	}
	
}

