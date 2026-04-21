#include <iostream>

using namespace std;

int triangle[501][501];
int sum[501][501];
int maximum = -99999999;



int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	int size;
	int num;
	cin >> size;
	cin >> num;
	triangle[1][1] = num;
	sum[1][1] = triangle[1][1];
	for (int i = 2; i <= size; i++)
	{
		for (int j = 1; j <= i; j++)
		{
			cin >> num;
			triangle[i][j] = num;
			sum[i][j] = triangle[i][j] + max(sum[i - 1][j - 1], sum[i - 1][j]);
		}
	}
	for (int i = 1; i <= size; i++)
	{
		maximum = max(sum[size][i], maximum);
	}
	cout << maximum << '\n';
}