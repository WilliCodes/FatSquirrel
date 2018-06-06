package de.hsa.games.fatsquirrel.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

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
import de.hsa.games.fatsquirrel.core.XY;

public class BoardTest {

    @Mock
	public Board testBoard;
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
		testBoard = Mockito.mock(Board.class);
	}
	
	@Test
	public void flattenTest() {
		Board testBoard2 = new Board(new BoardConfig());
		Entity[][] flatTest = testBoard.flatten();
		Entity[][] flatTest2 = testBoard2.flatten();
		assertNotSame(flatTest, flatTest2);
	}

	@Test
	public void randomPositionTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Board testBoard = new Board(new BoardConfig());
	    ArrayList<XY> blockedXy = new ArrayList<XY>();
		Method testMethod = testBoard.getClass().getDeclaredMethod("randomPosition", ArrayList.class);
		testMethod.setAccessible(true);
		XY xy = (XY) testMethod.invoke(testBoard, blockedXy);
		XY xy2 =(XY) testMethod.invoke(testBoard, blockedXy);
		assertNotSame(xy, xy2);
	}
	
	
}
