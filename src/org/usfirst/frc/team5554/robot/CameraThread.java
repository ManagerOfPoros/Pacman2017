package org.usfirst.frc.team5554.robot;

import java.util.HashMap;

import org.opencv.core.Scalar;
import org.usfirst.frc.team5554.cameras.CameraHandler;
import org.usfirst.frc.team5554.cameras.GuideLines;
import org.usfirst.frc.team5554.cameras.VideoBox;
import edu.wpi.first.wpilibj.Joystick;

public class CameraThread extends Thread
{
	private Joystick joy;
	private Joystick xbox;
	public static boolean toSwitch = false;
	public static double shootingPoint = 0;
	
	private HashMap<String,GuideLines> gls = new HashMap<String,GuideLines>();
	
	public CameraThread(Joystick switcher, Joystick shooter)
	{
		xbox = switcher;
		joy = shooter;
	}
	
	@Override	
	public void run()
	{		
		/******************************Streaming Objects*******************************************/
	
		CameraHandler cameras = new CameraHandler(RobotMap.NUMBER_OF_CAMERAS,320,240);
		VideoBox screen = new VideoBox(320,240, "Live Feed");		

		/******************************Sets All Of The Guide Lines*********************************/

		gls.put("ShootingGuider", new GuideLines(98, 189, 45, 150, new Scalar(0,0,255), 3));
		gls.put("GearGuider", new GuideLines(228 , 48 , 0 , 240 , new Scalar(255,0,0), 2));
		
		/*****************************Flags****************************************************/
	
		boolean showGearGuider = false;
		boolean ignoreButton3 = false;
		int liveCamera = RobotMap.FRONT_CAMERA_IDX;
				
		/******************************The Thread Main body***************************************/
		while (!Thread.interrupted()) 
		{
			if(toSwitch)
			{
				/***********************Chooses Which Camera To Stream********************************/
				
				liveCamera = CameraHandler.PickCamera(liveCamera, RobotMap.CAMERAPOV, xbox, RobotMap.NUMBER_OF_CAMERAS , 0);
				cameras.SetStreamer(liveCamera);
				
				/********************************Chooses The GuideLines TO Show***********************************/	
				
				if(joy.getRawButton(RobotMap.JOYSTICK_SHOW_GEARLINES) && ignoreButton3 == false && liveCamera == RobotMap.FRONT_CAMERA_IDX)
				{
					ignoreButton3 = true;
					
					if(showGearGuider == true)
					{
						showGearGuider = false;
					}
					else
					{
						showGearGuider = true;
					}
				}
				else if(!joy.getRawButton(RobotMap.JOYSTICK_SHOW_GEARLINES))
				{
					ignoreButton3 = false;
				}
				else if(liveCamera != RobotMap.FRONT_CAMERA_IDX)
				{
					showGearGuider = false;
				}
				
				/********************************Narrows And Dialates Shooter Gls********************************/
				
				if(liveCamera == RobotMap.SHOOTER_CAMERA_IDX && !Robot.isInShootingMode)
				{
					if(joy.getPOV() == 0)
					{
							gls.get("ShootingGuider").NarrowWidth(1);
					}
					if(joy.getPOV() == 180)
					{
							gls.get("ShootingGuider").DialateWidth(1);
					}
				}
				
				/*************************Streams***********************************/
				if(showGearGuider == true)
				{
					screen.DrawGuideLines(cameras.GetStream(), gls.get("GearGuider"));
				}
				else if(liveCamera == RobotMap.SHOOTER_CAMERA_IDX)
				{
					screen.DrawGuideLines(cameras.GetStream(), gls.get("ShootingGuider"));
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
						
		}
	}
	
}