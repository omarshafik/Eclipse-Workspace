package defaultPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Vector;



public class StrongComponents {

	int[] finish_time = new int[875715];
	int t = 0;
	boolean[] explored = new boolean[875715];
	int[] leader = new int[875715];
	int s;

	public static void DFS_Loop_r(Vector<Integer>[] G) {

	}

	public static void DFS(Vector<Integer>[] G, Vector<Integer> v) {

	}

	public static void sortbyColumn(int arr[][], int col) {
		// Using built-in sort function Arrays.sort
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			// Compare values according to columns
			public int compare(final int[] entry1, final int[] entry2) {

				// To sort in descending order revert
				// the '>' Operator
				if (entry1[col] > entry2[col])
					return 1;
				else
					return -1;
			}
		}); // End of function call sort().
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File file = new File("/home/omar/Downloads/_410e934e6553ac56409b2cb7096a44aa_SCC.txt");

		FastScanner fs = new FastScanner(file);

		int[][] edge = new int[5105044][2];
		int[][] edgeR = new int[5105044][2];
		int[] vertexAt = new int[875715];

		edgeR[0][0] = 0;
		edgeR[0][1] = 0;

		for (int i = 1, j = 0; i < 5105044; i++) {
			edge[i][0] = fs.nextInt();
			edge[i][1] = fs.nextInt();
			edgeR[i][0] = edge[i][0];
			edgeR[i][1] = edge[i][1];
			if (edge[i][0] > j) {
				j = edge[i][0];
				vertexAt[j] = i;
			}
		}

		sortbyColumn(edgeR, 1);

//		Vector<Integer>[] v;
//		v = new Vector[875715];
//		int vertex = fs.nextInt();
//		int val = fs.nextInt();
//		for (int i = 1; i < 875715; i++) {
//			v[i] = new Vector<Integer>();
//			while (vertex == i) {
//				try {
//					v[i].add(val);
//					vertex = fs.nextInt();
//					val = fs.nextInt();
//				} catch (Exception e) {
//					vertex = i + 1;
//				}
//			}
//		}
		System.out.println(1);
	}

	static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(File file) {
			try {
				br = new BufferedReader(new FileReader(file));
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
