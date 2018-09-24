package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Node {

	static Node root = new Node();

	Node[] children = new Node[1];
	Node parent = root;

	public void addParent(Node parent) {
		this.parent = parent;
		parent.addChild(this);
	}

	public void addChild(Node child) {
		child.parent = this;
		if (children[0] == null) {
			children[0] = child;
			return;
		}
		Node[] temp = new Node[children.length];
		for (int i = 0; i < children.length; i++) {
			temp[i] = children[i];
		}
		children = new Node[children.length + 1];
		int j;
		for (j = 0; j < temp.length; j++) {
			children[j] = temp[j];
		}
		children[j] = child;
	}

	public static int getTreeDistance(Node root, int n) {
		Node[] queue = new Node[n + 1];
		for (int i = 0; i <= n; i++) {
			queue[i] = new Node();
		}
		int front = 0, back = 0;
		queue[back] = root;
		back++;
		int counter = 0;
		Node node = new Node();
		int h = 0;
		int m = queue[front].parent.children.length;
		while (front != back) {
			if (h < 1) {
				h = m;
				m = 0;
				counter++;
			}
			h--;
			if (queue[front].children[0] != null)
				m += queue[front].children.length;
			node = queue[front];
			front++;
			if (front == queue.length) {
				front = 0;
			}

			for (int i = 0; i < node.children.length; i++) {
				if (node.children[i] != null) {
					queue[back] = node.children[i];
					back++;
					if (back == queue.length) {
						back = 0;
					}
				}
			}
		}
		return counter;
	}

	public static int getTreeHeight(int[] tree) {
		int[] height = new int[tree.length];
		int k;
		int h = 0;

		for (int i = 0; i < tree.length; i++) {

			if (height[i] == 0) {
				k = i;
				h = 0;
				while (k != -1 && height[k] == 0) {
					h++;
					k = tree[k];
				}
				if (k != -1) {
					height[i] = h + height[k];
				}
				k = i;
				while (k != -1 && height[k] == 0) {
					height[k] = h--;
					k = tree[k];
				}
			}
		}
		int max = 0;
		for (int i = 0; i < tree.length; i++) {
			if (height[i] > max) {
				max = height[i];
			}
		}

		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);
//		Scanner s = new Scanner(System.in);
		int n = fs.nextInt();
		int[] input = new int[n];
		for (int i = 0; i < n; i++) {
			input[i] = fs.nextInt();
		}
//		Node[] tree = new Node[n];
//		for (int i = 0; i < n; i++) {
//			tree[i] = new Node();
//		}
//		for (int i = 0; i < n; i++) {
//			if (input[i] != -1) {
//				tree[i].addParent(tree[input[i]]);
//			} else {
//				tree[i].addParent(root);
//			}
//		}
//		System.out.println(getTreeDistance(root.children[0], n));

		System.out.println(getTreeHeight(input));

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
