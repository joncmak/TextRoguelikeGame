package com.github.joncmak.mapGenerator;

import java.awt.Point;
import java.util.List;

public class FloorHandler
{
	private int mFloorNumber;
	AbstractBasicRoom mCurrentFloor;
	
	public FloorHandler()
	{
		mFloorNumber = 0;
	}
	
	public int[][] getNextFloor()
	{
		if(mFloorNumber > 0 && mFloorNumber % 10 == 0)
		{
			mCurrentFloor = new BossRoom();
		}
		else
		{
			mCurrentFloor = new MazeGenerator(8, 8);
		}
		return mCurrentFloor.getRoom();
	}
	
	public void updateFloorNumber()
	{
		mFloorNumber++;
	}
	
	public void display(int x, int y, List<Point> pPlayerPath)
	{
		mCurrentFloor.display(x, y, pPlayerPath);
	}
}
