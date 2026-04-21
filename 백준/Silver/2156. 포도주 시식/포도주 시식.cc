#include <iostream>
#include <vector>

using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;

	vector<int> wines(n + 1);
	vector<int> dp(n + 1);
	for (int i = 1; i <= n; i++)
	{
		int x;
		cin >> x;
		wines[i] = x;
	}
	dp[1] = wines[1];
	dp[2] = wines[1] + wines[2];
	for (int i = 3; i <= n; i++)
	{
		dp[i] = max(dp[i - 3] + wines[i] + wines[i - 1], dp[i - 2] + wines[i]);
		dp[i] = max(dp[i], dp[i - 1]);
	}

	cout << dp[n];
}