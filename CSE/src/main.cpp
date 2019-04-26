#include "stddef.h"
#include <algorithm>
#include <bits/stdc++.h>
#include <iostream>
#include <list>
#include <math.h>
#include <string>
#include <vector>

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
            std::cout << "Bug\n";
        } else {
            std::cout << "Correct\n";
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
            std::cout << "No\n";
        } else {
            for (unsigned int j = 0; j < str1.length(); j++) {
                if (str1[j] != str2[j]) {
                    if (str1[j] == 'b' || str1[j] == 'B') {
                        if (str2[j] != 'p' && str2[j] != 'P' &&
                            str2[j] != 'b' && str2[j] != 'B') {
                            std::cout << "No\n";
                            eq = false;
                            break;
                        }
                    } else if (str1[j] == 'p' || str1[j] == 'P') {
                        if (str2[j] != 'b' && str2[j] != 'B' &&
                            str2[j] != 'p' && str2[j] != 'P') {
                            std::cout << "No\n";
                            eq = false;
                            break;
                        }
                    } else if (str1[j] == 'e' || str1[j] == 'E') {
                        if (str2[j] != 'i' && str2[j] != 'I' &&
                            str2[j] != 'e' && str2[j] != 'E') {
                            std::cout << "No\n";
                            eq = false;
                            break;
                        }
                    } else if (str1[j] == 'i' || str1[j] == 'I') {
                        if (str2[j] != 'e' && str2[j] != 'E' &&
                            str2[j] != 'i' && str2[j] != 'I') {
                            std::cout << "No\n";
                            eq = false;
                            break;
                        }
                    } else {
                        if (str1[j] < 97) {
                            if (str1[j] + 32 != str2[j]) {
                                std::cout << "No\n";
                                eq = false;
                                break;
                            }
                        } else if (str1[j] - 32 != str2[j]) {
                            std::cout << "No\n";
                            eq = false;
                            break;
                        }
                    }
                }
            }
            if (eq == true) {
                std::cout << "Yes\n";
            }
        }
    }
}

bool isNumber(string s) {
    for (int i = 0; i < s.length(); i++)
        if (isdigit(s[i]) == false)
            return false;

    return true;
}

void reverse_polish() {
    string input;
    getline(cin, input);
    stringstream ss(input);
    vector<string> line;
    string word;
    while (ss >> word) {
        line.push_back(word);
    }

    stack<int> stack;
    bool parse_correct = false;
    for (string c : line) {
        if (c == "+" || c == "-" || c == "*" || c == "/" || c == "x" ||
            c == "y" || c == "z") {
            parse_correct = true;
            int n1, n2, n3;
            if (c == "y" && stack.size() >= 1) {
                n1 = stack.top();
                stack.pop();
                stack.push(2 * n1 + 1);
                continue;
            } else if (c == "z" && stack.size() >= 3) {
                n3 = stack.top();
                stack.pop();
                n2 = stack.top();
                stack.pop();
                n1 = stack.top();
                stack.pop();
                stack.push(n1 + 2 * n2 + 3 * n3);
                continue;
            } else if (c == "x" && stack.size() >= 2) {
                n2 = stack.top();
                stack.pop();
                n1 = stack.top();
                stack.pop();
                stack.push(n1 * n1 + n2);
                continue;
            } else if (c == "+" && stack.size() >= 2) {
                n2 = stack.top();
                stack.pop();
                n1 = stack.top();
                stack.pop();
                stack.push(n1 + n2);
                continue;
            } else if (c == "/" && stack.size() >= 2) {
                n2 = stack.top();
                stack.pop();
                n1 = stack.top();
                stack.pop();
                stack.push(n1 / n2);
                continue;
            } else if (c == "*" && stack.size() >= 2) {
                n2 = stack.top();
                stack.pop();
                n1 = stack.top();
                stack.pop();
                stack.push(n1 * n2);
                continue;
            } else if (c == "-" && stack.size() >= 2) {
                n2 = stack.top();
                stack.pop();
                n1 = stack.top();
                stack.pop();
                stack.push(n1 - n2);
                continue;
            } else {
                std::cout << "NO" << endl;
                return;
            }
        } else {
            stack.push(stoi(c));
        }
    }
    if (stack.size() > 1 || !parse_correct || stack.empty()) {
        std::cout << "NO" << endl;
        return;
    }
    std::cout << stack.top() << endl;
}

class Node {
  public:
    string data;
    Node *next;
    Node *prev;
    void insertAfter(Node *prev_node, string new_data) {
        if (prev_node == NULL) {
            return;
        }
        Node *new_node = new Node();
        new_node->data = new_data;
        new_node->next = prev_node->next;
        prev_node->next = new_node;
        new_node->prev = prev_node;
        if (new_node->next != NULL)
            new_node->next->prev = new_node;
    }
    void deleteNode(Node **head_ref, Node *del) {
        if (*head_ref == NULL || del == NULL)
            return;
        if (*head_ref == del)
            *head_ref = del->next;
        if (del->next != NULL)
            del->next->prev = del->prev;
        if (del->prev != NULL)
            del->prev->next = del->next;
        // free(del);
        return;
    }
};

