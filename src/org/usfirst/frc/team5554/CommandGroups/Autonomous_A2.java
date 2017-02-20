package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.CommandGroups.Commands.*;
import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous_A2 extends CommandGroup {

    public Autonomous_A2(Driver driver) {
       addSequential(new DriveDistance(0.4, driver , 2));
       }
}
