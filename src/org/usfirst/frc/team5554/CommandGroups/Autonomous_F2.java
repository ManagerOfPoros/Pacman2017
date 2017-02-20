package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.CommandGroups.Commands.*;
import org.usfirst.frc.team5554.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous_F2 extends CommandGroup {

    public Autonomous_F2(Driver driver) {
    	addSequential(new DriveDistance(0.4, driver , 3));
    	addSequential(new AutoSpin(-RobotMap.DEGREES_TO_TURN_TO_AIRSHIP, driver));
    	addSequential(new DriveDistance(0.4, driver , 2));

    }
}
