#include <bits/stdc++.h>

using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int T;
	vector<int> dp;
	cin >> T;
	dp = vector<int>(11,-1);
	dp[1] = 1;
	dp[2] = 2;
	dp[3] = 4;

	for (int i = 0; i < T; i++) {
		int x;
		cin >> x;

		if (dp[x] != -1) {
			cout << dp[x] << "\n";
			continue;
		}
		
		for (int j = 4; j <= x; j++) {
			if (dp[j] != -1)
				continue;
			dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
		}

		cout << dp[x] << '\n';
	}
	return 0;
}