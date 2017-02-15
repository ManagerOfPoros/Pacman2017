package org.usfirst.frc.team5554.robot;

import edu.wpi.first.wpilibj.Victor;

public class Feeder {

	private Victor feeder;
	
	public Feeder(int feederPort)
	{
		feeder = new Victor(feederPort);
	}
	
	public void feed(double speed)
	{
		feeder.set(speed);
	}
	
}
