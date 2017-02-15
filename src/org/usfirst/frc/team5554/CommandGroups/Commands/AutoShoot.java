package org.usfirst.frc.team5554.CommandGroups.Commands;

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
		shooter.autoShoot();
		System.out.println("What a great shot");
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
