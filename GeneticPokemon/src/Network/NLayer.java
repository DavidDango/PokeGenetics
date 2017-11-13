package Network;

import java.util.ArrayList;
import java.util.Random;

public class NLayer {
	private boolean inputLayer;
	private boolean outputLayer;
	private ArrayList<Neuron> neurons;
	private NLayer previousLayer;
	private NLayer nextLayer;
	private int inputs;
	private int outputs;
	private boolean empty;
	private double[] initialInputs;
	
	/**
	 * This constructor doesn't take inputs and creates a default empty layer.
	 * Sets itself as both an input Layer and an output Layer.
	 * 
	 */
	public NLayer(){
		inputLayer = true;
		outputLayer = true;
		neurons = new ArrayList<Neuron>();
		previousLayer = null;
		nextLayer = null;
		inputs = 0;
		outputs = 0;
		empty = true;
		initialInputs = null;
	}
	
	/**
	 * This constructor creates a new Neuron layer with a Neuron already inside.
	 * It uses the Neuron's input count as the Layer's input count.
	 * Sets itself as both an input Layer and an output Layer.
	 * 
	 * @param n Neuron
	 */
	public NLayer(Neuron n){
		inputLayer = true;
		outputLayer = true;
		neurons = new ArrayList<Neuron>();
		neurons.add(n);
		previousLayer = null;
		nextLayer = null;
		inputs = n.inputs();
		outputs = 1;
		empty = false;
	}
	
	/**
	 * Creates a new Layer using another Layer as argument. It takes the previous Layer's
	 * output count as the new Layer's input count and sets the provided Layer as the
	 * stored previous Layer, sets itself as an output Layer and sets itself as a non
	 * input Layer.
	 * 
	 * @param n Neuron Layer
	 */
	public NLayer(NLayer n){
		inputLayer = false;
		outputLayer = true;
		n.nextLayer(this);
		neurons = new ArrayList<Neuron>();
		previousLayer = n;
		nextLayer = null;
		inputs = n.getOutputs();
		outputs = 0;
		empty = true;
	}

	/**
	 * Sets the provided Layer as this Neuron's next layer and then makes the provided
	 * Layer set this Layer as it's previous Layer and sets itself as a non output Layer.
	 * 
	 * @param n Neuron Layer
	 */
	public void nextLayer(NLayer n) {
		outputLayer = false;
		nextLayer = n;
		n.previousLayer(this);
	}

	/**
	 * Sets the provided Layer as the previous Layer and sets itself as a non input Layer.
	 * 
	 * @param n Previous Neuron Layer
	 */
	public void previousLayer(NLayer n) {
		previousLayer = n;
		inputLayer = false;
	}

	/**
	 * Adds a Neuron to the Layer's Neurons.
	 * If Layer is empty sets the data according to the Neuron.
	 * If the Neuron's data doesn't match the Layer's data the method answers false.
	 * If the Layer isn't empty and the Neuron is acceptable, the Layer's data is updated.
	 * 
	 * @param n New Neuron
	 * @return Boolean
	 */
	public boolean add(Neuron n) {
		if(empty){
			empty = false;
			neurons.add(n);
			inputs = n.inputs();
			outputs = 1;
			return true;
		}
		else{
			if(n.inputs() == inputs){
				neurons.add(n);
				outputs++;
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	/**
	 * Adds a new Neuron using the data to create it.
	 * 
	 * @param weights Weights
	 * @param bias Bias
	 * @param learningRate Learning rate
	 */
	public void add(double[] weights, double bias, double learningRate) {
		this.add(new Neuron(weights, bias, learningRate));
	}

	/**
	 * Returns the input count.
	 * 
	 * @return Inputs
	 */
	public int getInputs() {
		return inputs;
	}

	/**
	 * Returns the output count.
	 * 
	 * @return Outputs
	 */
	public int getOutputs() {
		return outputs;
	}

	/**
	 * Feeds an input to the Layer's Neurons and returns an array with one output
	 * per Neuron.
	 * 
	 * @param input Inputs
	 * @return Outputs
	 */
	public double[] feed(double[] input) {
		initialInputs = input.clone();
		double[] result = new double[outputs];
		for(int i = 0; i < outputs; i++){
			result[i] = neurons.get(i).process(input);
		}
		if(outputLayer){
			return result;
		}
		return nextLayer.feed(result);
	}
	

	/**
	 * Returns Delta*Weight[i] for each Neuron using and index i to get the requested
	 * weight for each Neuron.
	 * 
	 * @param i Index
	 * @return Delta*Weight[i]
	 */
	private double[] getDW() {
		double[] temp = new double[inputs];
		for(int i = 0; i < inputs; i++){
			temp[i] = 0;
		}
		for(int i = 0; i < outputs; i++){
			for(int j = 0; j < inputs; j++){
				temp[j] = temp[j] + neurons.get(i).getWeightDelta(j);
			}
		}
		return temp;
	}

	/**
	 * Sets the error for each Neuron comparing the output with the expected output
	 * if the Layer is output and using the next Layer's Delta*Weight 
	 * 
	 * @param expected Expected output.
	 */
	public void propagateError(double[] expected) {
		if(outputLayer){
			for(int i = 0; i < outputs; i++){
				neurons.get(i).setData(expected[i], true);
			}
		}
		else{
			for(int i = 0; i < outputs; i++){
				neurons.get(i).setData(nextLayer.getDW()[i], false);
			}
		}
		if(!inputLayer){
			previousLayer.propagateError(this.getDW());
		}
	}

	/**
	 * Fixes the Neuron's data and returns the error of the first input Layer.
	 * 
	 * @return double[] Error data.
	 */
	public double[] fix() {
		for(Neuron n: neurons){
			n.fix(initialInputs);
		}
		if(!outputLayer){
			return nextLayer.fix();
		}
		return this.error();
	}
	
	/**
	 * Returns and array with the errors of every Neuron on the Layer.
	 * 
	 * @return double[] Error array.
	 */
	private double[] error() {
		double[] errors = new double[outputs];
		for (int i = 0; i < outputs; i++){
			errors[i] = neurons.get(i).getError();
		}
		return errors;
	}

	/**
	 * Asks if the Layer is empty.
	 * 
	 * @return Boolean
	 */
	public boolean isEmpty(){
		return empty;
	}

	public Neuron copyNeuron(int i, double mutationRate, Random generator) {
		return neurons.get(i).copyNeuron(mutationRate, generator);
	}
}