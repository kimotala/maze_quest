package com.daedalus.impl.remote;

import java.io.IOException;

import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import com.daedalus.Game;
import com.daedalus.Maze;
import com.daedalus.Position;
import com.daedalus.exception.MazeRequestException;

/**
 * A wrapper for interfacing the Daedalus's remote server via HTTP GET / POST
 *
 * @author kimo
 *
 */
public class RemoteMaze implements Maze {
	private static String DAEDALUS_MAZE_HOST = "http://52.27.140.147:9099/maze";
	private static String DAEDALUS_MAZE_POSITION_CHECK = DAEDALUS_MAZE_HOST + "/%s/check?x=%d&y=%d";
	private static String DAEDALUS_MAZE_SOLVE = DAEDALUS_MAZE_HOST + "/%s/solve";

	@Override
	public Game start() {
		Game game = null;
		try {
			game =	Request.Post(DAEDALUS_MAZE_HOST)
					.version(HttpVersion.HTTP_1_1)
					.execute()
					.handleResponse(new StartMazeRespHandler());
		} catch (IOException e) {
			throw new MazeRequestException("Encountered I/O exception. " + e.getMessage());
		}
		return game;
	}

	@Override
	public boolean moveTo(String gId, Position position) {
		String url = String.format(DAEDALUS_MAZE_POSITION_CHECK, gId, position.getX(), position.getY());
		Boolean ok = false;
		try {
			ok = Request.Get(url)
					.execute().handleResponse(new ConfirmationRespHandler());
		} catch (IOException e) {
			throw new MazeRequestException("Encountered I/O exception. " + e.getMessage());
		}
		return ok;
	}

	@Override
	public boolean solve(String gId, String path) {
		String url = String.format(DAEDALUS_MAZE_SOLVE, gId);
		Boolean result = false;
		try {
			result = Request.Post(url)
					.version(HttpVersion.HTTP_1_1)
					.bodyString(path, ContentType.APPLICATION_JSON)
					.execute().handleResponse(new ConfirmationRespHandler());
		} catch (IOException e) {
			throw new MazeRequestException("Encountered I/O exception. " + e.getMessage());
		}
		return result;
	}
}
