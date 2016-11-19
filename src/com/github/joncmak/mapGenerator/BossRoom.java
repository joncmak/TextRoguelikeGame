package com.github.joncmak.mapGenerator;

import java.awt.Point;

import com.github.joncmak.dictionaries.TextColourDictionary;

public class BossRoom extends AbstractBasicRoom
{
	private final Point mBossLocation;
	
	public BossRoom()
	{
		mRoomHeight = 1;
		mRoomWidth = 4;
		
		mRoom = new int[mRoomWidth][mRoomHeight];
		mExit = new Point(3, 0);
		mBossLocation = new Point(2, 0);
		
		generateBossRoom();
	}
	
	@Override
	public int[][] getRoom()
	{
		return mRoom;
	}
	
	@Override
	public void display(Point pPlayerPoint)
	{
		for(int y = 0; y < mRoomHeight; y++)
		{
			for(int x = 0; x < mRoomWidth; x++)
			{
				if(isVisible(new Point(x, y), pPlayerPoint, -1, 0) || isVisible(new Point(x, y), pPlayerPoint, 0, 0) || isVisible(new Point(x, y), pPlayerPoint, 1, 0))
					System.out.print((mRoom[x][y] & 1) == 0 ? "+---+" : "+   +");
			}
			System.out.println();
			
			for(int x = 0; x < mRoomWidth; x++)
			{
				if(isVisible(new Point(x, y), pPlayerPoint, -1, 0) || isVisible(new Point(x, y), pPlayerPoint, 0, 0) || isVisible(new Point(x, y), pPlayerPoint, 1, 0))
					printHorizontal(new Point(x, y), new Point(pPlayerPoint.x, pPlayerPoint.y));
			}
			System.out.println();
			
			for(int x = 0; x < mRoomWidth; x++)
			{
				if(isVisible(new Point(x, y), pPlayerPoint, -1, 0) || isVisible(new Point(x, y), pPlayerPoint, 0, 0) || isVisible(new Point(x, y), pPlayerPoint, 1, 0))
					System.out.print((mRoom[x][y] & 2) == 0 ? "+---+" : "+   +");
			}
			System.out.println();
		}
	}
	
	public boolean playerIsAtBoss(int pPlayerX, int pPlayerY)
	{
		return (mBossLocation.x == pPlayerX) ? true : false;
	}
	
	public boolean playerIsAtExit(Point pPlayerPoint)
	{
		return (mExit.x == pPlayerPoint.x) ? true : false;
	}
	
	private void generateBossRoom()
	{
		mRoom[0][0] = 4;
		mRoom[1][0] = 12;
		mRoom[2][0] = 12;
		mRoom[3][0] = 8;
	}
	private boolean isVisible(Point pPoint, Point pPlayerPoint, int pOffsetX, int pOffsetY)
	{
		if(pPoint.x == pPlayerPoint.x + pOffsetX && pPoint.y == pPlayerPoint.y + pOffsetY)
		{
			return true;
		}
		return false;
	}
	
	private void printHorizontal(Point pPoint, Point pPlayerPoint)
	{
		System.out.print((mRoom[pPoint.x][pPoint.y] & 8) == 0 ? "| " : "  ");
		if(pPlayerPoint != null && pPoint.x == pPlayerPoint.x && pPoint.y == pPlayerPoint.y)
			System.out.print(TextColourDictionary.ANSI_GREEN + "P" + TextColourDictionary.ANSI_RESET);
		else if(pPoint.x == mExit.x && pPoint.y == mExit.y)
			System.out.print(TextColourDictionary.ANSI_YELLOW + "@" + TextColourDictionary.ANSI_RESET);
		else if(pPoint.x == mBossLocation.x && pPoint.y == mBossLocation.y)
			System.out.print(TextColourDictionary.ANSI_RED + "B" + TextColourDictionary.ANSI_RESET);
		else
			System.out.print(" ");
		System.out.print((mRoom[pPoint.x][pPoint.y] & 4) == 0 ? " |" : "  ");
	}
}
