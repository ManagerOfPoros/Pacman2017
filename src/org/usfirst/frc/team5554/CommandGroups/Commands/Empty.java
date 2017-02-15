package org.usfirst.frc.team5554.CommandGroups.Commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class Empty extends InstantCommand {

    public Empty() {
    	super("Empty");
    }
    @Override
    protected void initialize() {
    	System.out.println("This is the empty command");
    }
    
}
