package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.CommandGroups.Commands.*;
import org.usfirst.frc.team5554.robot.RobotMap;
import org.usfirst.frc.team5554.robot.Shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous_F1 extends CommandGroup {

    public Autonomous_F1(Driver driver, Shooter shooter) {
    	addSequential(new DriveDistance(RobotMap.DISTANCE_TO_BASELINE_FROM_START + RobotMap.ROBOT_LENGTH, driver));
    	addSequential(new AutoSpin(-RobotMap.DEGREES_TO_TURN_TO_AIRSHIP, driver));
    	addSequential(new DriveDistance(RobotMap.DISTANCE_TO_AIRSHIP_FROM_BASELINE, driver));
    	addSequential(new AutoSpin(180, driver));
    	addSequential(new AutoShoot(shooter));
    }
}
