package com.github.joncmak.mapGenerator;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import com.github.joncmak.dictionaries.TextColourDictionary;

public class MazeRoom extends AbstractBasicRoom
{	
	public MazeRoom(int pMazeWidth, int pMazeHeight)
	{
		mRoomWidth = pMazeWidth;
		mRoomHeight = pMazeHeight;
		mRoom = new int[mRoomWidth][mRoomHeight];
		generateMaze(0, 0);
		mExit = addExitPortal();
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
				if(isVisible(new Point(x, y), pPlayerPoint, -1, 0) 
						|| isVisible(new Point(x, y), pPlayerPoint, 0, 0) 
						|| isVisible(new Point(x, y), pPlayerPoint, 1, 0)
						|| isVisible(new Point(x, y), pPlayerPoint, 0, -1)
						|| isVisible(new Point(x, y), pPlayerPoint, 0, 1))
					System.out.print((mRoom[x][y] & 1) == 0 ? "+---+" : "+   +");
				else
					System.out.print("     ");
			}
			System.out.println();
			
			for(int x = 0; x < mRoomWidth; x++)
			{
				if(isVisible(new Point(x, y), pPlayerPoint, -1, 0) 
						|| isVisible(new Point(x, y), pPlayerPoint, 0, 0) 
						|| isVisible(new Point(x, y), pPlayerPoint, 1, 0)
						|| isVisible(new Point(x, y), pPlayerPoint, 0, -1)
						|| isVisible(new Point(x, y), pPlayerPoint, 0, 1))					
					printHorizontal(new Point(x, y), new Point(pPlayerPoint.x, pPlayerPoint.y));
				else
					System.out.print("     ");
			}
			System.out.println();
			
			for(int x = 0; x < mRoomWidth; x++)
			{
				if(isVisible(new Point(x, y), pPlayerPoint, -1, 0) 
						|| isVisible(new Point(x, y), pPlayerPoint, 0, 0) 
						|| isVisible(new Point(x, y), pPlayerPoint, 1, 0)
						|| isVisible(new Point(x, y), pPlayerPoint, 0, -1)
						|| isVisible(new Point(x, y), pPlayerPoint, 0, 1))					
					System.out.print((mRoom[x][y] & 2) == 0 ? "+---+" : "+   +");
				else
					System.out.print("     ");
			}
			System.out.println();
		}
	}	
	
	public boolean playerIsAtExit(Point pPlayerPoint)
	{
		return (mExit.x == pPlayerPoint.x && mExit.y == pPlayerPoint.y) ? true : false;
	}
	
	private void generateMaze(int cx, int cy)
	{
		DIRECTIONS[] directions = DIRECTIONS.values();
		Collections.shuffle(Arrays.asList(directions));
		for(DIRECTIONS dir : directions)
		{
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			if(between(nx, mRoomWidth) && between(ny, mRoomHeight) && mRoom[nx][ny] == 0)
			{
				mRoom[cx][cy] |= dir.bit;
				mRoom[nx][ny] |= dir.opposite.bit;
				generateMaze(nx, ny);
			}
		}
	}
	
	private Point addExitPortal()
	{
		Random rand = new Random();
		int randNum = rand.nextInt(3) + 1;
		
		switch(randNum)
		{
			case 1:
				return new Point(0, mRoomHeight-1);
			case 2:
				return new Point(mRoomWidth-1, 0);
			case 3:
				return new Point(mRoomWidth-1, mRoomHeight-1);
			default:
				return new Point(mRoomWidth-1, mRoomHeight-1);
		}
	}
	
	private static boolean between(int pValue, int pUpper)
	{
		return (pValue >= 0) && (pValue < pUpper);
	}
	
	private void printHorizontal(Point pPoint, Point pPlayerPoint)
	{
		System.out.print((mRoom[pPoint.x][pPoint.y] & 8) == 0 ? "| " : "  ");
		if(pPlayerPoint != null && pPoint.x == pPlayerPoint.x && pPoint.y == pPlayerPoint.y)
			System.out.print(TextColourDictionary.ANSI_GREEN + "P" + TextColourDictionary.ANSI_RESET);
		else if(pPoint.x == mExit.x && pPoint.y == mExit.y)
			System.out.print(TextColourDictionary.ANSI_YELLOW + "@" + TextColourDictionary.ANSI_RESET);
		else
			System.out.print(" ");
		System.out.print((mRoom[pPoint.x][pPoint.y] & 4) == 0 ? " |" : "  ");
	}
	
	private boolean isVisible(Point pPoint, Point pPlayerPoint, int pOffsetX, int pOffsetY)
	{
		int x = pPlayerPoint.x + pOffsetX;
		int y = pPlayerPoint.y + pOffsetY;
		
		if(x < 0 || x > mRoomWidth)
			return false;
		
		if(y < 0 || y > mRoomHeight)
			return false;
		
		if(pPoint.x == x && pPoint.y == y)
		{
			if(pOffsetX == 0 && pOffsetY == 0)
				return true;
			if(!isBlocked(pPlayerPoint, new Point(x, y)))
				return true;
		}
		return false;
	}
}
