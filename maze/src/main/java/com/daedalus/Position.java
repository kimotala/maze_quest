package com.daedalus;

import java.util.Objects;

/**
 * This class provides the location at any point in the maze
 *
 * @author kimo
 *
 */
public class Position {
	private final int x;
	private final int y;

	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Position right() {
		return new Position(getX(), getY() + 1);
	}

	public Position left() {
		return new Position(getX(), getY() - 1);
	}

	public Position top() {
		return new Position(getX() - 1, getY());
	}

	public Position bottom() {
		return new Position(getX() + 1, getY());
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (object == null || !(object instanceof Position)) {
			return false;
		}

		Position that = (Position) object;
		if (this.getX() != that.getX() || this.getY() != that.getY()) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY());
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
