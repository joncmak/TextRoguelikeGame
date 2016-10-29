package com.github.joncmak.gameMain;

import java.awt.Point;

import com.github.joncmak.dictionaries.CommandValidator;
import com.github.joncmak.entities.Player;

public class CommandHandler
{
	public String[] parseCommand(String pCommand)
	{
		return pCommand.split(" ", 2);
	}
	
	public boolean isValidCommand(String[] pCommands)
	{
		boolean isValid = false;
		for(String action : CommandValidator.VALID_ACTIONS)
		{
			if(pCommands[0].equalsIgnoreCase(action))
			{
				isValid = true;
				break;
			}
		}
		if(isValid == false)
		{
			//TODO print valid arguments
			
		}
		
		//TODO validate move arguments for valid direction?
		return isValid;	
	}
	
	public void executeCommand(String[] pCommands, Player pPlayer)
	{
		String action = pCommands[0];
		if(action.equalsIgnoreCase("move"))
		{
			//TODO check for wall
			Point directionPoint = convertDirectionStringToInt(pCommands[1]);
			pPlayer.updateLocation(directionPoint.x, directionPoint.y);
		}
		else if(action.equalsIgnoreCase("use"))
		{
			
		}
		else if(action.equalsIgnoreCase("item"))
		{
			
		}
	}
	
	private Point convertDirectionStringToInt(String pDirection)
	{
		int direction = 0;
		for(int i = 0; i < 4; i++)
		{
			if(pDirection.equalsIgnoreCase(CommandValidator.VALID_DIRECTIONS_ALT[i]))
			{
				direction = i;
				break;
			}
			if(pDirection.equalsIgnoreCase(CommandValidator.VALID_DIRECTIONS_FULL[i]))
			{
				direction = i;
				break;
			}
			if(pDirection.equalsIgnoreCase(CommandValidator.VALID_DIRECTIONS_SHORT[i]))
			{
				direction = i;
				break;
			}
		}
		
		switch(direction)
		{
			case 0:
				return new Point(0, -1);
			case 1:
				return new Point(1, 0);
			case 2:
				return new Point(0, 1);
			case 3:
				return new Point(-1, 0);
		}
		return new Point(0, 0);
	}
}
