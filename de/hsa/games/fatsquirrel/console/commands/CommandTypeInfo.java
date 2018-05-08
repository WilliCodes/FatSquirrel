package de.hsa.games.fatsquirrel.console.commands;


public interface CommandTypeInfo {

	public String getName();
	public String getHelpText();
	public Class<?>[] getParamTypes();

	public boolean execute(Object obj, Object[] params);
}
