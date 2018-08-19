package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DiscreteKnapsack {

	public static int getMaxNoRep(int[] values, int[] weights, int maxWeight) {
		int[][] knapsack_w_i = new int[maxWeight + 1][values.length + 1];
		int value = 0;
		for (int i = 1; i <= values.length; i++) {
			for (int w = 0; w <= maxWeight; w++) {
				knapsack_w_i[w][i] = knapsack_w_i[w][i - 1];
				if (weights[i - 1] <= w) {
					value = knapsack_w_i[w - weights[i - 1]][i - 1] + values[i - 1];
					if (value > knapsack_w_i[w][i]) {
						knapsack_w_i[w][i] = value;
					}
				}
			}
		}

		return knapsack_w_i[maxWeight][values.length];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);
		int n = fs.nextInt();
		int maxWeight = fs.nextInt();
		int[] values = new int[n];
		int[] weights = new int[n];
		for (int i = 0; i < n; i++) {
			weights[i] = fs.nextInt();
			values[i] = fs.nextInt();
		}
		int result = getMaxNoRep(values, weights, maxWeight);
		System.out.println(result);

	}

	static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(InputStream stream) {
			try {
				br = new BufferedReader(new InputStreamReader(stream));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

}
