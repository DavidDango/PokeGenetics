package Pokes;

import Moves.Move;

public class Scrappy extends AbstractAbility{

	public double offensive(Move move, Pokemon pokemon) {
		if((move.type().isNormal() || move.type().isFighting()) && pokemon.isGhost()) {
			return 1;
		}
		return effectiveness(move.type(), pokemon.type1())*effectiveness(move.type(), pokemon.type2());
	}
}
