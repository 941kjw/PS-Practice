#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> adj;
vector<int> parent;
vector<bool> visited;

void DFS(int cur)
{
	visited[cur] = true;

	for (int i = 0; i < adj[cur].size(); i++)
	{
		int next = adj[cur][i];

		if (!visited[next])
		{
			parent[next] = cur;
			DFS(next);
		}
	}
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N, from, to;

	cin >> N;
	adj = vector<vector<int>>(N + 1);
	parent = vector<int>(N + 1);
	visited = vector<bool>(N + 1);
	for (int i = 0; i < N-1; i++)
	{
		cin >> from >> to;
		adj[from].push_back(to);
		adj[to].push_back(from);
	}
	DFS(1);

	for (int i = 2; i < N + 1; i++)
		cout << parent[i] << '\n';
}