package de.hsa.games.fatsquirrel.core;

public abstract class Entity {
	
	private int id;
	private int energy;
	private XY position;
	private Boolean isActive = true;
	
	/**
	 * 
	 * @param id as int
	 * @param energy as int
	 * @param position as XY
	 */
	public Entity(int id, int energy, XY position) {
		this.id = id;
		this.energy = energy;
		this.position = position;
	}

	/**
	 * 
	 * @return id from Entity
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return Energy of Entity
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * 
	 * @return XY position of Entity
	 */
	public XY getPosition() {
		return position;
	}
	
	/**
	 * 
	 * @return if Entity is active
	 */
	public Boolean isActive() {
		return isActive;
	}
	
	/**
	 * deactivates Entity for reaspawn next move
	 */
	public void deactivate() {
		isActive = false;
	}
	
	/**
	 * moves the Entity to given coordinates
	 * @param position as XY
	 */
	public void setPosition(XY position) {
		this.position = position;
	}
	
	/**
	 * updates the Energy of the Entity for given amount
	 * @param deltaEnergy as int
	 */
	public void updateEnergy(int deltaEnergy) {
		if (this instanceof MasterSquirrel && energy + deltaEnergy < 0) 
			energy = 0;
		else
			energy += deltaEnergy;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Entity) {
			Entity entity = (Entity) o;
			if (entity.id == this.id)
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "  |  Role: " + this.getClass().getName() + "  |  Energy: " + energy + "  |  Position: " + position.toString() + "  |  " + (isActive ? "(active)" : "(INactive)");
	}

}
