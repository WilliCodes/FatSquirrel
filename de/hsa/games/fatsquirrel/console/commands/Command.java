package de.hsa.games.fatsquirrel.console.commands;


public class Command {
	
	public final CommandTypeInfo commandTypeInfo;
	public final Object[] params;
	
	public Command(CommandTypeInfo commandTypeInfo, Object[] params) {
		this.commandTypeInfo = commandTypeInfo;
		this.params = params;
	}
}
