package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.CommandGroups.Commands.*;
import org.usfirst.frc.team5554.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous_D1 extends CommandGroup {

    public Autonomous_D1(Driver driver) {
		addSequential(new DriveDistance(RobotMap.DISTANCE_TO_BASELINE_FROM_START + RobotMap.ROBOT_LENGTH, driver));
		addSequential(new AutoSpin(RobotMap.DEGREES_TO_TURN_TO_AIRSHIP, driver));
		addSequential(new AutoSpin(-RobotMap.DEGREES_TO_TURN_TO_AIRSHIP, driver));
		addSequential(new DriveDistance(RobotMap.DISTANCE_TO_HOPPER_FROM_BASELINE, driver));
		
    }
}
