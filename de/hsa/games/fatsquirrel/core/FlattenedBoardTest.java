package de.hsa.games.fatsquirrel.core;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class FlattenedBoardTest {


    @Mock
	private FlattenedBoard flatBoardTest;
	private BoardConfig config;
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
		config = Mockito.mock(BoardConfig.class);
		flatBoardTest = Mockito.mock(FlattenedBoard.class);
	}

	@Test
	public void getRespawnListTest() {
		
		assertEquals(flatBoardTest.getRespawnList().size(), 0);
	}
	
	@Test
	public void tryMoveMasterTest() {
	    
	    MasterSquirrel masterMock = mock(MasterSquirrel.class);
	    when(masterMock.getPosition()).thenReturn(new XY(2, 2));
	    flatBoardTest.tryMove(masterMock, XY.DOWN);
	    verify(masterMock).setPosition(new XY(1,2));
	    }
	
	
	
}
