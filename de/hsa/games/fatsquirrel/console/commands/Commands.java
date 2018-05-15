package de.hsa.games.fatsquirrel.console.commands;

import de.hsa.games.fatsquirrel.core.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.MoveCommand;

public class Commands {

	public void Master_Energy(MasterSquirrel ms) {
		System.out.println("Master's Energy: " + ms.getEnergy());
		ms.setNextCommand(new MoveCommand(5));
	}
	
	public void SPAWN_MINI(HandOperatedMasterSquirrel ms, Object[] params) {
		if (ms.setSpawnMini((int) params[0]))
		throw new notEnoughEnergyException("Shared Enregy is higher than available Energy");
	}
	
	public void EXIT() {
		System.exit(0);
	}
}
