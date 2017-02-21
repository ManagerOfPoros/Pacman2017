package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.CommandGroups.Commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous_C4 extends CommandGroup {

    public Autonomous_C4(Driver driver) {
		addSequential(new TimedDrive(0.4, driver , 3));
    }
}
