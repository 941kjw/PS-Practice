#include <iostream>
#include <cstring>
using namespace std;


int main() {
	int n, m;
	int answer = 0, count = 0;
	string s;
	cin >> n >> m >> s;

	for (int i = 0; i < m-1;) {
		if (s.substr(i, 3) == "IOI") {
			i += 2;
			count++;
			if (count == n) {
				answer++;
				count--;
			}
		}
		else {
			i++;
			count = 0;
		}
	}

	cout << answer << "\n";
	return 0;
}
