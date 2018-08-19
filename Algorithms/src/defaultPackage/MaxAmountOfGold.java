package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaxAmountOfGold {

	public static int getMaxGold(int[] items, int maxWeight) {
		int[][] values = new int[maxWeight + 1][items.length + 1];
		int value;
		for (int i = 1; i <= items.length; i++) {
			for (int w = 1; w <= maxWeight; w++) {
				values[w][i] = values[w][i - 1];
				if (items[i - 1] <= w) {
					value = values[w - items[i - 1]][i - 1] + items[i - 1];
					if (value > values[w][i]) {
						values[w][i] = value;
					}
				}
			}
		}
		return values[maxWeight][items.length];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);
		int maxWeight = fs.nextInt();
		int n = fs.nextInt();
		int[] items = new int[n];
		for (int i = 0; i < n; i++) {
			items[i] = fs.nextInt();
		}
		int result = getMaxGold(items, maxWeight);
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
