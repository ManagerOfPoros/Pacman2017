package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.CommandGroups.Commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous_E extends CommandGroup {

    public Autonomous_E(Driver driver) {
    	addSequential(new TimedDrive(0.4, driver , 3));
    }
}
