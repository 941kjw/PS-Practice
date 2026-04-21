#include <iostream>
#include <vector>
#include <queue>

using namespace std;


int V, E,start;

vector<int> distances;
vector<vector<pair<int, int>>> adj;


int main()
{
	cin >> V >> E >> start;
	adj = vector<vector<pair<int, int>>>(V+1);
	distances = vector<int>(V + 1, 99999999);
	for (int i = 0; i < E; i++)
	{
		int u, v, w;
		cin >> u >> v >> w;
		adj[u].push_back({ v,w });
	}

	priority_queue<pair<int, int>> pq;

	pq.push({ 0,start });
	distances[start] = 0;

	while (!pq.empty())
	{
		int cost = -pq.top().first;
		int cur = pq.top().second;

		pq.pop();

		for (int i = 0; i < adj[cur].size(); i++)
		{
			int next = adj[cur][i].first;
			int ncost = adj[cur][i].second;
			if (distances[next] > cost + ncost)
			{
				distances[next] = cost + ncost;
				pq.push({ -distances[next] , next });
			}
		}
	}
	for (int i = 1; i <= V; i++)
	{
		if (distances[i] == 99999999)
			cout << "INF\n";
		else
			cout << distances[i] << '\n';
	}
}