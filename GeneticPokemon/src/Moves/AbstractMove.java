package Moves;

import Types.Types;

public abstract class AbstractMove implements Move {
	private Types type;
	private int power;
	private int priority;
	private Status secondaryEffect;
	
	public void set(Types type, int power, int priority, Status effect) {
		this.type = type;
		this.power = power;
		this.priority = priority;
		this.secondaryEffect = effect;
	}
	
	public int getPower() {
		return power;
	}
	
	public Types type() {
		return type;
	}
	
	public Status getStatus() {
		return secondaryEffect;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public boolean isFlinch() {
		return secondaryEffect.isFlinch();
	}
	
	public boolean requiresFirstTurn() {
		return secondaryEffect.requiresFirstTurn();
	}
}
