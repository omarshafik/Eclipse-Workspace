package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrimitiveCalc {

	public static int[] calc(int n) {
		int[] route = new int[n + 1];
		route[0] = 0;
		route[1] = 0;
		for (int i = 2; i <= n; i++) {
			if (i % 3 == 0 && i % 2 == 0) {
				route[i] = 1 + Math.min(route[i / 3], Math.min(route[i - 1], route[i / 2]));
			} else if (i % 3 == 0) {
				route[i] = 1 + Math.min(route[i - 1], route[i / 3]);
			} else if (i % 2 == 0) {
				route[i] = 1 + Math.min(route[i - 1], route[i / 2]);
			} else {
				route[i] = 1 + route[i - 1];
			}
		}
		return route;
	}

	public static int[] calcAndPrint(int[] steps) {
		int n = steps.length - 1;
		int currentStep = steps[n];
		int currentNu[] = new int[currentStep + 1];
		int j = currentStep;
		currentNu[j] = n;
		for (int i = n - 1; i > 0 && j > 0; i--) {
			if ((steps[i] + 1) == currentStep
					&& (i * 3 == currentNu[j] || i * 2 == currentNu[j] || i + 1 == currentNu[j])) {
				j--;
				currentNu[j] = i;
				currentStep = steps[i];
			}
		}
		return currentNu;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);
		int n = fs.nextInt();
		int[] stepsCount = calc(n);
		int[] steps = calcAndPrint(stepsCount);
		System.out.println(stepsCount[n]);
		for (int i = 0; i <= stepsCount[n]; i++) {
			System.out.print(steps[i] + " ");
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
