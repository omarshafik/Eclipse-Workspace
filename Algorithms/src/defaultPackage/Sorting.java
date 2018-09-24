package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Sorting {
	private static Random random = new Random();

	private static int[] partition3(int[] a, int l, int r) {
		// write your code here
		int x = a[l];
		int j = l - 1;
		int p = r + 1;
		int c = l;
		int i;
		for (i = l; c <= r; c++) {
			if (a[i] < x) {
				j++;
				int t = a[i];
				a[i] = a[j];
				a[j] = t;
				i++;
			} else if (a[i] == x) {
				p--;
				int t = a[i];
				a[i] = a[p];
				a[p] = t;
			} else {
				i++;
			}
		}
		i = p - j - 1;
		c = 0;
		while (c < i && c < (r - p + 1)) {
			c++;
			a[r + 1 - c] = a[j + c];
			a[j + c] = x;
		}

		int m1 = j;
		int m2 = r - i + 1;
		int[] m = { m1, m2 };
		return m;
	}

	private static void randomizedQuickSort(int[] a, int l, int r) {
		if (l >= r) {
			return;
		}
		int k = random.nextInt(r - l);
		int t = a[l];
		a[l] = a[k + l];
		a[k + l] = t;
		// use partition3
		int[] m = partition3(a, l, r);
		randomizedQuickSort(a, l, m[0]);
		randomizedQuickSort(a, m[1], r);
	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		randomizedQuickSort(a, 0, n - 1);
		for (int i = 0; i < n; i++) {
			System.out.print(a[i] + " ");
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
