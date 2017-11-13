package Utils;

import java.util.Random;

public class eUtils {
	private Random rand;
	
	public eUtils(Random random){
		rand = random;
	}
	
	public eUtils() {
		this(new Random());
	}

	public double[] makeRandomArray(int i, double base, double error) {
		double[] out = new double[i];
		for(int j = 0; j < i; j++){
			out[j] = getRandom(base, error);
		}
		return out;
	}

	public double[] makeArray(int i, int j) {
		double[] out = new double[i];
		for(int k = 0; k < i; k++){
			out[k] = j;
		}
		return out;
	}

	public double getRandom(double base, double error) {
		double out = ((rand.nextDouble()*2 - 1)*error) + base;
		return out;
		
	}

	public double[] join(int[] fighting, double healthA, double healthB, int turnA) {
		double [] output = new double[fighting.length + 3];
		for(int i = 0; i < fighting.length; i++) {
			output[i] = fighting[i];
		}
		output[fighting.length] = healthA;
		output[fighting.length + 1] = healthB;
		if(turnA > 1) {
			output[fighting.length + 2] = 1;
		}
		else {
			output[fighting.length + 2] = 0;
		}
		return output;
	}

	public int[] fighting(int i) {
		int[] output = new int[] {0, 0, 0, 0, 0, 0, 0};
		output[i] = 1;
		return output;
	}

	public void printList(int[] fighting) {
		for(int i: fighting) {
			System.out.println(i);
		}
	}

	public double[] join(int[] avaibleA, int[] fighting, double healthB) {
		double[] output = new double[avaibleA.length + fighting.length + 1];
		for(int i = 0; i < avaibleA.length; i++) {
			output[i] = avaibleA[i];
		}
		for(int i = 0; i < fighting.length; i++) {
			output[avaibleA.length + i] = fighting[i];
		}
		output[avaibleA.length + fighting.length] = healthB;
		return output;
	}

	public double transform(int select, int i) {
		double temp = 1/(double) (i - 1);
		return temp*select;
	}

	public double[] join(int[] selected, int[] temp, int[] enemy, double healthA, double healthB) {
		double[] output = new double[selected.length + temp.length + enemy.length + 2];
		for(int i = 0; i < selected.length; i++) {
			output[i] = selected[i];
		}
		for(int i = 0; i < temp.length; i++) {
			output[selected.length + i] = temp[i];
		}
		for(int i = 0; i < enemy.length; i++) {
			output[selected.length + temp.length + i] = enemy[i];
		}
		output[selected.length + temp.length + enemy.length] = healthA;
		output[selected.length + temp.length + enemy.length + 1] = healthB;
		return output;
	}

}