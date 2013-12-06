package com.getintent.interview;

import java.util.BitSet;

public class MonochromeScreenImpl implements MonochromeScreen {
    private static final byte SCREEN_COLOR = 1;
    private BitSet screen;
    private int width;

    @Override
    public void init(int width, int height) {
        this.screen = new BitSet(width * height);
        this.width = width;
    }

    @Override
    public void drawHorizontalLine(int x1, int x2, int y) {
        for (int x = x1; x < x2; ++x) {
            drawPoint(x, y, SCREEN_COLOR);
        }
    }

    private void drawPoint(int x, int y, byte color) {
        int i = (y - 1) * width + x - 1;
        screen.set(i, color);
    }

    @Override
    public void drawCircle(int x, int y, int r) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        throw new UnsupportedOperationException();
    }
}
