package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.CommandGroups.Commands.AutoSpin;
import org.usfirst.frc.team5554.CommandGroups.Commands.DistanceGyroDrive;
import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceLeftSideGear extends CommandGroup {

    public PlaceLeftSideGear(Driver driver) 
 
    	 {
    			driver.CalibrateGyro();
    			Timer.delay(0.1);
    			addSequential(new DistanceGyroDrive(0.35, driver , RobotMap.DISTANCE_FROM_ALLIANCE_WALL_TO_LEFT_SPRING));
    			Timer.delay(0.1);
    			addSequential(new AutoSpin(62, driver , 5.5 ));	
    			Timer.delay(0.1);
    			driver.CalibrateGyro();
    			Timer.delay(0.1);
    			addSequential(new DistanceGyroDrive(0.3, driver , 50 ));
    	    }
}
