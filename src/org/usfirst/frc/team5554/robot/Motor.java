package org.usfirst.frc.team5554.robot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Victor;

public class Motor extends Victor{

	private Encoder encoder;
	private PIDController controller;
	
	
	public Motor(int port) 
	{
		super(port);
	}
	
	
	public void SetFeedbackDevice(Encoder enc)          
	{
		this.encoder = enc;
	}

	public void SetPIDType(PIDSourceType type)
	{
		encoder.setPIDSourceType(type);
	}
	
	public void SetPID(double p, double i, double d)
	{
		SetPIDController(p, i, d, 0);
	}
	
	public void SetPID(double p, double i, double d, double f)
	{
		SetPIDController(p, i, d, f);
	}		


	private void SetPIDController(double p,double i, double d, double f)
	{
		if(controller == null)
		{
			System.out.println("PID controller created");
			controller = new PIDController(p, i, d,f, encoder, this);
		}
		else
		{
			controller.setPID(p, i, d , f);
		}
	}
	
	public void StartPIDLoop(double SetPoint)
	{
		if(controller==null)
		{
			System.out.println("PIDController is null");
		}
		else
		{	
			controller.setSetpoint(SetPoint);
			
			if(!controller.isEnabled())
			{
				controller.enable();
			}
		}
	}
	
	public void GoDistance(double distance)
	{
		StartPIDLoop(distance);
	}
	

	public void GoSteady(double speed)
	{
		StartPIDLoop(speed);
	}
	
	public void disController(){
		controller.disable();
	}
	
	public boolean onTarget(){
		return controller.onTarget();
	}
	
	public PIDController getcontroller()
	{
		return controller;
	}


}
