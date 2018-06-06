package de.hsa.games.fatsquirrel.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hsa.games.fatsquirrel.core.EntitySet;
import de.hsa.games.fatsquirrel.core.GoodBeast;
import de.hsa.games.fatsquirrel.core.Wall;
import de.hsa.games.fatsquirrel.core.XY;

public class EntitySetTest {

    //Keine Mocks werden verwendet => Integrationstest?
	@Test
	public void createGoodBeastIT() {
	    EntitySet eSet = new EntitySet();
	    XY loc = new XY (2,6);
	    eSet.placeGoodBeast(loc);
	    GoodBeast gBeast = new GoodBeast(0,loc);
	    eSet.getEntities().get(0);
	    assertEquals(eSet.getEntities().get(0), gBeast);
	}
	
	@Test
	public void createWallIT() {
	    EntitySet eSet = new EntitySet();
        XY loc = new XY (2,6);
        eSet.placeWall(loc);
        Wall wall = new Wall(0,loc);
        eSet.getEntities().get(0);
        assertEquals(eSet.getEntities().get(0), wall);
	}
	

}
