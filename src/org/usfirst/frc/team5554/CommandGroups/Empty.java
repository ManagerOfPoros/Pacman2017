package org.usfirst.frc.team5554.CommandGroups;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This is an empty command that runs when the robot isn't moving
 * during the autonomous period
 *
 */
public class Empty extends Command
{

	/**
	 * The command instantly ends
	 *
	 */
	@Override
	protected boolean isFinished()
	{
		return false;
	}


}
