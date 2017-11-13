package Pokes;

import Moves.Move;

public class Levitate extends AbstractAbility {

	@Override
	public double defensive(Move move, Pokemon pokemon) {
		if(move.type().isGround()) {
			return 0;
		}
		return 1;
	}

}
