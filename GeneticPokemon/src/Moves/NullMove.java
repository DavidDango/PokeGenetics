package Moves;

import Pokes.Pokemon;
import Types.NullType;

public class NullMove extends AbstractMove {
	
	public NullMove() {
		this.set(new NullType(), 0, 0, new NullStatus());
	}
	
	public double effectiveAttack(Pokemon activeA) {
		return 0;
	}

	public double effectiveDefense(Pokemon activeB) {
		return 0;
	}
}
