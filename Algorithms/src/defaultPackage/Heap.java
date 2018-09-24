package defaultPackage;

public class Heap {

	private int maxSize = 0;
	private int size = 0;

	public int maxSize() {
		return maxSize;
	}

	public int size() {
		return size;
	}

	private int[] H;

	Heap(int maxSize) {
		H = new int[maxSize];
		this.maxSize = maxSize;
	}

	private void swap(int index1, int index2) {
		int temp = this.H[index1];
		this.H[index1] = this.H[index2];
		this.H[index2] = temp;
	}

	public int[] getH() {
		return H;
	}

	public int Parent(int index) {
		if (index > 0)
			return ((int) index / 2);
		else
			return -1;
	}

	public int LeftChild(int index) {
		if (index * 2 < maxSize)
			return 2 * index;
		else
			return -1;
	}

	public int RightChild(int index) {
		if ((index * 2) + 1 < maxSize)
			return 2 * index + 1;
		else
			return -1;
	}

	public void ShiftUp(int index) {
		while (index > 0 && H[this.Parent(index)] < H[index]) {
			swap(this.Parent(index), index);
			index = this.Parent(index);
		}
	}

	public void ShiftDown(int index) {
		int maxIndex = index;
		boolean recurse = true;
		while (recurse) {
			recurse = false;
			int left = this.LeftChild(index);
			if (left != -1 && H[left] > H[maxIndex]) {
				maxIndex = left;
				recurse = true;
			}
			int right = this.RightChild(index);
			if (right != -1 && H[right] > H[maxIndex]) {
				maxIndex = right;
				recurse = true;
			}
			if (recurse) {
				swap(index, maxIndex);
				index = maxIndex;
			}
		}
	}

	public void Insert(int value) {
		if (size + 1 == maxSize) {
			return;
		} else {
			H[size] = value;
			this.ShiftUp(size);
			this.size++;
		}
	}

	public int ExtractMax() {
		int result = H[0];
		H[1] = H[size];
		size--;
		this.ShiftDown(0);
		return result;
	}

	public void Remove(int index) {
		H[index] = Integer.MAX_VALUE;
		this.ShiftUp(index);
		this.ExtractMax();
	}

	public void ChangePriority(int index, int value) {
		int oldV = H[index];
		H[index] = value;
		if (value > oldV) {
			this.ShiftUp(index);
		} else {
			ShiftDown(index);
		}
	}

}
