#include <iostream>
using namespace std;
int main()
{
	int storage[1001];
	int num;
	int temp = 0;
	cin >> num;
	for (int c = 0; c < num; c++)
	{
		cin >> storage[c];
	}
	for (int c = 0; c < num; c++)
	{
		for (int c = 0; c < num; c++)
		{
			if (c == 0)
			{
				continue;
			}
			else if (storage[c - 1] > storage[c])
			{
				temp = storage[c];
				storage[c] = storage[c - 1];
				storage[c - 1] = temp;
			}
		}
	}
	for (int c = 0; c < num; c++)
	{
		cout << storage[c] << endl;
	}
	return 0;
}