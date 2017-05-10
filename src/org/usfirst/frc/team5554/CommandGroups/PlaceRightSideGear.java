package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.CommandGroups.Commands.AutoSpin;
import org.usfirst.frc.team5554.CommandGroups.Commands.DistanceGyroDrive;
import org.usfirst.frc.team5554.CommandGroups.Commands.Timeout;
import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceRightSideGear extends CommandGroup {

    public PlaceRightSideGear(Driver driver)
       
    	 {
    	///////////////////////////////Left////////////////////////
    		driver.CalibrateGyro();
    		addSequential(new Timeout(0.2));
    		addSequential(new DistanceGyroDrive(0.25, driver , RobotMap.DISTANCE_FROM_ALLIANCE_WALL_TO_RIGHT_SPRING ));
    		addSequential(new Timeout(0.8));
    		addSequential(new AutoSpin(RobotMap.ROTATION_DEGREE_FROM_ALLIANCE_WALL_TO_SIDE_GEAR_RIGHT, driver , 5.5 ));
    		addSequential(new Timeout(0.8));
    		addSequential(new DistanceGyroDrive(0.3, driver , 65 ));
    		
//        	///////////////////////////////Right////////////////////////
//    		driver.CalibrateGyro();
//    		addSequential(new Timeout(0.2));
//    		addSequential(new DistanceGyroDrive(0.3, driver , RobotMap.DISTANCE_FROM_ALLIANCE_WALL_TO_RIGHT_SPRING+20 ));
//    		addSequential(new Timeout(0.6));
//    		addSequential(new AutoSpin(RobotMap.ROTATION_DEGREE_FROM_ALLIANCE_WALL_TO_SIDE_GEAR_RIGHT, driver , 5.5 ));
//    		addSequential(new Timeout(0.2));
//    		addSequential(new DistanceGyroDrive(0.3, driver , 72.5+20 ));
    	    
    	 }
}