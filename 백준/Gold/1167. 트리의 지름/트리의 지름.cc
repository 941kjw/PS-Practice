#include <iostream>
#include <vector>

using namespace std;

vector<vector<pair<int, int>>> adj;
vector<bool> visited;

int maxd = 0, endv;

void DFS(int cur,int dist)
{
	if (visited[cur])
		return;
	visited[cur] = true;
	if (dist > maxd)
	{
		maxd = dist;
		endv = cur;
	}

	for (int i = 0; i < adj[cur].size(); i++)
	{
		DFS(adj[cur][i].first,dist+adj[cur][i].second);
	}
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int V;
	cin >> V;

	adj = vector<vector<pair<int, int>>>(V + 1);
	visited = vector<bool>(V + 1);
	
	for (int i = 0; i < V; i++)
	{
		int vertex;
		
		cin >> vertex;
		
		while (true)
		{
			int to, dist;
			cin >> to;
			if (to == -1)
				break;
			cin >> dist;
			adj[vertex].push_back({ to,dist });
		}
	}
	DFS(1, 0);
	visited = vector<bool>(V + 1);
	DFS(endv, 0);

	cout << maxd << '\n';
}