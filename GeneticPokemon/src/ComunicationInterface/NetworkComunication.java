package ComunicationInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import Moves.*;
import Network.*;
import Pokes.*;
import Types.*;
import Utils.eUtils;

public class NetworkComunication {
//	private static GNNetwork changeChoiceNetwork;
	private static GNNetwork changePokemonNetwork;
	private static GNNetwork[] pokemonModeNetworks;
	private static GNNetwork[] pokemonAttackNetworks;
	private static GNNetwork[] pokemonBuffNetworks;
	private static GameInterface arenaInterface;
	
	public static void main(String[] args) throws IOException {
		System.out.println("starting");
		Random generator = new Random(549);
		setUpInterface();
		setUpNetworks(generator);
		for(int i = 0; i < 1000; i++) {
			practice1v1(0, generator);
			breedMoveRelated(0, generator);
		}
		System.out.println("Dragonite trained!");
		for(int i = 0; i < 1000; i++) {
			practice1v1(1, generator);
			breedMoveRelated(1, generator);
		}
		System.out.println("Lopunny trained!");
		for(int i = 0; i < 1000; i++) {
			practice1v1(2, generator);
			breedMoveRelated(2, generator);
		}
		System.out.println("Blissey trained!");
		for(int i = 0; i < 1000; i++) {
			practice1v1(3, generator);
			breedMoveRelated(3, generator);
		}
		System.out.println("Gardevoir trained!");
		for(int i = 0; i < 1000; i++) {
			practice1v1(4, generator);
			breedMoveRelated(4, generator);
		}
		System.out.println("Gengar trained!");
		for(int i = 0; i < 1000; i++) {
			practice1v1(5, generator);
			breedMoveRelated(5, generator);
		}
		System.out.println("Weavile trained!");
		for(int i = 0; i < 1000; i++) {
			practice1v1(6, generator);
			breedMoveRelated(6, generator);
		}
		System.out.println("Diancie trained!");
		
		for(int c = 0; c < 1000; c++) {
			for(int x = 0; x < 7; x++) {
				trainAgainstTeam(x, generator);
				breedMoveRelated(x, generator);
			}
		}
		System.out.println("Pokemon trained against team");
		
		for(int i = 0; i < 1000; i++) {
			trainRandomSwitch(generator);
			breedChangeRelated(generator);
		}
		System.out.println("Best switches trained");
//		for(int i = 0; i < 1000000; i++) {
//			trainSwitchChoice(generator);
//			
//		}
//		System.out.println("Switching situations trained");
		System.out.println("");
		System.out.println("----------------------------");
		System.out.println("");
		arenaInterface.reset();
		arenaInterface.arena().playerBSwitch(5);
		while (!arenaInterface.gameOver()) {
			fightAgainstPlayer();
		}
		if(arenaInterface.victoriousA()) {
			System.out.println("You won!");
			return;
		}
		if(arenaInterface.victoriousB()) {
			System.out.println("You Lost!");
			return;
		}
	}
	
	private static void breedChangeRelated(Random generator) {
		changePokemonNetwork.breed(generator, 0.005);
	}

	private static void breedMoveRelated(int i, Random generator) {
		pokemonModeNetworks[i].breed(generator, 0.005);
		pokemonAttackNetworks[i].breed(generator, 0.005);
		pokemonBuffNetworks[i].breed(generator, 0.005);
	}

	private static void fightAgainstPlayer() throws IOException {
		System.out.println("Player's Pokemon is: " + pokemonByList(arenaInterface.getPokemonNumberA()));
		System.out.println(pokemonByList(arenaInterface.getPokemonNumberA()) + "'s health is: " +  arenaInterface.arena().PokemonA().getHealth() + " / " +  arenaInterface.arena().PokemonA().getRemainingHealth());
		System.out.println("Enemy's Pokemon is: " + pokemonByList(arenaInterface.getPokemonNumberB()));
		System.out.println(pokemonByList(arenaInterface.getPokemonNumberB()) + "'s health is: " +  arenaInterface.arena().PokemonB().getHealth() + " / " +  arenaInterface.arena().PokemonB().getRemainingHealth());
		if(arenaInterface.moreThanOneA()) {
			System.out.println("Press 0 to fight and anything else to switch out");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String input = bufferedReader.readLine();
			if(input.equals("0")) {
				playerFights();
			}
			else {
				playerSwitches();
			}
		}
		else if (arenaInterface.oneA()){
			playerFights();
		}
		
		if(arenaInterface.gameOver()) {
			return;
		}
		
		if(!arenaInterface.arena().PokemonA().canFight() ) {
			playerSwitch();
		}
		if(!arenaInterface.arena().PokemonB().canFight()) {
			eUtils e = new eUtils();
			int[] temp = arenaInterface.arena().avaibleB().clone();
			temp[arenaInterface.arena().fightingPokemonB()] = 0;
			int switchIn = interpretSwitchB(temp, e.fighting(arenaInterface.arena().fightingPokemonB()));
			arenaInterface.arena().playerBSwitch(switchIn);
			System.out.println("Enemy's pokemon fainted, " + pokemonByList(switchIn) + " comes in");
		}
	}

	private static void playerSwitch() throws IOException {
		System.out.println("Your pokemon fainted, please select a new one to switch in:");
		getPokemon();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String input = bufferedReader.readLine();
		if(input.equals("0")) {
			arenaInterface.arena().playerASwitch(0);
			System.out.println(pokemonByList(0) + " comes in");
		}
		if(input.equals("1")) {
			arenaInterface.arena().playerASwitch(1);
			System.out.println(pokemonByList(1) + " comes in");
		}
		if(input.equals("2")) {
			arenaInterface.arena().playerASwitch(2);
			System.out.println(pokemonByList(2) + " comes in");
		}
		if(input.equals("3")) {
			arenaInterface.arena().playerASwitch(3);
			System.out.println(pokemonByList(3) + " comes in");
		}
		if(input.equals("4")) {
			arenaInterface.arena().playerASwitch(4);
			System.out.println(pokemonByList(4) + " comes in");
		}
		if(input.equals("5")) {
			arenaInterface.arena().playerASwitch(5);
			System.out.println(pokemonByList(5) + " comes in");
		}
		if(input.equals("6")) {
			arenaInterface.arena().playerASwitch(6);
			System.out.println(pokemonByList(6) + " comes in");
		}
	}

