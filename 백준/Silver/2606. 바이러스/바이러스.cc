#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> network;
vector<bool> visited;
int result = -1;

void DFS(int cur)
{
	if (visited[cur] == true)
	{
		return;
	}
	else
	{
		result++;
		visited[cur] = true;
		for (int i = 0; i < network[cur].size(); i++)
			DFS(network[cur][i]);
	}
}

int main()
{
	visited.resize(101);
	network.resize(101);
	for (vector<int> internalnet : network)
		internalnet.resize(101);

	int computers;
	int connections;
	int from;
	int to;

	cin >> computers >> connections;

	for (int i = 0; i < connections; i++)
	{
		cin >> from >> to;
		network[from].push_back(to);
		network[to].push_back(from);
	}
	DFS(1);
	cout << result;
}