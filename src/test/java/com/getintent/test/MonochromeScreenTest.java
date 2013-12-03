package com.getintent.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.getintent.interview.MonochromeScreen;
import com.getintent.interview.impl.MonochromeScreenImpl;

public class MonochromeScreenTest {

	@Test
	public void testInit() {
		MonochromeScreen screen = new MonochromeScreenImpl();
		try{
			screen.init(0, 0);
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)){
				fail("0 0 must throw IAE exception");
			}
		}
		try{
			screen.init(1, 0);
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)){
				fail("1 0 must throw IAE exception");
			}
		}
		try{
			screen.init(-1, 1);
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)){
				fail("-1 1 must throw IAE exception");
			}
		}
		try{
			screen.init(-1, -1);
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)){
				fail("-1 -1 must throw IAE exception");
			}
		}
		screen.init(1, 1);
		screen.init(100, 100);
	}
	
	@Test
	public void testDrawHorizontalLine(){
		MonochromeScreenImpl screen = createScreen();
		try{
			screen.drawHorizontalLine(0, 100, 0);
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)){
				fail(e.getMessage());
			}
		}
		try{
			screen.drawHorizontalLine(-1, 1, 0);
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)){
				fail(e.getMessage());
			}
		}
		try{
			screen.drawHorizontalLine(0, 0, 100);
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)){
				fail(e.getMessage());
			}
		}
		try{
			screen.drawHorizontalLine(10, 0, 0);
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)){
				fail(e.getMessage());
			}
		}
		screen.drawHorizontalLine(0, 99, 99);
		screen.drawHorizontalLine(99, 99, 99);
		screen.drawHorizontalLine(0, 0, 0);
	}

	@Test
	public void testScreen(){
		MonochromeScreenImpl screen = createScreen();
		try{
			screen.getPoint(0, -1);
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)){
				fail(e.getMessage());
			}
		}
		try{
			screen.getPoint(-1, 0);
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)){
				fail(e.getMessage());
			}
		}
		try{
			screen.getPoint(100, 100);
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)){
				fail(e.getMessage());
			}
		}
		
		assertEquals(0, screen.getPoint(0, 0));
		assertEquals(0, screen.getPoint(99, 99));
		
		screen.drawHorizontalLine(0, 99, 99);
		assertEquals(1, screen.getPoint(99, 99));
		assertEquals(1, screen.getPoint(45, 99));
		assertEquals(0, screen.getPoint(0, 0));
		assertEquals(0, screen.getPoint(45, 45));
		
		screen = createScreen();
		screen.drawHorizontalLine(0, 0, 0);
		assertEquals(1, screen.getPoint(0, 0));
		assertEquals(0, screen.getPoint(0, 1));
		
		screen = createScreen();
		screen.drawHorizontalLine(99, 99, 99);
		assertEquals(1, screen.getPoint(99, 99));
		assertEquals(0, screen.getPoint(0, 0));
		assertEquals(0, screen.getPoint(45, 99));
	}

	private MonochromeScreenImpl createScreen() {
		MonochromeScreenImpl screen = new MonochromeScreenImpl();
		screen.init(100, 100);
		return screen;
	}
	
}
