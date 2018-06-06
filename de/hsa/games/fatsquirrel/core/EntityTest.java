package de.hsa.games.fatsquirrel.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntityTest {

	@Test
	public void deactivateTest() {
		GoodBeast gB = new GoodBeast(1, new XY(0,0));
		gB.deactivate();
		assertFalse(gB.isActive());
	}
	
	@Test
	public void setPositionTest() {
		BadBeast bB = new BadBeast(1, new XY(0,0));
		bB.setPosition(new XY(2,2));
		assertEquals(new XY(2,2), bB.getPosition());
	}

	@Test
	public void energyTest() {
		GoodPlant gP = new GoodPlant(1, new XY(0,0));
		gP.updateEnergy(100);
		assertEquals(gP.getEnergy(), 200);
	}
	
	@Test
	public void equalsTest() {
		BadPlant bP = new BadPlant(1, new XY(0,0));
		assertTrue(bP.equals(new BadPlant(1, new XY(0,0))));
	}
}