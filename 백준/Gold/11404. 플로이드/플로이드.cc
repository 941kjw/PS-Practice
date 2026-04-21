#include <iostream>
#include <vector>

using namespace std;

int n, m, a, b, c;

vector<vector<int>> matrix;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;

	matrix = vector<vector<int>>(n + 1, vector<int>(n + 1,999999999));

	for (int i = 0; i < m; i++)
	{
		cin >> a >> b >> c;
		if(matrix[a][b] > c)
			matrix[a][b] = c;
	}

	for (int k = 1; k <= n; k++)
	{
		for (int i = 1; i <= n; i++)
		{
			for (int j = 1; j <= n; j++)
			{
				if (matrix[i][k] != 999999999 && matrix[k][j] != 999999999)
					matrix[i][j] = min(matrix[i][j], matrix[i][k] + matrix[k][j]);
			}
		}
	}

	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			if (i == j || matrix[i][j] == 999999999)
				cout << 0 << ' ';
			else
				cout << matrix[i][j] << ' ';
		}
		cout << '\n';
	}
}