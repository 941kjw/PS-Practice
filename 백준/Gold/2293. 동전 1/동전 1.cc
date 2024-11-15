#include <iostream>
#include <vector>
using namespace std;

int n,k;
int dp[10001];

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> n >> k;
	int price[101];
	for (int i = 0; i < n; i++) {
		cin >> price[i];
	}
	
	dp[0] = 1;

	for (int i = 0; i < n; i++) {
		for (int j = price[i]; j <= k; j++) {
			dp[j] = dp[j] + dp[j - price[i]];
		}
	}

	cout << dp[k] << "\n";
}