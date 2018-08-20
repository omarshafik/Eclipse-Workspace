package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BSUsession {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);
		int n = fs.nextInt();
		int[] time = new int[n * 2];
		for (int i = 0; i < n * 2; i++) {
			time[i] = fs.nextInt();
		}
		Arrays.sort(time);
		int mismatch = 1;
		int match = 0;
		int current = time[0];
		for (int j = 1; j < 2 * n; j++) {
			if (time[j] != current) {
				mismatch++;
				if (mismatch <= n)
					current = time[j];
			} else {
				match++;
			}
		}
		if (match == n) {
			System.out.println(current);
		} else {
			System.out.println("-1");
		}

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
