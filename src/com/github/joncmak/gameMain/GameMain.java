package com.github.joncmak.gameMain;

import java.awt.Point;
import java.util.Scanner;

import com.github.joncmak.entities.Player;
import com.github.joncmak.mapGenerator.FloorHandler;

public class GameMain
{	
	private static Player sPlayer;
	private static CommandHandler sCmdHandler;
	private static FloorHandler sFloorHandler;
	
	private static void gameInit()
	{
		sCmdHandler = new CommandHandler();
		sPlayer = new Player();
		sFloorHandler = new FloorHandler();
		sFloorHandler.getNextFloor();
		sFloorHandler.display(new Point(0, 0));
	}
	
	private static boolean gameLoop(Scanner pUserInput)
	{
		while(sPlayer.isAlive())
		{
			System.out.println("Enter Command: [move/use/item/quit] [arguments]");
			String command = pUserInput.nextLine();
			
			String[] commandArray = sCmdHandler.parseCommand(command);
			if(sCmdHandler.isValidCommand(commandArray))
			{
				if(commandArray[0].equalsIgnoreCase("quit"))
				{
					System.out.println("Quitting...");
					return false;
				}
				else
				{
					sCmdHandler.executeCommand(commandArray, sPlayer, sFloorHandler);
					Point currentLocation = sPlayer.getLocation();
					sFloorHandler.display(currentLocation);
				}
			}
			else
			{
				System.out.println("Invalid Command");
			}
		}
		System.out.println("Game Over");
		return false;
	}
	
	public static void main(String[] args)
	{
		System.out.println("Welcome");
		System.out.println("Begin Game? Y/N");
		Scanner userInput = new Scanner(System.in);
		
		boolean continueGame = true;
		while(continueGame)
		{
			String command = userInput.nextLine();
			
			if(command.equalsIgnoreCase("Y"))
			{
				gameInit();
				continueGame = gameLoop(userInput);
			}
			else if(command.equalsIgnoreCase("N"))
			{
				continueGame = false;
			}
			else
			{
				System.out.println("Invalid Command");
			}
		}
		
		userInput.close();
		System.out.println("Goodbye");
		System.out.flush();
		return;
	}
}
