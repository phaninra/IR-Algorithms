package ir.vsm;

/**
 * Document represented as a Vector of weights. Generally the weights are tf-idf weights.
 * @author Phani
 *
 */
public class DocumentVector {

	private int id;
	private double[] weights;

	public DocumentVector(double[] w, int id1) {
		this.weights = w;
		this.id = id1;
	}

	public void addWeight(double w, int index) {
		weights[index] = w;
	}

	public double getWeight(int index) {
		return weights[index];
	}
	
	public int getLength(){
		return weights.length;
	}
	
	public double[] getWeights(){
		return this.weights;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString(){
//		return "Id: " + id + ", Weights: " + Arrays.toString(NumberFormatUtil.truncateTo3Decimals(weights));
		return "Id: " + id ;
	}

}
