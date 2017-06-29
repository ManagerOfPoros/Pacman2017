package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Shooter;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command activates pacman's shooter system,
 * the scramble and shooter for a specified amount of time
 *
 */
public class AutoShoot extends Command
{
	Shooter shooter;
	double speed;

	/**
	 * Creates the command with a shooter object that contains
	 * Pacman's shooter and scramble and the amount of time the command
	 * will run
	 *
	 * @param _shooter The shooter object containing Pacman's shooter and scramble
	 * @param speed The speed the shooter will run at
	 * @param timeout The amount of time the command will run
	 */
	public AutoShoot(Shooter _shooter , double speed, double timeout)
	{
		super("Auto Shoot" , timeout);
		shooter = _shooter;
		this.speed = speed;
	}

	/**
	 * Initializes the command' starts the shooter
	 * and starts the scrambler
	 *
	 */
	@Override
	public void initialize()
	{
		shooter.PidShoot(speed);
		shooter.scramble(0.8);
	}

	@Override
	protected void execute()
	{
	}

	/**
	 * The command ends when the timeout has ended
	 *
	 */
	@Override
	protected boolean isFinished()
	{
		if(isTimedOut())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * When the command ends stops the shooter and scrambler
	 *
	 */
	@Override
	protected void end()
	{
		shooter.shoot(0);
		shooter.scramble(0);
	}

}
