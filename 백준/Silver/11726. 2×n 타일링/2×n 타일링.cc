#include <iostream>
#include <vector>
using namespace std;

vector<int> dp(1001, 0);

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int n;
	cin >> n;
	
	dp[1] = 1;
	dp[2] = 2;


	for (int i = 3; i <= n; i++)
	{
		dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
	}

	cout << dp[n];

	return 0;
}