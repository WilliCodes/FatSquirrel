package de.hsa.games.fatsquirrel.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class XYTest {

	@Test
	public void moveTest() {
		XY xy = new XY(5, 7);
		xy  = xy.plus(new XY(1,1));
		assertEquals(xy, new XY(6,8));
	}
	
	@Test
	public void equalsTest() {
		assertTrue(new XY(0,0).equals(new XY(0,0)));
	}
	
	

}
