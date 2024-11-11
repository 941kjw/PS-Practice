#include <bits/stdc++.h>
#define pii pair<int,int>
using namespace std;

struct distanceInfo {
	int from;
	int to;
	int dist;
};

struct cmp {
	bool operator()(distanceInfo& a, distanceInfo& b) {
		return a.dist > b.dist;
	}
};

int distance(pii p1, pii p2) {
	return pow(p1.first - p2.first, 2) + pow(p1.second - p2.second, 2);
}

int head[2001];

int findHead(int p) {
	if (head[p] == p)
		return p;
	return head[p] = findHead(head[p]);
}

void unionHead(int a, int b) {
	a = findHead(a);
	b = findHead(b);

	if (a > b)
		head[a] = b;
	else if(b>a)
		head[b] = a;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int N, C;

	cin >> N >> C;
	vector<pii> points;
	int x, y;
	for (int i = 0; i < N; i++) {
		cin >> x >> y;
		head[i] = i;
		points.push_back({ x,y });
	}
	distanceInfo tmp;
	priority_queue<distanceInfo, vector<distanceInfo>, cmp> pq;
	for (int i = 0; i < N-1; i++) {
		for (int j = i + 1; j < N; j++) {
			int d = distance(points[i], points[j]);

			tmp.from = i;
			tmp.to = j;
			tmp.dist = d;
			pq.push(tmp);
		}
	}


		int sum = 0, count = 0;
		while (!pq.empty()) {
			tmp = pq.top();
			pq.pop();
			if (tmp.dist < C)
				continue;
			int fromHead = findHead(tmp.from);
			int toHead = findHead(tmp.to);
			if (fromHead == toHead)
				continue;
			unionHead(fromHead, toHead);
			sum += tmp.dist;
			count++;
			if (count == N - 1)
				break;
		}
		if (count != N - 1)
			sum = -1;
		cout << sum << '\n';
	
}