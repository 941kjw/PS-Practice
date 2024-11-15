#include <iostream>

using namespace std;

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	int N;
	int dp[31]{ 0, };

	cin >> N;

	dp[2] = 3;
	dp[4] = 11;

	for (int i = 6; i <= N; i += 2) {
		dp[i] += 3 * dp[i - 2];

		for (int j = 2; j < i - 2; j += 2) {
			dp[i] += 2 * dp[j];
		}
		dp[i] += 2;
	}

	cout << dp[N];
}