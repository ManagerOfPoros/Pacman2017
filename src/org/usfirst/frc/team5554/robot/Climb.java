package org.usfirst.frc.team5554.robot;

import edu.wpi.first.wpilibj.Victor;

public class Climb {
	
	private Victor climber;
	private Victor climbertwo;
	
	public Climb(int climberPort, int climberPortTwo)
	{
		climber = new Victor(climberPort);
		climbertwo = new Victor(climberPortTwo);
	}
	
	public void climb(double speed) //INFO: First press activates climbing, second press stops climbing.
	{
		climber.set(-speed);
		climbertwo.set(speed);
	}

}
