#include <iostream>
#include <vector>

#define INT_MAX 2147483648
using namespace std;

int N, M, A, B, C;

vector <vector<pair<int,int>>> edge;
long long distances[510];

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> M;
	for (int i = 1; i <= N; i++)
		distances[i] = 987654321;
	edge = vector<vector<pair<int, int>>>(N + 1);
	for (int i = 0; i < M; i++)
	{
		cin >> A >> B >> C;
		edge[A].push_back({ B,C });
	}

	distances[1] = 0;
	bool cycle = false;

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <=N; j++)
		{
			for (int k = 0; k < edge[j].size(); k++)
			{
				int next = edge[j][k].first;
				int d = edge[j][k].second;

				if (distances[j] != 987654321 && distances[next] > distances[j] + d)
				{
					distances[next] = distances[j] + d;
					if (i == N)
						cycle = true;
				}
			}
			

			
		}
	}
	
	if (cycle)
		cout << -1 << '\n';
	else
	{
		for (int i = 2; i <= N; i++)
			cout << (distances[i] != 987654321 ? distances[i] : -1) << '\n';
	}

}