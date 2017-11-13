package Pokes;

import Moves.Move;

public interface Ability {

	double offensive(Move move, Pokemon pokemon);
	double defensive(Move move, Pokemon pokemon);

}
