package org.usfirst.frc.team5554.Controllers;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PWMSpeedController;

/**
 * This class helps us use PID for a speed controller.
 * It represents a PWMSpeedController with a feedback device that the PIDController reads values from.
 *
 */
public class Motor extends PWMSpeedController{

	private PIDSource PidSource;
	private PIDController controller;

	/**
	 * Creates a motor object that represents a speed controller connected to a port
	 *
	 * @param port The speed controller's port
	 */
	public Motor(int port)
	{
		super(port);
	}

	/**
	 * Sets a feedback device that the motor will check in the pid
	 *
	 * @param PidSource The feedback device
	 */
	public void SetFeedbackDevice(PIDSource PidSource)
	{
		this.PidSource = PidSource;
	}

	/**
	 * Sets a type for the PID controller, whether it will go and remain at a value or just go to a value
	 *
	 * @param type The PID type
	 */
	public void SetPIDType(PIDSourceType type)
	{
		PidSource.setPIDSourceType(type);
	}

	/**
	 * Sets the values for the PID controller
	 *
	 * @param p The proportional value
	 * @param i The integral value
	 * @param d The derivative value
	 */
	public void SetPID(double p, double i, double d)
	{
		SetPIDController(p, i, d, 0);
	}

	/**
	 * Sets the values for the PID controller
	 *
	 * @param p The proportional value
	 * @param i The integral value
	 * @param d The derivative value
	 * @param f The feed forward value
	 *
	 */
	public void SetPID(double p, double i, double d, double f)
	{
		SetPIDController(p, i, d, f);
	}


	/**
	 * Sets the values for the PID controller
	 *
	 * @param p The proportional value
	 * @param i The integral value
	 * @param d The derivative value
	 * @param f The feed forward value
	 */
	private void SetPIDController(double p,double i, double d, double f)
	{
		if(controller == null)
		{
			controller = new PIDController(p, i, d,f, PidSource, this);
		}
		else
		{
			controller.setPID(p, i, d , f);
		}
	}

	/**
	 * Starts the PID loop with a specified target (setPoint)
	 *
	 * @param SetPoint The PID's set point (target)
	 */
	public void StartPIDLoop(double SetPoint)
	{

		if(controller==null)
		{
			System.out.println("PIDController is null");
		}
		else
		{
			controller.setSetpoint(SetPoint);

			if(!controller.isEnabled())
			{
				controller.enable();
			}
		}
	}

	/**
	 * Changes the direction of the front of the motor
	 *
	 * @param The invert flag
	 */
	public void invertMotor(boolean inverted)
	{
		setInverted(inverted);
	}

	/**
	 * Disables the PID controller
	 *
	 */
	public void disController()
	{
		if(controller != null)
		{
			controller.disable();
		}
	}

	/**
	 * Enables the PID controller
	 *
	 */
	public void enableController()
	{
		if(controller != null)
		{
			controller.enable();
		}
	}

	/**
	 * Returns whether the PID controller error is in the tolerable range
	 *
	 * @return true if the error is within the range
	 */
	public boolean onTarget()
	{
		return controller.onTarget();
	}

	/**
	 * Set the absolute error which is considered tolerable for use with OnTarget.
	 *
	 * @param tolerance In units of the input, the absolute tolerable error
	 */
	public void SetAbsTolernace(double tolerance)
	{
		controller.setAbsoluteTolerance(tolerance);

	}


	/**
	 * Gets the PID controller's error from the setPoint (How far the value is from the target)
	 *
	 * @return
	 */
	public double GetError()
	{
		return controller.getError();
	}

	/**
	 * Sets the maximum and minimum values expected from the input and setPoint.
	 *
	 * @param minValue The minimum values expected from the input and setPoint.
	 * @param maxValue The maximum values expected from the input and setPoint.
	 */
	public void setInputRange(double minValue , double maxValue)
	{
		controller.setInputRange(minValue, maxValue);
	}

}
