#include <iostream>
#include <queue>
using namespace std;

int friends[101][101];
int N, M;

void floyd() {
	for (int k = 1; k <= N; k++)
	{
		for (int i = 1; i <= N; i++)
		{
			for (int j = 1; j <= N; j++)
			{
				if (i == j)
					continue;
				else if (friends[i][k] != 0 && friends[k][j] != 0)
				{
					if (friends[i][j] == 0)
						friends[i][j] = friends[i][k] + friends[k][j];
					else
						friends[i][j] = min(friends[i][j], friends[i][k] + friends[k][j]);
				}
			}
		}
	}
}

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	while (M--)
	{
		int A, B;
		cin >> A >> B;
		friends[A][B] = 1;
		friends[B][A] = 1;
	}

	floyd();

	int result = 999999999;
	int inssa = 0;

	for (int i = 1; i <= N; i++)
	{
		int sum = 0;
		for (int j = 1; j <= N; j++)
			sum += friends[i][j];

		if (result > sum) {
			result = sum;
			inssa = i;
		}

	}

	cout << inssa;

	return 0;
}