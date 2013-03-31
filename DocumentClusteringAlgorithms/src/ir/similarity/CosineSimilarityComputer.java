package ir.similarity;

/**
 * Computes the cosine similarity between two document vectors.
 * Cosine similarity between two document (A, B) = (A . B) / (|A||B|)
 * Check the text document for clear description.
 * @author Phani
 *
 */
public class CosineSimilarityComputer {

	public static double getCosineSimilarity(double[] one, double[] two) {
		double numerator = 0;
		double denom1 = 0;
		double denom2 = 0;
		for (int i = 0; i < one.length; i++) {
			numerator = numerator + one[i] * two[i];
			denom1 = denom1 + Math.pow(one[i], 2);
			denom2 = denom2 + Math.pow(two[i], 2);
		}
		return numerator / (Math.sqrt(denom1 * denom2));
	}
	
}
