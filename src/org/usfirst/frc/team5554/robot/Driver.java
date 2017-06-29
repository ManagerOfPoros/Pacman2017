package org.usfirst.frc.team5554.robot;

import org.usfirst.frc.team5554.Controllers.Motor;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * This class handles the robot's driver system.
 * It contains the robot's driving speed controllers, and works with their encoders.
 * It also works the robot gyro and can read its values and turn using it.
 *
 */
public class Driver extends RobotDrive
{
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private ADXRS450_Gyro gyro;
	private boolean isInvert = true;

	private boolean isEnabled = true;


	/**
	 * The constructor configures the motors objects to certain ports, configurates the robot encoders and gyro
	 * @param left The robot's left side speed controller
	 * @param right The robot's right side speed controller
	 * @param leftEnc The robot's left side encoder
	 * @param rightEnc The robot's right side encoder
	 * @param gyro The robot's gyro
	 */
	public Driver(Motor left, Motor right , Encoder leftEnc, Encoder rightEnc, ADXRS450_Gyro gyro)
	{
		super(left , right);

		this.leftEncoder = leftEnc;
		this.rightEncoder = rightEnc;

		this.gyro = gyro;
		gyro.reset();
	}

	/**
	 * The constructor configures the motors objects to certain ports and configures the robot encoders\
	 * @param left The robot's left side speed controller
	 * @param right The robot's right side speed controller
	 * @param leftEnc The robot's left side encoder
	 * @param rightEnc The robot's right side encoder
	 */
	public Driver(Motor left, Motor right , Encoder leftEnc, Encoder rightEnc)
	{
		super(left , right);

		this.leftEncoder = leftEnc;
		this.rightEncoder = rightEnc;
	}

	/**
	 * The constructor configures the motors objects to certain ports and the robot's gyro
	 * @param left The robot's left side speed controller
	 * @param right The robot's right side speed controller
	 * @param gyro The robot's gyro
	 */
	public Driver(Motor left, Motor right , ADXRS450_Gyro gyro)
	{
		super(left , right);

		this.gyro = gyro;
		gyro.reset();
	}

	/**
	 * The constructor configures the motors objects to certain ports
	 * @param left The robot's left side speed controller
	 * @param right The robot's right side speed controller
	 */
	public Driver(Motor left, Motor right)
	{
		super(left , right);
	}


	/**
	 * This function drives the robot according to a speed value and a turn value
	 * (usually sent from 2 joystick axis).
	 *
	 * @param slider A value that controlles the max output of the driver system
	 * (function as a sensitivity)
	 * @param speed The speed of the robot
	 * @param turn The angle the robot will drive in
	 */
	public void ArcadeDrive(double slider, double speed , double turn)
	{
		if(isEnabled)
		{
			slider = (-slider+1)/2;

			//Gives us freedom to manipulte the front of the robot.
			//If +slider and -slider can change the front of the motor since
			//they determine if the scalar is from 0-1 or from -1 to 0.

			setSafetyEnabled(false);

			if(isInvert == false)
			{
				setMaxOutput(slider);
				arcadeDrive(speed , -turn);
			}
			else
			{
				setMaxOutput(-slider);
				arcadeDrive(speed , turn);
			}

		}

	}

	/**
	 * This function drives the robot according to a left value and a right value,
	 * every value is incharge of every side's speed.
	 * (usually sent from 2 joysticks's main axis)
	 * @param leftValue The left side speed
	 * @param rightValue The right side speed
	 * @param slider A value that controlles the max output of the driver system
	 * (function as a sensitivity)
	 */
	public void TankDrive(double leftValue, double rightValue , double slider)
	{
		if(isEnabled)
		{

			slider = (-slider+1)/2;

			//Gives us freedom to manipulte the front of the robot.
			//If +slider and -slider can change the front of the motor since
			//they determine if the scalar is from 0-1 or from -1 to 0.

			setSafetyEnabled(false);

			if(isInvert == false)
			{
				setMaxOutput(slider);
				tankDrive(leftValue , rightValue);
			}
			else
			{
				setMaxOutput(-slider);
				tankDrive(leftValue , rightValue);
			}

		}

	}

	/**
	 * Drives the robot according to a speed value and a turn value
	 * this function is used for driving the robot not using a joystick.
	 * (Mostly used for the autonomous period)
	 *
	 * @param speed The speed of the robot
	 * @param curve The angle the robot will drive in
	 */
	public void autonomousDrive(double speed , double curve)
	{

		setMaxOutput(-1.0);
		setSafetyEnabled(false);
		drive(speed, curve);

	}

	/**
	 * Spins the robot a certain amount of degrees using the gyro.
	 * To reach the accurate degree, uses a PID controller.
	 *
	 * @param degrees The degrees the robot will turn
	 */
	public void Spin(double degrees)
	{
		if(isEnabled)
		{
			gyro.reset();

			((Motor)this.m_rearLeftMotor).SetFeedbackDevice(gyro);
			((Motor)this.m_rearRightMotor).SetFeedbackDevice(gyro);

			((Motor)this.m_rearRightMotor).SetPIDType(PIDSourceType.kDisplacement);
			((Motor)this.m_rearLeftMotor).SetPIDType(PIDSourceType.kDisplacement);

			((Motor)this.m_rearRightMotor).SetPID(0.01, 0.000, 0.002);
			((Motor)this.m_rearLeftMotor).SetPID(0.01, 0.000, 0.002);

			((Motor)this.m_rearRightMotor).StartPIDLoop(degrees);
			((Motor)this.m_rearLeftMotor).StartPIDLoop(degrees);
		}
	}


