#include<bits/stdc++.h>

using namespace std;

void countFrogs(int& a, int& b) {
	a++;
	if (b)
		b--;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	string s;
	cin >> s;

	int k = 0, p = 0;
	int ans = 1;

	for (int i = 0; i < s.size(); i++) {
		s[i] == 'K' ? countFrogs(k, p) : countFrogs(p, k);
		ans = max(ans, max(k, p));
	}

	cout << ans;
	return 0;
}