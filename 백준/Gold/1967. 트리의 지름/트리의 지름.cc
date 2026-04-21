#include <iostream>
#include <vector>
#include <queue>
using namespace std;



vector<vector<pair<int,int>>> adj = (10001,vector<vector<pair<int,int>>>(10001));
bool visited[10001] = { false, };
int maxV = 0;
int endpoint;

void dfs(int cur, int dist)
{
	if (visited[cur])
		return;
	visited[cur] = true;
	if (maxV < dist)
	{
		maxV = dist;
		endpoint = cur;
	}
	for (int i = 0; i < adj[cur].size(); i++)
	{
		dfs(adj[cur].at(i).first, dist + adj[cur].at(i).second);
	}
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int testcase;
	int homenum;
	int from;
	int to;
	int friendly;
		cin >> homenum;
		for (int j = 1; j < homenum; j++)
		{
			cin >> from;
			cin >> to;
			cin >> friendly;
			adj[from].push_back({ to,friendly });
			adj[to].push_back({ from,friendly });
			
		}
		dfs(1, 0);
		fill_n(visited, 10001, false);
		dfs(endpoint, 0);
		cout << maxV << '\n';
	
}