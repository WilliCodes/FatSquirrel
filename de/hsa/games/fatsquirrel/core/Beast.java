package de.hsa.games.fatsquirrel.core;

public abstract class Beast extends Character{
	
	protected static final int movePeriod = 4;

	/**
	 * 
	 * @param id as int
	 * @param energy as int
	 * @param position as XY
	 */
	public Beast(int id, int energy, XY position) {
		super(id, energy, position);
		nextMove = movePeriod - 1;
	}
	
	
	/**
	 * 
	 * @param context as EntityContext
	 * @return position of the beast after its move
	 */
	protected XY beastMove(EntityContext context) {
		
		if (nextMove > 0) {
			nextMove--;
			return null;
		}
			
		nextMove = movePeriod - 1;
		
		PlayerEntity nearestPE = context.nearestPlayerEntity(this.getPosition());
		
		if (nearestPE == null) {
			return XY.randomVector();
		}
		
		XY moveVector = XY.vectorToEntity(this, nearestPE);
		
		return moveVector;
	}


	@Override
	public abstract void nextStep(EntityContext context);
	
	

}
