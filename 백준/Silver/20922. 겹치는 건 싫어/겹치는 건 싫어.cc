#include <iostream>

using namespace std;

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	int N, K;
	int counts[100001]{ 0, };
	cin >> N >> K;
	int* numbers = new int[N + 1];

	for (int i = 0; i < N; i++) {
		cin >> numbers[i];
	}

	int start=0,end=0,result = 0;

	for (start = 0; start < N; start++) {
		while (end < N && counts[numbers[end]] < K) {
			counts[numbers[end]]++;
			end++;
		}
		result = max(result, end - start);
		if (end == N)
			break;
		counts[numbers[start]]--;
	}
	cout << result << '\n';
}