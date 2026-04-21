#include <iostream>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int n;
	int max = -1;
	int maxidx = -1;

	for (int i = 0; i < 9; i++)
	{
		cin >> n;
		if (max < n)
		{
			max = n;
			maxidx = i;
		}
	}
	cout << max << '\n' << maxidx + 1  << '\n';

	return 0;
}