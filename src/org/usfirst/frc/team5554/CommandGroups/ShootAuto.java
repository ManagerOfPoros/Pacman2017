package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.CommandGroups.Commands.AutoShoot;
import org.usfirst.frc.team5554.robot.Shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootAuto extends CommandGroup {

    public ShootAuto(Shooter shooter) {
    	addSequential(new AutoShoot(shooter , 0.62 , 14));
    }
}
