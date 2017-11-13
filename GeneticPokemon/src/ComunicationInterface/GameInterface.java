package ComunicationInterface;

import java.util.stream.IntStream;

import Pokes.*;

public class GameInterface {
	private PokemonGame arena;
	private Pokemon[] pokemonSetA;
	private Pokemon[] pokemonSetB;
	private int gameStatus;
	
	public GameInterface(Pokemon[] playerAPokemon, Pokemon[] playerBPokemon) {
		pokemonSetA = playerAPokemon.clone();
		pokemonSetB = playerBPokemon.clone();
		this.reset();
	}

	public void reset() {
		arena = new PokemonGame();
		Player playerA = new Player();
		Player playerB = new Player();
		for(Pokemon p: pokemonSetA) {
			playerA.addPokemon(p.clonePokemon());
		}
		for(Pokemon p: pokemonSetB) {
			playerB.addPokemon(p.clonePokemon());
		}
		arena.players(playerA, playerB);
		gameStatus = 0;
	}
	
	public boolean victoriousA() {
		for(int i: arena.avaibleB()) {
			if(i == 1) {
				return false;
			}
		}
		return true;
	}
	
	public boolean victoriousB() {
		for(int i: arena.avaibleA()) {
			if(i == 1) {
				return false;
			}
		}
		return true;
	}
	
	public boolean gameOver() {
		return victoriousA() || victoriousB();
	}
	
	public int status() {
		return gameStatus;
	}
	
	public boolean moreThanOneA() {
		if(IntStream.of(arena.avaibleA()).sum() > 1) {
			return true;
		}
		return false;
	}
	
	public boolean moreThanOneB() {
		if(IntStream.of(arena.avaibleB()).sum() > 1) {
			return true;
		}
		return false;
	}
	
	public PokemonGame arena() {
		return arena;
	}

	public double getHealthA() {
		double a = arena.PokemonA().getHealth();
		double b = arena.PokemonA().getRemainingHealth();
		return b/a;
	}

	public double getHealthB() {
		double a = arena.PokemonB().getHealth();
		double b = arena.PokemonB().getRemainingHealth();
		return b/a;
	}

	public int getTurnA() {
		return arena.getTurnA();
	}

	public int getTurnB() {
		return arena.getTurnB();
	}

	public int getPokemonNumberA() {
		return arena.fightingPokemonA();
	}

	public int getPokemonNumberB() {
		return arena.fightingPokemonB();
	}

	public boolean oneA() {
		if(IntStream.of(arena.avaibleA()).sum() == 1) {
			return true;
		}
		return false;
	}
}
