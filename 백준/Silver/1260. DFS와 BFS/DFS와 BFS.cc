#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

vector<vector<int>> adjacent;
vector<bool> visited;


void dfs(int cur)
{
	if (visited[cur] == true)
		return;
	else
	{
		cout << cur << ' ';
		visited[cur] = true;
		for (int i = 0; i < adjacent[cur].size(); i++)
		{
			dfs(adjacent[cur][i]);
		}
	}
}

void bfs(int cur)
{
	queue<int> to_visit;
	to_visit.push(cur);
	visited[cur] = true;

	while (!to_visit.empty())
	{
		int next = to_visit.front();
		to_visit.pop();
		cout << next << ' ';
		for (int i = 0; i < adjacent[next].size(); i++)
		{
			int neighbour = adjacent[next][i];
			if (!visited[neighbour])
			{
				to_visit.push(neighbour);
				visited[neighbour] = true;
			}
		}
	}
}

int main()
{
	int vertex;
	int edge;
	int root;
	int from;
	int to;
	cin >> vertex >> edge >> root;
	adjacent.resize(1001);
	visited.resize(1001);
	for (vector<int> i : adjacent)
	{
		i.resize(1001);
	}
	for (int i = 0; i < edge ; i++)
	{
		cin >> from >> to;
		adjacent[from].push_back(to);
		adjacent[to].push_back(from);
	}
	for (int i = 0; i < adjacent.size(); i++)
	{
		sort(adjacent[i].begin(),adjacent[i].end());
	}

	dfs(root);
	cout << '\n';
	for (int i =0;i<visited.size();i++)
	{
		visited[i] = false;
	}
	bfs(root);
}