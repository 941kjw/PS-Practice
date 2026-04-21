#include <iostream>
#include <algorithm>
using namespace std;

int n, m;
int arr[9];
int num[9];
bool check[9];

void seq(int len) {
	if (len == m) {
		for (int i = 0; i < m; i++)
			cout << arr[i] << ' ';
		cout << '\n';
		return;
	}
	for (int i = 0; i < n; i++) {
		if (!check[i]) {
			arr[len] = num[i];
			check[i] = true;
			seq(len + 1);
			check[i] = false;
		}
	}
	return;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;

	for (int i = 0; i < n; i++)
		cin >> num[i];

	sort(&num[0], &num[n]);

	seq(0);

	return 0;
}