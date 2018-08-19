package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ClosestPair {

	public static void sortbyColumn(int arr[][], int col) {
		// Using built-in sort function Arrays.sort
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			// Compare values according to columns
			public int compare(final int[] entry1, final int[] entry2) {

				// To sort in descending order revert
				// the '>' Operator
				if (entry1[col] > entry2[col])
					return 1;
				else
					return -1;
			}
		}); // End of function call sort().
	}

	public static double SplitPair(int[][] px, int[][] py, double delta, int low, int high) {
		int xb = (high - low) / 2 + low;
		double dist;
		boolean found = false;
		int c = -1;
		int[][] sy = new int[high - low + 1][2];
		for (int i = low; i <= high; i++) {
			if (i != xb) {
				dist = Math.sqrt(Math.pow(py[i][0] - px[xb][0], 2) + Math.pow(py[i][1] - px[xb][1], 2));
				if (dist < delta) {
					found = true;
					c++;
					sy[c][0] = py[i][0];
					sy[c][1] = py[i][1];
				}
			}
		}
		if (found == false) {
			return delta;
		}
		double best = delta;
		for (int i = 0; i < c; i++) {
			for (int j = 1; j < ((c + 1 - i < 7) ? c + 1 - i : 7); j++) {
				dist = Math.sqrt(Math.pow(sy[i][0] - sy[j + i][0], 2) + Math.pow(sy[i][1] - sy[j + i][1], 2));
				if (dist < best) {
					best = dist;
				}
			}
		}
		return best;
	}

	public static double BruteForce(int[][] px, int low, int high) {
		double minDist = 4000000000D;
		double dist;
		for (int i = low; i < high; i++) {
			for (int j = 1; i + j <= high; j++) {
				dist = Math.sqrt(Math.pow(px[i][0] - px[i + j][0], 2) + Math.pow(px[i][1] - px[i + j][1], 2));
				if (dist < minDist) {
					minDist = dist;
				}
			}
		}
		return minDist;
	}

	public static double getClosestPair(int[][] px, int[][] py, int low, int high) {

		int n = (low - high) + 1;
		if (n <= 3) {
			return BruteForce(px, low, high);
		} else {
			double a = getClosestPair(px, py, low, (high - low) / 2 + low);
			double b = getClosestPair(px, py, (high - low) / 2 + low + 1, high);
			double delta = ((a > b) ? a : b);
			double c = SplitPair(px, py, delta, low, high);
			return c;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);
		int n = fs.nextInt();
		int[][] points = new int[n][2];
		int[][] px = new int[n][2];
		int[][] py = new int[n][2];
		for (int i = 0; i < n; i++) {
			points[i][0] = fs.nextInt();
			points[i][1] = fs.nextInt();
			px[i][0] = points[i][0];
			py[i][0] = points[i][0];
			px[i][1] = points[i][1];
			py[i][1] = points[i][1];
		}
		sortbyColumn(px, 0);
		sortbyColumn(py, 1);
		double resut = getClosestPair(px, py, 0, n - 1);
		System.out.println(resut);
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
