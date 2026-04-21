#include <iostream>
using namespace std;
int main()
{
	int temp, temp2, stanum, change, select,currp;
	select = 0;
	currp = 0;
	temp2 = 0;
	stanum = 1;
	temp = 0;
	while (stanum <= 4)
	{
		cin >> change;
		temp2 = currp - change;
		if (select <= temp2)
		{
			select = temp2;
		}
		cin >> change;
		temp = temp2 + change;
		if (select <= temp)
		{
			select = temp;
		}
		currp = temp;
		stanum++;
	}
	cout << select << endl;
	return 0;	
}