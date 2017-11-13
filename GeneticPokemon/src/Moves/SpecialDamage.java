package Moves;

import Pokes.Pokemon;
import Types.Types;

public class SpecialDamage extends AbstractMove {
	
	public SpecialDamage(Types type, int i, int j, Status status) {
		this.set(type, i, j, status);
	}

	public double effectiveAttack(Pokemon activeA) {
		return activeA.effectiveSpecialAttack();
	}

	public double effectiveDefense(Pokemon activeB) {
		return activeB.effectiveSpecialDefense();
	}
}
