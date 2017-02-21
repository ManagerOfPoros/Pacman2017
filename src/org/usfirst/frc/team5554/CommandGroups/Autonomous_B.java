package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.CommandGroups.Commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous_B extends CommandGroup {

    public Autonomous_B(Driver driver) {
    	addSequential(new AutoSpin(90, driver));
    }
}