	private static void playerSwitches() throws IOException {
		System.out.println("Player's pokemon are:");
		getPokemon();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String input = bufferedReader.readLine();
//		if(networkSwitches()) {
//			if(input.equals("0")) {
//				arenaInterface.arena().playerASwitch(0);
//				System.out.println(pokemonByList(0) + " comes in");
//			}
//			if(input.equals("1")) {
//				arenaInterface.arena().playerASwitch(1);
//				System.out.println(pokemonByList(1) + " comes in");
//				arenaInterface.arena().playerASwitch(2);
//				System.out.println(pokemonByList(2) + " comes in");
//			}
//			if(input.equals("3")) {
//				arenaInterface.arena().playerASwitch(3);
//				System.out.println(pokemonByList(3) + " comes in");
//			}
//			if(input.equals("4")) {
//				arenaInterface.arena().playerASwitch(4);
//				System.out.println(pokemonByList(4) + " comes in");
//			}
//			if(input.equals("5")) {
//				arenaInterface.arena().playerASwitch(5);
//				System.out.println(pokemonByList(5) + " comes in");
//			}
//			if(input.equals("6")) {
//				arenaInterface.arena().playerASwitch(6);
//				System.out.println(pokemonByList(6) + " comes in");
//			}
//		}
//		else {
			if(input.equals("0")) {
				arenaInterface.arena().playerASwitch(0);
				System.out.println(pokemonByList(0) + " comes in");
				System.out.println("Oponent uses " + nameMove(arenaInterface.getPokemonNumberB(), getEnemyMove()));
				arenaInterface.arena().BtoA(getEnemyMove());
			}
			else if(input.equals("1")) {
				arenaInterface.arena().playerASwitch(1);
				System.out.println(pokemonByList(1) + " comes in");
				System.out.println("Oponent uses " + nameMove(arenaInterface.getPokemonNumberB(), getEnemyMove()));
				arenaInterface.arena().BtoA(getEnemyMove());
			}
			else if(input.equals("2")) {
				arenaInterface.arena().playerASwitch(2);
				System.out.println(pokemonByList(2) + " comes in");
				System.out.println("Oponent uses " + nameMove(arenaInterface.getPokemonNumberB(), getEnemyMove()));
				arenaInterface.arena().BtoA(getEnemyMove());
			}
			if(input.equals("3")) {
				arenaInterface.arena().playerASwitch(3);
				System.out.println(pokemonByList(3) + " comes in");
				System.out.println("Oponent uses " + nameMove(arenaInterface.getPokemonNumberB(), getEnemyMove()));
				arenaInterface.arena().BtoA(getEnemyMove());
			}
			else if(input.equals("4")) {
				arenaInterface.arena().playerASwitch(4);
				System.out.println(pokemonByList(4) + " comes in");
				System.out.println("Oponent uses " + nameMove(arenaInterface.getPokemonNumberB(), getEnemyMove()));
				arenaInterface.arena().BtoA(getEnemyMove());
			}
			else if(input.equals("5")) {
				arenaInterface.arena().playerASwitch(5);
				System.out.println(pokemonByList(5) + " comes in");
				System.out.println("Oponent uses " + nameMove(arenaInterface.getPokemonNumberB(), getEnemyMove()));
				arenaInterface.arena().BtoA(getEnemyMove());
			}
			else if(input.equals("6")) {
				arenaInterface.arena().playerASwitch(6);
				System.out.println(pokemonByList(6) + " comes in");
				System.out.println("Oponent uses " + nameMove(arenaInterface.getPokemonNumberB(), getEnemyMove()));
				arenaInterface.arena().BtoA(getEnemyMove());
//			}
		}
	}

	private static void getPokemon() {
		for(int i = 0; i < 7; i++) {
			if(arenaInterface.arena().avaibleA()[i] > 0) {
				System.out.println(i + ": " + pokemonByList(i));
			}
		}
	}

	private static void playerFights() throws IOException {
		System.out.println(pokemonByList(arenaInterface.getPokemonNumberA()) + "'s moves are:");
		getMoves();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String input = bufferedReader.readLine();
//		if(networkSwitches()) {
//			if(input.equals("0")) {
//				arenaInterface.arena().AtoB(0);
//			}
//			if(input.equals("1")) {
//				arenaInterface.arena().AtoB(1);
//			}
//			if(input.equals("2")) {
//				arenaInterface.arena().AtoB(2);
//			}
//			if(input.equals("3")) {
//				arenaInterface.arena().AtoB(3);
//			}
//		}
//		else {
			if(input.equals("0")) {
				System.out.println("Oponent uses " + nameMove(arenaInterface.getPokemonNumberB(), getEnemyMove()));
				arenaInterface.arena().battle(0, getEnemyMove());
			}
			else if(input.equals("1")) {
				System.out.println("Oponent uses " + nameMove(arenaInterface.getPokemonNumberB(), getEnemyMove()));
				arenaInterface.arena().battle(1, getEnemyMove());
			}
			else if(input.equals("2")) {
				System.out.println("Oponent uses " + nameMove(arenaInterface.getPokemonNumberB(), getEnemyMove()));
				arenaInterface.arena().battle(2, getEnemyMove());
			}
			else if(input.equals("3")) {
				System.out.println("Oponent uses " + nameMove(arenaInterface.getPokemonNumberB(), getEnemyMove()));
				arenaInterface.arena().battle(3, getEnemyMove());
//			}
		}
	}
	
