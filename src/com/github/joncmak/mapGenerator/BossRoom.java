package com.github.joncmak.mapGenerator;

import java.awt.Point;
import java.util.List;

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
	public void display(int pPlayerX, int pPlayerY, List<Point> pPlayerPath)
	{
		for(int y = 0; y < mRoomHeight; y++)
		{
			for(int x = 0; x < mRoomWidth; x++)
			{
				if(pPlayerPath.contains(new Point(x, y)))
					System.out.print((mRoom[x][y] & 1) == 0 ? "+---+" : "+   +");
				else
					System.out.print("     ");
			}
			System.out.println();
			
			for(int x = 0; x < mRoomWidth; x++)
			{
				if(pPlayerPath.contains(new Point(x, y)))
				{
					System.out.print((mRoom[x][y] & 8) == 0 ? "| " : "  ");
					if(x == pPlayerX && y == pPlayerY)
						System.out.print(TextColourDictionary.ANSI_GREEN + "P" + TextColourDictionary.ANSI_RESET);
					else if(x == mExit.x && y == mExit.y)
						System.out.print(TextColourDictionary.ANSI_YELLOW + "@" + TextColourDictionary.ANSI_RESET);
					else if(x == mBossLocation.x && y == mBossLocation.y)
						System.out.print(TextColourDictionary.ANSI_RED + "B" + TextColourDictionary.ANSI_RESET);
					else
						System.out.print(" ");
					System.out.print((mRoom[x][y] & 4) == 0 ? " |" : "  ");
				}
				else
					System.out.print("     ");
			}
			System.out.println();
			
			for(int x = 0; x < mRoomWidth; x++)
			{
				if(pPlayerPath.contains(new Point(x, y)))
					System.out.print((mRoom[x][y] & 2) == 0 ? "+---+" : "+   +");
				else
					System.out.print("     ");
			}
			System.out.println();
		}
	}
	
	public boolean playerIsAtBoss(int pPlayerX, int pPlayerY)
	{
		return (mBossLocation.x == pPlayerX) ? true : false;
	}
	
	public boolean playerIsAtExit(int pPlayerX, int pPlayerY)
	{
		return (mExit.x == pPlayerX) ? true : false;
	}
	
	private void generateBossRoom()
	{
		mRoom[0][0] = 4;
		mRoom[1][0] = 12;
		mRoom[2][0] = 12;
		mRoom[3][0] = 8;
	}
}
