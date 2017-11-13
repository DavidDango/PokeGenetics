package Moves;

import Pokes.Pokemon;
import Types.Types;

public interface Move {
	int getPower();
	Types type();
	double effectiveAttack(Pokemon activeA);
	double effectiveDefense(Pokemon activeB);
	Status getStatus();
	int getPriority();
	boolean isFlinch();
	boolean requiresFirstTurn();
}
