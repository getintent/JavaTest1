package com.getintent.interview;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.RoundingMode;

import static com.google.common.math.IntMath.sqrt;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by rindon on 12.12.13.
 */
public class BitSetMonochromeScreenTest {

    private BitSetMonochromeScreen screen;

    @Before
    public void setUp() throws Exception {
        screen = new BitSetMonochromeScreen();

    }

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void shouldFailWithNegativeWidth() throws Exception {
        exception.expect(IllegalArgumentException.class);
        screen.init(-1, 10);
    }

    @Test
    public void shouldFailWithNegativeHeight() throws Exception {
        exception.expect(IllegalArgumentException.class);
        screen.init(1, -10);
    }

    @Test
    public void shouldFailWithTooBigDimensions() throws Exception {
        exception.expect(ArithmeticException.class);
        screen.init(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Test
    public void shouldInitialize() throws Exception {
        screen.init(1, 10);
        assertTrue(screen.isInitialized());
    }

    @Test
    public void shouldInitializeWithBigDimensions() throws Exception {
        screen.init(sqrt(Integer.MAX_VALUE, RoundingMode.HALF_DOWN) - 1,
                sqrt(Integer.MAX_VALUE, RoundingMode.HALF_DOWN) - 1);
    }

    @Test
    public void shouldThrowExceptionWhenNotInitialized() throws Exception {
        exception.expect(IllegalStateException.class);
        screen.drawHorizontalLine(1, 2, 3);
    }

    @Test
    public void shouldThrowExceptionWhenCoordinatesTooBig() throws Exception {
        exception.expect(IllegalArgumentException.class);
        screen.init(10, 10);
        screen.drawHorizontalLine(11, 2, 3);
    }

    @Test
    public void shouldThrowExceptionWhenLeftIsLessThanRight() throws Exception {
        exception.expect(IllegalArgumentException.class);
        screen.init(10, 10);
        screen.drawHorizontalLine(3, 2, 3);
    }

    @Test
    public void shouldDrawAPoint() throws Exception {
        screen.init(10, 10);
        screen.drawHorizontalLine(0, 0, 0);
        assertTrue(screen.hasBlackPoint(0, 0));
    }

    @Test
    public void shouldDrawHorizontalLine() throws Exception {
        screen.init(10, 10);
        screen.drawHorizontalLine(0, 3, 5);
        for (int i = 0; i <=3; ++i) {
            assertTrue(screen.hasBlackPoint(i, 5));
        }
        //check that other points are white
        Range<Integer> xs = Range.closedOpen(4, 10);
        Range<Integer> ys = Range.closedOpen(6, 10);
        for (int x : ContiguousSet.create(xs,DiscreteDomain.integers())) {
            for (int y : ContiguousSet.create(ys,DiscreteDomain.integers())) {
                assertFalse(screen.hasBlackPoint(x, y));
            }
        }
    }
}
