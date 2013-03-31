package ir.algo.cluster.kmeans;

import ir.algo.cluster.Clusterer;
import ir.algo.cluster.result.DocumentClustersResult;
import ir.similarity.CosineSimilarityComputer;
import ir.util.LogUtil;
import ir.util.NumberFormatUtil;
import ir.vsm.DocumentVector;
import ir.vsm.DocumentVectorCluster;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Clusters the document collection using K-Means algorithm. The k document seed ids have to be passed via constructor.
 * @author Phani
 *
 */
public class KMeansClusterer extends Clusterer {
	
	/**
	 * Document Vectors to be clustered.
	 */
	private Map<Integer, DocumentVector> docVectorsMap = null;

	/**
	 * <K, V> = <Cluster Id, Cluster information object>
	 */
	private Map<Integer, DocumentVectorCluster> clusterMap = new HashMap<Integer, DocumentVectorCluster>();

	/**
	 * Initial Centroid doc ids.
	 */
	private int[] seedCentroidDocIds = null;
	
	/**
	 * Cluster centroid Ids of previous iteration.
	 * <K, V> = <Cluster id, Centroid Weights>
	 */
	private Map<Integer, double[]> prevCentroidsMap = new HashMap<Integer, double[]>();
	
	/**
	 * @param initialCentroidDocIds1 K seed docIds in to-be-clustered-collection.
	 */
	public KMeansClusterer(int[] initialCentroidDocIds1) {
		this.seedCentroidDocIds = initialCentroidDocIds1;
	}
	
	/**
	 * Builds the Clusters map based on seed centroid weights.
	 * @param seedCentroidWeights Weights of k document seeds.
	 */
	private void buildClusterMap(double[]... seedCentroidWeights){
		DocumentVectorCluster c = null;
		int i = 1;
		for (double[] weight : seedCentroidWeights) {
			c = new DocumentVectorCluster();
			c.setId(i++);
			c.setCentroidWeights(weight);
			clusterMap.put(c.getId(), c);
		}
	}

	/**
	 * Reset the k document seeds for each algorithm-run.
	 * @param initialCentroidDocIds1 K seed docIds in to-be-clustered-collection.
	 */
	public void resetInitialCentroidDocIds(int[] initialCentroidDocIds1){
		this.seedCentroidDocIds = initialCentroidDocIds1;
	}
	
	/**
	 * Initialize resources.
	 */
	private void init(){
		// Build seed centroid weights.
		double[][] seedCentroidWeights = new double[seedCentroidDocIds.length][];
		for(int i = 0; i < seedCentroidDocIds.length; i++){
			seedCentroidWeights[i] = docVectorsMap.get(seedCentroidDocIds[i]).getWeights();
		}
		// build Clusters Map
		buildClusterMap(seedCentroidWeights);
	}

	@Override
	public DocumentClustersResult clusterDocumentVectors(Map<Integer, DocumentVector> docVectors1) {
		// init
		docVectorsMap = docVectors1;
		init();
		// run
		runAlgorithm();
		// Populate result.
		DocumentClustersResult result = new DocumentClustersResult(clusterMap);
		return result;
	}
	
	/**
	 * Runs the algorthim step by step.
	 */
	private void runAlgorithm(){
		int i = 1;
		while (!isConverged()) {
			LogUtil.log("--------------------------------- Iteration " + i++);
			copyCurrentCentroidsToPrevious();
			printPrevCentroids();
			doClusterize();
			recomputeCentroids();
			System.out.println("Clusters: " + clusterMap);
		}
	}
	
	/**
	 * Copies to previous centroid weights.
	 */
	private void copyCurrentCentroidsToPrevious(){
		for(DocumentVectorCluster c : clusterMap.values()){
			prevCentroidsMap.put(c.getId(), c.getCentroidWeights());
		}
	}

	/**
	 * Runs one iteration of K-Means algorithm.
	 */
	private void doClusterize() {
		double maxSimilarity = 0;
		int maxSimClusterId = -1;
		double temp = 0;
		clearAllClusters();
		for (DocumentVector dv : docVectorsMap.values()) {
			for (DocumentVectorCluster c : clusterMap.values()) {
				temp = CosineSimilarityComputer.getCosineSimilarity(dv.getWeights(), c.getCentroidWeights());
				LogUtil.log("Temp similarity between " + Arrays.toString(NumberFormatUtil.truncateTo3Decimals(dv.getWeights())) + ", " + Arrays.toString(NumberFormatUtil.truncateTo3Decimals(c.getCentroidWeights())) + ": " + temp);
				if (temp >= maxSimilarity) {
					System.out.println("Resetting max DocumentVectorCluster id to " + c.getId());
					maxSimilarity = temp;
					maxSimClusterId = c.getId();
				}
			}
			LogUtil.log("---Clustered- DV: " + dv + ", maxSimClusterId : " + maxSimClusterId);
			clusterMap.get(maxSimClusterId).addDocVector(dv);
			maxSimilarity = 0;
			maxSimClusterId = -1;
		}
	}

	private void clearAllClusters() {
		for (DocumentVectorCluster c : clusterMap.values()) {
			c.clearDocVectors();
		}
	}

	private void recomputeCentroids() {
		for (DocumentVectorCluster c : clusterMap.values()) {
			c.recomputeCentroid();
		}
	}

	/**
	 * Checks whether the algorithm is converged.
	 * @return converged boolean value
	 */
	private boolean isConverged() {
		if (prevCentroidsMap == null) {
			return false;
		}
		for (DocumentVectorCluster c : clusterMap.values()) {
			if (!Arrays.equals(c.getCentroidWeights(), prevCentroidsMap.get(c.getId()))) {
				return false;
			}
		}
		return true;
	}
	
	private void printPrevCentroids(){
		System.out.print("Previous Centroids: ");
		for(Integer cId : prevCentroidsMap.keySet()){
			LogUtil.log("Id: " + cId + ", Weights: " + Arrays.toString(NumberFormatUtil.truncateTo3Decimals(prevCentroidsMap.get(cId))));
		}
		System.out.println();
	}

}
