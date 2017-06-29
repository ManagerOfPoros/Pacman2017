package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.CommandGroups.Commands.AutoShoot;
import org.usfirst.frc.team5554.robot.Shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command activates Pacman's shooter system
 */
public class ShootAuto extends CommandGroup {


	/**
	 * Creates the command with a shooter object that contains
	 * Pacman's shooter and scramble
	 *
	 * @param shooter The shooter object
	 */
	public ShootAuto(Shooter shooter)
    {
    	addSequential(new AutoShoot(shooter , 0.62 , 14));
    }
}
