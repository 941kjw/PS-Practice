#include <bits/stdc++.h>

using namespace std;

int dp[11] = { 0,1,2,4 };

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int T;
	cin >> T;
	for (int i = 4; i < 11; i++) {
		dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
	}

	for (int i = 0; i < T; i++) {
		int x;
		cin >> x;
		cout << dp[x] << '\n';
	}
	return 0;
}