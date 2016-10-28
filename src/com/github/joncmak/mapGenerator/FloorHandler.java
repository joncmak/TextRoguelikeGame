package com.github.joncmak.mapGenerator;

public class FloorHandler
{
	private int mFloorNumber;
	
	public FloorHandler()
	{
		mFloorNumber = 0;
	}
	
	public void makeNextFloor()
	{
		if(mFloorNumber > 0 && mFloorNumber % 10 == 0)
		{
			BossRoom room = new BossRoom();
		}
		else
		{
			MazeGenerator maze = new MazeGenerator(8, 8);
		}
	}
}
