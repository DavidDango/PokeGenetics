package Testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Moves.*;
import Pokes.*;
import Types.*;

public class PokemonTest {
	PokemonGame arena;
	Player playerA;
	Player playerB;
	Pokemon dragonite;
	Pokemon lopunny;
	Pokemon blissey;
	Pokemon gardevoir;
	Pokemon gengarA;
	Pokemon gengarB;
	Move dragonDance;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//runs once
	}

	@Before
	public void setUp() throws Exception {
		//runs multiple times
		playerA = new Player();
		playerB = new Player();
		arena = new PokemonGame();
		dragonite = new Pokemon(167, 186, 115, 108, 120, 145, new Dragon(), new Flying());
		lopunny = new Pokemon(141, 188, 114, 66, 116, 205, new Normal(), new Fighting());
		blissey = new Pokemon(331, 13, 68, 95, 187, 75, new Normal(), new NullType());
		gardevoir = new Pokemon(145, 81, 86, 214, 155, 167, new Psychic(), new Fairy());
		gengarA = new Pokemon(136, 63, 80, 182, 95, 178, new Ghost(), new Poison());
		Status flinch = new StatusMove(0, 0, 0, 0, 0, 0, true, true);
		dragonDance = new PhysicalDamage(new NullType(), 0, 0, new StatusMove(0, 1, 0, 0, 0, 1, false, false));
		dragonite.addMove(dragonDance);
		dragonite.addMove(new PhysicalDamage(new Dragon(), 120, 0, new NullStatus()));
		dragonite.addMove(new PhysicalDamage(new Normal(), 80, 2, new NullStatus()));
		dragonite.addMove(new PhysicalDamage(new Ground(), 100, 0, new NullStatus()));
		lopunny.addMove(new PhysicalDamage(new Fighting(), 130, 0, new NullStatus()));
		lopunny.addMove(new PhysicalDamage(new Normal(), 102, 0, new NullStatus()));
		lopunny.addMove(new PhysicalDamage(new Normal(), 40, 3, flinch));
		lopunny.addMove(new PhysicalDamage(new Ice(), 75, 0, new NullStatus()));
		blissey.addMove(new StatusMove(0, 0, 0, 1, 1, 0, false, false));
		blissey.addMove(new StatusMove(4, 0, 0, 0, 0, 0, false, false));
		blissey.addMove(new SpecialDamage(new Electric(), 90, 0, new NullStatus()));
		blissey.addMove(new SpecialDamage(new Ice(), 90, 0, new NullStatus()));
		gardevoir.addMove(new SpecialDamage(new Fairy(), 108, 0, new NullStatus()));
		gardevoir.addMove(new SpecialDamage(new Psychic(), 90, 0, new NullStatus()));
		gardevoir.addMove(new SpecialDamage(new Fighting(), 120, 0, new NullStatus()));
		gardevoir.addMove(new StatusMove(0, 0, 0, 1, 1, 0, false, false));
		gengarA.addMove(new SpecialDamage(new Ghost(), 80, 0, new NullStatus()));
		gengarA.addMove(new SpecialDamage(new Poison(), 95, 0, new NullStatus()));
		gengarA.addMove(new SpecialDamage(new Fighting(), 120, 0, new NullStatus()));
		gengarA.addMove(new PhysicalDamage(new Normal(), 0, 4, flinch));
		dragonite.addAbility(new Multiscale());
		lopunny.addAbility(new Scrappy());
		gardevoir.addAbility(new Pixilate());
		gengarA.addAbility(new Levitate());
		gengarB = gengarA.clonePokemon();
		playerA.addPokemon(dragonite);
		playerA.addPokemon(blissey);
		playerA.addPokemon(gengarA);
		playerB.addPokemon(lopunny);
		playerB.addPokemon(gardevoir);
		playerB.addPokemon(gengarB);
		arena.players(playerA, playerB);
	}

	@Test
	public void BasicPokemonTest() {
		assertEquals(playerA.avaiblePokemon(), 3);
		assertEquals(dragonite, playerA.getPokemon(0));
		assertEquals(blissey, playerA.getPokemon(1));
		assertTrue(dragonite.canFight());
		assertEquals(dragonDance, dragonite.getMove(0));
		assertEquals(playerA.avaiblePokemon(), 3);
		dragonite.receiveDamage(dragonite.getHealth());
		assertFalse(dragonite.canFight());
		assertEquals(playerA.avaiblePokemon(), 3);
		assertEquals(dragonite.use(0), dragonDance);
	}
	
	@Test
	public void PokemonFightTest() {
		assertEquals(arena.PokemonA(), dragonite);
		assertEquals(arena.PokemonB(), lopunny);
		arena.playerASwitch(1);
		assertEquals(arena.PokemonA(), blissey);
		arena.BtoA(0);
		assertEquals(blissey.getRemainingHealth(), 0);
		assertEquals(playerA.avaiblePokemon(), 2);
		assertArrayEquals(arena.avaibleA(), new int[] {1, 0, 1});
		arena.playerASwitch(0);
		assertEquals((int) arena.PokemonA().effectiveAttack(), 186);
		arena.AtoB(0);
		assertEquals((int) arena.PokemonA().effectiveAttack(), 279);
		arena.AtoB(0);
		assertEquals((int) arena.PokemonA().effectiveAttack(), 372);
		arena.playerASwitch(2);
		arena.playerASwitch(0);
		assertEquals((int) arena.PokemonA().effectiveAttack(), 186);
		arena.playerBSwitch(2);
		assertEquals(arena.PokemonA(), dragonite);
		assertEquals(arena.PokemonB(), gengarB);
		arena.AtoB(2);
		assertEquals(arena.PokemonB().getHealth(), arena.PokemonB().getRemainingHealth());
		arena.AtoB(3);
		assertEquals(arena.PokemonB().getHealth(), arena.PokemonB().getRemainingHealth());
		arena.AtoB(1);
		assertEquals(arena.PokemonB().getRemainingHealth(), 0);
		arena.playerASwitch(2);
		arena.playerBSwitch(0);
		arena.BtoA(1);
		assertEquals(arena.PokemonA().getRemainingHealth(), 0);
	}
	
	@Test
	public void PriorityTest(){
		assertEquals(arena.PokemonA().getHealth(), arena.PokemonA().getRemainingHealth());
		assertEquals(arena.PokemonB().getHealth(), arena.PokemonB().getRemainingHealth());
		assertFalse(arena.priority(0,0));
		assertTrue(arena.priority(2,0));
		arena.AtoB(0);
		assertTrue(arena.priority(0,0));
		assertTrue(arena.priority(2,0));
		assertFalse(arena.priority(0,2));
		arena.playerASwitch(2);
		arena.playerASwitch(0);
		assertFalse(arena.priority(2,2));
		
	}
	
	@Test
	public void PokemonBattleTest() {
		arena.battle(2, 2);
		int tempHealth1 = arena.PokemonA().getRemainingHealth();
		assertEquals(arena.PokemonB().getHealth(), arena.PokemonB().getRemainingHealth());
		assertTrue(tempHealth1 < arena.PokemonA().getHealth());
		arena.battle(2, 2);
		int tempHealth2 = arena.PokemonB().getRemainingHealth();
		assertEquals(tempHealth1, arena.PokemonA().getRemainingHealth());
		assertTrue(tempHealth2 < arena.PokemonB().getHealth());
		arena.playerBSwitch(1);
		arena.playerBSwitch(0);
		arena.battle(2, 2);
		assertTrue(tempHealth1 > arena.PokemonA().getRemainingHealth());
		assertEquals(tempHealth2, arena.PokemonB().getRemainingHealth());
	}
}
