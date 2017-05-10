package org.usfirst.frc.team5554.CommandGroups.Commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Timeout extends Command {

    public Timeout(double timeout) 
    {
        super("Timeout" , timeout);
    }


    protected boolean isFinished()
    {
        if(isTimedOut())
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }
}