	private static int getEnemyMove() {
		eUtils e = new eUtils();
		double[] environment = e.join(e.fighting(arenaInterface.getPokemonNumberA()), arenaInterface.getHealthB(), arenaInterface.getHealthA(), arenaInterface.getTurnB());
		double[] temp = pokemonModeNetworks[arenaInterface.getPokemonNumberB()].feed(environment, 0);
		if(temp[0] < 0.5) {
			return reverseInterpretBuff(0, pokemonBuffNetworks[arenaInterface.getPokemonNumberB()].feed(environment, 0)[0]);
		}
		else {
			return reverseInterpretAttack(0, pokemonAttackNetworks[arenaInterface.getPokemonNumberB()].feed(environment, 0)[0]);
		}
	}

//	private static boolean networkSwitches() {
//		eUtils e = new eUtils();
//		int[] temp = arenaInterface.arena().avaibleB().clone();
//		temp[arenaInterface.arena().fightingPokemonB()] = 0;
//		if(changeChoiceNetwork.feed(e.join(e.fighting(arenaInterface.arena().fightingPokemonB()), temp, e.fighting(arenaInterface.arena().fightingPokemonA()), arenaInterface.getHealthB(), arenaInterface.getHealthA()))[0] < 0.5) {
//			int switchIn = interpretSwitchB(temp, e.fighting(arenaInterface.arena().fightingPokemonB()));
//			System.out.println("Oponent switches " + pokemonByList(arenaInterface.arena().fightingPokemonB()) + " for " + pokemonByList(switchIn));
//			arenaInterface.arena().playerBSwitch(switchIn);
//			return true;
//		}
//		return false;
//	}

	private static void getMoves() {
		System.out.println("0: " + nameMove(arenaInterface.getPokemonNumberA(), 0));
		System.out.println("1: " + nameMove(arenaInterface.getPokemonNumberA(), 1));
		System.out.println("2: " + nameMove(arenaInterface.getPokemonNumberA(), 2));
		System.out.println("3: " + nameMove(arenaInterface.getPokemonNumberA(), 3));
	}

	private static String nameMove(int pokemonNumber, int i) {
		if(pokemonNumber == 0) {
			if(i == 0) {
				return "Dragon dance";
			}
			if(i == 1) {
				return "Outrage";
			}
			if(i == 2) {
				return "Earthquake";
			}
			if(i == 3) {
				return "Extreme speed";
			}
		}
		if(pokemonNumber == 1) {
			if(i == 0) {
				return "High jump kick";
			}
			if(i == 1) {
				return "Return";
			}
			if(i == 2) {
				return "Fake out";
			}
			if(i == 3) {
				return "Ice punch";
			}
		}
		if(pokemonNumber == 2) {
			if(i == 0) {
				return "Calm mind";
			}
			if(i == 1) {
				return "Soft boiled";
			}
			if(i == 2) {
				return "Thunderbolt";
			}
			if(i == 3) {
				return "Ice beam";
			}
		}
		if(pokemonNumber == 3) {
			if(i == 0) {
				return "Hyper voice";
			}
			if(i == 1) {
				return "Psychic";
			}
			if(i == 2) {
				return "Focus blast";
			}
			if(i == 3) {
				return "Calm mind";
			}
		}
		if(pokemonNumber == 4) {
			if(i == 0) {
				return "Shadow ball";
			}
			if(i == 1) {
				return "Sludge wave";
			}
			if(i == 2) {
				return "Focus blast";
			}
			if(i == 3) {
				return "Protect";
			}
		}
		if(pokemonNumber == 5) {
			if(i == 0) {
				return "Iclicle crash";
			}
			if(i == 1) {
				return "Knock off";
			}
			if(i == 2) {
				return "Ice shard";
			}
			if(i == 3) {
				return "Poison jab";
			}
		}
		if(pokemonNumber == 6) {
			if(i == 0) {
				return "Rock polish";
			}
			if(i == 1) {
				return "Dazzling gleam";
			}
			if(i == 2) {
				return "Diamond storm";
			}
			if(i == 3) {
				return "Earth Power";
			}
		}
		return null;
	}

	private static String pokemonByList(int pokemonNumber) {
		if(pokemonNumber == 0) {
			return "Dragonite";
		}
		if(pokemonNumber == 1) {
			return "Lopunny";
		}
		if(pokemonNumber == 2) {
			return "Blissey";
		}
		if(pokemonNumber == 3) {
			return "Gardevoir";
		}
		if(pokemonNumber == 4) {
			return "Gengar";
		}
		if(pokemonNumber == 5) {
			return "Weavile";
		}
		return "Diancie";
	}

//	private static void trainSwitchChoice(Random generator) {
//		eUtils e = new eUtils(generator);
//		boolean flag = true;
//		while(flag) {
//			arenaInterface.reset();
//			for(int i = 0; i < 7; i++) {
//				if(generator.nextInt(2) > 0) {
//					arenaInterface.arena().killA(i);
//				}
//			}
//			if(arenaInterface.moreThanOneA()) {
//				flag = false;
//			}
//		}
//		int fighting = generator.nextInt(7);
//		int[] enemy = e.fighting(fighting);
//		int select = 0;
//		while(!flag) {
//			select = generator.nextInt(7);
//			if(arenaInterface.arena().avaibleA()[select] == 1) {
//				flag = true;
//			}
//		}
//		int[] selected = e.fighting(select);
//		ArrayList<double[]> environment = new ArrayList<double[]>();
//		ArrayList<double[]> expected = new ArrayList<double[]>();
//		while(arenaInterface.arena().PokemonA().canFight() && arenaInterface.arena().PokemonB().canFight()) {
//			int[] temp = arenaInterface.arena().avaibleA().clone();
//			temp[select] = 0;
//			environment.add(e.join(selected, temp, enemy, arenaInterface.getHealthA(), arenaInterface.getHealthB()));
//			double[] temp2 = new double[] {generator.nextInt(2)};
//			expected.add(temp2);
//			if(temp2[0] < 1){
//				select = interpretSwitchA(temp, enemy);
//				selected = e.fighting(select);
//				arenaInterface.arena().playerASwitch(select);
//				arenaInterface.arena().BtoA(generator.nextInt(4));
//				arenaInterface.arena().manuallyAdvanceTurnB();
//			}
//			else {
//				double[] newEnvironment = e.join(enemy, arenaInterface.getHealthA(), arenaInterface.getHealthB(), arenaInterface.getTurnA());
//				if(pokemonModeNetworks[select].feed(newEnvironment)[0] < 0.5) {
//					arenaInterface.arena().battle(reverseInterpretBuff(select, pokemonBuffNetworks[select].feed(newEnvironment)[0]), generator.nextInt(4));
//				}
//				else {
//					arenaInterface.arena().battle(reverseInterpretAttack(select, pokemonBuffNetworks[select].feed(newEnvironment)[0]), generator.nextInt(4));
//				}
//			}
//		}
//		if(arenaInterface.arena().PokemonA().canFight()) {
//			for(int i = 0; i < environment.size(); i++) {
//				ChoiceNetwork.train(environment.get(i), expected.get(i));
//			}
//		}
//	}

