#include<bits/stdc++.h>

using namespace std;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	string s;
	cin >> s;

	int k = 0, p = 0;
	int ans = 1;

	s[0] == 'K' ? k++ : p++;

	for (int i = 1; i < s.size(); i++) {
		if (s[i] == 'K') {
			k++;
			if (p > 0)
				p--;
		}
		else {
			p++;
			if (k > 0)
				k--;
		}
		ans = max(ans, max(k, p));
	}

	cout << ans;
	return 0;
}