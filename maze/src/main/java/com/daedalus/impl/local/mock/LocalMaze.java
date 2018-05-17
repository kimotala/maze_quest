package com.daedalus.impl.local.mock;


import org.apache.commons.lang3.StringUtils;

import com.daedalus.Game;
import com.daedalus.Maze;
import com.daedalus.Position;

/**
 * A local maze to facilitate testing
 *
 * @author kimo
 *
 */
public class LocalMaze implements Maze {
	private String mazeId;
	private int[][] mazeArray;
	private String[] mazePaths;

	public LocalMaze(int[][] mazeArray, String[] mazePaths, String gameId) {
		this.mazeArray = mazeArray;
		this.mazePaths = mazePaths;
		this.mazeId = gameId;
	}

	@Override
	public Game start() {
		Position begin = new Position(0,0);
		int height = mazeArray.length;
		int width = mazeArray[0].length;
		Position end = new Position(height - 1, width - 1);
		return new Game(begin, end, height, width, mazeId);
	}

	@Override
	public boolean moveTo(String gId, Position position) {
		validateGameId(gId);

		// validate the position
		return mazeArray[position.getX()][position.getY()] == 0;
	}

	@Override
	public boolean solve(String gId, String path) {
		validateGameId(gId);

		boolean correct = false;
		if (!StringUtils.isBlank(path)) {
			for (String validPath : mazePaths) {
				if (validPath.equals(path)) {
					correct = true;
					break;
				}
			}
		}
		return correct;
	}

	private void validateGameId(String gId) {
		if (!mazeId.equals(gId)) {
			throw new IllegalArgumentException("Unable to find the maze");
		}
	}
}
