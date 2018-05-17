package com.daedalus;

/**
 * This is a simple Maze interface which allows different implementations of Maze to exist.
 *
 * In this project, there are two different Maze implementations, LocalMaze and RemoteMaze.
 * RemoteMaze talks to the Daedalus Maze Server. LocalMaze is a simulation of the RemoteMaze and allows
 * testing of path finding algorithm without network connection or the Daedalus Maze server.
 *
 * @author kimo
 *
 */
public interface Maze {
	/**
	 * Start the Maze game.
	 * @return {@link Game} which contains the ID of the game, the layout of maze and
	 * the starting and ending positions
	 * @throws {@link MazeRequestException} when it fails to connect to the Maze services.
	 */
	Game start();

	/**
	 * Make a move to a new position
	 * @param gId is the game ID
	 * @param position is the position within the maze
	 * @return true if the position is allowed, false if it is blocking
	 *
	 */
	boolean moveTo(String gId, Position position);

	/**
	 * Solve the maze by providing path which is JSON string with the order set of positions.
	 * @param gId is the game ID
	 * @param path expressed as JSON string with an array of positions
	 * @return true if the solution is a valid path, false if the path is invalid
	 */
	boolean solve(String gId, String path);
}
