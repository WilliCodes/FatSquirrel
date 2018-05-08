package de.hsa.games.fatsquirrel.console.commands;

import de.hsa.games.fatsquirrel.core.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.MoveCommand;

public enum GameCommandType implements CommandTypeInfo{
	
	HELP("help", "  * list all commands"),
	EXIT("exit", "  * exit program"),
	ALL("all", " * no idea"),
	LEFT("left", " * move left"),
	RIGHT("right", " * move right"),
	UP("up", " * move up"),
	DOWN("down", " * move down"),
	MASTER_ENERGY("master_energy", " * print Energy of MasterSquirrel"),
	SPAWN_MINI("spawn_mini", " * spawn a MiniSquirrel with provided Energy", new Class<?>[] {int.class});
	
	private String name;
	private String helpText;
	private Class<?>[] paramTypes;
	
	private GameCommandType(String name, String helpText, Class<?>[] paramTypes) {
		this.name = name;
		this.helpText = helpText;
		this.paramTypes = paramTypes;
	}
	
	private GameCommandType(String name, String helpText) {
		this.name = name;
		this.helpText = helpText;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getHelpText() {
		return helpText;
	}

	@Override
	public Class<?>[] getParamTypes() {
		return paramTypes;
	}

	@Override
	public boolean execute(Object obj, Object[] params) {
		
		HandOperatedMasterSquirrel ms;
		
		if (obj != null && obj instanceof HandOperatedMasterSquirrel)
			ms = (HandOperatedMasterSquirrel) obj;
		else
			throw new ScanException("Not operating on a MasterSquirrel");
		
		switch (this ) {
		case ALL:
			return false;
		case DOWN:
			ms.setNextCommand(new MoveCommand(2));
			return true;
		case LEFT:
			ms.setNextCommand(new MoveCommand(4));
			return true;
		case MASTER_ENERGY:
			System.out.println("Master's Energy: " + ms.getEnergy());
			return false;
		case RIGHT:
			ms.setNextCommand(new MoveCommand(6));
			return true;
		case SPAWN_MINI:
			if (ms.setSpawnMini((int) params[0]))
				return true;
			throw new ScanException("Shared Enregy is higher than available Energy");
		case UP:
			ms.setNextCommand(new MoveCommand(8));
			return true;
		case HELP:
			return false;
		case EXIT:
			System.exit(0);
		default:
			throw new ScanException("Unknown Command");
		}
		
	}


}
