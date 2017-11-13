package Pokes;

import Moves.Move;

public class Multiscale extends AbstractAbility{

	public double defensive(Move move, Pokemon pokemon) {
		if(pokemon.damageTaken() == 0) {
			return 0.5;
		}
		return 1;
	}
}
