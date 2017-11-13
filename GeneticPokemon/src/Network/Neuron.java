package Network;

import java.util.Random;

/**
 * This class corresponds to a implementation of a Sigmoid neuron
 * 
 * @author David
 *
 */
public class Neuron {
	private double[] weights;
	private double bias;
	private double learningRate;
	private double output;
	private double error;
	private double delta;
	
	
	/**
	 * Constructor takes 3 parameters.
	 * 
	 * The w parameter determines the weights of the Neuron's inputs as a double[]. The
	 * number of elements in the array determines the number of inputs the Neuron will have.
	 * 
	 * The b parameter corresponds to the Neuron's bias.
	 * 
	 * The lr parameter corresponds to the Neuron's learning rate.
	 * 
	 * @param w Weights
	 * @param b Bias
	 * @param lr Learning rate
	 */
	public Neuron(double[] w, double b, double lr) {
		weights = w.clone();
		bias = b;
		learningRate = lr;
		output = 0;
		error = 0;
		delta = 0;
	}

	/**
	 * Returns the number of inputs the Neuron accepts.
	 * 
	 * @return Number of inputs
	 */
	public int inputs() {
		return weights.length;
	}

	/**
	 * Returns the Neuron's bias.
	 * 
	 * @return Bias
	 */
	public double getBias() {
		return bias;
	}

	/**
	 * Returns the Neuron's weights.
	 * 
	 * @return Weight
	 */
	public double[] getW() {
		return weights;
	}

	/**
	 * Returns the Neuron's learning rate.
	 * 
	 * @return Learning rate
	 */
	public double getLearningRate() {
		return learningRate;
	}

	/**
	 * Processes the input on a range 0-1 and returns a double between 0-1 as a double[]
	 * with the same number of elements as the number of inputs of the Neuron.
	 * 
	 * @param input Input array
	 * @return Output
	 */
	public double process(double[] input) {
		double x = 0;
		for(int i = 0; i < this.inputs(); i++){
			x = x + (weights[i]*input[i]);
		}
		output = (1/(1 + (Math.exp((-1)*(x + bias)))));
		return output;
	}
	
	/**
	 * Calculates the last input's transfer derivative.
	 * 
	 * @return Transfer derivative
	 */
	private double transferDerivative() {
		return (output*(1 - output));
	}
	
	/**
	 * Return's delta*weight for the desired weight.
	 * 
	 * @param Index
	 * @return Delta*Weight[i]
	 */
	public double getWeightDelta(int i){
		return delta*weights[i];
	}

	/**
	 * Sets the Neuron's error and delta using an expected output and the latest output.
	 * 
	 * @param expected Expected output
	 */
	public void setData(double expected, boolean outputLayer) {
		if(outputLayer){
			error = expected - output;
			delta = error*this.transferDerivative();
		}
		else{
			error = expected;
			delta = error*this.transferDerivative();
		}
	}

	/**
	 * Changes the Neuron's weights and bias using an array of inputs, the delta and
	 * the learning rate.
	 * 
	 * @param initialInputs Input array
	 */
	public void fix(double[] initialInputs) {
		for(int i = 0; i < weights.length; i++){
			weights[i] = weights[i] + (learningRate*delta*initialInputs[i]);
			bias = bias + (learningRate*delta);
		}
	}

	public double getError() {
		return error;
	}
	
	public int numberOfWeights() {
		return weights.length;
	}

	public Neuron copyNeuron(double mutationRate, Random generator) {
		double temp[] = weights.clone();
		double temp2 = 0;
		for(int i = 0; i < temp.length; i++) {
			if(generator.nextDouble() < mutationRate) {
				temp2 = (generator.nextDouble()*2) - 1;
			}
			temp[i] = weights[i] + temp2;
			temp2 = 0;
		}
		temp2 = (generator.nextDouble()*2) - 1;
		if(generator.nextDouble() < mutationRate) {
			return new Neuron(temp, bias + temp2, learningRate);
		}
		return new Neuron(temp, bias, learningRate);
	}
}
