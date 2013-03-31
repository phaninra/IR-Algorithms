package ir.util;

import java.text.DecimalFormat;

/**
 * Utility number formatting class.
 * @author Phani
 *
 */
public class NumberFormatUtil {

	// 3 digits
	public static double format(double d){
		DecimalFormat formatter = new DecimalFormat("#.###");
		return new Double(formatter.format(d));
	}
	
	//
	public static double[] truncateTo3Decimals(double[] d){
		double[] d1 = new double[d.length];
		DecimalFormat formatter = new DecimalFormat("#.###");
		for(int i = 0; i < d.length; i++){
			d1[i] = new Double(formatter.format(d[i]));
		}
		return d1;
	}
}
