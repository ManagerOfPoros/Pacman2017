package org.usfirst.frc.team5554.robot;

import edu.wpi.first.wpilibj.Victor;

/**
 *
 * This class controls the feeder speed controller
 *
 */
public class Feeder {

	private Victor feeder;

	/**
	 * Creates the feeder object and configures the feeder to its port
	 *
	 * @param feederPort The feeder port
	 */
	public Feeder(int feederPort)
	{
		feeder = new Victor(feederPort);
	}

	/**
	 * Activates the feeder with a given speed
	 *
	 * @param speed The speed the feeder will feed in
	 */
	public void feed(double speed)
	{
		feeder.set(speed);
	}

}
