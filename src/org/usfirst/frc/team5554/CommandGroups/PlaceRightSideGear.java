package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.CommandGroups.Commands.AutoSpin;
import org.usfirst.frc.team5554.CommandGroups.Commands.DistanceGyroDrive;
import org.usfirst.frc.team5554.CommandGroups.Commands.Timeout;
import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
*
* This command makes Pacman place a gear in the right side pin
*
*/
public class PlaceRightSideGear extends CommandGroup {


	/**
	 * Creates the command with a driver object containing the robot's speed controllers, encoders and gyro
	 *
	 * @param driver The driver object
	 */
	public PlaceRightSideGear(Driver driver)
	{
    	driver.CalibrateGyro();
    	addSequential(new Timeout(0.2));
    	addSequential(new DistanceGyroDrive(0.25, driver , RobotMap.DISTANCE_FROM_ALLIANCE_WALL_TO_RIGHT_SPRING,0.00245 ));
    	addSequential(new Timeout(0.8));
    	addSequential(new AutoSpin(RobotMap.ROTATION_DEGREE_FROM_ALLIANCE_WALL_TO_SIDE_GEAR_RIGHT, driver ));
    	addSequential(new Timeout(0.8));
    	addSequential(new DistanceGyroDrive(0.3, driver , 65,0.00245 ));

    }
}