package Pokes;

import java.util.ArrayList;

public class Player {
	private ArrayList<Pokemon> pokemons;
	private int avaible;
	
	public Player() {
		pokemons = new ArrayList<Pokemon>();
		avaible = 0;
	}

	public void addPokemon(Pokemon pokemon) {
		if(pokemon.canFight()) {
			pokemons.add(pokemon);
			avaible++;
		}
	}

	public int avaiblePokemon() {
		return avaible;
	}

	public Pokemon getPokemon(int i) {
		return pokemons.get(i);
	}
	
	public void lost1() {
		avaible--;
	}
}
