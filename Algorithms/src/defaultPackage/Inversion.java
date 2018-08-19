package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Inversion {

	public static long sortAndSplitCount(int[] arr, int low, int high) {
		int n = high - low;
		if (n < 2) {
			if (arr[low] <= arr[high]) {
				return 0;
			} else {
				Arrays.sort(arr, low, high + 1);
				return 1;
			}
		} else {
			int i = low;
			int j = n / 2 + low + 1;
			int m = j;
			long count = 0;
			while (i <= (n / 2) + low && j <= high) {
				if (arr[i] < arr[j]) {
					i++;
				} else if (arr[i] > arr[j]) {
					count += m - i;
					j++;
				} else {
					count += m - i - 1;
					j++;
				}
			}
			Arrays.sort(arr, low, high + 1);
			return count;
		}

	}

	public static BigInteger getInversionsNo(int[] arr, int low, int high) {
		BigInteger x = new BigInteger("0");
		BigInteger y = new BigInteger("0");
		BigInteger z = new BigInteger("0");
		if (high - low < 2) {
			z = z.add(new BigInteger(Long.toString(sortAndSplitCount(arr, low, high))));
		} else {
			x = x.add(getInversionsNo(arr, low, (high - low) / 2 + low));
			y = y.add(getInversionsNo(arr, (high - low) / 2 + low + 1, high));
			z = z.add(new BigInteger(Long.toString(sortAndSplitCount(arr, low, high))));
		}
		return x.add(y).add(z);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);
		int n = fs.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = fs.nextInt();
		}
		String result = getInversionsNo(a, 0, a.length - 1).toString();
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
