package ir.algo.cluster.result;

import ir.vsm.DocumentVectorCluster;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds the result of a clustered algorithm, which is a group of clusters.
 * @author Phani
 *
 */
public class DocumentClustersResult {

	/**
	 * <K, V> = <Cluster Id, Cluster information object>
	 */
	private Map<Integer, DocumentVectorCluster> clusterMap = new HashMap<Integer, DocumentVectorCluster>();
	
	public DocumentClustersResult(Map<Integer, DocumentVectorCluster> clusterMap1){
		this.clusterMap = clusterMap1;
	}

	public Map<Integer, DocumentVectorCluster> getClusterMap() {
		return clusterMap;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(DocumentVectorCluster cluster : clusterMap.values()){
			sb.append("Cluster " + cluster.getId() + ":" + cluster.getMemberIds() + ", ");
		}
		return sb.toString();
	}
	
}
