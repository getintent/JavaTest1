package com.getintent.interview;

import com.google.common.annotations.VisibleForTesting;

import java.util.BitSet;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.math.IntMath.checkedAdd;
import static com.google.common.math.IntMath.checkedMultiply;

/**
 * Created by rindon on 12.12.13.
 */
public class BitSetMonochromeScreen implements MonochromeScreen {

    private BitSet state;
    private boolean initialized;
    private int width;
    private int height;

    /**
     * Initializes the screen.
     *
     * @param width  screen width
     * @param height screen width
     */
    @Override
    public void init(int width, int height) {
        checkArgument(width >= 0);
        checkArgument(height >= 0);
        this.width = width;
        this.height = height;
        this.state = new BitSet(checkedMultiply(width, height));
        this.initialized = true;
    }

    /**
     * Draws horizontal line.
     *
     * @param x1 left x coordinate of the line
     * @param x2 right x coordinate of the line
     * @param y  y coordinate of line
     */
    @Override
    public void drawHorizontalLine(int x1, int x2, int y) {
        checkState(initialized);
        checkArgument(x1 < width);
        checkArgument(x2 < width);
        checkArgument(y < height);
        checkArgument(x1 <= x2);
        int left = coordinatesToIndex(x1, y);
        int right = coordinatesToIndex(x2, y);
        state.set(left, right + 1);
    }

    /**
     * Draws circle
     *
     * @param x x coordinates of center
     * @param y y coordinate of center
     * @param r radius
     */
    @Override
    public void drawCircle(int x, int y, int r) {
        throw new UnsupportedOperationException("Not implemented");
    }


    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private int coordinatesToIndex(int x, int y) {
        // check for integer overflow
        return checkedAdd(x, checkedMultiply(y, width));
    }

    @VisibleForTesting boolean hasBlackPoint(int x, int y) {
        return state.get(coordinatesToIndex(x, y));
    }

    @VisibleForTesting boolean isInitialized() {
        return initialized;
    }
}
