package org.usfirst.frc.team5554.Controllers;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * This class holds a digital input and a speed controller as an output device.
 * The class can according to the current state of the digital input to send an outpot
 * to the speed controller.
 */
public class Indicator {

	private boolean indicator = false;
	private boolean startCounting = false;
	private DigitalInput indicatorDevice;
	private SpeedController output;
	private int timer = 0;

	/**
	 * Creates an indicator object that can read the state of a digital input and send an output and
	 * manipulate a speed controller according to the value of the digital input
	 * a speed controller
	 *
	 * @param indicatorDevice The digital input device
	 */
	public Indicator(DigitalInput indicatorDevice)
	{
		this.indicatorDevice =((DigitalInput)indicatorDevice);
		System.out.println("created object getting state : " + ((DigitalInput)indicatorDevice).get());
	}

	/**
	 * Sets a speed controller as the indicator object's output device
	 *
	 * @param outputDevice The output speed controller
	 */
	public void SetOutpotDevice(SpeedController outputDevice)
	{
		this.output = outputDevice;
	}

	/**
	 * If the digital input device state is true for an amount of time
	 * sends the output device a value. in order to work properly this function needs to be called iteratively
	 *
	 * @param outputValue The value sent to the output device
	 * @param indicatorTime The amount  of time the input's state needs to be true before the
	 * output device gets a value
	 */
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

	/**
	 * If the digital input device state is true sends the output device a value.
	 *
	 * @param outputValue The value that will be sent to the output device
	 */
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


	/**
	 * Gets the digital input's state
	 *
	 * @return true if the input device state is true
	 */
	public boolean getState()
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

	/**
	 * Sends an value to the output device
	 *
	 * @param the value sent to the device
	 */
	public void SetOutput(double value)
	{
		if(output != null)
		{
			output.set(value);
		}
	}


}
