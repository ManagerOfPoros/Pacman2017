package org.usfirst.frc.team5554.robot;

import edu.wpi.first.wpilibj.Victor;

public class Climb {
	
	private Victor climber;
	
	public Climb(int climberPort)
	{
		climber = new Victor(climberPort);
	}
	
	public void climb(double speed) //INFO: First press activates climbing, second press stops climbing.
	{
		climber.set(speed);
	}

}
