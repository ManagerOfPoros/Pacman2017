package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Robot;
import org.usfirst.frc.team5554.robot.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class AutoShoot extends Command
{
	Shooter shooter;

	public AutoShoot(Shooter _shooter)
	{
		super("Auto Shoot");
		shooter = _shooter;
	}
	
	@Override
	public void initialize()
	{
		System.out.println("What a great shot");
	}
	
	@Override
	protected void execute()
	{
		shooter.autoShoot();
	}
	
	@Override
	protected boolean isFinished()
	{
		if(Robot.isInAutonomousMode)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
