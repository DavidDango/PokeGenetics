package Network;

import java.util.ArrayList;
import java.util.Random;

import Utils.eUtils;

public class NNetwork {
	private eUtils e;
	private ArrayList<NLayer> layers;
	private int inputs;
	private int outputs;

	/**
	 * Creates a new Neural Network using a set of data to create the first layer.
	 * 
	 * @param in Desired number of inputs.
	 * @param neurons Desired number of Neurons.
	 * @param random Random data for Neurons.
	 * @param learningRate Desired Learning rate.
	 */
	public NNetwork(int in, int neurons, boolean random) {
		layers = new ArrayList<NLayer>();
		e = new eUtils();
		NLayer l = new NLayer();
		if(random){
			for(int i = 0; i < neurons; i++){
				l.add(new Neuron(e.makeRandomArray(in, 0, 5), e.getRandom(0, 5), 0.02));
			}
		}
		else{
			for(int i = 0; i < neurons; i++){
				l.add(new Neuron(e.makeArray(in, 0), 0, 0.02));
			}
		}
		inputs = in;
		outputs = neurons;
		layers.add(l);
	}
	
	public NNetwork(int in, int neurons, eUtils e) {
		layers = new ArrayList<NLayer>();
		this.e = e;
		NLayer l = new NLayer();
		for(int i = 0; i < neurons; i++){
			l.add(new Neuron(e.makeRandomArray(in, 0, 5), e.getRandom(0, 5), 0.02));
		}
		inputs = in;
		outputs = neurons;
		layers.add(l);
	}

	/**
	 * Creates a new Neural Network using a single Neuron to create it's first Layer.
	 * 
	 * @param neuron Neuron
	 */
	public NNetwork(Neuron neuron) {
		this(new NLayer(neuron));
	}

	/**
	 * Creates a new Neural Network using a provided Layer as a first Layer.
	 * 
	 * @param layer Neural Layer
	 */
	public NNetwork(NLayer layer) {
		layers = new ArrayList<NLayer>();
		e = new eUtils();
		layers.add(layer);
		inputs = layer.getInputs();
		outputs = layer.getOutputs();
	}
	
	/**
	 * Adds a new Neuron to the Neural Network. The Neuron can be added to the same Layer
	 * or be used to create a new Layer depending on the boolean value.
	 * 
	 * @param neuron Neuron
	 * @param sameLayer Same Layer
	 */
	public void add(Neuron n, boolean sameLayer) {
		if(sameLayer){
			layers.get(layers.size() - 1).add(n);
			outputs++;
		}
		else{
			NLayer newLayer = new NLayer(n);
			this.add(newLayer);
		}
	}
	
	/**
	 * Creates new identical Neurons to add to the Network using a set of data. Can be added
	 * to the latest Layer depending on a boolean value.
	 * 
	 * @param neurons Number of Neurons
	 * @param useSameLayer Use same Layer
	 * @param bias Bias
	 * @param learningRate Learning rate
	 */
	public void add(int neurons, boolean useSameLayer, double bias, double learningRate){
		if(useSameLayer){
			for(int i = 0; i < neurons; i++){
				layers.get(layers.size() - 1).add(e.makeArray(layers.get(layers.size() - 1).getInputs(), 0), bias, learningRate);
				outputs++;
			}
		}
		else{
			NLayer newLayer = new NLayer();
			double[] temp = new double[outputs];
			for(int i = 0; i < outputs; i++){
				temp[i] = 0;
			}
			for(int i = 0; i < neurons; i++){
				newLayer.add(e.makeArray(outputs, 0), bias, learningRate);
			}
			this.add(newLayer);
		}
	}
	
	/**
	 * Adds a requested number of Neurons with random data.
	 * 
	 * @param neurons Number of Neurons
	 * @param sameLayer Same Layer
	 */
	public void addRandom(int neurons, boolean sameLayer){
		if(sameLayer){
			for(int i = 0; i < neurons; i++){
				this.add(new Neuron(e.makeRandomArray(layers.get(layers.size() - 1).getInputs(), 0, 5), e.getRandom(0, 5), 0.02), sameLayer);
			}
		}
		else {
			this.add(new Neuron(e.makeRandomArray(outputs, 0, 5), e.getRandom(0, 5), 0.02), sameLayer);
			this.addRandom(neurons - 1, true);
		}
	}
	
	/**
	 * Adds a new Layer to the Network.
	 * 
	 * @param layer Neural Layer
	 */
	public void add(NLayer layer){
		layers.get(layers.size() - 1).nextLayer(layer);
		layers.add(layer);
		outputs = layer.getOutputs();
	}

	/**
	 * Trains the Neural Network.
	 * 
	 * @param in Input
	 * @param expected Expected output
	 */
	public double[] train(double[] in, double[] expected) {
		this.feed(in);
		layers.get(layers.size() - 1).propagateError(expected);
		return layers.get(0).fix();
	}
	
	public double[][][] trainWithEpochs(int iterations, double[][] inputs, double[][] expectedOutputs){		
		double answer[][] = new double[inputs.length][expectedOutputs[0].length];
		double[][][] answers = new double[iterations][expectedOutputs.length][expectedOutputs[0].length];
		for(int i = 0; i < iterations; i++){
			for(int j = 0; j < inputs.length; j++){
				this.train(inputs[j], expectedOutputs[j]);
			}
			for(int j = 0; j < inputs.length; j++){
				answer[j] = this.feed(inputs[j]);
			}
			answers[i] = answer.clone();
		}
		return answers;
	}

	/**
	 * Returns the Network's input count.
	 * 
	 * @return Inputs
	 */
	public int inputs() {
		return inputs;
	}

	/**
	 * Returns the output count.
	 * 
	 * @return Outputs
	 */
	public int outputs() {
		return outputs;
	}

	/**
	 * Feeds the Network and input.
	 * 
	 * @param in Input
	 * @return Output
	 */
	public double[] feed(double[] in) {
		return layers.get(0).feed(in);
	}

	public Neuron getNeuron(int i, double mutationRate, Random generator) {
		int temp1 = 0;
		int temp2;
		for(int j = 0; j < layers.size(); j++) {
			temp2 = layers.get(j).getOutputs();
			if(temp1 + temp2 >= i) {
				return layers.get(j).copyNeuron(i - temp1 - 1, mutationRate, generator);
			}
			temp1 = temp1 + temp2;
		}
		System.out.println("ERROR!");
		return null;
	}

	public boolean newLayer(int i) {
		int temp = 0;
		for(NLayer layer: layers) {
			temp = temp + layer.getOutputs();
			if(temp == i - 1) {
				return true;
			}
			else if (temp > i){
				return false;
			}
		}
		return false;
	}

	public int getNeuronsNumber() {
		int temp = 0;
		for(NLayer layer: layers) {
			temp = temp + layer.getOutputs();
		}
		return temp;
	}
}
