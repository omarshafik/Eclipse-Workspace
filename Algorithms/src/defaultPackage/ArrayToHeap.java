package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ArrayToHeap {

	static int counter = 0;

	static StringBuilder output = new StringBuilder();

	private static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	public static int Parent(int index) {
		return ((int) (index - 1) / 2);
	}

	public static void buildMinHeap(int index, int[] arr) {
		int first_index = index;
		int val = arr[index];
		int val_index = index;
		while (index > 0) {
			index = Parent(index);
			if (arr[index] > val) {
				val = arr[index];
				val_index = index;
			}
		}
		if (first_index != val_index) {
			counter++;
			swap(arr, val_index, first_index);
			output.append(val_index).append(" ").append(first_index).append("\n");
		}
	}

	public static void ConvertAndPrint(int[] arr) {
		for (int i = arr.length - 1; i > 0; i--) {
			buildMinHeap(i, arr);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);

		int n = fs.nextInt();
		int[] arr = new int[n];
		for (int j = 0; j < n; j++) {
			arr[j] = fs.nextInt();
		}

		ConvertAndPrint(arr);
		System.out.println(counter);
		System.out.println(output);
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
