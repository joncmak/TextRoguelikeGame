package com.github.joncmak.mapGenerator;

import java.awt.Point;

public class FloorHandler
{
	private int mFloorNumber;
	private AbstractBasicRoom mCurrentFloor;
	
	public FloorHandler()
	{
		mFloorNumber = 1;
	}
	
	public AbstractBasicRoom getRoom()
	{
		return mCurrentFloor;
	}
	
	public int[][] getNextFloor()
	{
		if(mFloorNumber > 0 && mFloorNumber % 10 == 0)
		{
			mCurrentFloor = new BossRoom();
		}
		else
		{
			mCurrentFloor = new MazeRoom(8, 8);
		}
		return mCurrentFloor.getRoom();
	}
	
	public void updateFloorNumber()
	{
		mFloorNumber++;
	}
	
	public void display(Point pPoint)
	{
		//mCurrentFloor.displayAll(pPoint);
		mCurrentFloor.display(pPoint);
	}
}
