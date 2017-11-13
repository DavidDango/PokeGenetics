package Pokes;

import Moves.Move;
import Types.Types;

public abstract class AbstractAbility implements Ability {
	public double offensive(Move move, Pokemon pokemon) {
		return effectiveness(move.type(), pokemon.type1())*effectiveness(move.type(), pokemon.type2());
	}
	
	public double defensive(Move move, Pokemon pokemon) {
		return 1;
	}

	public double effectiveness(Types offensiveType, Types defensiveType) {
		return offensiveType.effectiveness(defensiveType);
	}
}
