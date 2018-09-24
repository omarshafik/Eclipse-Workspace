package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class QuickSort_Analysis {
	private static Random random = new Random();
	private static int count = 0;

	private static int partition2(int[] a, int l, int r) {
		int x = a[l];
		int j = l;
		for (int i = l + 1; i <= r; i++) {
			if (a[i] <= x) {
				j++;
				int t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		}
		int t = a[l];
		a[l] = a[j];
		a[j] = t;
		return j;
	}

	private static void randomizedQuickSort(int[] a, int l, int r) {
		if (l >= r) {
			return;
		}
		count += r-l;
		int k = (r-l)/2 + l;
		if(a[k] < a[r]) {
			if(a[r] < a[l]) {
				k = r;
			} else if (a[k] < a[l]){
				k = l;
			}
		} else {
			if(a[l] < a[r]) {
				k = r;
			} else if (a[l] < a[k]){
				k = l;
			}
		}
		int t = a[l];
		a[l] = a[k];
		a[k] = t;
		// use partition3
		int m = partition2(a, l, r);
		randomizedQuickSort(a, l, m - 1);
		randomizedQuickSort(a, m + 1, r);
	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		randomizedQuickSort(a, 0, n - 1);
		System.out.println(count);
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
