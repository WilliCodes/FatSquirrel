package de.hsa.games.fatsquirrel.core;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class FlattenedBoardTest {


    @Mock
    private Board boardMock;
	private FlattenedBoard flattenedBoard;
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
		flattenedBoard = Mockito.mock(FlattenedBoard.class);
	}

	@Test
	public void getRespawnListTest() {
		
		assertEquals(flattenedBoard.getRespawnList().size(), 0);
	}
	
	@Test
	public void tryMoveMasterTest() {
	    
	    MasterSquirrel masterMock = mock(MasterSquirrel.class);
	    when(masterMock.getPosition()).thenReturn(new XY(2, 2));
	    flattenedBoard.tryMove(masterMock, XY.DOWN);
	    verify(masterMock).setPosition(new XY(2,3));
	    }
	
	@Test
    public void tryMoveMasterAgainstBadBeastTest() {
        MasterSquirrel masterMock = mock(MasterSquirrel.class);
        BadBeast badMock = mock(BadBeast.class);

        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.BAD_BEAST);
        when(badMock.getEnergy()).thenReturn(-150);
        when(masterMock.getPosition()).thenReturn(new XY(1, 1));
        when(badMock.getPosition()).thenReturn(new XY(2, 2));

        flattenedBoard.tryMove(masterMock, XY.RIGHT_DOWN);

        verify(masterMock).updateEnergy(-150);
        verify(masterMock, never()).setPosition(new XY(2,2).plus(XY.RIGHT_DOWN));
        verify(flattenedBoard, never()).killAndReplace(badMock);
    }
	

    @Test
    public void tryMoveMasterAgainstGoodBeastTest() {
        MasterSquirrel masterMock = mock(MasterSquirrel.class);
        GoodBeast goodMock = mock(GoodBeast.class);

        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.GOOD_BEAST);
        when(goodMock.getEnergy()).thenReturn(200);
        when(masterMock.getPosition()).thenReturn(new XY(1, 1));
        when(goodMock.getPosition()).thenReturn(new XY(2, 2));

        flattenedBoard.tryMove(masterMock, XY.RIGHT_DOWN);

        verify(masterMock).updateEnergy(200);
        verify(masterMock).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(flattenedBoard).killAndReplace(goodMock);
    }
    
    @Test
    public void tryMoveMasterAgainstMasterTest() {
        MasterSquirrel masterOneMock = mock(MasterSquirrel.class);
        MasterSquirrel masterTwoMock = mock(MasterSquirrel.class);

        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.MASTER_SQUIRREL);
        when(masterOneMock.getPosition()).thenReturn(new XY(1, 1));

        flattenedBoard.tryMove(masterOneMock, XY.RIGHT_DOWN);

        verify(masterOneMock, never()).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(masterOneMock, never()).updateEnergy(150);
        verify(masterTwoMock, never()).updateEnergy(150);
        verify(flattenedBoard, never()).kill(masterOneMock);
        verify(flattenedBoard, never()).kill(masterTwoMock);
    }
    
    @Test
    public void tryMoveMasterAgainstOwnMiniTest() {
        MasterSquirrel masterMock = mock(MasterSquirrel.class);
        MiniSquirrel miniMock = mock(MiniSquirrel.class);

        when(masterMock.isMyMini(miniMock)).thenReturn(true);
        when(miniMock.getEnergy()).thenReturn(200);
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.MINI_SQUIRREL);
        when(masterMock.getPosition()).thenReturn(new XY(1, 1));

        flattenedBoard.tryMove(masterMock, XY.RIGHT_DOWN);

        verify(masterMock).updateEnergy(200);
        verify(masterMock).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(flattenedBoard).kill(miniMock);
    }
    
    @Test
    public void tryMoveMasterAgainstEnemyMiniTest() {
        MasterSquirrel masterMock = mock(MasterSquirrel.class);
        MasterSquirrel masterTwoMock = mock(MasterSquirrel.class);
        MiniSquirrel miniMock = mock(MiniSquirrel.class);

        when(masterMock.getId()).thenReturn(1);
        when(masterTwoMock.getId()).thenReturn(2);
        when(miniMock.getMasterID()).thenReturn(1);
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.MINI_SQUIRREL);
        when(masterMock.getPosition()).thenReturn(new XY(1, 1));

        flattenedBoard.tryMove(masterMock, XY.RIGHT_DOWN);

        verify(masterMock).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(flattenedBoard).kill(miniMock);
    }
    
    @Test
    public void tryMoveMasterAgainstBadPlantsTest() {
        MasterSquirrel masterMock = mock(MasterSquirrel.class);
        BadPlant badPlantMock = mock(BadPlant.class);

        when(masterMock.getPosition()).thenReturn(new XY(1, 1));
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.BAD_PLANT);
        when(badPlantMock.getEnergy()).thenReturn(-100);

        flattenedBoard.tryMove(masterMock, XY.RIGHT_DOWN);

        verify(masterMock).updateEnergy(-100);
        verify(masterMock).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(flattenedBoard).killAndReplace(badPlantMock);
    }
    
    @Test
    public void tryMoveMasterAgainstGoodPlants() {
        MasterSquirrel masterMock = mock(MasterSquirrel.class);
        GoodPlant goodPlantMock = mock(GoodPlant.class);

        when(masterMock.getPosition()).thenReturn(new XY(1, 1));
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.GOOD_PLANT);
        when(goodPlantMock.getEnergy()).thenReturn(100);

        flattenedBoard.tryMove(masterMock, XY.RIGHT_DOWN);

        verify(masterMock).updateEnergy(100);
        verify(masterMock).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(flattenedBoard).killAndReplace(goodPlantMock);
    }
    
    @Test
    public void tryMoveMasterAgainstWallTest() {
        MasterSquirrel masterMock = mock(MasterSquirrel.class);
        Wall wallMock = mock(Wall.class);

        when(masterMock.getPosition()).thenReturn(new XY(1, 1));
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.WALL);
        when(wallMock.getEnergy()).thenReturn(-10);

        flattenedBoard.tryMove(masterMock, XY.RIGHT_DOWN);

        verify(masterMock).updateEnergy(-10);
        verify(masterMock).setParalyzed();
        verify(masterMock, never()).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(flattenedBoard, never()).kill(wallMock);
    }
    
    @Test
    public void tryMoveMiniAgainstOwnMasterTest() {
        MasterSquirrel masterMock = mock(MasterSquirrel.class);
        MiniSquirrel miniMock = mock(MiniSquirrel.class);

        when(masterMock.getId()).thenReturn(1);
        when(miniMock.getMasterID()).thenReturn(1);
        when(masterMock.isMyMini(miniMock)).thenReturn(true);
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.MINI_SQUIRREL);
        when(miniMock.getPosition()).thenReturn(new XY(1 , 1));
        when(miniMock.getEnergy()).thenReturn(100);
        flattenedBoard.tryMove(miniMock, XY.RIGHT_DOWN);

        verify(masterMock).updateEnergy(100);
        verify(flattenedBoard).kill(miniMock);
        verify(flattenedBoard, never()).killAndReplace(miniMock);
        verify(miniMock, never()).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
    }
    
    @Test
    public void tryMoveMiniAgainstEnemyMasterTest() {
        MiniSquirrel miniMock = mock(MiniSquirrel.class);
        MasterSquirrel masterMock = mock(MasterSquirrel.class);
        MasterSquirrel enemyMock = mock(MasterSquirrel.class);

        when(miniMock.getPosition()).thenReturn(new XY(1, 1));
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.MASTER_SQUIRREL);
        when(masterMock.getId()).thenReturn(1);
        when(miniMock.getMasterID()).thenReturn(1);
        when(masterMock.isMyMini(miniMock)).thenReturn(true);
        when(enemyMock.getId()).thenReturn(2);

        flattenedBoard.tryMove(miniMock, XY.RIGHT_DOWN);

        verify(enemyMock).updateEnergy(150);
        verify(flattenedBoard).kill(miniMock);
        verify(flattenedBoard, never()).killAndReplace(miniMock);
        verify(miniMock, never()).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
    }

    @Test
    public void tryMoveMiniAgainstMiniTest() {
        MiniSquirrel miniMock = mock(MiniSquirrel.class);
        MiniSquirrel enemyMiniMock = mock(MiniSquirrel.class);
        MasterSquirrel masterMock = mock(MasterSquirrel.class);
        MasterSquirrel enemyMock = mock(MasterSquirrel.class);

        when(masterMock.getId()).thenReturn(1);
        when(enemyMock.getId()).thenReturn(2);
        when(miniMock.getMasterID()).thenReturn(1);
        when(enemyMiniMock.getMasterID()).thenReturn(2);
        when(miniMock.getPosition()).thenReturn(new XY(1, 1));
        when(flattenedBoard.getEntityType(new XY(2,2))).thenReturn(EntityType.MASTER_SQUIRREL);

        flattenedBoard.tryMove(miniMock, XY.RIGHT_DOWN);

        verify(flattenedBoard).kill(miniMock);
        verify(flattenedBoard).kill(enemyMiniMock);
        verify(flattenedBoard, never()).killAndReplace(miniMock);
        verify(flattenedBoard, never()).killAndReplace(enemyMiniMock);
        verify(miniMock, never()).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
    }

    @Test
    public void tryMoveMiniAgainstBadBeastTest() {
        MiniSquirrel miniMock = mock(MiniSquirrel.class);
        BadBeast badMock = mock(BadBeast.class);

        when(miniMock.getEnergy()).thenReturn(200);
        when(miniMock.getPosition()).thenReturn(new XY(1, 1));
        when(badMock.getEnergy()).thenReturn(-150);
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.BAD_BEAST);

        flattenedBoard.tryMove(miniMock, XY.RIGHT_DOWN);

        verify(miniMock).updateEnergy(-150);
        verify(miniMock, never()).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(flattenedBoard, never()).kill(miniMock);
    }

    @Test
    public void tryMoveMiniAgainstGoodBeastTest() {
        MiniSquirrel miniMock = mock(MiniSquirrel.class);
        GoodBeast goodMock = mock(GoodBeast.class);

        when(miniMock.getPosition()).thenReturn(new XY(1, 1));
        when(goodMock.getEnergy()).thenReturn(200);
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.GOOD_PLANT);

        flattenedBoard.tryMove(miniMock, XY.RIGHT_DOWN);

        verify(miniMock).updateEnergy(200);
        verify(miniMock).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(flattenedBoard).killAndReplace(goodMock);
    }

    @Test
    public void tryMoveMiniAgainstBadPlantsTest() {
        MiniSquirrel miniMock = mock(MiniSquirrel.class);
        BadPlant badMock = mock(BadPlant.class);

        when(miniMock.getPosition()).thenReturn(new XY(1, 1));
        when(badMock.getEnergy()).thenReturn(-100);
        when(miniMock.getEnergy()).thenReturn(150);
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.BAD_PLANT);

        flattenedBoard.tryMove(miniMock, XY.RIGHT_DOWN);

        verify(flattenedBoard, never()).kill(miniMock);
        verify(flattenedBoard, never()).killAndReplace(miniMock);
        verify(flattenedBoard).killAndReplace(badMock);
        verify(miniMock).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
    }

    @Test
    public void tryMoveMiniAgainstGoodPlantsTest() {
        MiniSquirrel miniMock = mock(MiniSquirrel.class);
        GoodPlant goodMock = mock(GoodPlant.class);

        when(miniMock.getPosition()).thenReturn(new XY(1, 1));
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.GOOD_PLANT);
        when(goodMock.getEnergy()).thenReturn(200);

        flattenedBoard.tryMove(miniMock, XY.RIGHT_DOWN);

        verify(miniMock).updateEnergy(200);
        verify(miniMock).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(flattenedBoard).killAndReplace(goodMock);
    }

    @Test
    public void tryMoveMiniAgainstWallTest() {
        MiniSquirrel miniMock = mock(MiniSquirrel.class);
        Wall wallMock = mock(Wall.class);

        when(miniMock.getPosition()).thenReturn(new XY(1, 1));
        when(miniMock.getEnergy()).thenReturn(100);
        when(wallMock.getEnergy()).thenReturn(-10);
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.WALL);

        flattenedBoard.tryMove(miniMock, XY.RIGHT_DOWN);

        verify(miniMock).updateEnergy(-10);
        verify(miniMock, never()).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(miniMock).setParalyzed();
        verify(flattenedBoard, never()).kill(wallMock);
    }

    @Test
    public void tryMoveGoodBeastAgainstSquirrelTest() {
        MasterSquirrel squirrelMock = mock(MasterSquirrel.class);
        GoodBeast goodMock = mock(GoodBeast.class);

        when(goodMock.getEnergy()).thenReturn(200);
        when(goodMock.getPosition()).thenReturn(new XY(1, 1));
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.MASTER_SQUIRREL);

        flattenedBoard.tryMove(goodMock, XY.RIGHT_DOWN);

        verify(squirrelMock).updateEnergy(goodMock.getEnergy());
        verify(flattenedBoard).killAndReplace(goodMock);
    }

    @Test
    public void tryMoveGoodBeastAgainstNonSquirrelTest() {
        GoodBeast goodMock = mock(GoodBeast.class);
        BadPlant badPlantMock = mock(BadPlant.class);

        when(goodMock.getPosition()).thenReturn(new XY(1, 1));
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.BAD_PLANT);

        flattenedBoard.tryMove(goodMock, XY.RIGHT_DOWN);

        verify(goodMock, never()).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(flattenedBoard, never()).killAndReplace(goodMock);
        verify(flattenedBoard, never()).kill(goodMock);
        verify(flattenedBoard, never()).killAndReplace(badPlantMock);
        verify(flattenedBoard, never()).kill(badPlantMock);
    }

    @Test
    public void tryMoveBadBeastAgainstSquirrelsTest() {
        MasterSquirrel squirrelMock = mock(MasterSquirrel.class);
        BadBeast badMock = mock(BadBeast.class);

        when(badMock.getPosition()).thenReturn(new XY(1, 1));
        when(badMock.getEnergy()).thenReturn(-150);
        when(flattenedBoard.getEntityType(new XY(2, 2))).thenReturn(EntityType.MASTER_SQUIRREL);
        when(squirrelMock.getEnergy()).thenReturn(1000);

        flattenedBoard.tryMove(badMock, XY.RIGHT_DOWN);

        verify(squirrelMock).updateEnergy(-150);
        verify(badMock, never()).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
    }

    @Test
    public void tryMoveBadBeastAgainstNonSquirrelTest() {
        BadBeast badMock = mock(BadBeast.class);
        GoodBeast goodMock = mock(GoodBeast.class);

        when(badMock.getPosition()).thenReturn(new XY(1, 1));
        when(flattenedBoard.getEntityType(new XY(2,2 ))).thenReturn(EntityType.GOOD_BEAST);

        flattenedBoard.tryMove(badMock, XY.RIGHT_DOWN);

        verify(badMock, never()).setPosition(new XY(1,1).plus(XY.RIGHT_DOWN));
        verify(flattenedBoard, never()).killAndReplace(goodMock);
        verify(flattenedBoard, never()).kill(goodMock);
        verify(flattenedBoard, never()).killAndReplace(badMock);
        verify(flattenedBoard, never()).kill(badMock);
    }
	
}
