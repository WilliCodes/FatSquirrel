package de.hsa.games.fatsquirrel.core;

public abstract class Entity {
	
	private int id;
	private int energy;
	private XY position;
	private Boolean isActive = true;
	
	public Entity(int id, int energy, XY position) {
		this.id = id;
		this.energy = energy;
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public int getEnergy() {
		return energy;
	}

	public XY getPosition() {
		return position;
	}
	
	public Boolean isActive() {
		return isActive;
	}
	
	public void deactivate() {
		isActive = false;
	}
	
	public void setPosition(XY position) {
		this.position = position;
	}
	
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
