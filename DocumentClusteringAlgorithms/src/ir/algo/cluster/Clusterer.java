package ir.algo.cluster;

import ir.algo.cluster.result.DocumentClustersResult;
import ir.holder.Document;
import ir.holder.DocumentCollectionStats;
import ir.vsm.DocumentVector;
import ir.vsm.util.DocumentVectorBuilder;

import java.util.Map;

/**
 * Abstraction of a Clustering algorithm.
 * Template design pattern is followed while supporting multiple input formats.
 * Concrete clustering algorithms can extend from this class and implement the corresponding strategy.
 * @author Phani
 *
 */
public abstract class Clusterer {

	/**
	 * Clusters the provided document collection.
	 * 
	 * @param documentList Collection to be clustered.
	 * @return Populated DocumentClustersResult.
	 */
	public DocumentClustersResult clusterDocuments(Map<Integer, Document> documentList) {
		// Compute DocumentCollection Statistics information, for ex inverse document frequency (idf).
		DocumentCollectionStats docCollectionStats = new DocumentCollectionStats(documentList.values());

		// Build Document Vectors
		Map<Integer, DocumentVector> documentVectorMap = DocumentVectorBuilder.buildDocVectorsByTfIdf(docCollectionStats);

		// Deligate
		return clusterDocumentVectors(documentVectorMap);
	}

	/**
	 * Clusters the passed in document vector collection.
	 * 
	 * @param docVectorList DocVectors to be clustered.
	 * @return Populated DocumentClustersResult.
	 */
	public abstract DocumentClustersResult clusterDocumentVectors(Map<Integer, DocumentVector> docVectorList);
}
