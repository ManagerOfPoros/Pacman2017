package org.usfirst.frc.team5554.cameras;

import org.opencv.core.Scalar;

public class GuideLines 
{
	private int glXLeft;
	private int glXRight;
	private int glYUp;
	private int glYDown;
	private Scalar glColor;
	private int glThickness;
	
	private boolean isMax = false;
	private int maxRange;
	private boolean isMin = false;
	private int minRange;
	
	public GuideLines(int xLeft , int xRight , int yUp , int yDown , Scalar color, int thickness)
	{
		glXLeft = xLeft;
		glXRight = xRight;
		glYUp = yUp;
		glYDown = yDown;
		glColor = color;
		glThickness = thickness;
	}
	
	public void NarrowWidth(int narrow)
	{
		if(!isMin||!(this.GetLeftX()+this.minRange >= this.GetRightX()))
		{
			glXLeft = glXLeft+narrow;
			glXRight = glXRight-narrow;
		}
	}
	
	public void DialateWidth(int dialate)
	{
		if(!isMax||!(Math.abs(this.glXLeft - this.glXRight) >= this.maxRange))
		{
			glXLeft = glXLeft-dialate;
			glXRight = glXRight+dialate;
		}
	}
	
	public void SetBoundries(int minDis , int maxDis)
	{
		SetBoundries(minDis , maxDis , true , true);
	}
	
	public void MaxRange(int range)
	{
		SetBoundries(this.minRange, range, this.isMin, true);
	}
	
	public void MinRange(int range)
	{
		SetBoundries(range, this.maxRange, true, this.isMax);
	}
	
	private void SetBoundries(int minDis, int maxDis, boolean isMin, boolean isMax)
	{
		this.minRange = minDis;
		this.maxRange = maxDis;
		this.isMin = isMin;
		this.isMax = isMax;
	}
	
	public double GetDistance(double FocalLength, double objectwidth)
	{
		int pixDistance = Math.abs(this.glXLeft - this.glXRight);
		
		return (objectwidth*FocalLength)/pixDistance;
	}
	
	public int GetLeftX()
	{
		return glXLeft;
	}
	
	public int GetRightX()
	{
		return glXRight;
	}
	
	public int GetUpY()
	{
		return glYUp;
	}
	
	public int GetDownY()
	{
		return glYDown;
	}
	
	public int GetThickness()
	{
		return glThickness;
	}
	
	public Scalar GetColor()
	{
		return glColor;
	}
	
}