	private static int interpretSwitchA(int[] available, int[] fighting, int k) {
		eUtils e = new eUtils();
		double[] response = changePokemonNetwork.feed(e.join(available, fighting, arenaInterface.getHealthB()), k);
		int temp = 10;
		double otherTemp = 10;
		for(int i = 0; i < 7; i++) {
			if(available[i] > 0 && Math.abs(otherTemp*i - response[0]) < Math.abs(otherTemp*temp - response[0])) {
				temp = i;
			}
		}
		return temp;
	}
	
	private static int interpretSwitchB(int[] available, int[] fighting) {
		eUtils e = new eUtils();
		double[] response = changePokemonNetwork.feed(e.join(available, fighting, arenaInterface.getHealthA()), 0);
		int temp = 10;
		double otherTemp = 1/(double) 6;
		for(int i = 0; i < 7; i++) {
			if(available[i] > 0 && Math.abs(otherTemp*i - response[0]) < Math.abs(otherTemp*temp - response[0])) {
				temp = i;
			}
		}
		return temp;
	}
	
	private static void trainRandomSwitch(Random generator) {
		eUtils e = new eUtils(generator);
		for(int k = 0; k < 100; k++) {
			double fitness = 0;
			
			boolean flag = true;
			while(flag) {
				arenaInterface.reset();
				for(int i = 0; i < 7; i++) {
					if(generator.nextInt(2) > 0) {
						arenaInterface.arena().killA(i);
					}
				}
				if(!arenaInterface.gameOver()) {
					flag = false;
				}
			}
			flag = true;
			int[] fighting = new int[] {1, 0, 0, 0, 0, 0, 0};
			int select = 0;
			select = interpretSwitchA(arenaInterface.arena().avaibleA().clone(), fighting, k);
			arenaInterface.arena().playerASwitch(select);
			arenaInterface.arena().playerBSwitch(0);
			if(autoFight(generator, select, 0, k, e)) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			
			while(flag) {
				arenaInterface.reset();
				for(int i = 0; i < 7; i++) {
					if(generator.nextInt(2) > 0) {
						arenaInterface.arena().killA(i);
					}
				}
				if(!arenaInterface.gameOver()) {
					flag = false;
				}
			}
			flag = true;
			fighting = new int[] {0, 1, 0, 0, 0, 0, 0};
			select = interpretSwitchA(arenaInterface.arena().avaibleA().clone(), fighting, k);
			arenaInterface.arena().playerASwitch(select);
			arenaInterface.arena().playerBSwitch(1);
			if(autoFight(generator, select, 1, k, e)) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			
			while(flag) {
				arenaInterface.reset();
				for(int i = 0; i < 7; i++) {
					if(generator.nextInt(2) > 0) {
						arenaInterface.arena().killA(i);
					}
				}
				if(!arenaInterface.gameOver()) {
					flag = false;
				}
			}
			flag = true;
			fighting = new int[] {0, 0, 1, 0, 0, 0, 0};
			select = interpretSwitchA(arenaInterface.arena().avaibleA().clone(), fighting, k);
			arenaInterface.arena().playerASwitch(select);
			arenaInterface.arena().playerBSwitch(2);
			if(autoFight(generator, select, 2, k, e)) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			while(flag) {
				arenaInterface.reset();
				for(int i = 0; i < 7; i++) {
					if(generator.nextInt(2) > 0) {
						arenaInterface.arena().killA(i);
					}
				}
				if(!arenaInterface.gameOver()) {
					flag = false;
				}
			}
			flag = true;
			fighting = new int[] {0, 0, 0, 1, 0, 0, 0};
			select = interpretSwitchA(arenaInterface.arena().avaibleA().clone(), fighting, k);
			arenaInterface.arena().playerASwitch(select);
			arenaInterface.arena().playerBSwitch(3);
			if(autoFight(generator, select, 3, k, e)) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			
			while(flag) {
				arenaInterface.reset();
				for(int i = 0; i < 7; i++) {
					if(generator.nextInt(2) > 0) {
						arenaInterface.arena().killA(i);
					}
				}
				if(!arenaInterface.gameOver()) {
					flag = false;
				}
			}
			flag = true;
			fighting = new int[] {0, 0, 0, 0, 1, 0, 0};
			select = interpretSwitchA(arenaInterface.arena().avaibleA().clone(), fighting, k);
			arenaInterface.arena().playerASwitch(select);
			arenaInterface.arena().playerBSwitch(4);
			if(autoFight(generator, select, 4, k, e)) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			
			while(flag) {
				arenaInterface.reset();
				for(int i = 0; i < 7; i++) {
					if(generator.nextInt(2) > 0) {
						arenaInterface.arena().killA(i);
					}
				}
				if(!arenaInterface.gameOver()) {
					flag = false;
				}
			}
			flag = true;
			fighting = new int[] {0, 0, 0, 0, 0, 1, 0};
			select = interpretSwitchA(arenaInterface.arena().avaibleA().clone(), fighting, k);
			arenaInterface.arena().playerASwitch(select);
			arenaInterface.arena().playerBSwitch(5);
			if(autoFight(generator, select, 5, k, e)) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			
			while(flag) {
				arenaInterface.reset();
				for(int i = 0; i < 7; i++) {
					if(generator.nextInt(2) > 0) {
						arenaInterface.arena().killA(i);
					}
				}
				if(!arenaInterface.gameOver()) {
					flag = false;
				}
			}
			flag = true;
			fighting = new int[] {0, 0, 0, 0, 0, 0, 1};
			select = interpretSwitchA(arenaInterface.arena().avaibleA().clone(), fighting, k);
			arenaInterface.arena().playerASwitch(select);
			arenaInterface.arena().playerBSwitch(6);
			if(autoFight(generator, select, 6, k, e)) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			changePokemonNetwork.setFitness(k, fitness);
		}
	}

