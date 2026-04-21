#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;

	vector<int>dp(n+1);
	vector<pair<int,int>>line;

	for (int i = 0; i < n; i++)
	{
		int x, y;
		cin >> x >> y;
		line.push_back({ x,y });
	}
	sort(line.begin(), line.end());
	for (int i = 0; i < n; i++)
	{
		dp[i] = 1;
		for (int k = 0; k < i; k++)
		{
			if(line[k].second < line[i].second)
				dp[i] = max(dp[i], dp[k] + 1);
		}
	}
	int result = 0;
	for (int i = 0; i < n; i++)
	{
		result = max(result, dp[i]);
	}
	cout << n - result;
}