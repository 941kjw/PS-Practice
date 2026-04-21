#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	string s;
	string t;

	cin >> s;
	cin >> t;

	vector<vector<int>>dp(s.length() + 1, vector<int>(t.length() + 1));

	for (int i = 1;i<=s.length(); i++)
	{
		for (int j = 1; j<=t.length(); j++)
		{
			if (s[i - 1] == t[j - 1])
			{
				dp[i][j] = dp[i - 1][j - 1] + 1;
			}
			else
			{
				dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
	}
	
	cout << dp[s.length()][t.length()];

	return 0;
}