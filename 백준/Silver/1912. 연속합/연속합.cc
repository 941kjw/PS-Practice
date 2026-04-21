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
	int answer;

	for (int i = 0; i < n; i++)
	{
		int x;
		cin >> x;
		numbers[i] = x;
	}
	dp[0] = numbers[0];
	answer = dp[0];
	for (int i = 0; i < n; i++)
	{
		dp[i] = max(dp[i - 1] + numbers[i], numbers[i]);
		answer = max(answer, dp[i]);
	}
	cout << answer;
}