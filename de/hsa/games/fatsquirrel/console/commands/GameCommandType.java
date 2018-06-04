package de.hsa.games.fatsquirrel.console.commands;

import de.hsa.games.fatsquirrel.core.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.XY;

public enum GameCommandType implements CommandTypeInfo{
	
	HELP("help", "  * list all commands"), // TODO + method (invoke later)
	EXIT("exit", "  * exit program"),
	ALL("all", " * no idea"),
	LEFT("left", " * move left"),
	RIGHT("right", " * move right"),
	UP("up", " * move up"),
	DOWN("down", " * move down"),
	STAY("stay", " * stay stationary"),
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
	public void execute(Object obj, Object[] params) {
		
		MasterSquirrel ms;
		
		if (obj != null && obj instanceof MasterSquirrel)
			ms = (MasterSquirrel) obj;
		else
			throw new ScanException("Not operating on a MasterSquirrel");
		
//		try {
//			Method methode = Commands.class.getMethod(name, (Class<?>[]) params);
//			methode.invoke(name, params);
//		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//			e.printStackTrace();
//		}
		
		
		switch (this) { 
		case ALL:
			break;
		case DOWN:
			ms.setNextCommand(XY.DOWN);
			break;
		case LEFT:
			ms.setNextCommand(XY.LEFT);
			break;
		case MASTER_ENERGY:
			System.out.println("Master's Energy: " + ms.getEnergy());
			ms.setNextCommand(XY.ZERO_ZERO);
			break;
		case RIGHT:
			ms.setNextCommand(XY.RIGHT);
			break;
		case SPAWN_MINI:
			if (ms.setSpawnMini((int) params[0]))
				break;
			throw new notEnoughEnergyException("Shared Enregy is higher than available Energy");
		case UP:
			ms.setNextCommand(XY.UP);
			break;
		case STAY:
			ms.setNextCommand(XY.ZERO_ZERO);
			break;
		case HELP: ms.setNextCommand(XY.ZERO_ZERO);
			break;
		case EXIT:
			System.exit(0);
		default:
			throw new ScanException("Unknown Command");
		}
		
	}


}
