package ir.vsm.util;


/**
 * Supports computation of different tf-idf weighting schemes. Currently, only logarithmic weights are included.
 * @author Phani
 *
 */
public class VectorSpaceModelWeightsUtil {

	/**
	 * Combines tf and idf weights
	 * @param tfWeight term frequency weight
	 * @param idfWeight idf weight
	 * @return combined tf-idf weight
	 */
	public static double getTfIdfWeight(double tfWeight, double idfWeight) {
		return tfWeight * idfWeight;
	}
	
	/**
	 * Logarithmic idf = log (N/Df)
	 * @param df document frequency
	 * @param collectionSize collection size
	 * @return idf-weight
	 */
	public static double getIdfWeightLogarithmic(int df, int collectionSize){
		return Math.log10((double) collectionSize / df);
	}
	
	/**
	 * logarithmic tf= 0 | (1+log(tf))
	 * @param tf term frequency
	 * @return tf weight
	 */
	public static double computeTfWeightLogarithmic(int tf){
		if(tf > 0){
			return (1 + Math.log10(tf));
		}else{
			return tf;
		}
	}
}
