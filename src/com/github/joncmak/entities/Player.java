package com.github.joncmak.entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Player
{
	private List<Point> mPlayerPath;
	private int mHP;
	
	public Player()
	{
		mPlayerPath = new ArrayList<Point>();
		mHP = 10;
	}
	
	public void addPoint(int x, int y)
	{
		mPlayerPath.add(new Point(x, y));
	}
	
	public boolean isAlive()
	{
		return (mHP > 0);
	}
}
