#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	long long n, m, max = -1;
	long long left, right, mid, sum;
	vector<int> trees;
	cin >> n >> m;
	trees = vector<int>(n, 0);
	for (int i = 0; i < n; i++)
	{
		cin >> trees[i];
	}

	sort(trees.begin(), trees.end());

	left = 0;
	right = trees[n - 1];

	while (left <= right)
	{
		mid = (left + right) / 2;
		sum = 0;
		for (int i = 0; i < n; i++)
		{
			if (trees[i] - mid > 0)
				sum += trees[i] - mid;
		}

		if (sum < m)
		{
			right = mid - 1;
		}

		else
		{
			left = mid + 1;
			max = max < mid ? mid : max;
		}
	}

	cout << max;

	return 0;

}