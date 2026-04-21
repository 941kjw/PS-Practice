#include <iostream>

using namespace std;

int custom_max = -1000000001;
int custom_min = 1000000001;
int numcount;
int numbers[11];
int operators[4];



void solve(int result,int idx)
{
	if (idx == numcount)
	{
		if (result > custom_max)
			custom_max = result;
		if (result < custom_min)
			custom_min = result;
		return;
	}
	for (int i = 0; i < 4; i++)
	{
		if (operators[i] > 0)
		{
			operators[i]--;
			if (i == 0)
				solve(result + numbers[idx], idx + 1);
			else if (i == 1)
				solve(result - numbers[idx], idx + 1);
			else if (i == 2)
				solve(result * numbers[idx], idx + 1);
			else
				solve(result / numbers[idx], idx + 1);
			operators[i]++;
		}
	}
	return;
}

int main()
{
	cin >> numcount;
	for (int i = 0; i < numcount; i++)
	{
		cin >> numbers[i];
	}
	for (int i = 0; i < 4; i++)
	{
		cin >> operators[i];
	}
	solve(numbers[0],1);
	cout << custom_max << '\n';
	cout << custom_min;
}