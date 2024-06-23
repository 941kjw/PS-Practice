#include <bits/stdc++.h>

using namespace std;

int dp[11];

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;

	cin >> n;
	dp[1] = 0;
	dp[2] = 1;

	for (int i = 3; i <= n; i++) {
		int div = i / 2;
		dp[i] = (div * (i - div)) + dp[div] + dp[i - div];
	}

	cout << dp[n];
	return 0;
}
