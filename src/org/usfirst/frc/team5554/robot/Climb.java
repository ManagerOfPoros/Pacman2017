package org.usfirst.frc.team5554.robot;

import edu.wpi.first.wpilibj.Victor;

/**
*
* This class controls the climber speed controllers
*
*/
public class Climb {

	private Victor climber;
	private Victor climbertwo;

	/**
	 * Creates the climber object and configures climber to its ports
	 *
	 * @param climberPort The first climber port
	 * @param climberPortTwo The second climber port
	 */
	public Climb(int climberPort, int climberPortTwo)
	{
		climber = new Victor(climberPort);
		climbertwo = new Victor(climberPortTwo);
	}

	/**
	 * Activates the climber with a given speed
	 *
	 * @param speed The speed the climber will climb in
	 */
	public void climb(double speed)
	{
		climber.set(-speed);
		climbertwo.set(speed);
	}

}
