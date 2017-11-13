package Moves;

public class StatusMove implements Status {
	private int[] statIncrease;
	private int health;
	private boolean flinch;
	private boolean firstTurn;

	public StatusMove(int healthIncrease, int attack, int defense, int specialAttack, int specialDefense, int speed, boolean flinches, boolean firstTurn) {
		statIncrease = new int[5];
		statIncrease[0] = attack;
		statIncrease[1] = defense;
		statIncrease[2] = specialAttack;
		statIncrease[3] = specialDefense;
		statIncrease[4] = speed;
		health = healthIncrease;
		flinch = flinches;
		this.firstTurn = firstTurn;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public int getModifier(int i) {
		return statIncrease[i];
	}
	
	@Override
	public boolean isFlinch() {
		return flinch;
	}
	
	@Override
	public boolean requiresFirstTurn() {
		return firstTurn;
	}
}