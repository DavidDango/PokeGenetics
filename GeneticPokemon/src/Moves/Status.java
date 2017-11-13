package Moves;

public interface Status {

	int getHealth();
	int getModifier(int i);
	boolean isFlinch();
	boolean requiresFirstTurn();
}
