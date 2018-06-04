package de.hsa.games.fatsquirrel.core;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class FlattenedBoardTest {


    
	private FlattenedBoard flatBoardTest;
	private BoardConfig config;
	
	@Before
	public void setUp() {
		BoardConfig config = Mockito.mock(BoardConfig.class);
		flatBoardTest = Mockito.mock(FlattenedBoard.class);
	}

	@Test
	public void getRespawnListTest() {
		
		assertEquals(flatBoardTest.getRespawnList().size(), 0);
	}
	
	@Test
	public void tryMoveTest() {
	    
	}
}
