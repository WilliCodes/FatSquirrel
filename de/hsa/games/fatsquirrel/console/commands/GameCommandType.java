package de.hsa.games.fatsquirrel.console.commands;


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
	public void execute(Object obj, Object[] params) {
		
	}

}
