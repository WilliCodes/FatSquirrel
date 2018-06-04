package de.hsa.games.fatsquirrel.console.commands;

import de.hsa.games.fatsquirrel.core.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.MoveCommand;


//				this should be the invoke class for
public class Commands {

	
	public void SPAWN_MINI(HandOperatedMasterSquirrel ms, Object[] params) {
		if (ms.setSpawnMini((int) params[0]))
		throw new notEnoughEnergyException("Shared Enregy is higher than available Energy");
	}
	
	public void EXIT() {
		System.exit(0);
	}
	
	public void help() {
		
	}
}
