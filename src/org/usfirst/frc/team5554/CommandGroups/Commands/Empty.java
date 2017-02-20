package org.usfirst.frc.team5554.CommandGroups.Commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Empty extends Command {

    public Empty() 
    {
    	super("Empty");
    }
    @Override
    protected void initialize() {
    	System.out.println("This is the empty command");
    }
	@Override
	protected boolean isFinished() {
		return true;
	}
    
}
