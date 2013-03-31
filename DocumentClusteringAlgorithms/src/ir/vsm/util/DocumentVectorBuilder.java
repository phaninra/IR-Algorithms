package ir.vsm.util;

import ir.holder.Document;
import ir.holder.DocumentCollectionStats;
import ir.vsm.DocumentVector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Converts list of Document instances to their corresponding Document Vector format using tf-idf information.
 * @author Phani
 *
 */
public class DocumentVectorBuilder {

	/**
	 * Converts documents in the collection to Document Vector format.
	 * @param dc Collection of documents and their stats.
	 * @return Map of Document Vectors <Doc id, Document Vector Instance>
	 */
	public static Map<Integer, DocumentVector> buildDocVectorsByTfIdf(DocumentCollectionStats dc) {
		Map<Integer, DocumentVector> documentVectorMap = new HashMap<Integer, DocumentVector>();
		double[] weights;
		List<String> terms = dc.getSortedTerms();
		int i = 0;
		for (Document d : dc.getDocs().values()) {
			weights = new double[terms.size()];
			for (String term : terms) {
				weights[i++] = VectorSpaceModelWeightsUtil.getTfIdfWeight(d.getDefaultTfWeight(term), dc.getDefaultIdfWeight(term));
			}
			i = 0;
			documentVectorMap.put(d.getId(), new DocumentVector(weights, d.getId()));
		}
		return documentVectorMap;
	}
	
	
}
