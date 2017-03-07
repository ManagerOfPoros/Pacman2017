package org.usfirst.frc.team5554.Controllers;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.SpeedController;

public class Indicator {

	private boolean indicator = false;
	private boolean startCounting = false;
	private DigitalInput indicatorDevice;
	private SpeedController output;
	private int timer = 0;
	
	public Indicator(SensorBase indicatorDevice)
	{
		this.indicatorDevice =((DigitalInput)indicatorDevice);
		System.out.println("created object getting state : " + ((DigitalInput)indicatorDevice).get());
	}
	
	public void SetOutpotDevice(SpeedController outputDevice)
	{
		this.output = outputDevice;
	}
	
	public void SendOutput(double outputValue,  int indicatorTime)
	{
		if (((DigitalInput)indicatorDevice).get()) // micro is pressed and indicator false
		{
			indicator = true; // gear is in
			startCounting = true;
			timer = 0;

		}
		else if(timer > (indicatorTime*30))
		{
			indicator = false;
			timer = 0;
		}

		
		if(indicator)
		{
			output.set(outputValue);
		}
		else
		{
			output.set(0);
		}
		
		if(startCounting )
		{
			timer++;
		}

	}
	
	public void SendOutput(double outputValue)
	{
		if (((DigitalInput)indicatorDevice).get()) 
		{
			output.set(outputValue);
		}
		else
		{
			output.set(0);
		}
	}
	
	public boolean getBoolState()
	{
		if (((DigitalInput)indicatorDevice).get()) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void SetOutputDeviceState(double state)
	{
		if(output != null)
		{
			output.set(state);
		}
	}
	
	
}
