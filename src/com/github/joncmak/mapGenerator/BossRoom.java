package com.github.joncmak.mapGenerator;

public class BossRoom
{
	private int[] mRoom;
	
	private final int mExit;
	private final int mBossLocation;
	
	public BossRoom()
	{
		mRoom = new int[4];
		mExit = 3;
		mBossLocation = 2;
		
		generateBossRoom();
	}
	
	public void display(int pPlayerX, int pPlayerY)
	{
		for(int x = 0; x < 4; x++)
		{
			System.out.print("+---");
		}
		System.out.println("+");
		
		for(int x = 0; x < 4; x++)
		{
			if(x == 0)
				System.out.print("|");
			else
				System.out.print(" ");
			if(x == pPlayerX)
				System.out.print(TextColourDictionary.ANSI_GREEN + " P " + TextColourDictionary.ANSI_RESET);
			else if(x == mExit)
				System.out.print(TextColourDictionary.ANSI_YELLOW + " @ " + TextColourDictionary.ANSI_RESET);
			else if(x == mBossLocation)
				System.out.print(TextColourDictionary.ANSI_RED + " B " + TextColourDictionary.ANSI_RESET);
			else
				System.out.print("   ");
		}
		System.out.println("|");
		
		for(int x = 0; x < 4; x++)
		{
			System.out.print("+---");
		}
		System.out.println("+");
	}
	
	public boolean playerIsAtBoss(int pPlayerX, int pPlayerY)
	{
		return (mBossLocation == pPlayerX) ? true : false;
	}
	
	public boolean playerIsAtExit(int pPlayerX, int pPlayerY)
	{
		return (mExit == pPlayerX) ? true : false;
	}
	
	private void generateBossRoom()
	{
		for(int i = 0; i < 4; i++)
		{
			if(i == mExit)
			{
				mRoom[i] = 1;
			}
			else if(i == mBossLocation)
			{
				mRoom[i] = 2;
			}
			else
			{
				mRoom[i] = 0;
			}
		}
	}
}
