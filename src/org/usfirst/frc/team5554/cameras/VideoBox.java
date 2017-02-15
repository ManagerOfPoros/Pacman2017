package org.usfirst.frc.team5554.cameras;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;

public class VideoBox
{
	private CvSource outputStream;
	
	public VideoBox(int width, int height, String name)
	{
		outputStream = CameraServer.getInstance().putVideo(name, width, height);
	}
	
	public void stream(Mat feed)
	{
		outputStream.putFrame(feed);
	}
	
	public Mat DrawGuideLines(Mat mat , GuideLines gl)
	{
		Imgproc.line(mat, new Point(gl.GetLeftX(),gl.GetUpY()) , new Point(gl.GetLeftX(),gl.GetDownY()),gl.GetColor(),gl.GetThickness());
		Imgproc.line(mat, new Point(gl.GetRightX(),gl.GetUpY()) , new Point(gl.GetRightX(),gl.GetDownY()),gl.GetColor(),gl.GetThickness());
		return mat;
	}

}
