package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LongestSubsequence {

	public static int[][] getLCSLength(int[] seq1, int[] seq2) {
		int n = seq1.length, m = seq2.length;
		int[][] seqLength = new int[n + 1][m + 1];
		for (int i = 0; i <= m; i++) {
			seqLength[0][i] = 0;
		}
		for (int i = 0; i <= n; i++) {
			seqLength[i][0] = 0;
		}
		int match;
		int mismatch;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				match = seqLength[j - 1][i - 1] + 1;
				mismatch = Math.max(seqLength[j - 1][i], seqLength[j][i - 1]);
				if (seq1[j - 1] == seq2[i - 1]) {
					seqLength[j][i] = Math.max(match, mismatch);
				} else {
					seqLength[j][i] = mismatch;
				}
			}
		}
		return seqLength;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner(System.in);
		int n = fs.nextInt();
		int[] seq1 = new int[n];
		for (int i = 0; i < n; i++) {
			seq1[i] = fs.nextInt();
		}
		int m = fs.nextInt();
		int[] seq2 = new int[m];
		for (int i = 0; i < m; i++) {
			seq2[i] = fs.nextInt();
		}
		int[][] seqLength = getLCSLength(seq1, seq2);
		System.out.println(seqLength[n][m]);
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
