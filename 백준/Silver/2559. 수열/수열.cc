#include <iostream>
#include <vector>

using namespace std;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N, K;

	int maxTemperatureSum;
	int sum = 0;

	cin >> N >> K;

	vector<int> temps = vector<int>(N);

	for (int i = 0; i < N; i++) {
		cin >> temps[i];
		if (i < K)
			sum += temps[i];
	}
    maxTemperatureSum = sum;
	for (int i = 1; i < N - K + 1; i++) {
		sum = sum - temps[i - 1] + temps[i + K - 1];
		maxTemperatureSum = max(maxTemperatureSum, sum);
	}

	cout << maxTemperatureSum << "\n";
	return 0;
}