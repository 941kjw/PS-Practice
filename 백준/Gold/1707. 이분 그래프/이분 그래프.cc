#include <iostream>
#include <vector>
#include <queue>

#define BLUE 1
#define RED 2
using namespace std;

int K, V, E, u, t;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> K;
	while (K--)
	{
		cin >> V >> E;
		vector<vector<int>> adj(V+1);
		vector<int>visited(V + 1);
		for (int i = 0; i < E; i++)
		{
			cin >> u >> t;
			adj[u].push_back(t);
			adj[t].push_back(u);
		}

		for (int i = 1; i <= V; i++)
		{
			if (!visited[i])
			{
				int color = RED;
				queue<int> q;
				q.push(i);
				visited[i] = color;

				while (!q.empty())
				{
					int cur = q.front();
					q.pop();

					if (visited[cur] == RED)
						color = BLUE;
					else if (visited[cur] == BLUE)
						color = RED;

					for (int i = 0; i < adj[cur].size(); i++)
					{
						int next = adj[cur][i];
						if (!visited[next])
						{
							visited[next] = color;
							q.push(next);
						}
					}
				}
			}
		}
		bool flag = true;

		for (int i = 1; i <= V; i++)
		{
			for (int j = 0; j < adj[i].size(); j++)
			{
				int next = adj[i][j];
				if (visited[i] == visited[next])
				{
					flag = false;
					i = V + 1;
					break;
				}
			}
		}

		if (flag)
			cout << "YES\n";
		else
			cout << "NO\n";
	}
}

