#include <iostream>

using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int numbers[5];
	int sixth = 0;
	for (int i = 0; i < 5; i++)
	{
		cin >> numbers[i];
	}



	for (int i = 0; i < 5; i++)
	{
		sixth += (numbers[i] * numbers[i]);
	}

	sixth %= 10;

	cout << sixth << '\n';
	
	return 0;
}