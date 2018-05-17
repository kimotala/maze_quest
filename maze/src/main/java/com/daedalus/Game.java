package com.daedalus;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * This class contains the initial layout information of the maze
 *
 * @author kimo
 *
 */
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	private final int height;
	private final int width;
	private final String id;
	private transient Position begin;
	private transient Position end;


	public Game(Position begin, Position end, int height, int width, String id) {
		super();
		this.begin = begin;
		this.end = end;
		this.height = height;
		this.width = width;
		this.id = id;
	}

	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public String getId() {
		return id;
	}

	public Position getBegin() {
		return begin;
	}

	public void setBegin(Position begin) {
		this.begin = begin;
	}

	public Position getEnd() {
		return end;
	}

	public void setEnd(Position end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
	}
}
