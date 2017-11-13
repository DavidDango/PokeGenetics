package Pokes;

import Moves.Move;
import Types.Fairy;
import Types.Normal;

public class Pixilate extends AbstractAbility {

	public double offensive(Move move, Pokemon pokemon) {
		return effectiveness(move.type(), pokemon.type1())*effectiveness(move.type(), pokemon.type2());
	}
	
	public double effectiveness(Normal normal, Pokemon pokemon) {
		Fairy temp = new Fairy();
		return temp.effectiveness(pokemon.type1())*temp.effectiveness(pokemon.type2())*1.8;
	}
}