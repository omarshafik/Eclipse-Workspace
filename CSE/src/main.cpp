#include <iostream>
#include <vector>
#include <string>

using namespace std;

string repeated_element() {
	int n;
	cin >> n;
	vector<int> in;
	int temp;

	for (int i = 0; i < n; i++) {
		cin >> temp;
		in.push_back(temp);
	}
	string result = "NO";
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			if (in[i] == in[j]) {
				result = "YES";
				break;
			}
		}
		if (result == "YES") {
			break;
		}
	}
	return result;
}

string repeated_element_sorted() {
	int n;
	cin >> n;
	vector<int> in;
	int temp;

	for (int i = 0; i < n; i++) {
		cin >> temp;
		in.push_back(temp);
	}
	for (int j = 1; j < n; j++) {
		if (in[j - 1] == in[j]) {
			return "YES";
		}
	}
	return "NO";
}

void ratings() {
	vector<string> result;
	int n;
	int n_ratings, final_rating, sum, rating;
	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> n_ratings >> final_rating;
		sum = 1500;
		for (int j = 0; j < n_ratings; j++) {
			cin >> rating;
			sum += rating;
		}
		if (sum != final_rating) {
			cout << "Bug\n";
		} else {
			cout << "Correct\n";
		}
	}
}

void bonapity() {
	int n;
	cin >> n;
	string str1, str2;
	bool eq;
	for (int i = 0; i < n; i++) {
		cin >> str1 >> str2;
		eq = true;
		if (str1.length() != str2.length()) {
			cout << "No\n";
		} else {
			for (unsigned int j = 0; j < str1.length(); j++) {
				if (str1[j] != str2[j]) {
					if (str1[j] == 'b' || str1[j] == 'B') {
						if (str2[j] != 'p' && str2[j] != 'P' && str2[j] != 'b'
								&& str2[j] != 'B') {
							cout << "No\n";
							eq = false;
							break;
						}
					} else if (str1[j] == 'p' || str1[j] == 'P') {
						if (str2[j] != 'b' && str2[j] != 'B' && str2[j] != 'p'
								&& str2[j] != 'P') {
							cout << "No\n";
							eq = false;
							break;
						}
					} else if (str1[j] == 'e' || str1[j] == 'E') {
						if (str2[j] != 'i' && str2[j] != 'I' && str2[j] != 'e'
								&& str2[j] != 'E') {
							cout << "No\n";
							eq = false;
							break;
						}
					} else if (str1[j] == 'i' || str1[j] == 'I') {
						if (str2[j] != 'e' && str2[j] != 'E' && str2[j] != 'i'
								&& str2[j] != 'I') {
							cout << "No\n";
							eq = false;
							break;
						}
					} else {
						if (str1[j] < 97) {
							if (str1[j] + 32 != str2[j]) {
								cout << "No\n";
								eq = false;
								break;
							}
						} else if (str1[j] - 32 != str2[j]) {
							cout << "No\n";
							eq = false;
							break;
						}
				}
				}
			}
			if (eq == true) {
				cout << "Yes\n";
			}
		}
	}
}

int main(void) {

	bonapity();

	return 0;
}




