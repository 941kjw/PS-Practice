#include <iostream>

using namespace std;

int arr[1000][1000];
int arr_recover[300][300];
int h,w, x, y;

void solve()
{
	for (int i = 0; i < h; i++)
	{
		for (int j = 0; j < w; j++)
		{
			arr_recover[i][j] = arr[i][j];
		}
	}

	for (int i = 0; i < h; i++)
	{
		for (int j = 0; j < w; j++)
		{
			if (i + x >= h || j + y >= w)
				continue;
			arr_recover[i + x][j + y] -= arr_recover[i][j];
		}
	}

	for (int i = 0; i < h; i++)
	{
		for (int j = 0; j < w; j++)
		{
			cout << arr_recover[i][j] << ' ';
		}
		cout << '\n';
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	cin >> h >> w >> x >> y;

	for (int i = 0; i < h + x; i++)
	{
		for (int j = 0; j < w + y; j++)
		{
			cin >> arr[i][j];
		}
	}

	solve();
}
