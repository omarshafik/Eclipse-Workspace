package defaultPackage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Knapsack {

	public static void sortbyColumn(double arr[][], int col) {
		// Using built-in sort function Arrays.sort
		Arrays.sort(arr, new Comparator<double[]>() {

			@Override
			// Compare values according to columns
			public int compare(final double[] entry1, final double[] entry2) {

				// To sort in descending order revert
				// the '>' Operator
				if (entry1[col] > entry2[col])
					return 1;
				else
					return -1;
			}
		}); // End of function call sort().
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int maxWeight = scanner.nextInt();
		double[][] weight_ratio = new double[n][2];
		double value;
		for (int i = 0; i < n; i++) {
			value = scanner.nextInt();
			weight_ratio[i][0] = scanner.nextInt();
			weight_ratio[i][1] = value / weight_ratio[i][0];
		}
		scanner.close();
		sortbyColumn(weight_ratio, 1);
		double currentValue = 0;
		for (int i = n - 1; i >= 0 && maxWeight != 0; i--) {
			if (weight_ratio[i][0] <= maxWeight) {
				maxWeight -= weight_ratio[i][0];
				currentValue += weight_ratio[i][0] * weight_ratio[i][1];
			} else {
				currentValue += maxWeight * weight_ratio[i][1];
				maxWeight = 0;
			}
		}
		System.out.println(currentValue);
	}

}
