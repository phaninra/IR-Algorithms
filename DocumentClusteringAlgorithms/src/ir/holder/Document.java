package ir.holder;

import ir.vsm.util.VectorSpaceModelWeightsUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a document. Contains the id, text, and tf information.
 * @author Phani
 *
 */
public class Document {

	/*
	 * Document id
	 */
	private int id;
	/*
	 * Text in the document.
	 */
	private String text;
	/*
	 * <K, V> = <Term, Frequency in the document>
	 */
	private Map<String, Integer> termFrequencyMap = new HashMap<String, Integer>();

	public Document(String txt, int id) {
		this.text = txt.toLowerCase();
		this.id = id;
		computeTermFrequencies();
	}

	private void computeTermFrequencies() {
		String[] terms = text.split(" ");
		Integer tf;
		for (String term : terms) {
			tf = termFrequencyMap.get(term);
			if (tf == null) {
				termFrequencyMap.put(term, 1);
			} else {
				termFrequencyMap.put(term, tf + 1);
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Integer> getTfs() {
		return termFrequencyMap;
	}

	public void setTfs(Map<String, Integer> tfs) {
		this.termFrequencyMap = tfs;
	}

	public double getLogarithmicTfWeight(String term){
		if(termFrequencyMap.containsKey(term)){
			return VectorSpaceModelWeightsUtil.computeTfWeightLogarithmic(termFrequencyMap.get(term));			
		}else{
			return 0;
		}
	}
	
	public double getDefaultTfWeight(String term) {
		return getLogarithmicTfWeight(term);
	}

}
