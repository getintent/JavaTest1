package com.getintent.interview;

/**
 * The interface that represents state of monochrome screen
 */
public interface MonochromeScreen {
    /**
     * Initializes the screen.
     * @param width screen width
     * @param height screen width
     */
    public void init(int width, int height);

    /**
     * Draws horizontal line.
     * @param x1 left x coordinate of the line
     * @param x2 right x coordinate of the line
     * @param y y coordinate of line
     */
    public void drawHorizontalLine(int x1, int x2, int y);

    /**
     * Draws circle
     * @param x x coordinates of center
     * @param y y coordinate of center
     * @param r radius
     */
    public void drawCircle(int x, int y, int r);

    /**
     * Draws line
     */
    public void drawLine(int x1, int y1, int x2, int y2);
}
