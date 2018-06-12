package de.hsa.games.fatsquirrel.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntitySet;
import de.hsa.games.fatsquirrel.core.XY;

public class BoardTest {

   
	public Board testBoard = new Board(new BoardConfig());
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void flattenIT() {
		Board testBoard2 = new Board(new BoardConfig());
		Entity[][] flatTest = testBoard.flatten();
		Entity[][] flatTest2 = testBoard2.flatten();
		if(flatTest.equals(flatTest2)) {
		    fail();
		}else {
		    assertTrue(true);
		}
		
	}

	@Test
	public void randomPositionIT() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Board testBoard = new Board(new BoardConfig());
	    ArrayList<XY> blockedXy = new ArrayList<XY>();
		Method testMethod = testBoard.getClass().getDeclaredMethod("randomPosition", ArrayList.class);
		testMethod.setAccessible(true);
		XY xy = (XY) testMethod.invoke(testBoard, blockedXy);
		XY xy2 =(XY) testMethod.invoke(testBoard, blockedXy);
		assertNotSame(xy, xy2);
	}
	
	@Test
	public void surroundWithWallsIT() {
	    BoardConfig bC = new BoardConfig();
	    Board testBoard = new Board(bC);
	    EntitySet eSet = new EntitySet();
	    
	    for(int a = 0; a < (bC.getHeight()+ bC.getWidth()); a++) {
	        eSet.placeWall(new XY(1,2).randomXY(0, 100, 0, 100));
	    }
	    
	    for(int a = 0; a < (bC.getHeight() + bC.getWidth()); a++) {
	        
	    assertEquals(testBoard.getEntitySet().getEntities().get(a), eSet.getEntities().get(a));
	    
	    }
	}
	
	
}