	private static boolean autoFight(Random generator, int select, int enemy, int i, eUtils e) {
		int turnCounter = 0;
		while(arenaInterface.arena().PokemonA().canFight() && arenaInterface.arena().PokemonB().canFight() && turnCounter < 100) {
			double[] environment = e.join(e.fighting(enemy), arenaInterface.getHealthA(), arenaInterface.getHealthB(), arenaInterface.getTurnA());
			double[] temp = pokemonModeNetworks[select].feed(environment, i);
			int temp2;
			if(temp[0] < 0.5) {
				temp2 = reverseInterpretBuff(0, pokemonBuffNetworks[select].feed(environment, i)[0]);
			}
			else {
				temp2 = reverseInterpretAttack(0, pokemonAttackNetworks[select].feed(environment, i)[0]);
			}
			arenaInterface.arena().battle(temp2, generator.nextInt(4));
			turnCounter++;
		}
		return !arenaInterface.arena().PokemonB().canFight();
	}

	private static void trainAgainstTeam(int i, Random generator) {
		for (int k = 0; k < 100; k++) {
			eUtils e = new eUtils(generator);
			double fitness = 0;
			
			int counter = 0;
			while(counter < 10) {
				arenaInterface.reset();
				arenaInterface.arena().playerASwitch(i);
				arenaInterface.arena().playerBSwitch(0);
				int[] fighting = new int[] {1, 0, 0, 0, 0, 0, 0};
				int turnCounter = 0;
				while(arenaInterface.arena().PokemonA().canFight() && !arenaInterface.gameOver() && turnCounter < 1000) {
					double[] environment = e.join(fighting, arenaInterface.getHealthA(), arenaInterface.getHealthB(), arenaInterface.getTurnA());
					double[] temp = pokemonModeNetworks[i].feed(environment, k);
					int temp1;
					if(temp[0] < 0.5) {
						temp1 = reverseInterpretBuff(0, pokemonBuffNetworks[0].feed(environment, k)[0]);
					}
					else {
						temp1 = reverseInterpretAttack(0, pokemonAttackNetworks[0].feed(environment, k)[0]);
					}
					int temp2 = generator.nextInt(4);
					arenaInterface.arena().battle(temp1, temp2);
					while(!arenaInterface.arena().PokemonB().canFight() && !arenaInterface.gameOver()) {
						int fightNext = generator.nextInt(7);
						if(arenaInterface.arena().avaibleB()[fightNext] == 1) {
							fighting = e.fighting(fightNext);
							arenaInterface.arena().playerBSwitch(fightNext);
						}
					}
					turnCounter++;
				}
				counter++;
				fitness = fitness + 7 - sum(arenaInterface.arena().avaibleB());
			}
			pokemonModeNetworks[i].setFitness(k, fitness);
			pokemonBuffNetworks[i].setFitness(k, fitness);
			pokemonAttackNetworks[i].setFitness(k, fitness);
		}
	}

	private static double sum(int[] avaible) {
		int temp = 0;
		for(int i: avaible) {
			temp = temp + i;
		}
		return temp;
	}

	private static int reverseInterpretBuff(int i, double d) {
		if(i == 0) {
			return 0;
		}
		
		else if(i == 2) {
			if(d < 0.5) {
				return 0;
			}
			
			return 1;
		}
		
		else if(i == 3) {
			return 3;
		}
		
		else if(i == 4) {
			return 3;
		}
		
		else{
			return 0;
		}
	}

	private static int reverseInterpretAttack(int i, double d) {
		if(i == 0) {
			if(d < 0.25) {
				return 1;
			}
			else if(d > 0.75) {
				return 3;
			}
			else {
				return 2;
			}
		}
		else if(i == 1) {
			if(d < (0.33/2)) {
				return 0;
			}
			else if(d > (0.33/2) && d < 0.5) {
				return 1;
			}
			else if(d > 0.5 && d < (1.67/2)) {
				return 2;
			}
			return 3;
		}
		else if(i == 2) {
			if(d < 0.5) {
				return 2;
			}
			return 3;
		}
		else if(i == 3) {
			if(d < 0.25) {
				return 0;
			}
			else if(d > 0.75) {
				return 2;
			}
			else {
				return 1;
			}
		}
		else if(i == 4) {
			if(d < 0.25) {
				return 0;
			}
			else if(d > 0.75) {
				return 2;
			}
			else {
				return 1;
			}
		}
		else if(i == 5) {
			if(d < (0.33/2)) {
				return 0;
			}
			else if(d > (0.33/2) && d < 0.5) {
				return 1;
			}
			else if(d > 0.5 && d < (1.67/2)) {
				return 2;
			}
			return 3;
		}
		else{
			if(d < 0.25) {
				return 1;
			}
			else if(d > 0.75) {
				return 3;
			}
			else {
				return 2;
			}
		}
	}