	/**
	 * Drives the robot a certain amount of distance.
	 * To reach the accurate distance, uses a PID controller
	 *
	 * @param leftDistance The distance the left side will drive (in the left encoder units)
	 * @param rightDistance The distance the right side will drive (in the right encoder units)
	 */
	public void DriveDistance(double leftDistance, double rightDistance)
	{
		if(isEnabled)
		{
			((Motor)this.m_rearRightMotor).SetFeedbackDevice(rightEncoder);
			((Motor)this.m_rearLeftMotor).SetFeedbackDevice(leftEncoder);

			((Motor)this.m_rearRightMotor).SetPIDType(PIDSourceType.kDisplacement);
			((Motor)this.m_rearLeftMotor).SetPIDType(PIDSourceType.kDisplacement);

			((Motor)this.m_rearRightMotor).SetPID(0.003, 0, 0);
			((Motor)this.m_rearLeftMotor).SetPID(0.003, 0, 0);

			((Motor)this.m_rearRightMotor).StartPIDLoop(rightDistance);
			((Motor)this.m_rearLeftMotor).StartPIDLoop(leftDistance);
		}
	}

	/**
	 * Returns if the left motor PID controller error is in the tolerable range
	 *
	 * @return if the left motor PID controller error is in the tolerable range
	 */
	public boolean LeftOnTarget()
	{
		return ((Motor)this.m_rearLeftMotor).onTarget();
	}

	/**
	 * Returns if the right motor PID controller error is in the tolerable range
	 *
	 * @return if the right motor PID controller error is in the tolerable range
	 */
	public boolean RightOnTarget()
	{
		return ((Motor)this.m_rearRightMotor).onTarget();
	}

	/**
	 * Set the absolute error which is considered tolerable for use with OnTarget for both sides.
	 *
	 * @param tolerance In units of the input, the absolute tolerable error
	 */
	public void setAbsTolerance(double tolerance)
	{
		((Motor)this.m_rearLeftMotor).SetAbsTolernace(tolerance);
		((Motor)this.m_rearRightMotor).SetAbsTolernace(tolerance);
	}


	/**
	 * Enables or disables the driver
	 * (This function won't affect anything if a PID controller is running)
	 *
	 * @param enabled If true enables the robot driver
	 */
	public void setEnable(boolean enabled)
	{
		if(enabled)
		{
			this.isEnabled = enabled;
		}
		else
		{
			this.m_rearLeftMotor.set(0);
			this.m_rearRightMotor.set(0);
			this.isEnabled = enabled;
		}
	}


	/**
	 * Enables or disables the right motor and left PID Controller
	 *
	 *@param enabled If true, enables both controllers
	 */
	public void setControllerEnb(boolean enabled)
	{
		if(enabled)
		{
			((Motor)this.m_rearRightMotor).enableController();
			((Motor)this.m_rearLeftMotor).enableController();
		}
		else
		{
			((Motor)this.m_rearRightMotor).disController();
			((Motor)this.m_rearLeftMotor).disController();
		}
	}


	/**
	 * Gets the right motor PID Controller error
	 *
	 * @return The right motor PID Controller error
	 */
	public double GetRightError()
	{
		return ((Motor)this.m_rearRightMotor).GetError();
	}

	/**
	 * Gets the left motor PID Controller error
	 *
	 * @return The left motor PID Controller error
	 */
	public double GetLeftError()
	{
		return ((Motor)this.m_rearLeftMotor).GetError();
	}

	/**
	 * Gets the driver's gyro current angle
	 *
	 * @return The driver's gyro current angle
	 */
	public double GetAngle()
	{
		return gyro.getAngle();
	}

	/**
	 * Calibrates the driver's gyro
	 */
	public void CalibrateGyro()
	{
		gyro.calibrate();
	}

	/**
	 * Resets the driver's gyro
	 */
	public void ResetGyro()
	{
		gyro.reset();
	}

	/**
	 * Resets the driver's encoders
	 *
	 */
	public void ResetEncoders()
	{
		leftEncoder.reset();
		rightEncoder.reset();
	}

	/**
	 * Gets the current left encoder count
	 *
	 * @return The current left encoder count
	 */
	public double GetLeftEncValue()
	{
		return leftEncoder.getDistance();
	}

	/**
	 * Gets the current right encoder count
	 *
	 * @return The current right encoder count
	 */
	public double GetRightEncValue()
	{
		return rightEncoder.getDistance();
	}

	/**
	 * Inverts the driver object front of the robot
	 *
	 */
	public void invert()
	{
		if(this.isInvert == false)
		{
			this.isInvert = true;
		}
		else
		{
			this.isInvert = false;
		}
	}

	/**
	 * Gets if the driver has been inverted
	 *
	 *@return True is the driver has been inverted
	 */
	public boolean isInverted()
	{
		return isInvert;
	}





}
