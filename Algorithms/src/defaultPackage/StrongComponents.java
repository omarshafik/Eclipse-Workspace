package defaultPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

public class StrongComponents {

	static public int vNumber = 875715;

	static Vector<Vector<Integer>> rVertices = new Vector<Vector<Integer>>(vNumber);
	static Vector<Vector<Integer>> vertices = new Vector<Vector<Integer>>(vNumber);

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

	class Node {
		Vector<Integer> tails = new Vector<Integer>();
		Vector<Integer> heads = new Vector<Integer>();
	}

	static int[][] finish_time = new int[vNumber][2];
	static int t = 0;
	static boolean[] explored1 = new boolean[vNumber];
	static boolean[] explored2 = new boolean[vNumber];
	static int[] leader = new int[vNumber];
	static int sourceVertexIndex;
	static int sccCounter = 0;
	static Vector<Vector<Integer>> sccSize = new Vector<Vector<Integer>>();

	public static void DFS_Loop_reverse() {

		for (int i = vertices.size() - 1; i > 0; i--) {
			if (explored1[i] == false) {
				sourceVertexIndex = i;
				iterativeDFS1(i);
			}
		}

		sortbyColumn(finish_time, 0);

	}

	public static void DFS_Loop() {

		for (int i = vertices.size() - 1; i > 0; i--) {
			if (explored2[finish_time[i][1]] == false) {
				sourceVertexIndex = finish_time[i][1];
				iterativeDFS2(finish_time[i][1]);
			}
		}

	}

	public static void iterativeDFS1(int vertexIndex) {
		boolean deadend = true;
		Stack<Integer> stack = new Stack<>();
		stack.push(vertexIndex);
		while (stack.empty() == false) {

			deadend = true;
			vertexIndex = stack.peek();

			Vector<Integer> v = rVertices.elementAt(vertexIndex);
			for (int i = 0; i < v.size(); i++) {
				if (explored1[v.elementAt(i)] == false) {
					deadend = false;
					stack.push(v.elementAt(i));
					explored1[v.elementAt(i)] = true;
				}
			}
			if (deadend == true) {
				stack.pop();
				t++;
				finish_time[vertexIndex][0] = t;
				finish_time[vertexIndex][1] = vertexIndex;
			}
		}
	}

	public static void iterativeDFS2(int vertexIndex) {
		Stack<Integer> stack = new Stack<>();
		stack.push(vertexIndex);
		sccSize.add(new Vector<Integer>());
		while (stack.empty() == false) {

			vertexIndex = stack.peek();
			stack.pop();
			leader[vertexIndex] = sourceVertexIndex;
			if (explored2[vertexIndex] == false) {
				explored2[vertexIndex] = true;
				sccSize.elementAt(sccCounter).add(vertexIndex);
			}

			Vector<Integer> v = vertices.elementAt(vertexIndex);
			for (int i = 0; i < v.size(); i++) {
				if (explored2[v.elementAt(i)] == false) {
					stack.push(v.elementAt(i));
				}
			}

		}
		sccCounter++;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File file = new File("/home/omar/Downloads/SCC.txt");

		FastScanner fs = new FastScanner(file);

//		Node[] graph = new Node[875715];
//		StrongComponents sc = new StrongComponents();
//		for (int i = 0; i < graph.length; i++) {
//			graph[i] = sc.new Node();
//		}
//
//		for (int i = 1; i < 5105044; i++) {
//			int head = fs.nextInt();
//			int tail = fs.nextInt();
//			graph[head].tails.add(tail);
//			graph[tail].heads.add(head);
//		}
//		
//		DFS_Loop_reverse(graph);
//		System.out.println(finish_time[0][0]);

		for (int i = 0; i < vNumber; i++) {
			vertices.add(new Vector<Integer>());
		}
		for (int i = 0; i < vNumber; i++) {
			rVertices.add(new Vector<Integer>());
		}

		for (int i = 1; i > 0; i++) {
			try {
				int head = fs.nextInt();
				int tail = fs.nextInt();
				if (head != tail) {
					vertices.elementAt(head).add(tail);
					rVertices.elementAt(tail).add(head);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				i = -2;
			}
		}

		DFS_Loop_reverse();
		DFS_Loop();

		int[] arr = new int[sccSize.size()];
		for (int i = 0; i < sccSize.size(); i++) {
			arr[i] = sccSize.elementAt(i).size();
		}
		Arrays.sort(arr);

		for (int i = sccSize.size() - 1; i > sccSize.size() - 6; i--) {
			System.out.println(arr[i]);
		}

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

//public static void DFS_Loop_reverse(Node[] graph) {
//
//	for (int i = 0; i < graph.length; i++) {
//		if (explored1[i] == false) {
//			sourceVertexIndex = i;
//			DFS_reverse(graph, i);
//		}
//	}
//
//	sortbyColumn(finish_time, 0);
//
//}
//

//public static void DFS_reverse(int vertexIndex) {
//	explored1[vertexIndex] = true;
//	for (int i = 0; i < rVertices.elementAt(vertexIndex).size(); i++) {
//		if (explored1[rVertices.elementAt(vertexIndex).elementAt(i)] == false) {
//			DFS_reverse(rVertices.elementAt(vertexIndex).elementAt(i));
//
//		}
//	}
//	t++;
//	finish_time[vertexIndex][0] = t;
//	finish_time[vertexIndex][1] = vertexIndex;
//}
//
//public static void DFS_reverse(Node[] graph, int vertexIndex) {
//	explored1[vertexIndex] = true;
//	for (int i = 0; i < graph[vertexIndex].heads.size(); i++) {
//		if (explored1[graph[vertexIndex].heads.elementAt(i)] == false) {
//			DFS_reverse(graph, graph[vertexIndex].heads.elementAt(i));
//
//		}
//	}
//	t++;
//	finish_time[vertexIndex][0] = t;
//	finish_time[vertexIndex][1] = vertexIndex;
//}
//
//public static void DFS(Node[] graph, int vertexIndex) {
//	explored1[vertexIndex] = true;
//	leader[vertexIndex] = sourceVertexIndex;
//	for (int i = 0; i < graph[vertexIndex].tails.size(); i++) {
//		if (explored1[graph[vertexIndex].tails.elementAt(i)] == false) {
//			DFS(graph, graph[vertexIndex].tails.elementAt(i));
//		}
//	}
//}
