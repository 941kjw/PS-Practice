#include <bits/stdc++.h>

using namespace std;

vector<pair<int, int>> items;
vector<vector<int>> b;
int n, k;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> k;
	b = vector<vector<int>>(n+1, vector<int>(k+1,0));
	items.push_back({ 0,0 });

	for (int i = 1; i <= n; i++) {
		int w, v;
		cin >> w >> v;
		items.push_back({ w,v });
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= k; j++) {
			int weight = items[i].first;
			int value = items[i].second;

			if (weight <= j) {
				b[i][j] = max(value + b[i - 1][j - weight], b[i - 1][j]);
			}
			else
				b[i][j] = b[i - 1][j];
		}
	}

	cout << b[n][k];
	return 0;
}
