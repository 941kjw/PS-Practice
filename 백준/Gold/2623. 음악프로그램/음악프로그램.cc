#include <bits/stdc++.h>

using namespace std;

int N,M;
vector<int> in, result;
vector<vector<int>> graph;


int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> M;
	int count, singNum;
	in.assign(N + 1, 0);
	result.assign(N, 0);
	graph.assign(N + 1, vector<int>(0, 0));
	
	for (int i = 0; i < M; i++) {
		cin >> count;
		cin >> singNum;
		for (int j = 1; j < count; j++) {
			int prev = singNum;
			cin >> singNum;
			graph[prev].push_back(singNum);
			in[singNum]++;
		}
	}

	queue<int> q; 

	for (int i = 1; i <= N; i++) {
		if (in[i] == 0)
			q.push(i);
	}

	for (int i = 0; i < N; i++) {
		if (q.empty() && i < N-1) {
			cout << "0";
			return 0;
		}
			
		int cur = q.front();
		result[i] = cur;
		q.pop();

		for (int j = 0; j < graph[cur].size(); j++) {
			int next = graph[cur][j];
			if (--in[next] == 0)
				q.push(next);
		}
	}
	for (int i : result) {
		cout << i << "\n";
	}
	return 0;
}