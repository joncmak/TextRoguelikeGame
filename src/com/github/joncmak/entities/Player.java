package com.github.joncmak.entities;

import java.awt.Point;

public class Player
{
	private int mHP;
	private Point mLocation;
	
	public Player()
	{
		mHP = 10;
		Point initLocation = new Point(0, 0);
		mLocation = initLocation;
	}
	
	public boolean isAlive()
	{
		return (mHP > 0);
	}
	
	public void updateLocation(int x, int y)
	{
		Point newLocation = new Point(mLocation.x + x, mLocation.y + y);
		mLocation = newLocation;
	}
	
	public void setLocation(int x, int y)
	{
		Point newLocation = new Point(x, y);
		mLocation = newLocation;
	}
	
	public void resetLocation()
	{
		mLocation = new Point(0, 0);
	}
	
	public Point getLocation()
	{
		return mLocation;
	}
}