	private static void practice1v1(int i, Random generator) {
		for(int k = 0; k < 100; k++) {
			double fitness = 0;
			eUtils e = new eUtils(generator);
			
			arenaInterface.reset();
			arenaInterface.arena().playerASwitch(i);
			arenaInterface.arena().playerBSwitch(0);
			int turnCounter = 0;
			int[] fighting = new int[] {1, 0, 0, 0, 0, 0, 0};
			while(arenaInterface.arena().PokemonB().canFight() && arenaInterface.arena().PokemonA().canFight() && turnCounter < 100) {
				double[] environment = e.join(fighting, arenaInterface.getHealthA(), arenaInterface.getHealthB(), arenaInterface.getTurnA());
				int temp2 = generator.nextInt(4);
				double[] temp = pokemonModeNetworks[i].feed(environment, k);
				int temp1;
				if(temp[0] < 0.5) {
					temp1 = reverseInterpretBuff(0, pokemonBuffNetworks[i].feed(environment, k)[0]);
				}
				else {
					temp1 = reverseInterpretAttack(0, pokemonAttackNetworks[i].feed(environment, k)[0]);
				}
				arenaInterface.arena().battle(temp1, temp2);
				turnCounter++;
			}
			if(!arenaInterface.arena().PokemonB().canFight() && turnCounter < 100) {
				fitness = fitness + arenaInterface.getHealthA();
			}
		
			arenaInterface.reset();
			arenaInterface.arena().playerASwitch(i);
			arenaInterface.arena().playerBSwitch(1);
			turnCounter = 0;
			fighting = new int[] {0, 1, 0, 0, 0, 0, 0};
			while(arenaInterface.arena().PokemonB().canFight() && arenaInterface.arena().PokemonA().canFight() && turnCounter < 100) {
				double[] environment = e.join(fighting, arenaInterface.getHealthA(), arenaInterface.getHealthB(), arenaInterface.getTurnA());
				int temp2 = generator.nextInt(4);
				double[] temp = pokemonModeNetworks[i].feed(environment, k);
				int temp1;
				if(temp[0] < 0.5) {
					temp1 = reverseInterpretBuff(0, pokemonBuffNetworks[i].feed(environment, k)[0]);
				}
				else {
					temp1 = reverseInterpretAttack(0, pokemonAttackNetworks[i].feed(environment, k)[0]);
				}
				arenaInterface.arena().battle(temp1, temp2);
				turnCounter++;
			}
			if(!arenaInterface.arena().PokemonB().canFight() && turnCounter < 100) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			
			arenaInterface.reset();
			arenaInterface.arena().playerASwitch(i);
			arenaInterface.arena().playerBSwitch(2);
			fighting = new int[] {0, 0, 1, 0, 0, 0, 0};
			turnCounter = 0;
			while(arenaInterface.arena().PokemonB().canFight() && arenaInterface.arena().PokemonA().canFight() && turnCounter < 100) {
				double[] environment = e.join(fighting, arenaInterface.getHealthA(), arenaInterface.getHealthB(), arenaInterface.getTurnA());
				int temp2 = generator.nextInt(4);
				double[] temp = pokemonModeNetworks[i].feed(environment, k);
				int temp1;
				if(temp[0] < 0.5) {
					temp1 = reverseInterpretBuff(0, pokemonBuffNetworks[i].feed(environment, k)[0]);
				}
				else {
					temp1 = reverseInterpretAttack(0, pokemonAttackNetworks[i].feed(environment, k)[0]);
				}
				arenaInterface.arena().battle(temp1, temp2);
				turnCounter++;
			}
			if(!arenaInterface.arena().PokemonB().canFight() && turnCounter < 100) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			
			arenaInterface.reset();
			arenaInterface.arena().playerASwitch(i);
			arenaInterface.arena().playerBSwitch(3);
			fighting = new int[] {0, 0, 0, 1, 0, 0, 0};
			turnCounter = 0;
			while(arenaInterface.arena().PokemonB().canFight() && arenaInterface.arena().PokemonA().canFight() && turnCounter < 100) {
				double[] environment = e.join(fighting, arenaInterface.getHealthA(), arenaInterface.getHealthB(), arenaInterface.getTurnA());
				int temp2 = generator.nextInt(4);
				double[] temp = pokemonModeNetworks[i].feed(environment, k);
				int temp1;
				if(temp[0] < 0.5) {
					temp1 = reverseInterpretBuff(0, pokemonBuffNetworks[i].feed(environment, k)[0]);
				}
				else {
					temp1 = reverseInterpretAttack(0, pokemonAttackNetworks[i].feed(environment, k)[0]);
				}
				arenaInterface.arena().battle(temp1, temp2);
				turnCounter++;
			}
			if(!arenaInterface.arena().PokemonB().canFight() && turnCounter < 100) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			
			arenaInterface.reset();
			arenaInterface.arena().playerASwitch(i);
			arenaInterface.arena().playerBSwitch(4);
			fighting = new int[] {0, 0, 0, 0, 1, 0, 0};
			turnCounter = 0;
			while(arenaInterface.arena().PokemonB().canFight() && arenaInterface.arena().PokemonA().canFight() && turnCounter < 100) {
				double[] environment = e.join(fighting, arenaInterface.getHealthA(), arenaInterface.getHealthB(), arenaInterface.getTurnA());
				int temp2 = generator.nextInt(4);
				double[] temp = pokemonModeNetworks[i].feed(environment, k);
				int temp1;
				if(temp[0] < 0.5) {
					temp1 = reverseInterpretBuff(0, pokemonBuffNetworks[i].feed(environment, k)[0]);
				}
				else {
					temp1 = reverseInterpretAttack(0, pokemonAttackNetworks[i].feed(environment, k)[0]);
				}
				arenaInterface.arena().battle(temp1, temp2);
				turnCounter++;
			}
			if(!arenaInterface.arena().PokemonB().canFight() && turnCounter < 100) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			
			arenaInterface.reset();
			arenaInterface.arena().playerASwitch(i);
			arenaInterface.arena().playerBSwitch(5);
			fighting = new int[] {0, 0, 0, 0, 0, 1, 0};
			turnCounter = 0;
			while(arenaInterface.arena().PokemonB().canFight() && arenaInterface.arena().PokemonA().canFight() && turnCounter < 100) {
				double[] environment = e.join(fighting, arenaInterface.getHealthA(), arenaInterface.getHealthB(), arenaInterface.getTurnA());
				int temp2 = generator.nextInt(4);
				double[] temp = pokemonModeNetworks[i].feed(environment, k);
				int temp1;
				if(temp[0] < 0.5) {
					temp1 = reverseInterpretBuff(0, pokemonBuffNetworks[i].feed(environment, k)[0]);
				}
				else {
					temp1 = reverseInterpretAttack(0, pokemonAttackNetworks[i].feed(environment, k)[0]);
				}
				arenaInterface.arena().battle(temp1, temp2);
				turnCounter++;
			}
			if(!arenaInterface.arena().PokemonB().canFight() && turnCounter < 100) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			
			arenaInterface.reset();
			arenaInterface.arena().playerASwitch(i);
			arenaInterface.arena().playerBSwitch(6);
			fighting = new int[] {0, 0, 0, 0, 0, 0, 1};
			turnCounter = 0;
			while(arenaInterface.arena().PokemonB().canFight() && arenaInterface.arena().PokemonA().canFight() && turnCounter < 100) {
				double[] environment = e.join(fighting, arenaInterface.getHealthA(), arenaInterface.getHealthB(), arenaInterface.getTurnA());
				int temp2 = generator.nextInt(4);
				double[] temp = pokemonModeNetworks[i].feed(environment, k);
				int temp1;
				if(temp[0] < 0.5) {
					temp1 = reverseInterpretBuff(0, pokemonBuffNetworks[i].feed(environment, k)[0]);
				}
				else {
					temp1 = reverseInterpretAttack(0, pokemonAttackNetworks[i].feed(environment, k)[0]);
				}
				arenaInterface.arena().battle(temp1, temp2);
				turnCounter++;
			}
			if(!arenaInterface.arena().PokemonB().canFight() && turnCounter < 100) {
				fitness = fitness + arenaInterface.getHealthA();
			}
			
			pokemonModeNetworks[i].setFitness(k, fitness);
			pokemonBuffNetworks[i].setFitness(k, fitness);
			pokemonAttackNetworks[i].setFitness(k, fitness);
		}
	}

