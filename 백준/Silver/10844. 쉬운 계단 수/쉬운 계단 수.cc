#include <iostream>
#include <vector>
using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int n;
	cin >> n;

	vector<vector<long>> numbers(n + 1, vector<long>(10));
	for (int i = 1; i < 10; i++)
		numbers[1][i] = 1;
	numbers[1][0] = 0;
	for (int i = 2; i <= n; i++)
	{
		for (int j = 0; j < 10; j++)
		{
			if(j==0)
				numbers[i][0] = numbers[i - 1][1] % 1000000000;
			else if(j==9)
				numbers[i][9] = numbers[i - 1][8] % 1000000000;
			else
				numbers[i][j] = (numbers[i - 1][j - 1] + numbers[i - 1][j + 1]) % 1000000000;
		}
	}
	long result = 0;
	for (int i = 0; i <10; i++)
	{
		result += numbers[n][i];
	}
	cout << result % 1000000000;
}