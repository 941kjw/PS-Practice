#include <iostream>
#include <queue>
#include <vector>

using namespace std;


int main() {
	int a, b,sum = 0;
	priority_queue<int, vector<int>, greater<int>> pq;

	cin >> a;

	for (int i = 0; i < a; i++) {
		cin >> b;
		pq.push(b);
	}

	while (pq.size() > 1) {
		int x = pq.top();
		pq.pop();
		int y = pq.top();
		pq.pop();
		sum += x + y;
		pq.push(x + y);
	}

	cout << sum << '\n';
	return 0;
}