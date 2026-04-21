#include <iostream>
#include <vector>

using namespace std;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	int num;
	int temp;
	int count[10001] = { 0, };
	cin >> num;

	for (int i = 0; i < num; i++)
	{
		cin >> temp;
		count[temp]++;
	}

	for (int i = 0; i < 10001; i++)
	{
		for (int j = 0; j < count[i]; j++)
		{
			cout << i << '\n';
		}
	}
}