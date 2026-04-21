#include <iostream>

using namespace std;

int stairs[301];
int sum[301];
int maximum = -99999999;



int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	int size;
	int num;
	cin >> size;
	
	for (int i = 1; i <= size; i++)
	{
		cin >> stairs[i];
	}

	sum[1] = stairs[1];
	sum[2] = stairs[1] + stairs[2];
	sum[3] = max(stairs[1] + stairs[3], stairs[2] + stairs[3]);
	
	for (int i = 4; i <= size; i++)
	{
		sum[i] = max(sum[i - 2] + stairs[i], sum[i - 3] + stairs[i - 1] + stairs[i]);
	}

	cout << sum[size] << '\n';
}