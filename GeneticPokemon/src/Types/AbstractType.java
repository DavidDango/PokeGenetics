package Types;

public abstract class AbstractType implements Types {
	
	public abstract double stab(Types type);
	public abstract double stabBug(Bug type);
	public abstract double stabDark(Dark type);
	public abstract double stabDragon(Dragon type);
	public abstract double stabElectric(Electric type);
	public abstract double stabFairy(Fairy type);
	public abstract double stabFighting(Fighting type);
	public abstract double stabFire(Fire type);
	public abstract double stabFlying(Flying type);
	public abstract double stabGhost(Ghost type);
	public abstract double stabGrass(Grass type);
	public abstract double stabGround(Ground type);
	public abstract double stabIce(Ice type);
	public abstract double stabNormal(Normal type);
	public abstract double stabPoison(Poison type);
	public abstract double stabPsychic(Psychic type);
	public abstract double stabRock(Rock type);
	public abstract double stabSteel(Steel type);
	public abstract double stabWater(Water type);
	
	public abstract double effectiveness(Types type);
	public abstract double effectivenessBug(Bug type);
	public abstract double effectivenessDark(Dark type);
	public abstract double effectivenessDragon(Dragon type);
	public abstract double effectivenessElectric(Electric type);
	public abstract double effectivenessFairy(Fairy type);
	public abstract double effectivenessFighting(Fighting type);
	public abstract double effectivenessFire(Fire type);
	public abstract double effectivenessFlying(Flying type);
	public abstract double effectivenessGhost(Ghost type);
	public abstract double effectivenessGrass(Grass type);
	public abstract double effectivenessGround(Ground type);
	public abstract double effectivenessIce(Ice type);
	public abstract double effectivenessNormal(Normal type);
	public abstract double effectivenessPoison(Poison type);
	public abstract double effectivenessPsychic(Psychic type);
	public abstract double effectivenessRock(Rock type);
	public abstract double effectivenessSteel(Steel type);
	public abstract double effectivenessWater(Water type);
	
	public boolean isFighting() {
		return false;
	}
	
	public boolean isGhost() {
		return false;
	}
	
	public boolean isGround() {
		return false;
	}
	
	public boolean isNormal() {
		return false;
	}
}
