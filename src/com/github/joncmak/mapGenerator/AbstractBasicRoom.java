package com.github.joncmak.mapGenerator;

import java.awt.Point;

import com.github.joncmak.entities.Player;

public abstract class AbstractBasicRoom
{
	protected int[][] mRoom;
	protected int mRoomHeight;
	protected int mRoomWidth;
	protected Point mExit;
	
	public abstract int[][] getRoom();
	public abstract void display(Point pPlayerPoint);
	public abstract boolean playerIsAtExit(Point pPlayerPoint);
	
	public boolean isBlocked(Point pDirection, Player pPlayer)
	{	
		Point playerPoint = pPlayer.getLocation();
		Point nextPoint = new Point(playerPoint.x + pDirection.x, playerPoint.y + pDirection.y);
		
		return isBlocked(playerPoint, nextPoint);
	}
	
	public void displayAll(Point pPlayerPoint)
	{
		for(int y = 0; y < mRoomHeight; y++)
		{
			for(int x = 0; x < mRoomWidth; x++)
			{
				System.out.print((mRoom[x][y] & 1) == 0 ? "+---+" : "+   +");
			}
			System.out.println();
			
			for(int x = 0; x < mRoomWidth; x++)
			{
				System.out.print((mRoom[x][y] & 8) == 0 ? "| " : "  ");
				System.out.print(" ");
				System.out.print((mRoom[x][y] & 4) == 0 ? " |" : "  ");
			}
			System.out.println();
			
			for(int x = 0; x < mRoomWidth; x++)
			{
				System.out.print((mRoom[x][y] & 2) == 0 ? "+---+" : "+   +");			
			}
			System.out.println();
		}
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
