package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EditDistance {

	public static int[][] getEditDistance(char[] str1, char[] str2) {
		int n1 = str1.length, n2 = str2.length;
		int[][] editDist = new int[n1+1][n2+1];
		for (int i = 0; i <= n1; i++) {
			editDist[i][0] = i;
		}
		for (int i = 0; i <= n2; i++) {
			editDist[0][i] = i;
		}
		int insertion, deletion, match, mismatch;
		for (int j = 1; j <= n2; j++) {
			for (int i = 1; i <= n1; i++) {
				insertion = editDist[i][j - 1] + 1;
				deletion = editDist[i - 1][j] + 1;
				match = editDist[i - 1][j - 1];
				mismatch = editDist[i - 1][j - 1] + 1;
				if (String.valueOf(str1[i-1]).equals(String.valueOf(str2[j-1]))) {
					editDist[i][j] = Math.min(match, Math.min(insertion, deletion));
				} else {
					editDist[i][j] = Math.min(mismatch, Math.min(insertion, deletion));
				}
			}
		}
		return editDist;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);
		char[] str1 = fs.next().toCharArray();
		char[] str2 = fs.next().toCharArray();
		int[][] editDist = getEditDistance(str1, str2);
		System.out.println(editDist[str1.length][str2.length]);
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
