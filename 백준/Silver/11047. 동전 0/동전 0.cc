#include <iostream>

using namespace std;

int N, K, result = 0;
int coins[10];

int main()
{
	cin >> N >> K;
	for (int i = 0; i < N; i++)
	{
		cin >> coins[i];
	}

	for (int i = N-1; i >= 0;i--)
	{

		result += K / coins[i];
		K %= coins[i];

		if (K == 0)
			break;
	}
	cout << result;
}