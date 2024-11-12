#include <bits/stdc++.h>
#define pii pair<int,int>
using namespace std;

int N,P,K;
vector<pii> graph[1001];
int dist[1001];

bool find(int val) {
	priority_queue<pii> pq;
	pq.push({ 1,0 }); // nodeNum, sum (which costs more than val)
	while (!pq.empty()) {
		int cnode = pq.top().first;
		int cweight = -1 * pq.top().second; // 역순 정렬을 위해

		pq.pop();
		if (dist[cnode] < cweight) // check if visited && larger weight
			continue;

		for (int i = 0; i < graph[cnode].size(); i++) {
			int nnode = graph[cnode][i].first;
			int nweight = graph[cnode][i].second;

			int w = cweight + (nweight > val); //기준값보다 비싼 간선이면 sum++;
			if (dist[nnode] > w) {
				dist[nnode] = w;
				pq.push({ nnode,-1*w });
			}
		}
	}
	//최종적으로 N에 도달하는데 최단 거리이면서, val보다 비싼 간선의 갯수를 세게 됨.
	//그리고 공짜 갯수 제한 내로 해결 가능한지를 반환
	return dist[N] <= K;
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);


	cin >> N >> P >> K;

	for (int i = 0; i < P; i++) {
		int u, v, w;
		cin >> u >> v >> w;
		graph[u].push_back({ v,w });
		graph[v].push_back({ u,w });
	}

	int l = 0, r = 1e7, ans = -1;

	while (l <= r) {
		int mid = (l + r) >> 1;
		memset(dist, 0x3f, sizeof dist);
		dist[1] = 0;

		if (find(mid)) { // 공짜로 해결이 되는가?
			r = mid - 1; // 예산을 더 줄여볼까?
			ans = mid;
		}
		else { //불가능하다면 예산을 늘림.
			l = mid + 1;
		}
	}

	cout << ans;
}