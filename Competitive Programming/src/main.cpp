#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool sortcol(const vector<int>& v1, const vector<int>& v2) {
	return v1[0] < v2[0];
}

int find_sorted(vector<vector<int>> v, int val, int i) {
	if ((i == 0 || i == v.size() - 1) && v[i][1] != val) {
		return -1;
	}
	if (v[i][1] < val) {
		return find_sorted(v, val, i + (v.size() - i) / 2);
	} else if (v[i][1] > val) {
		return find_sorted(v, val, i - (v.size() - i) / 2);
	} else {
		return i;
	}
}

void tape() {
	int broken_n;
	int length;
	int max_pieces_n;
	cin >> broken_n >> length >> max_pieces_n;
	vector<int> broken;
	int temp;

	for (int i = 0; i < broken_n; ++i) {
		cin >> temp;
		broken.push_back(temp);
	}

	vector<int> row;
	vector<int> prev_row;
	int element = 1;

	int op1;
	int op2;

	for (int i = 0; i < max_pieces_n; i++) {
		row.push_back(element);
	}

	prev_row.assign(row.begin(), row.end());

	for (int i = 1; i < broken_n; i++) {
		row.clear();
		element = broken[i] - broken[0] + 1;
		row.push_back(element);
		for (int j = 1; j < max_pieces_n; j++) {
			op1 = prev_row.at(j - 1);
			op1 += 1;
			op2 = prev_row.at(j);
			op2 += broken[i] - broken[i - 1];
			if (op1 <= op2) {
				row.push_back(op1);
			} else {
				row.push_back(op2);
			}
		}
		prev_row.assign(row.begin(), row.end());
	}
	cout << row.at(max_pieces_n - 1);
}

void tape2() {
	int broken_n;
	int length;
	int max_pieces_n;
	cin >> broken_n >> length >> max_pieces_n;
	vector<int> broken;
	int temp;

	for (int i = 0; i < broken_n; ++i) {
		cin >> temp;
		broken.push_back(temp);
	}
	vector<vector<int>> diff(broken_n - 1);
	for (int i = 0; i < broken_n - 1; ++i) {
		diff[i].push_back(broken[i + 1] - broken[i] + 1);
		diff[i].push_back(i);
	}
	sort(diff.begin(), diff.end(), sortcol);
	int n_pieces = broken_n, i = 0, length_used = 0;
	while (n_pieces > max_pieces_n) {
		length_used += diff[i][0];
		i++;
		n_pieces--;
	}
	length_used += n_pieces - i;
	cout << length_used;
}

void disjoint_sets() {
	int n;
	cin >> n;
	vector<int> a(n);
	vector<int> b(n);
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}
	for (int i = 0; i < n; i++) {
		cin >> b[i];
	}
	sort(a.begin(), a.end());
	sort(b.begin(), b.end());
	int miss = 0, count = 0, j = 0;
	for (int i = 0; i < n; i++, j++) {
		if (a[i] != b[j]) {
			if (b[j] < a[i]) {
				i--;
			} else {
				j--;
				miss++;
			}
		} else {
			count++;
		}
	}
	if (miss)
		count++;
	else
		count--;

	cout << count;
}

void max_permutation() {
	int n, k;
	cin >> n >> k;
	vector<int> a(n);
	vector<int> index(n + 1);
	for (int i = 0; i < n; i++) {
		cin >> a[i];
		index[a[i]] = i;
	}
	int h = n;
	for (int i = 0; i < n && k > 0; i++) {
		if (a[i] != h) {
			int temp = a[i];
			swap(a.at(i), a.at(index[h]));
			swap(index[h], index[temp]);
			k--;
		}
		h--;
	}
	for (int i = 0; i < n; i++) {
		cout << a[i] << " ";
	}
}

void permuting_arrays() {
	int q;
	cin >> q;
	vector<int> n(q);
	vector<int> k(q);
	vector<vector<int>> A(q);
	vector<vector<int>> B(q);
	for (int i = 0; i < q; i++) {
		cin >> n[i] >> k[i];
		for (int j = 0; j < n[i]; j++) {
			int temp;
			cin >> temp;
			A[i].push_back(temp);
		}
		for (int j = 0; j < n[i]; j++) {
			int temp;
			cin >> temp;
			B[i].push_back(temp);
		}
		sort(A[i].begin(), A[i].end());
		sort(B[i].begin(), B[i].end(), greater<int>());
	}

	bool equal;
	for (int i = 0; i < q; i++) {
		equal = true;
		for (int j = 0; j < n[i]; j++) {
			if (A[i][j] + B[i][j] < k[i]) {
				equal = false;
				break;
			}
		}
		if (equal)
			cout << "YES\n";
		else
			cout << "NO\n";
	}
}

void cost() {
	int q;
	cin >> q;
	vector<int> n(q);
	vector<vector<int>> b(q);
	for (int o = 0; o < q; o++) {
		cin >> n[o];
		for (int i = 0; i < n[o]; i++) {
			int temp;
			cin >> temp;
			b[o].push_back(temp);
		}
	}

	vector<int> row;
	int cost = 0;
	vector<int> max(q);
	for (int o = 0; o < q; o++) {
		vector<int> prev_row(b[o][0]);
		cost = 0;
		for (int i = 1; i < n[o]; i++) {
			row.clear();
			for (int j = 1; j <= b[o][i]; j++) {
				max[o] = 0;
				for (int k = 0; k < b[o][i - 1]; k++) {
					if (prev_row[k] + abs(j - (k + 1)) > max[o]) {
						max[o] = prev_row[k] + abs(j - (k + 1));
					}
				}
				row.push_back(max[o]);
				if (max[o] > cost) {
					cost = max[o];
				}
			}
			prev_row.clear();
			prev_row.assign(row.begin(), row.end());
		}
		max[o] = cost;
	}
	for (int i = 0; i < q; i++) {
		cout << max[i] << "\n";
	}
}

void cost2() {
	int q;
	cin >> q;
	vector<int> n(q);
	vector<vector<int>> b(q);
	for (int o = 0; o < q; o++) {
		cin >> n[o];
		for (int i = 0; i < n[o]; i++) {
			int temp;
			cin >> temp;
			b[o].push_back(temp);
		}
	}

	vector<long long> row;
	vector<long long> prev_row(2);
	for (int o = 0; o < q; o++) {
		for (int i = 1; i < n[o]; i++) {
			row.clear();
			for (int j = 0; j < 2; j++) {
				int m = (j == 0) ? 1 : b[o][i];
				if (prev_row[0] + abs(m - 1)
						> prev_row[1] + abs(m - b[o][i - 1])) {
					row.push_back(prev_row[0] + abs(m - 1));
				} else {
					row.push_back(prev_row[1] + abs(m - b[o][i - 1]));
				}
			}
			prev_row.clear();
			prev_row.assign(row.begin(), row.end());
		}
		if (row[0] > row[1]) {
			cout << row[0];
		} else {
			cout << row[1];
		}
		prev_row.clear();
		prev_row.push_back(0);
		prev_row.push_back(0);
		cout << "\n";
	}
}

int main(void) {
	cost2();
	return 0;
}
