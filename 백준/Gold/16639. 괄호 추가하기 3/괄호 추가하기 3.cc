#include <bits/stdc++.h>

using namespace std;

int N;
string s;
int maxCache[10][10];
int minCache[10][10];

void init() {
	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			maxCache[i][j] = INT_MIN;
			minCache[i][j] = INT_MAX;
		}
	}
}

int calculate(int a, int b, char op) {
	switch (op) {
	case '+':
		return a + b;
	case '-':
		return a - b;
	case '*':
		return a * b;
	}
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> s;

	init();

	int numCount = N / 2 + 1;

	for (int i = 0; i < numCount; i++) {
		maxCache[i][i] = minCache[i][i] = s[i * 2] - '0';
	}

    for (int cnt = 1; cnt < numCount; cnt++)
    {
        for (int idx = 0; idx < numCount - cnt; idx++)
        {
            for (int i = 1, j = cnt; i <= cnt; i++, j--)
            {
                int opIdx = (idx + cnt - j) * 2 + 1;
                char op = s[opIdx];
                int candidates[4] = {
                    calculate(maxCache[idx][idx + cnt - j], maxCache[idx + i][idx + cnt], op), //idx에서 idx + cnt 까지 사이를 i/j로 조절
                    calculate(maxCache[idx][idx + cnt - j], minCache[idx + i][idx + cnt], op),
                    calculate(minCache[idx][idx + cnt - j], maxCache[idx + i][idx + cnt], op),
                    calculate(minCache[idx][idx + cnt - j], minCache[idx + i][idx + cnt], op)
                };
                sort(candidates, candidates + 4);
                maxCache[idx][idx + cnt] = max(maxCache[idx][idx + cnt], candidates[3]);
                minCache[idx][idx + cnt] = min(minCache[idx][idx + cnt], candidates[0]);
            }
        }

    }

	int result = maxCache[0][numCount - 1];
	cout << result << '\n';
}