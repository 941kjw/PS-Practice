#include <iostream>
#include <string>
#include <vector>
using namespace std;

vector<vector<int>> note;
vector<int> output = { 0,0,0 };

void compress(int n, int y, int x)
{
	bool allsame = true;
	int first = note[y][x];

	for (int i = y; i < y + n; i++)
	{
		for (int j = x; j < x + n; j++)
		{
			if (note[i][j] != first)
			{
				allsame = false;
				i = y + n;
				break;
			}
		}
	}
	if (allsame)
	{
		output[first + 1]++;
	}
	else
	{
		for (int i = y; i < y + n; i += n / 3)
		{
			for (int j = x; j < x + n; j += n / 3)
			{
				compress(n / 3, i, j);
			}
		}
	}
	return;
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;
	note = vector<vector<int>>(n);

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			int x;
			cin >> x;
			note[i].push_back(x);
		}
	}
	compress(n, 0, 0);

	cout << output[0] << '\n' << output[1] << '\n' << output[2] << '\n';
}