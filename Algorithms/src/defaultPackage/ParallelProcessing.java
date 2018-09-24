package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ParallelProcessing {

	public class Heap {

		private int maxSize = 0;
		private int size = 0;
		private int id[];

		public void setId(int i, int id) {
			this.id[i] = id;
		}

		public int id(int i) {
			return id[i];
		}

		public int maxSize() {
			return maxSize;
		}

		public int size() {
			return size;
		}

		long[] H;

		Heap(int maxSize) {
			H = new long[maxSize];
			id = new int[maxSize];
			this.maxSize = maxSize;
		}

		private void swap(int index1, int index2) {
			long temp = this.H[index1];
			this.H[index1] = this.H[index2];
			this.H[index2] = temp;
			temp = this.id[index1];
			this.id[index1] = this.id[index2];
			this.id[index2] = (int) temp;
		}

		public long[] getH() {
			return H;
		}

		public int Parent(int index) {
			return ((int) (index - 1) / 2);
		}

		public int LeftChild(int index) {
			return 2 * index + 1;
		}

		public int RightChild(int index) {
			return 2 * index + 2;
		}

		public void ShiftUp(int index) {
			while (index > 0 && H[this.Parent(index)] >= H[index]) {
				if (H[this.Parent(index)] == H[index] && id[this.Parent(index)] < id[index]) {
				} else {
					swap(this.Parent(index), index);
					index = this.Parent(index);
				}
			}
		}

		public void ShiftDown(int index) {
			int minIndex = index;
			boolean recurse = true;
			while (recurse) {
				recurse = false;
				int left = this.LeftChild(index);
				if (left < size) {
					if (H[left] < H[minIndex]) {
						minIndex = left;
						recurse = true;
					} else if (H[left] == H[minIndex] && id(left) < id(minIndex)) {
						minIndex = left;
						recurse = true;
					}
				}
				int right = this.RightChild(index);
				if (right < size) {
					if (H[right] < H[minIndex]) {
						minIndex = right;
						recurse = true;
					} else if (H[right] == H[minIndex] && id(right) < id(minIndex)) {
						minIndex = right;
						recurse = true;
					}
				}
				if (recurse) {
					swap(index, minIndex);
					index = minIndex;
					if (index * 2 + 1 >= size) {
						recurse = false;
					}
				}
			}
		}

		public void Insert(long value, int id) {
			H[size] = value;
			this.setId(size, id);
			this.ShiftUp(size);
			this.size++;
		}

		public long ExtractMin() {
			long result = H[0];
			size--;
			swap(0, size);
			this.ShiftDown(0);
			return result;
		}

		public void Remove(int index) {
			H[index] = Long.MAX_VALUE;
			this.ShiftUp(index);
			this.ExtractMin();
		}

		public void ChangePriority(int index, long value) {
			H[index] = value;
			ShiftDown(index);
		}
	}

	public static void process(long[] op, int threadsNu) {
		int nextOp = threadsNu;
		long currentTime = 0;
		ParallelProcessing pp = new ParallelProcessing();
		Heap heap = pp.new Heap(threadsNu);
		for (int i = 0; i < threadsNu && i < op.length; i++) {
			heap.Insert(currentTime + op[i], i);
			System.out.println(i + " " + currentTime);
		}
		for (; nextOp < op.length; nextOp++) {
			int id = heap.id(0);
			currentTime = heap.H[0];
			System.out.println(id + " " + currentTime);
			heap.ChangePriority(0, op[nextOp] + currentTime);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		FastScanner fs = new FastScanner(System.in);
		int threadsNu = (int) fs.nextInt();
		int opNu = (int) fs.nextInt();
		long[] op = new long[opNu];
		for (int i = 0; i < opNu; i++) {
			op[i] = fs.nextInt();
		}
		process(op, threadsNu);
		
		long end = System.currentTimeMillis();
	    System.out.println((end - start) + " ms");
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

		long nextInt() {
			return Long.parseLong(next());
		}
	}

}
