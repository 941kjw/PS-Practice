#include <iostream>

using namespace std;

int main()
{
	int screensize;
	int bagunisize;
	int saguacount;
	int saguadropzone;
	int movement = 0;

	int leftbound = 1;
	int rightbound;

	cin >> screensize;
	cin >> bagunisize;
	cin >> saguacount;

	rightbound = bagunisize;
	for (int i = 0; i < saguacount; i++)
	{
		cin >> saguadropzone;
		if (rightbound < saguadropzone)
		{
			leftbound = saguadropzone - bagunisize + 1;
			movement += (saguadropzone - rightbound);
			rightbound = saguadropzone;
			continue;
		}
		if (leftbound > saguadropzone)
		{
			rightbound = saguadropzone + bagunisize - 1;
			movement += (leftbound - saguadropzone);
			leftbound = saguadropzone;
			continue;
		}
	}
	cout << movement;
	return 0;
}