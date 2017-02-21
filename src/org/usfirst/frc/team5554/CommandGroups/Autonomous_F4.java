package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.CommandGroups.Commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous_F4 extends CommandGroup {

    public Autonomous_F4(Driver driver) {
    	addSequential(new TimedDrive(0.4, driver , 2));
    }
}
