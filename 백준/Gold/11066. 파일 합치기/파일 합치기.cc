#include <iostream>
#include <vector>

#define INT_MAX 2147483647
using namespace std;

int T, K, page;

vector<vector<int>> dp;
vector<int> pages;
vector<int> adjSum;

int main()
{
	cin >> T;
	while (T--)
	{
		cin >> K;
		dp = vector<vector<int>>(K+1, vector<int>(K+1));
		pages = vector<int>(K+1);
		adjSum = vector<int>(K + 1);
		for (int i = 1; i <= K; i++)
		{
			cin >> page;
			pages[i] = page;
			adjSum[i] = adjSum[i - 1] + pages[i];
		}
		for (int i = 1; i < K; i++)
		{
			for (int j = 1; j + i <= K; j++)
			{
				int n = j + i;
				dp[j][n] = INT_MAX;
				for (int mid = j; mid < n; mid++)
					dp[j][n] = min(dp[j][n], dp[j][mid] + dp[mid + 1][n] + adjSum[n] - adjSum[j - 1]);
			}
		}
		cout << dp[1][K] << '\n';
	}
}
