package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class AutoSpin extends InstantCommand {
	
	int degrees;
	Driver driver;
	
    public AutoSpin(int _degrees, Driver _driver) {
    	super("Auto Spin");
    	degrees = _degrees;
    	driver = _driver;
    }

	@Override
    protected void initialize() {
    	//driver.Spin(degrees);
    	//System.out.printf("Spinned %d degrees", degrees);
    }

}
