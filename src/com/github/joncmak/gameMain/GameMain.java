package com.github.joncmak.gameMain;

import java.util.Scanner;

import com.github.joncmak.entities.Player;
import com.github.joncmak.mapGenerator.FloorHandler;

public class GameMain
{	
	private static int mFloorNumber;
	private static Player mPlayer;
	
	private static void gameInit()
	{
		mPlayer = new Player();
		FloorHandler fHandler = new FloorHandler();
		
	}
	
	public static void main(String[] args)
	{
		System.out.println("Welcome");
		System.out.println("Begin Game? Y/N");
		Scanner userInput = new Scanner(System.in);
		
		while(true)
		{
			String command = userInput.nextLine();
			
			if(command.equalsIgnoreCase("Y"))
			{
				gameInit();
			}
			else if(command.equalsIgnoreCase("N"))
			{
				break;
			}
			else
			{
				System.out.println("Invalid Command");
			}
		}
		
		userInput.close();
		System.out.println("Goodbye");
		return;
	}
}
