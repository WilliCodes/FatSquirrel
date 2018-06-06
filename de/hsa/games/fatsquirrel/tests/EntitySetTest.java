package de.hsa.games.fatsquirrel.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hsa.games.fatsquirrel.core.EntitySet;
import de.hsa.games.fatsquirrel.core.Wall;
import de.hsa.games.fatsquirrel.core.XY;

public class EntitySetTest {

	@Test
	public void placeWallTest() {
		EntitySet eS = new EntitySet();
		eS.placeWall(new XY(0,0));
		assertTrue(eS.getEntities().get(0).equals(new Wall(0,new XY(0,0))));
	}

}
