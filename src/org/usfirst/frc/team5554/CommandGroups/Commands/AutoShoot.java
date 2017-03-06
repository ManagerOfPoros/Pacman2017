package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoShoot extends Command
{
	Shooter shooter;
	double speed;

	public AutoShoot(Shooter _shooter , double speed, double timeout)
	{
		super("Auto Shoot" , timeout);
		shooter = _shooter;
		this.speed = speed;
	}
	
	@Override
	public void initialize()
	{
		shooter.PidShoot(speed);
		Timer.delay(2);
		shooter.scramble(0.8);
	}
	
	@Override
	protected void execute()
	{
	}
	
	@Override
	protected boolean isFinished()
	{		
		if(isTimedOut())
		{
			shooter.shoot(0);
			shooter.scramble(0);
			return true;
		}
		else
		{
			return false;
		}
	}

}
