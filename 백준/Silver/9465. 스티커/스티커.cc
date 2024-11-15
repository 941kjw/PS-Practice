#include <bits/stdc++.h>

using namespace std;

int n,t;
int board[2][100001];
int dp[2][100001];

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> t;
	while (t--) {

		cin >> n;
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j <= n; j++) {
				cin >> board[i][j];
			}
		}
		dp[0][0] = dp[1][0] = 0;
		dp[0][1] = board[0][1];
		dp[1][1] = board[1][1];
		for (int i = 2; i <= n; i++) {
			dp[0][i] = max(dp[1][i - 1], dp[1][i - 2]) + board[0][i];
			dp[1][i] = max(dp[0][i - 1], dp[0][i - 2]) + board[1][i];
		}

		cout << max(dp[0][n], dp[1][n])<<"\n";
	}
}