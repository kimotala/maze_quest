package com.daedalus.app;

import com.daedalus.Maze;
import com.daedalus.exception.MazeRequestException;
import com.daedalus.impl.Quest;
import com.daedalus.impl.remote.RemoteMaze;

public class App
{
	public static void main( String[] args )
	{
		System.out.println( "Welcome to Daedalus's Maze Quest!" );
		try {
			Maze maze = new RemoteMaze();
			boolean result = new Quest(maze).play();
			System.out.println("The quest to solve the maze resulted in " + (result ? "success!" : "failure. Try again!"));
		} catch (MazeRequestException e) {
			System.out.println("Encountered failure in communicating with maze service. " + e.getMessage());
		}
	}
}
