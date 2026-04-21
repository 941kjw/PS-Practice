#include <iostream>
#include <vector>
#include <queue>

using namespace std;


int V, E, v1, v2, answer = 0;

vector<int> distances;
vector<vector<pair<int, int>>> adj;


int main()
{
	cin >> V >> E;
	adj = vector<vector<pair<int, int>>>(V + 1);
	distances = vector<int>(V + 1, 99999999);
	for (int i = 0; i < E; i++)
	{
		int u, v, w;
		cin >> u >> v >> w;
		adj[u].push_back({ v,w });
		adj[v].push_back({ u,w });
	}
	cin >> v1 >> v2;
	priority_queue<pair<int, int>> pq;

	pq.push({ 0,1 });
	distances[1] = 0;

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
	int stov1 = distances[v1];
	int stov2 = distances[v2];
	distances = vector<int>(V + 1, 99999999);
	pq.push({ 0,v1 });
	distances[v1] = 0;
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
	int v1tov2 = distances[v2];

	distances = vector<int>(V + 1, 99999999);
	pq.push({ 0,V });
	distances[V] = 0;
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
	int vtov1 = distances[v1];
	int vtov2 = distances[v2];
	stov1 += (v1tov2 + vtov2);
	stov2 += (v1tov2 + vtov1);
	answer = stov1 > stov2 ? stov2 : stov1;
	answer = answer >= 99999999 ? -1 : answer;
	cout << answer << '\n';
}