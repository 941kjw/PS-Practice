#include <bits/stdc++.h>

using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N;
	vector<int> dp;
	cin >> N;
	dp = vector<int>(N + 1);

	for (int i = 1; i <= N; i++) {
		dp[i] = i;
	}
	

	for (int i = 2; i <= N; i++){
		if (!(i % 2)) 
			dp[i] = min(dp[i], dp[i / 2]);
		if (!(i % 3))
			dp[i] = min(dp[i], dp[i / 3]);
		dp[i] = min(dp[i],dp[i-1])+1;
	}

	cout << dp[N] - 1 << '\n';

	while (N != 0) {
		cout << N << ' ';
		if (dp[N] == dp[N - 1] + 1)
			N--;
		else if (!(N % 2) && dp[N] == dp[N / 2] + 1)
			N /= 2;
		else if (!(N % 3) && dp[N] == dp[N / 3] + 1)
			N /= 3;
	}
	return 0;
}