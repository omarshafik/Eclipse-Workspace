#include <algorithm>
#include <bits/stdc++.h>
#include <iostream>
#include <math.h>
#include <vector>

using namespace std;

bool sortcol(const vector<int> &v1, const vector<int> &v2) {
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
                if (prev_row[0] + abs(m - 1) >
                    prev_row[1] + abs(m - b[o][i - 1])) {
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

void DianaAndLiana(void) {
    int n_f, n_f_in_wreath, n_cit, n_f_Dseq;
    cin >> n_f >> n_f_in_wreath >> n_cit >> n_f_Dseq;
    vector<int> liana(n_f);
    vector<int> diana(n_f_Dseq);
    for (int i = 0; i < n_f; i++) {
        cin >> liana[i];
    }
    for (int i = 0; i < n_f_Dseq; i++) {
        cin >> diana[i];
    }
    int spare = n_f - (n_f_in_wreath * n_cit);
    int length;
    vector<int> element;
    int last;
    for (int i = 0; i < n_f; i++) {
        if (i % n_f_in_wreath <= spare) {
            length = n_f_in_wreath + spare - (i % n_f_in_wreath);
        } else {
            length = 0;
        }
        for (vector<int>::iterator j = diana.begin(); j < diana.end(); j++) {
            if (liana[i] == *j) {
                element.push_back(i);
                element.push_back(length);
                diana.erase(j);
                break;
            }
        }
        while (!element.empty() && element[0] + element[1] <= i) {
            diana.push_back(liana[element[0]]);
            element.erase(element.begin());
            element.erase(element.begin());
        }
        if (diana.empty()) {
            last = i;
            break;
        }
    }
    if (!diana.empty()) {
        cout << -1;
    } else {
    }
}

string morganAndString(string a, string b) {
    int la = a.length();
    int lb = b.length();
    string result = "";
    int i = 0, j = 0;
    int x = 1, y = 1, w, u;
    while (i < la && j < lb) {
        if (a[i] == b[j]) {
            w = (int)a[i];
            u = (int)b[j];
            x = 1;
            y = 1;
            while (w == u && ((i + x) < la && (j + y) < lb)) {
                w += a[i + x];
                u += b[j + y];
                x++;
                y++;
            }
            while (a[i] == b[j]) {
                if (w < u) {
                    result += a[i];
                    i++;
                } else {
                    result += b[j];
                    j++;
                }
            }
        } else {
            if (a[i] < b[j]) {
                result += a[i];
                i++;
            } else {
                result += b[j];
                j++;
            }
        }
    }
    if (i < la) {
        while (i < la) {
            result += a[i];
            i++;
        }
    } else if (j < lb) {
        while (j < lb) {
            result += b[j];
            j++;
        }
    }
    return result;
}

vector<string> read_output() {
    int n;
    cin >> n;
    vector<string> output(n);
    for (int i = 0; i < n; i++) {
        cin >> output[i];
    }
    return output;
}

void lex_min_string() {
    int t;
    vector<string> output(read_output());
    cin >> t;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    for (int t_itr = 0; t_itr < t; t_itr++) {
        string a;
        getline(cin, a);

        string b;
        getline(cin, b);

        string result = morganAndString(a, b);
        if (result != output[t_itr]) {
            char str[output[t_itr].length()];
            strcpy(str, output[t_itr].c_str());
            cout << result.find(str, 0, output[t_itr].length()) << "wrong\n";
        }
        cout << result << "\n";
    }
}

void game23() {
    int a, b;
    cin >> a >> b;
    int c = b / a;
    if (b % a != 0) {
        cout << -1 << endl;
        return;
    } else {
        int i = 0;
        while (c % 2 == 0) {
            c = c / 2;
            i++;
        }
        while (c % 3 == 0) {
            c = c / 3;
            i++;
        }
        if (c > 1) {
            cout << "-1\n";
            return;
        }
        cout << i << endl;
    }
}

void polycarb() {
    int n;
    cin >> n;
    int first = 0, max = 0, i = 0, count = 0;
    int k = 1;
    while (k == 1) {
        cin >> k;
        if (k == 1) {
            first++;
        } else {
            if (first > max) {
                max = first;
            }
        }
        i++;
    }
    while (i < n) {
        cin >> k;
        if (k == 1) {
            count++;
        } else {
            if (count > max) {
                max = count;
            }
            count = 0;
        }
        i++;
    }
    if (first + count > max) {
        max = first + count;
    }
    cout << max << endl;
}

void permutation() {
    int n;
    cin >> n;
    vector<int> q(n);
    vector<int> p(n);
    vector<int> arr;
    int qSum = 0;
    for (int i = 0; i < n - 1; i++) {
        cin >> q[i];
        qSum += q[i];
        p[i] = 0;
    }
    if (qSum > 0) {
        int last = n, first = n - qSum;
        arr.push_back(first);

        while (first <= n && last > 0) {

            for (int i = 0; i < n - 1; i++) {
                if (arr[i] + q[i] <= n && arr[i] + q[i] > 0 &&
                    p[(arr[i] + q[i]) - 1] == 0) {
                    arr.push_back(arr[i] + q[i]);
                    p[(arr[i] + q[i]) - 1] = 1;
                } else {
                    arr[0] = -1;
                    break;
                }
            }
            if (arr[0] == -1) {
                first++;
                last--;
                arr.clear();
                p.clear();
                vector<int> p(n);
            } else {
                for (int i = 0; i < n; i++) {
                    cout << arr[i] << " ";
                }
                return;
            }
        }
    } else {
        int first = n, last = n + qSum;
        arr.push_back(first);

        while (last <= n && first > 0) {

            for (int i = 0; i < n - 1; i++) {
                if (arr[i] + q[i] <= n && arr[i] + q[i] > 0 &&
                    p[(arr[i] + q[i]) - 1] == 0) {
                    arr.push_back(arr[i] + q[i]);
                    p[(arr[i] + q[i]) - 1] = 1;
                } else {
                    arr[0] = -1;
                    break;
                }
            }
            if (arr[0] == -1) {
                last++;
                first--;
                arr.clear();
                p.clear();
                vector<int> p(n);
            } else {
                for (int i = 0; i < n; i++) {
                    cout << arr[i] << " ";
                }
                return;
            }
        }
    }
    cout << "-1\n";
}

class Node {
  public:
    Node *next;
};

void LynyrdPermutation() {
    int n, m, q;
    Node *last;
    vector<int> p(n + 1);
    vector<int> perms(n);
    for (int i = 0; i < n; i++) {
        cin >> perms[i];
    }
    for (int i = 0; i < n; i++) {
        p[perms[i]] = perms[i + 1];
    }
    p[n] = perms[0];
    vector<int> a(m);
    for (int i = 0; i < m; i++) {
        cin >> a[i];
    }
    list<Node> roots;
    for (int i = 0; i < m; i++) {
    }
}

int main(void) {
    LynyrdPermutation();
    return 0;
}
