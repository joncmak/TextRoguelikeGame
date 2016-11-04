package com.github.joncmak.mapGenerator;

import java.awt.Point;
import java.util.List;

import com.github.joncmak.entities.Player;

public abstract class AbstractBasicRoom
{
	protected int[][] mRoom;
	protected int mRoomHeight;
	protected int mRoomWidth;
	protected Point mExit;
	
	public abstract int[][] getRoom();
	public abstract void display(int x, int y, List<Point> pPlayerPath);
	public boolean isBlocked(Point pDirection, Player pPlayer)
	{	
		Point playerPoint = pPlayer.getLocation();
		Point nextPoint = new Point(playerPoint.x + pDirection.x, playerPoint.y + pDirection.y);
		
		return isBlocked(playerPoint, nextPoint);
	}
	
	public boolean isBlocked(Point pOrigin, Point pNextPoint)
	{
		int dX = pNextPoint.x - pOrigin.x;
		int dY = pNextPoint.y - pOrigin.y;
		
		try
		{
			if(dX == 0 && dY == -1)
			{
				return (mRoom[pOrigin.x][pOrigin.y] & 1) == 0;
			}
			else if(dX == 0 && dY == 1)
			{
				return (mRoom[pOrigin.x][pOrigin.y] & 2) == 0;
			}
			else if(dX == 1 && dY == 0)
			{
				return (mRoom[pOrigin.x][pOrigin.y] & 4) == 0;
			}
			else if(dX == -1 && dY == 0)
			{
				return (mRoom[pOrigin.x][pOrigin.y] & 8) == 0;
			}
			else
			{
				return true;
			}
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			return true;
		}
	}
	
	enum DIRECTIONS
	{
		North(1, 0, -1), South(2, 0, 1), East(4, 1, 0), West(8, -1, 0);
		protected final int bit;
		protected final int dx;
		protected final int dy;
		DIRECTIONS opposite;
		
		static
		{
			North.opposite = South;
			South.opposite = North;
			East.opposite = West;
			West.opposite = East;
		}
		
		private DIRECTIONS(int pBit, int pDX, int pDY)
		{
			bit = pBit;
			dx = pDX;
			dy = pDY;
		}
	};
}
