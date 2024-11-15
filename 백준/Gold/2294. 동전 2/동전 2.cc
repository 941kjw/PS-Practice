#include <iostream>
using namespace std;

int n,k;


int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> n >> k;
	int* price = new int[n];
	int* dp = new int[k + 1] {0, };
	for (int i = 0; i < n; i++) {
		cin >> price[i];
	}	

	for (int i = 1; i <= k; i++) {
		dp[i] = 10001;
	}
	for (int i = 0; i < n; i++) {
		for (int j = price[i]; j <= k; j++) {
			dp[j] = min(dp[j] , dp[j - price[i]] + 1);
		}
	}
	if (dp[k] == 10001)
		dp[k] = -1;
	cout << dp[k] << "\n";
}