void keyboard(void) {
    string strokes;
    cin >> strokes;
    Node *head = new Node();
    Node *tail = new Node();
    head->next = tail;
    head->prev = NULL;
    tail->next = NULL;
    tail->prev = head;
    Node *cursor = new Node();
    cursor = head;
    for (char stroke : strokes) {
        if (stroke == '<' || stroke == '>' || stroke == '-') {
            if (stroke == '<' && cursor != head) {
                cursor = cursor->prev;
            } else if (stroke == '>' && cursor != tail->prev) {
                cursor = cursor->next;
            } else if (stroke == '-' && head != tail && cursor != head) {
                Node *temp = new Node();
                temp = cursor->prev;
                cursor->deleteNode(&head, cursor);
                cursor = temp;
                // free(temp);
            }
        } else {
            string str;
            str.push_back(stroke);
            Node *temp = new Node();
            temp = cursor;
            head->insertAfter(temp, str);
            // free(temp);
            cursor = cursor->next;
            str.clear();
        }
    }
    cursor = head->next;
    while (cursor != tail) {
        cout << cursor->data;
        cursor = cursor->next;
    }
    cout << endl;
    return;
}

bool isAffordable(unsigned long long r, unsigned long long nb,
                  unsigned long long ns, unsigned long long nc,
                  unsigned long long pb, unsigned long long ps,
                  unsigned long long pc) {
    if (nb * pb + ns * ps + nc * pc <= r) {
        return true;
    }
    return false;
}

void hamburgers() {
    string s;
    getline(cin, s);
    unsigned long long kb, ks, kc, pb, ps, pc, r;
    cin >> kb >> ks >> kc >> pb >> ps >> pc >> r;
    unsigned long long ham_price = 0, ham_b = 0, ham_s = 0, ham_c = 0;
    for (char c : s) {
        switch (c) {
        case 'B':
            ham_price += pb;
            ham_b++;
            break;
        case 'C':
            ham_price += pc;
            ham_c++;
            break;
        case 'S':
            ham_price += ps;
            ham_s++;
            break;
        default:
            break;
        }
    }
    unsigned long long upperbound = 1000000000000000;
    unsigned long long lowerbound = 0;
    unsigned long long pivot;
    unsigned long long b_req, s_req, c_req;
    while (upperbound > lowerbound) {
        pivot = (lowerbound + upperbound) / 2;
        if (pivot == lowerbound) {
            pivot++;
        }
        b_req = (ham_b * pivot >= kb) ? ham_b * pivot - kb : 0;
        s_req = (ham_s * pivot >= ks) ? ham_s * pivot - ks : 0;
        c_req = (ham_c * pivot >= kc) ? ham_c * pivot - kc : 0;
        if (isAffordable(r, b_req, s_req, c_req, pb, ps, pc)) {
            lowerbound = pivot;
        } else {
            upperbound = pivot - 1;
        }
    }
    cout << lowerbound << endl;
}

void expressions() {
    int a, b, c;
    cin >> a >> b >> c;
    cout << max(max((a + b) * c, a * (b + c)), max(a * b * c, a + b + c))
         << endl;
}

void my_binary_search(vector<int> arr, int l, int r, int element) {
    int pivot = (l + r) / 2;
    int pick = arr[pivot];
    if (l <= r) {
        if (pick == element) {
            cout << "YES" << endl;
            return;
        }
        if (pick < element) {
            for (int i = pivot + 1; i <= r; i++) {
                cout << arr[i] << " ";
            }
            cout << endl;
            my_binary_search(arr, pivot + 1, r, element);
            return;
        }
        if (l < pivot) {
            for (int i = l; i < pivot; i++) {
                cout << arr[i] << " ";
            }
            cout << endl;
        }
        my_binary_search(arr, l, pivot - 1, element);
        return;
    }
    cout << "NO\n";
    return;
}

void bs() {
    int target, n;
    cin >> target >> n;
    vector<int> array(n);
    for (int i = 0; i < n; i++) {
        cin >> array[i];
    }
    my_binary_search(array, 0, n - 1, target);
}

void pair_search() {
    int n, sum;
    cin >> n >> sum;
    vector<int> array(n);
    for (int i = 0; i < n; i++) {
        cin >> array[i];
    }
    sort(array.begin(), array.end());
    vector<int>::iterator it;
    it = array.begin();
    for (; it < array.end(); it++) {
        int element = sum - *it;
        if (element == *it) {
            if (it + 1 != array.end()) {
                if (*(it + 1) == element) {
                    cout << "YES\n";
                    return;
                }
            }
            if (it != array.begin()) {
                if (*(it - 1) == element) {
                    cout << "YES\n";
                    return;
                }
            }
        } else {
            if (binary_search(array.begin(), array.end(), element)) {
                cout << "YES" << endl;
                return;
            }
        }
    }
    cout << "NO" << endl;
}

void registration() {
    int n;
    cin >> n;
    vector<string> lines(n);
    for (int i = 0; i < n; i++) {
        cin >> lines[i];
    }
    unordered_map<string, int> database;
    for (int i = 0; i < n; i++) {
        if (database.find(lines[i]) == database.end()) {
            cout << "OK\n";
            database.insert({lines[i], 1});
        } else {
            cout << lines[i] << database[lines[i]] << endl;
            database[lines[i]]++;
            database.insert(
                {lines[i].append(to_string(database.at(lines[i]))), 1});
        }
    }
}

void prioQueue() {
    int serverCount, requestCount;
    cin >> serverCount >> requestCount;
    queue<int> requests;
    float waitingTime = 0;
    for (int i = 0; i < requestCount; i++) {
        int temp;
        cin >> temp;
        requests.push(temp);
    }
    priority_queue<int, std::vector<int>, std::greater<int>> servers;
    int totalTime = 0;
    while (!requests.empty()) {
        if (servers.size() < serverCount) {
            servers.push(requests.front());
            requests.pop();
        } else {
            waitingTime += servers.top();
            int temp = servers.top();
            servers.pop();
            servers.push(temp + requests.front());
            requests.pop();
        }
    }
    while (!servers.empty()) {
        totalTime = servers.top();
        servers.pop();
    }
    cout << totalTime << " " << waitingTime / (float)requestCount << endl;
}

int main(void) {
    prioQueue();
    return 0;
}
