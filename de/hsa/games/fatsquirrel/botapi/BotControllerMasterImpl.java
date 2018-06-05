package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.XY;

public class BotControllerMasterImpl implements BotController{
	
	private XY generalDirection = XY.DOWN;
	private int count = 0;
	
	public BotControllerMasterImpl() {
	}

	@Override
	public void nextStep(ControllerContext view) {
		count++;
		view.move(XY.randomVector());
		
		if (count % 10 == 0)
		try {
			view.spawnMiniBot(XY.randomVector(), 10);
		} catch (SpawnException e) {
			System.out.println("Oops");
		}
	}
	
	
	public EntityType getNextGoodEntity() {
		return EntityType.NONE;
	}

}
