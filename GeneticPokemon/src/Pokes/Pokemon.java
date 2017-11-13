package Pokes;

import Moves.Move;
import Moves.NullMove;
import Moves.PhysicalDamage;
import Moves.Status;
import Types.NullType;
import Types.Types;

public class Pokemon {
	private int health;
	private int attack;
	private int defense;
	private int specialAttack;
	private int specialDefense;
	private int speed;
	private int damageTaken;
	private Types type1;
	private Types type2;
	private Ability ability;
	private Move[] moves;
	private int currentMoves;
	private int[] modifiers;

	public Pokemon(int healthValue, int attackValue, int defenseValue, int specialAttackValue, int specialDefenseValue, int speedValue, Types t1, Types t2) {
		health = healthValue;
		attack = attackValue;
		defense = defenseValue;
		specialAttack = specialAttackValue;
		specialDefense = specialDefenseValue;
		speed = speedValue;
		type1 = t1;
		type2 = t2;
		damageTaken = 0;
		ability = new NullAbility();
		moves = new Move[4];
		moves[0] = new NullMove();
		moves[1] = new NullMove();
		moves[2] = new NullMove();
		moves[3] = new NullMove();
		currentMoves = 0;
		modifiers = new int[] {0, 0, 0, 0, 0};
	}

	public Pokemon(int healthValue, int attackValue, int defenseValue, int specialAttackValue, int specialDefenseValue, int speedValue, Types t1, Types t2, Ability pAbility, Move[] pMoves) {
		health = healthValue;
		attack = attackValue;
		defense = defenseValue;
		specialAttack = specialAttackValue;
		specialDefense = specialDefenseValue;
		speed = speedValue;
		type1 = t1;
		type2 = t2;
		damageTaken = 0;
		ability = pAbility;
		moves = pMoves;
		currentMoves = 4;
		modifiers = new int[] {0, 0, 0, 0, 0};
	}

	public void addMove(Move m) {
		if(currentMoves < 4){
			moves[currentMoves] = m;
			currentMoves++;
		}
	}

	public void addMove(Status statusMove) {
		this.addMove(new PhysicalDamage(new NullType(), 0, 0, statusMove));
	}
	
	public void addAbility(Ability newAbility) {
		ability = newAbility;
	}

	public boolean canFight() {
		return damageTaken < health;
	}

	public Move getMove(int i) {
		return moves[i];
	}

	public int getHealth() {
		return health;
	}

	public void receiveDamage(int d) {
		damageTaken = damageTaken + d;
	}

	public Move use(int i) {
		return moves[i];
	}
	
	public int damageTaken() {
		return damageTaken;
	}
	
	public double effectiveAttack() {
		return attack*(1 + 0.5*modifiers[0]);
	}
	
	public double effectiveSpecialAttack() {
		return specialAttack*(1 + 0.5*modifiers[2]);
	}

	public double effectiveDefense() {
		return defense*(1 + 0.5*modifiers[1]);
	}
	
	public double effectiveSpecialDefense() {
		return specialDefense*(1 + 0.5*modifiers[3]);
	}

	public Types type1() {
		return type1;
	}
	
	public Types type2() {
		return type2;
	}

	public Ability getAbility() {
		return ability;
	}

	public void apply(Status status) {
		damageTaken = damageTaken - status.getHealth()*(health/8);
		if(damageTaken < 0) {
			damageTaken = 0;
		}
		for(int i = 0; i < 5; i++) {
			if(modifiers[i] < 6) {
				modifiers[i] = modifiers[i] + status.getModifier(i);
			}
		}
	}

	public int getRemainingHealth() {
		if(damageTaken > health) {
			return 0;
		}
		return health - damageTaken;
	}

	public Pokemon clonePokemon() {
		return new Pokemon(health, attack, defense, specialAttack, specialDefense, speed, type1, type2, ability, moves);
	}

	public boolean isGhost() {
		return type1.isGhost() || type2.isGhost();
	}

	public void resetStats() {
		modifiers = new int[] {0, 0, 0, 0, 0};
	}

	public double effectiveSpeed() {
		return speed*(1 + 0.5*modifiers[4]);
	}
}
