package ir.algo.cluster.kmeans;

import ir.algo.cluster.Clusterer;
import ir.algo.cluster.result.DocumentClustersResult;
import ir.holder.Document;
import ir.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * This class does not contain automated unit test cases. 
 * The intention is to experiment with different inputs and analyze the outputs.
 * @author Phani
 *
 */
public class KMeansManualTest {

	public static void main(String[] args){
		testKMeans();
	}
	
	private static void testKMeans(){
		Map<Integer, Document> docs = getTestDocuments();
		Clusterer c = new KMeansClusterer(new int[]{1, 2, 3, 5});
		DocumentClustersResult result = c.clusterDocuments(docs);
		LogUtil.log(result);
		
	}
	
	private static Map<Integer, Document> getTestDocuments(){
		Map<Integer, Document> docs = new HashMap<Integer, Document>();
		Document doc = null;
		//
		doc = new Document("a a a b b b c c c d d", 1);
		docs.put(1, doc);
		//
		doc = new Document("c e f g h", 2);
		docs.put(2, doc);
		//
		doc = new Document("i j k k k i k j a", 3);
		docs.put(3, doc);
		//
		doc = new Document("i j j k k k a a a", 4);
		docs.put(4, doc);
		//
		doc = new Document("m n o p m n o p m m n n", 5);
		docs.put(5, doc);
		//
		doc = new Document("m n", 6);
		docs.put(6, doc);
		//
		doc = new Document("a b c m", 7);
		docs.put(7, doc);
		//
		doc = new Document("x y z x y z u v", 8);
		docs.put(8, doc);
		//
		doc = new Document("u v w w w", 9);
		docs.put(9, doc);
		//
		doc = new Document("s t u v w x y z", 10);
		docs.put(10, doc);
		//
		doc = new Document("s t s t s t u v", 11);
		docs.put(11, doc);
		//
		doc = new Document("a b c x d", 12);
		docs.put(12, doc);
		//
		doc = new Document("m o p", 13);
		docs.put(13, doc);
		//
		doc = new Document("w x y z", 14);
		docs.put(14, doc);
		//
		doc = new Document("e f g h", 15);
		docs.put(15, doc);
		return docs;
	}

}
