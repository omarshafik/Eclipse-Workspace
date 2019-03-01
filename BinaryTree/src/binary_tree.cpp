#include <iostream>
#include <algorithm>
#include <vector>
#include <stack>
using namespace std;

class Node {
public:
	int value;
	int rightChild;
	int leftChild;
	int parent;
	Node(int v, int l, int r) {
		value = v;
		rightChild = r;
		leftChild = l;
		parent = -1;
	}
	Node() {
		value = -1;
		rightChild = -1;
		leftChild = -1;
		parent = -1;
	}
	void operator=(Node node) {
		this->value = node.value;
		this->leftChild = node.leftChild;
		this->rightChild = node.rightChild;
	}
	void set_parent(int p) {
		this->parent = p;
	}
};

class BinaryTree {
public:
	vector<Node> nodes;
	void add_node_at(int v, int l, int r, int index) {
		Node node(v, l, r);
		nodes[index] = node;
		if (l != -1) {
			nodes[l].set_parent(index);
		}
		if (r != -1) {
			nodes[r].set_parent(index);
		}
	}
	BinaryTree(int size) {
		nodes.resize(size);
	}
};

void in_order(BinaryTree tree) {
	int counter = 0;
	int i = 0;
	int val = -2;
	stack<int> stack;
	if (tree.nodes[0].rightChild != -1) {
		stack.push(tree.nodes[0].rightChild);

	}
	stack.push(i);
	do {
		while (counter < tree.nodes.size() - 1) {
			if (tree.nodes[stack.top()].rightChild != -1 && val == -1) {
				val = tree.nodes[stack.top()].rightChild;
				counter++;
			}
			i = tree.nodes[i].leftChild;
			stack.push(i);
			counter++;
			if (val > -1)
				stack.push(val);
			val = -1;
		}
		cout << tree.nodes[stack.top()].value << "\n";
		stack.pop();
	} while (!stack.empty());
}

int main(void) {

	int n;
	cin >> n;
	BinaryTree tree(n);
	for (int i = 0; i < n; ++i) {
		int v, r, l;
		cin >> v >> l >> r;
		tree.add_node_at(v, l, r, i);
	}

	in_order(tree);

	return 0;
}

