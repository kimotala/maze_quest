package com.daedalus.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import com.daedalus.Game;
import com.daedalus.Maze;
import com.daedalus.Position;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * A quest to initiate a new game, find a valid path and solve the maze
 *
 * @author kimo
 *
 */
public class Quest {

	private final Maze maze;
	private final Stack<Position> path;

	private Game game;
	private Set<Position> visited;

	public Quest(Maze maze) {
		this.maze = maze;
		path = new Stack<>();
	}

	public boolean play() {
		game = maze.start();
		if (game != null) {
			visited = new HashSet<>();
			findPath(game.getBegin());
			String jsonPath = path2Json(path);
			System.out.println("Final Path: " + jsonPath );
			return maze.solve(game.getId(), jsonPath);
		}
		return false;
	}

	private boolean findPath(Position position) {
		if (!isInRange(position) || hasVisited(position)) {
			return false;
		}

		visited.add(position);
		boolean valid = maze.moveTo(game.getId(), position);
		System.out.println("move to " + position + " results in " + valid);
		if (!valid) {
			return false;
		}

		if (position.equals(game.getEnd())) {
			path.push(position);
			return true;
		}

		boolean found =
				findPath(position.bottom()) || findPath(position.left()) ||
				findPath(position.top()) || findPath(position.right()) ;
		if (found) {
			path.push(position);
		}
		return found;
	}

	private boolean isInRange(Position pos) {
		return !(pos.getX() < 0 || pos.getX() > game.getHeight() - 1 || pos.getY() < 0 || pos.getY() > game.getWidth() - 1);
	}

	private boolean hasVisited(Position pos) {
		return visited.contains(pos);
	}

	private static String path2Json(Stack<Position> path) {
		Gson gson = new Gson();
		JsonArray jsonArray = new JsonArray();
		while (!path.isEmpty()) {
			Position pos = path.pop();
			JsonObject obj = new JsonObject();
			obj.addProperty("x",  pos.getX());
			obj.addProperty("y", pos.getY());
			jsonArray.add(obj);
		}
		return gson.toJson(jsonArray);
	}
}
