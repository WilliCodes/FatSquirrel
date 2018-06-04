package de.hsa.games.fatsquirrel.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class XYTest {

	@Test
	public void plusTest() {
		XY xy = new XY(5, 7);
		xy  = xy.plus(new XY(1,1));
		assertEquals(xy, new XY(6,8));
	}
	
	@Test
	public void equalsTest() {
		assertTrue(new XY(0,0).equals(new XY(0,0)));
	}
	
	@Test
	public void minusTest() {
		assertEquals(new XY(2,3).minus(new XY(2,2)), new XY(0,-1));
	}
	
	@Test
	public void timesTest() {
		assertEquals(new XY(2,3).times(2), new XY(4,6));
	}

}
