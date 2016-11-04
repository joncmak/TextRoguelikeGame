package com.github.joncmak.mapGenerator;

import java.awt.Point;
import java.util.Arrays;

import java.util.Collections;
import java.util.List;
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
					else if(x == mExit.x &&y == mExit.y)
						System.out.print(TextColourDictionary.ANSI_YELLOW + "@" + TextColourDictionary.ANSI_RESET);
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
	
	public boolean playerIsAtExit(int pPlayerX, int pPlayerY)
	{
		return (mExit.x == pPlayerX && mExit.y == pPlayerY) ? true : false;
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
}
