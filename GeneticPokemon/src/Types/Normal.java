package Types;

public class Normal extends AbstractType {
	
	@Override
	public double stab(Types type) {
		return type.stabNormal(this);
	}
	
	@Override
	public double stabBug(Bug type) {
		return 1;
	}

	@Override
	public double stabDark(Dark type) {
		return 1;
	}

	@Override
	public double stabDragon(Dragon type) {
		return 1;
	}

	@Override
	public double stabElectric(Electric type) {
		return 1;
	}

	@Override
	public double stabFairy(Fairy type) {
		return 1;
	}

	@Override
	public double stabFighting(Fighting type) {
		return 1;
	}

	@Override
	public double stabFire(Fire type) {
		return 1;
	}

	@Override
	public double stabFlying(Flying type) {
		return 1;
	}

	@Override
	public double stabGhost(Ghost type) {
		return 1;
	}

	@Override
	public double stabGrass(Grass type) {
		return 1;
	}

	@Override
	public double stabGround(Ground type) {
		return 1;
	}

	@Override
	public double stabIce(Ice type) {
		return 1;
	}

	@Override
	public double stabNormal(Normal type) {
		return 1.5;
	}

	@Override
	public double stabPoison(Poison type) {
		return 1;
	}

	@Override
	public double stabPsychic(Psychic type) {
		return 1;
	}

	@Override
	public double stabRock(Rock type) {
		return 1;
	}

	@Override
	public double stabSteel(Steel type) {
		return 1;
	}

	@Override
	public double stabWater(Water type) {
		return 1;
	}
	
	@Override
	public double effectiveness(Types type) {
		return type.effectivenessNormal(this);
	}

	@Override
	public double effectivenessBug(Bug type) {
		return 1;
	}

	@Override
	public double effectivenessDark(Dark type) {
		return 1;
	}

	@Override
	public double effectivenessDragon(Dragon type) {
		return 1;
	}

	@Override
	public double effectivenessElectric(Electric type) {
		return 1;
	}

	@Override
	public double effectivenessFairy(Fairy type) {
		return 1;
	}

	@Override
	public double effectivenessFighting(Fighting type) {
		return 2;
	}

	@Override
	public double effectivenessFire(Fire type) {
		return 1;
	}

	@Override
	public double effectivenessFlying(Flying type) {
		return 1;
	}

	@Override
	public double effectivenessGhost(Ghost type) {
		return 0;
	}

	@Override
	public double effectivenessGrass(Grass type) {
		return 1;
	}

	@Override
	public double effectivenessGround(Ground type) {
		return 1;
	}

	@Override
	public double effectivenessIce(Ice type) {
		return 1;
	}

	@Override
	public double effectivenessNormal(Normal type) {
		return 1;
	}

	@Override
	public double effectivenessPoison(Poison type) {
		return 1;
	}

	@Override
	public double effectivenessPsychic(Psychic type) {
		return 1;
	}

	@Override
	public double effectivenessRock(Rock type) {
		return 1;
	}

	@Override
	public double effectivenessSteel(Steel type) {
		return 1;
	}

	@Override
	public double effectivenessWater(Water type) {
		return 1;
	}
	
	public boolean isNormal() {
		return true;
	}
	
	public String strType() {
		return "Normal";
	}
}
