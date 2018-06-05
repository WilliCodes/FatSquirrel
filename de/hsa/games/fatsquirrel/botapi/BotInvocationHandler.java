package de.hsa.games.fatsquirrel.botapi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class BotInvocationHandler implements InvocationHandler {
	
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private final ControllerContext conCon;
	
	public BotInvocationHandler(ControllerContext conCon) {
		this.conCon = conCon;
	}

	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable, SpawnException {
		logger.finer("Bot invoked: " + arg1.getName());
		try {
			arg1.invoke(conCon, arg2);
		} catch (InvocationTargetException e) {
		}
			
		return null;
	}

}
