package ir.vsm;

import ir.util.NumberFormatUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cluster containing one or more document vectors.
 * @author Phani
 *
 */
public class DocumentVectorCluster {
	/*
	 * Id of this cluster
	 */
	private int id;
	/*
	 * Document Vectors in this cluster. 
	 */
	List<DocumentVector> documentVectors = new ArrayList<DocumentVector>();
	/*
	 * Centroid weights of this cluster.
	 */
	private double[] centroidWeights;
	
	public void addDocVector(DocumentVector dv){
		documentVectors.add(dv);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double[] recomputeCentroid(){
		if(documentVectors.size() > 0){
			double[] weights = new double[documentVectors.get(0).getLength()];
			for(int i = 0; i < weights.length; i++){
				for(DocumentVector dv : documentVectors){
					weights[i] = weights[i] + dv.getWeight(i);
				}
				weights[i] = weights[i]/documentVectors.size();
			}
			this.centroidWeights = weights;
			return centroidWeights;
		}else{
			return null;
		}
	}
	
	public double[] getCentroidWeights() {
		return centroidWeights;
	}

	public void setCentroidWeights(double[] centroidVector) {
		this.centroidWeights = centroidVector;
	}
	
	public void clearDocVectors(){
		documentVectors = new ArrayList<DocumentVector>();
	}
	
	
	public String toString(){
		return "Id: " + id + ", Centroid Weights: " + Arrays.toString(NumberFormatUtil.truncateTo3Decimals(centroidWeights))  + ", DocVectors: " + documentVectors;
	}
	
	public List<Integer> getMemberIds(){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for(DocumentVector dv : documentVectors){
			ids.add(dv.getId());
		}
		return ids;
	}
	
}
