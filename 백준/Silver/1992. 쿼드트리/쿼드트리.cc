#include <iostream>
#include <string>
#include <vector>
using namespace std;

vector<vector<int>> note;
int globalBlue = 0;
int globalWhite = 0;

void compress(int n, int y, int x)
{
	if (n == 1)
	{
		cout << note[y][x];
		return;
	}

	bool isZero = true;
	bool isOne = true;

	for (int i = y; i < y + n; i++)
	{
		for (int j = x; j < x + n; j++)
		{
			if (note[i][j] == 1)
				isZero = false;
			else
				isOne = false;
			if (!isOne && !isZero)
				break;
		}
		if (!isOne && !isZero)
			break;
	}
	if (isOne)
		cout << 1;
	else if (isZero)
		cout << 0;
	else
	{
		cout << '(';
		compress(n / 2, y, x);
		compress(n / 2, y, x + n / 2);
		compress(n / 2, y + n / 2, x);
		compress(n / 2, y + n / 2, x + n / 2);
		cout << ')';
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
		string s;
		cin >> s;
		for (int j = 0; j < n; j++)
		{
			note[i].push_back(s[j] - '0');
		}
	}
	compress(n, 0, 0);
}