	private static double[] interpretBuff(int i, Integer integer) {
		if(i == 0) {
			return new double[] {0};
		}
		else if(i == 2) {
			if(integer == 0) {
				return new double[] {0};
			}
			
			return new double[] {1};
		}
		else if(i == 3) {
			return new double[] {0};
		}
		else{
			return new double[] {0};
		}
	}

	private static double[] interpretAttack(int i, Integer integer) {
		if(i == 0) {
			if(integer == 1) {
				return new double[] {0};
			}
			else if(integer == 2) {
				return new double[] {0.5};
			}
			return new double[] {1};
		}
		else if(i == 1) {
			if(integer == 0) {
				return new double[] {0};
			}
			else if(integer == 1) {
				return new double[] {0.33};
			}
			else if(integer == 2) {
				return new double[] {0.67};
			}
			return new double[] {1};
		}
		else if(i == 2) {
			if(integer == 2) {
				return new double[] {0};
			}
			return new double[] {1};
		}
		else if(i == 3) {
			if(integer == 0) {
				return new double[] {0};
			}
			else if(integer == 1) {
				return new double[] {0.5};
			}
			return new double[] {1};
		}
		else if(i == 4) {
			if(integer == 0) {
				return new double[] {0};
			}
			else if(integer == 1) {
				return new double[] {0.33};
			}
			else if(integer == 2) {
				return new double[] {0.67};
			}
			return new double[] {1};
		}
		else if(i == 5) {
			if(integer == 0) {
				return new double[] {0};
			}
			else if(integer == 1) {
				return new double[] {0.33};
			}
			else if(integer == 2) {
				return new double[] {0.67};
			}
			return new double[] {1};
		}
		else{
			if(integer == 1) {
				return new double[] {0};
			}
			else if(integer == 2) {
				return new double[] {0.5};
			}
			return new double[] {1};
		}
	}

	private static double[] interpretMode(int i, Integer integer) {
		if(i == 0) {
			if(integer == 0) {
				return new double[] {0};
			}
			return new double[] {1};
		}
		else if(i == 1) {
			return new double[] {1};
		}
		else if(i == 2) {
			if(integer == 0 || integer == 1) {
				return new double[] {0};
			}
			return new double[] {1};
		}
		else if(i == 3) {
			if(integer == 3) {
				return new double[] {0};
			}
			return new double[] {1};
		}
		else if(i == 4) {
			if(integer == 3) {
				return new double[] {0};
			}
			return new double[] {1};
		}
		else if(i == 5) {
			return new double[] {1};
		}
		else{
			if(integer == 0) {
				return new double[] {0};
			}
			return new double[] {1};
		}
	}

	private static void setUpNetworks(Random generator) {
		eUtils e = new eUtils(generator);
		
//		changeChoiceNetwork = new GNNetwork(23, 23, e);
//		changeChoiceNetwork.addRandom(1, false);
		
		changePokemonNetwork = new GNNetwork(15, 15, e, 1000);
		changePokemonNetwork.addRandom(1, false);
		
		pokemonModeNetworks = new GNNetwork[7];
		pokemonModeNetworks[0] = new GNNetwork(10, 10, e, 100);
		pokemonModeNetworks[0].addRandom(1, false);
		pokemonModeNetworks[1] = new GNNetwork(10, 10, e, 100);
		pokemonModeNetworks[1].addRandom(1, false);
		pokemonModeNetworks[2] = new GNNetwork(10, 10, e, 100);
		pokemonModeNetworks[2].addRandom(1, false);
		pokemonModeNetworks[3] = new GNNetwork(10, 10, e, 100);
		pokemonModeNetworks[3].addRandom(1, false);
		pokemonModeNetworks[4] = new GNNetwork(10, 10, e, 100);
		pokemonModeNetworks[4].addRandom(1, false);
		pokemonModeNetworks[5] = new GNNetwork(10, 10, e, 100);
		pokemonModeNetworks[5].addRandom(1, false);
		pokemonModeNetworks[6] = new GNNetwork(10, 10, e, 100);
		pokemonModeNetworks[6].addRandom(1, false);
		
		pokemonAttackNetworks = new GNNetwork[7];
		pokemonAttackNetworks[0] = new GNNetwork(10, 10, e, 100);
		pokemonAttackNetworks[0].addRandom(1, false);
		pokemonAttackNetworks[1] = new GNNetwork(10, 10, e, 100);
		pokemonAttackNetworks[1].addRandom(1, false);
		pokemonAttackNetworks[2] = new GNNetwork(10, 10, e, 100);
		pokemonAttackNetworks[2].addRandom(1, false);
		pokemonAttackNetworks[3] = new GNNetwork(10, 10, e, 100);
		pokemonAttackNetworks[3].addRandom(1, false);
		pokemonAttackNetworks[4] = new GNNetwork(10, 10, e, 100);
		pokemonAttackNetworks[4].addRandom(1, false);
		pokemonAttackNetworks[5] = new GNNetwork(10, 10, e, 100);
		pokemonAttackNetworks[5].addRandom(1, false);
		pokemonAttackNetworks[6] = new GNNetwork(10, 10, e, 100);
		pokemonAttackNetworks[6].addRandom(1, false);
		
		pokemonBuffNetworks = new GNNetwork[7];
		pokemonBuffNetworks[0] = new GNNetwork(10, 10, e, 100);
		pokemonBuffNetworks[0].addRandom(1, false);
		pokemonBuffNetworks[1] = new GNNetwork(10, 10, e, 100);
		pokemonBuffNetworks[1].addRandom(1, false);
		pokemonBuffNetworks[2] = new GNNetwork(10, 10, e, 100);
		pokemonBuffNetworks[2].addRandom(1, false);
		pokemonBuffNetworks[3] = new GNNetwork(10, 10, e, 100);
		pokemonBuffNetworks[3].addRandom(1, false);
		pokemonBuffNetworks[4] = new GNNetwork(10, 10, e, 100);
		pokemonBuffNetworks[4].addRandom(1, false);
		pokemonBuffNetworks[5] = new GNNetwork(10, 10, e, 100);
		pokemonBuffNetworks[5].addRandom(1, false);
		pokemonBuffNetworks[6] = new GNNetwork(10, 10, e, 100);
		pokemonBuffNetworks[6].addRandom(1, false);
	}

