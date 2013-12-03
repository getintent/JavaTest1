package com.getintent.interview.impl;

import java.util.BitSet;

import com.getintent.interview.MonochromeScreen;

public class MonochromeScreenImpl implements MonochromeScreen {

	private BitSet[] screen;
	private int width;

	@Override
	public void init(int width, int height) {
		checkInitRange(width, height);
		screen = new BitSet[height];
		this.width = width;
	}

	@Override
	public void drawHorizontalLine(int x1, int x2, int y) {
		checkLineRange(x1, x2, y);
		if (screen[y] == null) {
			screen[y] = new BitSet(width);
		}
		screen[y].set(x1, x2 + 1);
	}

	@Override
	public void drawCircle(int x, int y, int r) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		throw new UnsupportedOperationException();
	}

	public byte getPoint(int x, int y) {
		checkPointRange(x, y);
		if (screen[y] == null) {
			return 0;
		} else {
			return (byte) (screen[y].get(x) == false ? 0 : 1);
		}
	}

	private void checkInitRange(int width, int height) {
		if (width <= 0) {
			throw new IllegalArgumentException("Width must be greater then zero!");
		}
		if (height <= 0) {
			throw new IllegalArgumentException("Height must be greater then zero!");
		}
	}

	private void checkLineRange(int x1, int x2, int y) {
		if (x1 < 0 || x2 < 0 || y < 0) {
			throw new IllegalArgumentException("coordinates must be positive");
		}
		if (y >= screen.length) {
			throw new IllegalArgumentException("y must be <" + screen.length);
		}
		if (x1 > x2) {
			throw new IllegalArgumentException("x1 must be less or equals then x2");
		}
		if (x2 >= width) {
			throw new IllegalArgumentException("x must be <" + width);
		}
	}

	private void checkPointRange(int x, int y) {
		if (y < 0 || y >= screen.length) {
			throw new IllegalArgumentException("y must be >=0 and <" + screen.length);
		}
		if (x < 0 || x >= width) {
			throw new IllegalArgumentException("x must be >=0 and <" + width);
		}
	}

}
