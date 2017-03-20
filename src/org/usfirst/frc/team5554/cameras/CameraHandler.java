package org.usfirst.frc.team5554.cameras;

import java.util.HashMap;
import java.util.Map;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoException;


public class CameraHandler 
{
	private Map<Integer , UsbCamera> cameras = new HashMap<>();
	private CvSink cvSink = new CvSink("stream");
	private int currentCamera; //The port of the current camera
	private Mat mat;
	private int fps = 20;
	
	public CameraHandler(int ports,int width, int height)
	{
		currentCamera = 0;
		mat = new Mat();
		for(int i = 0; i < ports; i++)
		{
			AddCamera(i, width, height , 20 , 50);
		}
		cvSink.setSource(cameras.get(0));
	}
	
	
	
	private void AddCamera(int idx, int width , int height , int fps , int brightness)
	{
		if(!cameras.containsKey(idx))
		{
			try
			{
				UsbCamera cam = new UsbCamera("cam"+String.valueOf(idx) , idx);
				cam.setResolution(width, height);
				cam.setFPS(fps);
				cam.setBrightness(brightness);
				cameras.put(idx, cam);
				
			}
			catch(VideoException e)
			{
				System.out.println("Failed to initialize camera on port: " + idx);
				throw(e);
			}
		}
		else
		{
			System.out.println("Camera on port " + idx + " is already defined.");
		}
	}
	
	public void SetStreamer(int idx)
	{
		if(idx != currentCamera)
		{
			
			if(fps > 20)
			{
				cameras.get(currentCamera).setFPS(0);
				cameras.get(idx).setFPS(this.fps);
			}
			
			cvSink.setSource(cameras.get(idx));
						
			currentCamera = idx;
		}
	}
	
	public Mat GetStream()
	{
		cvSink.grabFrame(mat);
		return mat;
	}
	
	public void DeleteHandler()
	{
		cvSink = null;
		cameras.clear();
	}
	
	
	
}