	private static void setUpInterface() {
		Pokemon[] pokemons = new Pokemon[7];
		
		Status flinch = new StatusMove(0, 0, 0, 0, 0, 0, true, true);
		
		pokemons[0] = new Pokemon(167, 186, 115, 108, 120, 145, new Dragon(), new Flying());
		pokemons[0].addMove(new StatusMove(0, 1, 0, 0, 0, 1, false, false));
		pokemons[0].addMove(new PhysicalDamage(new Dragon(), 120, 0, new NullStatus()));
		pokemons[0].addMove(new PhysicalDamage(new Normal(), 80, 2, new NullStatus()));
		pokemons[0].addMove(new PhysicalDamage(new Ground(), 100, 0, new NullStatus()));
		pokemons[0].addAbility(new Multiscale());
		
		pokemons[1] = new Pokemon(141, 188, 114, 66, 116, 205, new Normal(), new Fighting());
		pokemons[1].addMove(new PhysicalDamage(new Fighting(), 130, 0, new NullStatus()));
		pokemons[1].addMove(new PhysicalDamage(new Normal(), 102, 0, new NullStatus()));
		pokemons[1].addMove(new PhysicalDamage(new Normal(), 40, 3, flinch));
		pokemons[1].addMove(new PhysicalDamage(new Ice(), 75, 0, new NullStatus()));
		pokemons[1].addAbility(new Scrappy());
		
		pokemons[2] = new Pokemon(331, 13, 68, 95, 187, 75, new Normal(), new NullType());
		pokemons[2].addMove(new StatusMove(0, 0, 0, 1, 1, 0, false, false));
		pokemons[2].addMove(new StatusMove(4, 0, 0, 0, 0, 0, false, false));
		pokemons[2].addMove(new SpecialDamage(new Electric(), 90, 0, new NullStatus()));
		pokemons[2].addMove(new SpecialDamage(new Ice(), 90, 0, new NullStatus()));
		
		pokemons[3] = new Pokemon(145, 81, 86, 214, 155, 167, new Psychic(), new Fairy());
		pokemons[3].addMove(new SpecialDamage(new Fairy(), 108, 0, new NullStatus()));
		pokemons[3].addMove(new SpecialDamage(new Psychic(), 90, 0, new NullStatus()));
		pokemons[3].addMove(new SpecialDamage(new Fighting(), 120, 0, new NullStatus()));
		pokemons[3].addMove(new StatusMove(0, 0, 0, 1, 1, 0, false, false));
		pokemons[3].addAbility(new Pixilate());
		
		pokemons[4] = new Pokemon(136, 63, 80, 182, 95, 178, new Ghost(), new Poison());
		pokemons[4].addMove(new SpecialDamage(new Ghost(), 80, 0, new NullStatus()));
		pokemons[4].addMove(new SpecialDamage(new Poison(), 95, 0, new NullStatus()));
		pokemons[4].addMove(new SpecialDamage(new Fighting(), 120, 0, new NullStatus()));
		pokemons[4].addMove(new PhysicalDamage(new Normal(), 0, 4, flinch));
		pokemons[4].addAbility(new Levitate());
		
		pokemons[5] = new Pokemon(146, 172, 85, 58, 105, 194, new Dark(), new Ice());
		pokemons[5].addMove(new PhysicalDamage(new Ice(), 110, 0, new NullStatus()));
		pokemons[5].addMove(new PhysicalDamage(new Dark(), 126, 0, new NullStatus()));
		pokemons[5].addMove(new PhysicalDamage(new Ice(), 52, 1, new NullStatus()));
		pokemons[5].addMove(new PhysicalDamage(new Poison(), 104, 0, new NullStatus()));
		
		pokemons[6] = new Pokemon(146, 172, 85, 58, 105, 194, new Fairy(), new Rock());
		pokemons[6].addMove(new StatusMove(0, 0, 0, 0, 0, 2, false, false));
		pokemons[6].addMove(new SpecialDamage(new Fairy(), 95, 0, new NullStatus()));
		pokemons[6].addMove(new PhysicalDamage(new Rock(), 100, 0, new StatusMove(0, 0, 1, 0, 0, 0, false, false)));
		pokemons[6].addMove(new SpecialDamage(new Ground(), 90, 0, new NullStatus()));
		
		arenaInterface = new GameInterface(pokemons, pokemons);
	}
	
}
