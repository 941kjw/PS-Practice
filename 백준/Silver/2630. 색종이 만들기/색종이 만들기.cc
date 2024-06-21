#include <iostream>
#include <string>
#include <vector>
using namespace std;

vector<vector<int>> colorPaper;
int globalBlue = 0;
int globalWhite = 0;

void cut(int n, int y, int x)
{
	bool isBlue = true;
	bool isWhite = true;

	for (int i = y; i < y + n; i++)
	{
		for (int j = x; j < x + n; j++)
		{
			if (colorPaper[i][j] == 1)
				isWhite = false;
			else
				isBlue = false;
			if (!isBlue && !isWhite)
				break;
		}
		if (!isBlue && !isWhite)
			break;
	}
	if (isBlue)
		globalBlue++;
	else if(isWhite)
		globalWhite++;
	else
	{
		cut(n / 2, y, x);
		cut(n / 2, y, x + n / 2);
		cut(n / 2, y + n / 2, x);
		cut(n / 2, y + n / 2, x + n / 2);
	}
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;
	colorPaper = vector<vector<int>>(n);

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			int x;
			cin >> x;
			colorPaper[i].push_back(x);
		}
	}
	cut(n, 0, 0);
	cout << globalWhite << '\n' << globalBlue << '\n';
}