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
public class PlaceRightSideGear extends CommandGroup {

    public PlaceRightSideGear(Driver driver) 
       
    	 {
    			driver.CalibrateGyro();
    			Timer.delay(0.1);
    			addSequential(new DistanceGyroDrive(0.3, driver , RobotMap.DISTANCE_FROM_ALLIANCE_WALL_TO_RIGHT_SPRING ));
    			Timer.delay(0.1);
    			addSequential(new AutoSpin(-63, driver , 5.5 ));
    			driver.CalibrateGyro();
    			Timer.delay(0.5);
    			addSequential(new DistanceGyroDrive(0.3, driver , 50 ));
    	    }
}