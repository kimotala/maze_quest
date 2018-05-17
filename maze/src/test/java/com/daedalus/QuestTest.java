package com.daedalus;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.daedalus.impl.Quest;
import com.daedalus.impl.local.mock.LocalMaze;

public class QuestTest {
	@Test
	public void testLocalMaze() {
		final int[][] mazeArray = new int [][] {
			{ 0, 1, 0, 0, 0 },
			{ 0, 0, 0, 1, 0 },
			{ 1, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 0, 0 }
		};
		final String[] mazePath = new String[] {
				"[{\"x\":0,\"y\":0},{\"x\":1,\"y\":0},{\"x\":1,\"y\":1},{\"x\":2,\"y\":1},{\"x\":3,\"y\":1},{\"x\":3,\"y\":2},{\"x\":3,\"y\":3},{\"x\":4,\"y\":3},{\"x\":4,\"y\":4}]",
				"[{\"x\":0,\"y\":0},{\"x\":1,\"y\":0},{\"x\":1,\"y\":1},{\"x\":1,\"y\":2},{\"x\":0,\"y\":2},{\"x\":0,\"y\":3},{\"x\":0,\"y\":4},{\"x\":1,\"y\":4},{\"x\":2,\"y\":4},{\"x\":2,\"y\":3},{\"x\":3,\"y\":3},{\"x\":4,\"y\":3},{\"x\":4,\"y\":4}]"
		};

		LocalMaze localMaze = new LocalMaze(mazeArray, mazePath, "testLocalMaze");
		Quest myQuest = new Quest(localMaze);
		boolean result = myQuest.play();
		assertTrue(result);
	}

	@Test
	public void testNoPath() {
		final int[][] mazeArray = new int [][] {
			{ 0, 1, 0, 0, 0, 1 },
			{ 0, 0, 0, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1 },
			{ 0, 1, 0, 0, 1, 0 },
			{ 1, 1, 1, 0, 0, 0 }
		};
		final String[] mazePath = new String[] {"[]"};

		LocalMaze localMaze = new LocalMaze(mazeArray, mazePath, "testNoPath");
		boolean result = new Quest(localMaze).play();
		assertTrue(result);
	}

	@Test
	public void testRectangleMaze() {
		final int[][] mazeArray = new int [][] {
			{ 0, 1, 0, 0, 0, 1 },
			{ 0, 0, 0, 1, 0, 1 },
			{ 1, 0, 1, 1, 0, 1 },
			{ 0, 0, 0, 0, 1, 0 },
			{ 1, 1, 1, 0, 0, 0 }
		};
		final String[] mazePath = new String[] {
				"[{\"x\":0,\"y\":0},{\"x\":1,\"y\":0},{\"x\":1,\"y\":1},{\"x\":2,\"y\":1},{\"x\":3,\"y\":1},{\"x\":3,\"y\":2},{\"x\":3,\"y\":3},{\"x\":4,\"y\":3},{\"x\":4,\"y\":4},{\"x\":4,\"y\":5}]"
		};

		LocalMaze localMaze = new LocalMaze(mazeArray, mazePath, "testRectMaze");
		boolean result = new Quest(localMaze).play();
		assertTrue(result);
	}
}
