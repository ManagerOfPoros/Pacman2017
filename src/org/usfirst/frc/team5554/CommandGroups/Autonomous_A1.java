package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.CommandGroups.Commands.*;
import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous_A1 extends CommandGroup
{
	public Autonomous_A1(Driver driver)
	{
		addSequential(new DriveDistance(0.5, driver , 2));
		addSequential(new AutoSpin(-RobotMap.DEGREES_TO_TURN_TO_AIRSHIP, driver));
		addSequential(new AutoSpin(RobotMap.DEGREES_TO_TURN_TO_AIRSHIP, driver));
		addSequential(new DriveDistance(0.5, driver , 2));
		
	}
}
