package ir.holder;


import ir.vsm.util.VectorSpaceModelWeightsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Computes the term statistics of the collection.
 * Readies the Document frequency information used in tf-idf computation.
 * @author Phani
 *
 */
public class DocumentCollectionStats {

	/**
	 * <K, V> = <Document id, Document object>
	 */
	private Map<Integer, Document> docMap = new HashMap<Integer, Document>();
	/**
	 * <K, V> = <Term in the collection, Document frequency>
	 */
	private Map<String, Integer> termDocumentFrequency = new TreeMap<String, Integer>();

	public DocumentCollectionStats(Collection<Document> docs) {
		for (Document doc : docs) {
			addDocument(doc);
		}
	}

	public void addDocument(Document doc) {
		docMap.put(doc.getId(), doc);
		updateDictionary(doc);
	}

	/**
	 * Updates the document frequency information for all the terms present in the provided document.
	 * @param doc Document
	 */
	private void updateDictionary(Document doc) {
		Integer df = null;
		for (String term : doc.getTfs().keySet()) {
			df = termDocumentFrequency.get(term);
			if (df == null) {
				termDocumentFrequency.put(term, 1);
			} else {
				termDocumentFrequency.put(term, df + 1);
			}
		}
	}

	/**
	 * Returns the Sorted term list
	 * @return
	 */
	public List<String> getSortedTerms() {
		ArrayList<String> terms = new ArrayList<String>();
		terms.addAll(termDocumentFrequency.keySet());
		return terms;
	}

    /**
     * @return Document map : <doc id, document object>
     */
	public Map<Integer, Document> getDocs() {
		return docMap;
	}

	/**
	 * Returns idf weight
	 * @param term
	 * @return
	 */
	public double getDefaultIdfWeight(String term) {
		return getIdfWeightLogarithmic(term);
	}
	
	/**
	 * Delegates call to VectorSpaceModelWeightsUtil
	 * @param term Term
	 * @return Idf Weight logarithmic.
	 */
	public double getIdfWeightLogarithmic(String term){
		if (termDocumentFrequency.containsKey(term)) {
			return VectorSpaceModelWeightsUtil.getIdfWeightLogarithmic(docMap.size(), termDocumentFrequency.get(term));
		} else {
			return 0;
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Dictionary: " + termDocumentFrequency);
		sb.append(", Doc size: " + docMap.size() + ", doc ids: " + docMap.keySet());
		return sb.toString();
	}
}
