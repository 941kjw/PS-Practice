#include <bits/stdc++.h>
#define pii pair<int,int>
using namespace std;


int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int N,Q;
	cin >> N >> Q;
	vector<pii>* vs = (vector<pii> *) malloc(sizeof(vector<pii>)* (N + 1));
	for (int i = 0; i < N-1; i++) {
		int p, q, r;
		cin >> p >> q >> r;
		vs[p].push_back({ q,r });
		vs[q].push_back({ p,r });
	}

	queue<int> q;	
	vector<int> visited;
	for (int i = 0; i < Q; i++) {
		visited = vector<int>(N + 1);
		int k,v;
		cin >> k>>v;
		int c = 0;
		q.push(v);
		visited[v] = true;
		while (!q.empty()) {
			int cur = q.front();
			q.pop();
			for (int j = 0; j < vs[cur].size(); j++) {
				if (vs[cur][j].second >= k && !visited[vs[cur][j].first]) {
					q.push(vs[cur][j].first);
					visited[vs[cur][j].first] = true;
					c++;
				}
			}
		}
		cout << c << '\n';
	}
	free(vs);
}