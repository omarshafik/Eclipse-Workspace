package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ChangeProblem {

	public static int getMinChange(int n, int[] coins) {
		int[] change = new int[n+1];
		change[0] = 0;
		int i;
		for (i = 1; i <= n; i++) {
			if (i >= coins[2]) {
				change[i] = 1 + Math.min(change[i - coins[2]], Math.min(change[i - coins[0]], change[i - coins[1]]));
			} else if (i >= coins[1]) {
				change[i] = 1 + Math.min(change[i - coins[0]], change[i - coins[1]]);
			} else {
				change[i] = 1 + change[i - 1];
			}
		}
		return change[i-1];
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner(System.in);
		int n = fs.nextInt();
		int[] coins = { 1, 3, 4 };
		int result = getMinChange(n, coins);
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
