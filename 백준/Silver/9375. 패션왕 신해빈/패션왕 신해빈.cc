#include <bits/stdc++.h>

using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int T,n;
	string clothName;
	string clothType;

	cin >> T;

	while (T--) {
		cin >> n;
		map<string, int> clothInfo;
		int sum = 1;
		while (n--) {
			cin >> clothName >> clothType;

			clothInfo[clothType]++;
		}
		for (map<string,int>::iterator it = clothInfo.begin(); it != clothInfo.end(); ++it) {
			sum *= it->second + 1;
		}
		cout <<sum - 1<< "\n";
	}	
	return 0;
}
