package Moves;

public class NullStatus implements Status {
	
	@Override
	public int getHealth() {
		return 0;
	}
	@Override
	public int getModifier(int i) {
		return 0;
	}

	@Override
	public boolean isFlinch() {
		return false;
	}

	@Override
	public boolean requiresFirstTurn() {
		return false;
	}

}
