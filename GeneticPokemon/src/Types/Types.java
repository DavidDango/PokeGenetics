package Types;
public interface Types {
	String strType();
	
	boolean isFighting();
	boolean isGhost();
	boolean isGround();
	boolean isNormal();
	
	double stab(Types type);
	double stabBug(Bug bug);
	double stabDark(Dark dark);
	double stabDragon(Dragon dragon);
	double stabElectric(Electric electric);
	double stabFairy(Fairy fairy);
	double stabFighting(Fighting fighting);
	double stabFire(Fire fire);
	double stabFlying(Flying flying);
	double stabGhost(Ghost ghost);
	double stabGrass(Grass grass);
	double stabGround(Ground ground);
	double stabIce(Ice ice);
	double stabNormal(Normal normal);
	double stabPoison(Poison poison);
	double stabPsychic(Psychic psychic);
	double stabRock(Rock rock);
	double stabSteel(Steel steel);
	double stabWater(Water water);
	
	double effectiveness(Types type);
	double effectivenessBug(Bug type);
	double effectivenessDark(Dark type);
	double effectivenessDragon(Dragon type);
	double effectivenessElectric(Electric type);
	double effectivenessFairy(Fairy type);
	double effectivenessFighting(Fighting type);
	double effectivenessFire(Fire type);
	double effectivenessFlying(Flying type);
	double effectivenessGhost(Ghost type);
	double effectivenessGrass(Grass type);
	double effectivenessGround(Ground type);
	double effectivenessIce(Ice type);
	double effectivenessNormal(Normal type);
	double effectivenessPoison(Poison type);
	double effectivenessPsychic(Psychic type);
	double effectivenessRock(Rock type);
	double effectivenessSteel(Steel type);
	double effectivenessWater(Water type);
}
