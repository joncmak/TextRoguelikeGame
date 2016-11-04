package com.github.joncmak.entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Player
{
	private final List<Point> mPlayerPath;
	private int mHP;
	private Point mLocation;
	
	public Player()
	{
		mPlayerPath = new ArrayList<Point>();
		mHP = 10;
		Point initLocation = new Point(0, 0);
		mPlayerPath.add(initLocation);
		mLocation = initLocation;
	}
	
	public boolean isAlive()
	{
		return (mHP > 0);
	}
	
	public void updateLocation(int x, int y)
	{
		Point newLocation = new Point(mLocation.x + x, mLocation.y + y);
		if(!mPlayerPath.contains(newLocation))
			mPlayerPath.add(newLocation);
		mLocation = newLocation;
	}
	
	public void setLocation(int x, int y)
	{
		Point newLocation = new Point(x, y);
		mPlayerPath.add(newLocation);
		mLocation = newLocation;
	}
	
	public Point getLocation()
	{
		return mLocation;
	}
	
	public List<Point> getPlayerPath()
	{
		return mPlayerPath;
	}
}
