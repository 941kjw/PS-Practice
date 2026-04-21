#include <bits/stdc++.h>
#define pii pair<int,int>
using namespace std;

int N;

vector<pii> cows;
int result = 0;

bool cmp(pii a, pii b) {
	return a.first < b.first;
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	int arrival, duration;
	for (int i = 0; i < N; i++) {
		cin >> arrival >> duration;
		cows.push_back({ arrival,duration });
	}

	sort(cows.begin(), cows.end(), cmp);

	int curTime = 0;

	for (pii c : cows) {
		if (c.first > curTime)
			curTime = c.first;
		
		curTime += c.second;
	}

	cout << curTime << '\n';
}