package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.CommandGroups.Commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous_D2 extends CommandGroup {

    public Autonomous_D2(Driver driver) {
        addSequential(new DriveDistance(0.3, driver,3));
    }
}
