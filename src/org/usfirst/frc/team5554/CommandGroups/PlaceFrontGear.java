package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.CommandGroups.Commands.*;
import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceFrontGear extends CommandGroup {

    public PlaceFrontGear(Driver driver) 
    {
		driver.CalibrateGyro();
		Timer.delay(0.1);
		addSequential(new TimedGyroDrive(0.3, driver , 2.5));
    }
}
