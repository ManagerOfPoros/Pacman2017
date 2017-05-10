package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.CommandGroups.Commands.*;
import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.robot.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PassBaseLine extends CommandGroup
{
	public PassBaseLine(Driver driver)
	{
		driver.CalibrateGyro();
		addSequential(new DistanceGyroDrive(0.3, driver , RobotMap.DISTANCE_FROM_ALLIANCE_WALL_TO_RIGHT_SPRING));
		
	}
}
