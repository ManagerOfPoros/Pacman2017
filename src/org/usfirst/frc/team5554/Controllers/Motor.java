package org.usfirst.frc.team5554.Controllers;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Victor;

public class Motor extends Victor{

	private PIDSource PidSource;
	private PIDController controller;
	
	public Motor(int port) 
	{
		super(port);
	}
	
	public void SetFeedbackDevice(PIDSource PidSource)          
	{
		this.PidSource = PidSource;
	}

	public void SetPIDType(PIDSourceType type)
	{
		PidSource.setPIDSourceType(type);
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
			controller = new PIDController(p, i, d,f, PidSource, this);
		}
		else
		{
			controller.setPID(p, i, d , f);
		}
	}
	
	public void StartPIDLoop(double SetPoint , boolean invertMotor)
	{
		setInverted(invertMotor);
		
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
	
	public void GoDistance(double distance , boolean invertMotor)
	{
		StartPIDLoop(distance , invertMotor);
	}
	

	public void GoSteady(double speed, boolean invertMotor)
	{
		StartPIDLoop(speed , invertMotor);
	}
	
	public void disController()
	{
		if(controller != null)
		{
			controller.disable();
		}
	}
	
	public void enableController()
	{
		if(controller != null)
		{
			controller.enable();
		}
	}
	
	public boolean onTarget(double tolerance)
	{		
		controller.setAbsoluteTolerance(tolerance);
		return controller.onTarget();		
	}
	
	public double GetError()
	{
		return controller.getError();
	}
	
	public void setInputRange(double minValue , double maxValue)
	{
		controller.setInputRange(minValue, maxValue);
	}
	
	public void setCountinues(boolean countinues)
	{
		controller.setContinuous(countinues);
	}
	



}
