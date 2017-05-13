package org.usfirst.frc.team5554.cameras;

import org.opencv.core.Scalar;

public class GuideLines 
{
	private int xLeft;
	private int xRight;
	private int yUp;
	private int yDown;
	private Scalar color;
	private int thickness;
	
	private boolean isMax = false;
	private int maxRange;
	private boolean isMin = false;
	private int minRange;
	
	public GuideLines(int xLeft , int xRight , int yUp , int yDown , Scalar color, int thickness)
	{
		this.xLeft = xLeft;
		this.xRight = xRight;
		this.yUp = yUp;
		this.yDown = yDown;
		this.color = color;
		this.thickness = thickness;
	}
	
	public void NarrowWidth(int narrow)
	{
		if(!isMin||!(this.GetLeftX()+this.minRange >= this.GetRightX()))
		{
			xLeft = xLeft+narrow;
			xRight = xRight-narrow;
		}
	}
	
	public void DialateWidth(int dialate)
	{
		if(!isMax||!(Math.abs(this.xLeft - this.xRight) >= this.maxRange))
		{
			xLeft = xLeft-dialate;
			xRight = xRight+dialate;
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
		int pixDistance = Math.abs(this.xLeft - this.xRight);
		
		return (objectwidth*FocalLength)/pixDistance;
	}
	
	public int GetPixDis()
	{
		return Math.abs(this.xLeft - this.xRight);
	}
	
	public int GetLeftX()
	{
		return xLeft;
	}
	
	public int GetRightX()
	{
		return xRight;
	}
	
	public int GetUpY()
	{
		return yUp;
	}
	
	public int GetDownY()
	{
		return yDown;
	}
	
	public int GetThickness()
	{
		return thickness;
	}
	
	public Scalar GetColor()
	{
		return color;
	}
	
}
