package com.github.joncmak.mapGenerator;

import java.awt.Point;
import java.util.List;

public abstract class AbstractBasicRoom
{
	public abstract int[][] getRoom();
	public abstract void display(int x, int y, List<Point> pPlayerPath);
}
