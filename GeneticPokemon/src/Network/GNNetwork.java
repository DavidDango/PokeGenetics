package Network;

import java.util.Arrays;
import java.util.Random;

import Utils.eUtils;

public class GNNetwork {
	private NNetwork[] networks;
	private double[] fitness;
	private int population;

	public GNNetwork(int i, int j, eUtils e, int population) {
		networks = new NNetwork[population];
		fitness = new double[population];
		for(int k = 0; k < population; k++) {
			networks[k] = new NNetwork(i, j, e);
			fitness[k] = 0;
		}
		this.population = population;
	}
	
	public void addRandom(int i, boolean b) {
		for(int k = 0; k < population; k++) {
			networks[k].addRandom(i, b);
		}
	}

	public double[] feed(double[] environment, int i) {
		return networks[i].feed(environment);
	}

	public void setFitness(int k, double fitness) {
		this.fitness[k] = fitness;
	}

	public void breed(Random generator, double mutationRate) {
		sortAll();
		double[] cumulativeFitness = new double[population];
		cumulativeFitness[0] = fitness[0];
		for (int i = 1; i < population; i++) {
            cumulativeFitness[i] = cumulativeFitness[i - 1] + fitness[i];
        }
		NNetwork selection[] = new NNetwork[population*2];
		
		for (int i = 0; i < population*2; i++) {
            double randomFitness = generator.nextDouble() * cumulativeFitness[population - 1];
            int index = Arrays.binarySearch(cumulativeFitness, randomFitness);
            if (index < 0) {
                index = Math.abs(index + 1);
            }
            selection[i] = networks[index];
        }
		
		NNetwork[] temp = new NNetwork[population];
		for(int i = 0; i < population; i++) {
			temp[i] = getMix(selection[2*i], selection[(2*i) + 1], mutationRate, generator);
		}
		networks = temp;
	}

	private NNetwork getMix(NNetwork n1, NNetwork n2, double mutationRate, Random generator) {
		NNetwork temp;
		if(generator.nextInt(2) < 1) {
			temp = new NNetwork(n1.getNeuron(1, mutationRate, generator));
		}
		else {
			temp = new NNetwork(n2.getNeuron(1, mutationRate, generator));
		}
		for(int i = 2; i <= n1.getNeuronsNumber(); i++) {
			if(generator.nextInt(2) < 1) {
				if(n1.newLayer(i)) {
					temp.add(n1.getNeuron(i, mutationRate, generator), false);
				}
				else {
					temp.add(n1.getNeuron(i, mutationRate, generator), true);
				}
			}
			else {
				if(n2.newLayer(i)) {
					temp.add(n2.getNeuron(i, mutationRate, generator), false);
				}
				else {
					temp.add(n2.getNeuron(i, mutationRate, generator), true);
				}
			}
		}
		return temp;
	}

	private void sortAll() {
		double[] temp1 = new double[population];
		NNetwork[] temp2 = new NNetwork[population];
		for(int i = 0; i < population; i++) {
			int index = getMax(fitness);
			temp1[i] = fitness[index];
			temp2[i] = networks[index];
			fitness[index] = -1;
		}
		fitness = temp1;
		networks = temp2;
	}

	private int getMax(double[] list) {
		double temp = -1;
		int i = 0;
		for(int j = 0; j < list.length; j++) {
			if(list[j] > temp) {
				temp = list[j];
				i = j;
			}
		}
		return i;
	}
}
