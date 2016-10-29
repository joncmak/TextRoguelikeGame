package com.github.joncmak.mapGenerator;

import java.awt.Point;
import java.util.Arrays;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.github.joncmak.dictionaries.TextColourDictionary;

public class MazeGenerator extends AbstractBasicRoom
{
	private enum DIRECTIONS
	{
		North(1, 0, -1), South(2, 0, 1), East(4, 1, 0), West(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;
		private DIRECTIONS opposite;
		
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
	
	private final int mMazeWidth;
	private final int mMazeHeight;
	private final int[][] mMaze;
	
	private final Point mExit;
	
	public MazeGenerator(int pMazeWidth, int pMazeHeight)
	{
		mMazeWidth = pMazeWidth;
		mMazeHeight = pMazeHeight;
		mMaze = new int[mMazeWidth][mMazeHeight];
		generateMaze(0, 0);
		mExit = addExitPortal();
	}
	
	public int[][] getRoom()
	{
		return mMaze;
	}
	
	public void display(int pPlayerX, int pPlayerY, List<Point> pPlayerPath)
	{
		for(int y = 0; y < mMazeHeight; y++)
		{
			for(int x = 0; x < mMazeWidth; x++)
			{
				if(pPlayerPath.contains(new Point(x, y)))
					System.out.print((mMaze[x][y] & 1) == 0 ? "+---+" : "+   +");
				else
					System.out.print("     ");
			}
			System.out.println();
			
			for(int x = 0; x < mMazeWidth; x++)
			{
				if(pPlayerPath.contains(new Point(x, y)))
				{
					System.out.print((mMaze[x][y] & 8) == 0 ? "| " : "  ");
					if(x == pPlayerX && y == pPlayerY)
						System.out.print(TextColourDictionary.ANSI_GREEN + "P" + TextColourDictionary.ANSI_RESET);
					else if(x == mExit.x &&y == mExit.y)
						System.out.print(TextColourDictionary.ANSI_YELLOW + "@" + TextColourDictionary.ANSI_RESET);
					else
						System.out.print(" ");
					System.out.print((mMaze[x][y] & 4) == 0 ? " |" : "  ");
				}
				else
					System.out.print("     ");
			}
			System.out.println();
			
			for(int x = 0; x < mMazeWidth; x++)
			{
				if(pPlayerPath.contains(new Point(x, y)))
					System.out.print((mMaze[x][y] & 2) == 0 ? "+---+" : "+   +");
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
			if(between(nx, mMazeWidth) && between(ny, mMazeHeight) && mMaze[nx][ny] == 0)
			{
				mMaze[cx][cy] |= dir.bit;
				mMaze[nx][ny] |= dir.opposite.bit;
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
				return new Point(0, mMazeHeight-1);
			case 2:
				return new Point(mMazeWidth-1, 0);
			case 3:
				return new Point(mMazeWidth-1, mMazeHeight-1);
			default:
				return new Point(mMazeWidth-1, mMazeHeight-1);
		}
	}
	
	private static boolean between(int pValue, int pUpper)
	{
		return (pValue >= 0) && (pValue < pUpper);
	}	
}
