package org.usfirst.frc.team5554.robot;

import edu.wpi.first.wpilibj.Victor;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

public class Shooter 
{
	
	private CANTalon shooter0;
	private CANTalon shooter1;
	private Victor scramble;
	private double velocity = RobotMap.PID_SPEED;
	double targetSpeed;

	public Shooter(int shooter0Port, int shooter1Port, int scramblePort)
	{
		scramble = new Victor(scramblePort);
		shooter0 = new CANTalon(shooter0Port);   
		shooter1 = new CANTalon(shooter1Port);   
	
		shooter0.configNominalOutputVoltage(+0.0f, -0.0f);
		shooter0.configPeakOutputVoltage(+12.0f, -12.0f);

		shooter1.configNominalOutputVoltage(+0.0f, -0.0f);
		shooter1.configPeakOutputVoltage(+12.0f, -12.0f);
		
		shooter1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		shooter1.reverseSensor(true);
		
		shooter1.setProfile(0);
		shooter1.setF(RobotMap.PID_VALUE_F);
		shooter1.setP(RobotMap.PID_VALUE_P);
		shooter1.setI(RobotMap.PID_VALUE_I); 
		shooter1.setD(RobotMap.PID_VALUE_D);
		
		shooter0.enable();
		shooter1.enableControl();
	}
	
	public void shoot(double speed)
	{
		shooter0.changeControlMode(TalonControlMode.PercentVbus);
		shooter1.changeControlMode(TalonControlMode.PercentVbus);
		
		shooter0.set(speed);
		shooter1.set(speed);

		

	}
		
	public void scramble(double speed)
	{
		scramble.set(speed);
	}
	
	public void PidShoot(double speed)
	{					
		
		System.out.println(shooter1.getEncVelocity());
		
		final int RPM = 1500; 
        /* Speed mode */
        targetSpeed = speed * RPM; /* 1500 RPM in either direction */
        
        shooter1.changeControlMode(TalonControlMode.Speed);
        shooter0.changeControlMode(TalonControlMode.Follower);
       
        shooter1.set((-targetSpeed));
        shooter0.set(shooter1.getDeviceID());

	}
	
	public double GetSpeed()
	{
		return velocity;
	}

}
	
