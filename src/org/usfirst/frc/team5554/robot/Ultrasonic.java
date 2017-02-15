package org.usfirst.frc.team5554.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class Ultrasonic 
{
	AnalogInput ultrasonic;
	
	public Ultrasonic(int Port)
	{
		ultrasonic = new AnalogInput(Port);
		ultrasonic.setOversampleBits(10);
	}
	
	public double getRangeCm()
	{
		double voltage = ultrasonic.getVoltage();
		double mm = voltage*(1023/10);
		
		return mm;
	}
	
	public double getRangeMeters(){
		return this.getRangeCm()/100;
	}
}