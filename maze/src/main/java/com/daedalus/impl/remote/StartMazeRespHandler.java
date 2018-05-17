package com.daedalus.impl.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.daedalus.Game;
import com.daedalus.Position;
import com.daedalus.exception.MazeRequestException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * A HTTP POST response handler upon requesting to start a new maze.
 *
 * @author kimo
 *
 */
public class StartMazeRespHandler implements ResponseHandler<Game> {
	@Override
	public Game handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		int status = response.getStatusLine().getStatusCode();
		Game game = null;
		if (status == 201) {
			HttpEntity entity = response.getEntity();
			if (entity == null || entity.getContent() == null) {
				String msg = "The maze server returns empty content.";
				throw new MazeRequestException(msg);
			}

			String contents = getResponseString(entity.getContent());
			System.out.println("Maze service returns the game layout data as: " + contents);

			try {
				Gson gson = new Gson();
				game = gson.fromJson(contents, Game.class);
			} catch (JsonSyntaxException e) {
				String msg = "The maze server returns unexpected JSON response format: " + contents;
				throw new MazeRequestException(msg);
			}

			System.out.println("Successfully start a maze game. Id = " + game.getId());
			enrich(game);
		} else {
			onUnexpectedResp(response);
		}
		return game;
	}

	public void onUnexpectedResp(HttpResponse response) {
		int status = response.getStatusLine().getStatusCode();
		String str = "Encountered unexpected response. Status code: " + status ;
		// Note: We can handle the different class of error, 4xx or 5xx differently in this method.
		System.out.print(str);
		throw new MazeRequestException(str);
	}

	private void enrich(Game game) {
		game.setBegin(new Position(0, 0));
		game.setEnd(new Position(game.getHeight()-1, game.getWidth()-1));
	}

	private String getResponseString(InputStream is) {
		StringBuilder builder = new StringBuilder();
		if (is != null) {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					builder.append(inputLine);
				}
			} catch (IOException e) {
				String str = "Failure to read the contents of the response";
				System.out.println(str);
				throw new MazeRequestException(str);
			}
		}
		return builder.toString();
	}
}
