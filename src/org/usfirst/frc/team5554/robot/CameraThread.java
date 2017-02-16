package org.usfirst.frc.team5554.robot;

import java.util.HashMap;
import java.util.Map;
import org.opencv.core.Scalar;
import org.usfirst.frc.team5554.cameras.CameraHandler;
import org.usfirst.frc.team5554.cameras.GuideLines;
import org.usfirst.frc.team5554.cameras.VideoBox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraThread extends Thread
{
	private Joystick joy;
	private Joystick xbox;
	private boolean toSwitch = false;
	public static double distance = 0;
	
	private Map<String,GuideLines> gls = new HashMap<String,GuideLines>();
	
	public CameraThread(Joystick operator, Joystick assistant)
	{
		joy = operator;
		xbox = assistant;
	}
	
	@Override	
	public void run()
	{		
		/******************************Streaming Objects*******************************************/
	
		CameraHandler cameras = new CameraHandler(RobotMap.NUMBER_OF_CAMERAS,320,240);
		int liveCamera = RobotMap.FRONT_CAMERA_IDX;
		VideoBox screen = new VideoBox(320 , 240 , "Live Feed");

		/******************************Sets All Of The Guide Lines*********************************/

		gls.put("Shooter", new GuideLines(0, 319, 0, 240, new Scalar(0,0,255), 2));
		gls.get("Shooter").SetBoundries(2, 319);
		gls.put("CenterPoint", new GuideLines(160 , 161 , 120 , 121 , new Scalar(255,0,0), 2));
		gls.put("GearGuider1", new GuideLines(50 , 100 , 0 , 240 , new Scalar(255,0,0), 2));
		gls.put("GearGuider2", new GuideLines(200 , 250 , 0 , 240 , new Scalar(255,0,0), 2));
		gls.put("FeederLines", new GuideLines(100, 200, 0 , 240 , new Scalar(255,0,0) , 2));
		
		/*****************************Flags****************************************************/
	
		boolean showGearGuide = false;
		boolean showFeedLines = false;
		boolean ignoreButton2 = false;
		boolean ignoreButton3 = false;
		boolean ignoreButton4 = false;
				
		/******************************The Thread Main body***************************************/
		while (!Thread.interrupted()) 
		{
			if(toSwitch)
			{
				/***********************Chooses Which Camera To Stream********************************/
				
				if(joy.getRawButton(RobotMap.JOYSTICK_CAM_SWITCH) && ignoreButton2== false)
				{
					ignoreButton2 = true;
				
					if(liveCamera == RobotMap.FRONT_CAMERA_IDX)
					{
						liveCamera = RobotMap.SHOOTER_CAMERA_IDX;
						
					}
					else
					{
						liveCamera = RobotMap.FRONT_CAMERA_IDX;
					}
	    		
				}
				else if(!joy.getRawButton(RobotMap.JOYSTICK_CAM_SWITCH))
				{
					ignoreButton2 = false;
				}
				
				cameras.SetStreamer(liveCamera);
				
				/********************************Chooses The GuideLines TO Show***********************************/	
				
				if(joy.getRawButton(RobotMap.JOYSTICK_SHOW_GEARGL) && ignoreButton3== false)
				{
					ignoreButton3 = true;
				
					if(showGearGuide == false)
					{
						showFeedLines = false;
						showGearGuide = true;
					}
					else
					{
						showGearGuide = false;
					}
	    		
				}
				else if(!joy.getRawButton(RobotMap.JOYSTICK_SHOW_GEARGL))
				{
					ignoreButton3 = false;
				}
				
				if(joy.getRawButton(RobotMap.JOYSTICK_SHOW_FEEDGL) && ignoreButton4== false)
				{
					ignoreButton4 = true;
				
					if(showFeedLines == false)
					{
						showGearGuide = false;
						showFeedLines = true;
						
					}
					else
					{
						showFeedLines = false;
					}
	    		
				}
				else if(!joy.getRawButton(RobotMap.JOYSTICK_SHOW_FEEDGL))
				{
					ignoreButton4 = false;
				}
				
				/********************************Narrows And Dialates Shooter Gls********************************/
				
				if(liveCamera == RobotMap.SHOOTER_CAMERA_IDX)
				{
					if(xbox.getPOV() == 0)
					{
							gls.get("Shooter").NarrowWidth(1);
					}
					if(xbox.getPOV() == 180)
					{
							gls.get("Shooter").DialateWidth(1);
					}
				}
				
				/********************************Chooses The GuideLines TO Show***********************************/
				
				if(liveCamera == RobotMap.SHOOTER_CAMERA_IDX)
				{
					screen.stream(
							screen.DrawGuideLines(
									screen.DrawGuideLines(cameras.GetStream(), gls.get("Shooter")), 
										gls.get("CenterPoint")));
				}
				else if(showFeedLines)
				{
					screen.stream(
							screen.DrawGuideLines(cameras.GetStream(), gls.get("FeederLines")));
				}
				else if(showGearGuide)
				{
					screen.stream(
							screen.DrawGuideLines(
									screen.DrawGuideLines(cameras.GetStream(), gls.get("GearGuider1")), 
										gls.get("GearGuider2")));
				}
				else
				{
					screen.stream(cameras.GetStream());
				}
				
			}
			else
			{
				cameras.SetStreamer(RobotMap.FRONT_CAMERA_IDX);
				screen.stream(cameras.GetStream());
			}
			
			/***********************************Dashboard Widgets****************************************************/
			
			distance = gls.get("Shooter").GetDistance(RobotMap.FOCAL_LENGTH, RobotMap.BOILER_WIDTH);
			
			if(liveCamera == RobotMap.SHOOTER_CAMERA_IDX)
			{
				SmartDashboard.putNumber("Distance: ", CameraThread.distance);
			}
			else
			{
				SmartDashboard.putNumber("Distance: ", 0);
			}
			
		}
	}
	
	public void SetSwitch(boolean toSwitch)
	{
		this.toSwitch = toSwitch;
	}
	
}
