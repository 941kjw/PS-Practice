#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;
int dist[2001];
vector<pi> graph[2001];//그래프
int tc, n, m, t, s, g, h, a, b, c, cost;
 
//st에서 시작해서 ed에서 끝나는 다익스트라
int dijk(int st, int ed) {
    for (int i = 1; i <= n; i++) dist[i] = 1e9;
    priority_queue<pi> q;
    dist[st] = 0; q.push({ 0, st });
    while (!q.empty()) {
        int x = q.top().second;
        int d = -q.top().first;
        if (x == ed) return dist[x];
        q.pop();
        if (dist[x] != d) continue; 
        for (pi nxt : graph[x]) {
            int nx = nxt.first;
            int nd = nxt.second + d;
            if (dist[nx] > nd) {
                dist[nx] = nd;
                q.push({ -dist[nx], nx });
            }
        }
    }
    return 1e9;
}
 
int main() {
    ios::sync_with_stdio(0); cin.tie(0);
    cin >> tc;
    while (tc--) {
        cin >> n >> m >> t;
        cin >> s >> g >> h;
        for (int i = 1; i <= n; i++) graph[i].clear(); //초기화
        while (m--) {
            cin >> a >> b >> c;
            graph[a].push_back({ b, c });
            graph[b].push_back({ a, c });
            if (a == g && b == h || a == h && b == g) cost = c;
        }
        vector<int> ans; //가능한 목적지 후보 저장
        while (t--) {
            int x; cin >> x;
            //g에서 h로 가는 경로가 최단경로라면
            if (dijk(s, x) == dijk(s, g) + cost + dijk(h, x))
                ans.push_back(x);
            //h에서 g로 가는 경로가 최단경로라면
            else if (dijk(s, x) == dijk(s, h) + cost + dijk(g, x))
                ans.push_back(x);
        }
        sort(ans.begin(), ans.end());
        for (int x : ans) cout << x << ' ';
        cout << '\n';
    }
}
