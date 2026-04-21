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
	vector<int> dp(n);
	vector<int>numbers(n);

	for (int i = 0; i < n; i++)
	{
		int x;
		cin >> x;
		numbers[i] = x;
	}
	for (int i = 0; i < n; i++)
	{
		dp[i] = 1;
		for (int k = 0; k < i; k++)
		{
			if (numbers[k] < numbers[i])
			{
				dp[i] = max(dp[i], dp[k] + 1);
			}
		}
	}

	int result = 0;
	for (int i = 0; i < n; i++)
	{
		result = max(result, dp[i]);
	}
	cout << result;
}