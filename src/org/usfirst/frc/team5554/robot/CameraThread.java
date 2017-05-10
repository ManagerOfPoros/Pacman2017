package org.usfirst.frc.team5554.robot;

import org.usfirst.frc.team5554.cameras.CameraHandler;
import org.usfirst.frc.team5554.cameras.VideoBox;
import edu.wpi.first.wpilibj.Joystick;

public class CameraThread extends Thread
{
	private Joystick joy;
	public static boolean toSwitch = false;
	public static double shootingPoint = 0;
	
//	private Map<String,GuideLines> gls = new HashMap<String,GuideLines>();
	
	public CameraThread(Joystick operator)
	{
		joy = operator;
	}
	
	@Override	
	public void run()
	{		
		/******************************Streaming Objects*******************************************/
	
		CameraHandler cameras = new CameraHandler(RobotMap.NUMBER_OF_CAMERAS,320,240);
		VideoBox screen = new VideoBox(320,240, "Live Feed");		

		/******************************Sets All Of The Guide Lines*********************************/

//		gls.put("ShootingPoint0", new GuideLines(98, 189, 45, 150, new Scalar(0,0,255), 3));
//		gls.put("ShootingPoint0_Bound", new GuideLines(98, 189, 45, 45, new Scalar(0,0,255), 3));
//		gls.put("ShootingPoint1", new GuideLines(63, 219, 157, 240, new Scalar(0,0,255), 2));
//		gls.put("GearGuider", new GuideLines(228 , 48 , 0 , 240 , new Scalar(255,0,0), 2));
		
		/*****************************Flags****************************************************/
	
//		boolean showGearGuider = false;
//		boolean ignoreButton3 = false;
		int liveCamera = RobotMap.FRONT_CAMERA_IDX;
				
		/******************************The Thread Main body***************************************/
		while (!Thread.interrupted()) 
		{
			if(toSwitch)
			{
				/***********************Chooses Which Camera To Stream********************************/
				
				liveCamera = CameraHandler.PickCamera(liveCamera, RobotMap.CAMERAPOV, joy, RobotMap.NUMBER_OF_CAMERAS , 0);
				cameras.SetStreamer(liveCamera);
				
				/********************************Chooses The GuideLines TO Show***********************************/	
				
//				if(joy.getRawButton(RobotMap.JOYSTICK_SHOW_GEARLINES) && ignoreButton3 == false && liveCamera == RobotMap.FRONT_CAMERA_IDX)
//				{
//					ignoreButton3 = true;
//				}
//				else if(!joy.getRawButton(RobotMap.JOYSTICK_SHOW_GEARLINES))
//				{
//					ignoreButton3 = false;
//				}
				
				/********************************Narrows And Dialates Shooter Gls********************************/
				
//				if(liveCamera == RobotMap.SHOOTER_CAMERA_IDX && !Robot.isInShootingMode)
//				{
//					if(xbox.getPOV() == 0)
//					{
//							gls.get("Shooter").NarrowWidth(1);
//					}
//					if(xbox.getPOV() == 180)
//					{
//							gls.get("Shooter").DialateWidth(1);
//					}
//				}
				
				/*************************Streams***********************************/
				
				screen.stream(cameras.GetStream());
				
			}
			else
			{
				cameras.SetStreamer(RobotMap.FRONT_CAMERA_IDX);
				screen.stream(cameras.GetStream());
			}
						
		}
	}
	
}