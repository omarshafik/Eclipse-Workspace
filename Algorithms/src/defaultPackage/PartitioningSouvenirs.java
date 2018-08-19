package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PartitioningSouvenirs {

	public static int check(int[] souv, int partition) {
		int status = 0;
		int[][] table = new int[partition + 1][souv.length + 1];
		int value;
		int count = 0;
		for (int i = 1; i <= souv.length; i++) {
			for (int s = 1; s <= partition; s++) {
				table[s][i] = table[s][i - 1];
				if (souv[i - 1] <= s) {
					value = table[s - souv[i - 1]][i - 1] + souv[i - 1];
					if (value <= s && value > table[s][i]) {
						table[s][i] = value;
					}
				}
			}
			if (table[partition][i] == partition) {
				count++;
			}
		}
		if (count >= 3) {
			status = 1;
		}
		return status;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);
		int n = fs.nextInt();
		int[] souv = new int[n];
		int sum = 0;
		for (int i = 0; i < n; i++) {
			souv[i] = fs.nextInt();
			sum += souv[i];
		}
		int partition = sum / 3;
		int result;
		if (partition * 3 == sum)
			result = check(souv, partition);
		else
			result = 0;
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
