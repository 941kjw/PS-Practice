#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<pair<int, int>> graph[2][1001];
vector<int> dist[2];
int resdist[1001];
int N, M, X;


void Dijkstra(int k) {
	dist[k][X] = 0;

	priority_queue < pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
	q.push({ 0,X });

	while (!q.empty()) {
		int min_cost = q.top().first;
		int now = q.top().second;

		q.pop();
		if (min_cost > dist[k][now])
			continue;
		for (int i = 0; i < graph[k][now].size(); i++) {
			int next = graph[k][now][i].second;
			int next_cost = min_cost + graph[k][now][i].first;

			if (next_cost < dist[k][next]) {
				dist[k][next] = next_cost;
				q.push({ next_cost,next });
			}
		}
	}

}

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	

	cin >> N >> M >> X;

	for (int i = 0; i < M; i++)
	{
		int s, e, t;
		cin >> s >> e >> t;
		graph[0][s].push_back({ t,e });
		graph[1][e].push_back({ t,s });
	}
	/*
	X->각 정점으로 가는건 정방향으로..
	역방향으로 입력을 받은 후 X에서 다익스트라를 돌리면...
	마치 각 정점에서 X로 오는 길만 뽑아서 최단거리를 얻는 효과를 볼 수 있다!
	*/
	dist[0].resize(N + 1, 1e9 + 7);
	dist[1].resize(N + 1, 1e9 + 7);

	Dijkstra(1);
	Dijkstra(0);
	int res = 0;
	for (int i = 1; i <= N; i++) {
		res = max(res, dist[0][i] + dist[1][i]);
	}

	cout << res;

	return 0;
}