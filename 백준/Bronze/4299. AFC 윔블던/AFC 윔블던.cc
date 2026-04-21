#include <iostream>
using namespace std;
int main()
{
	int total, gap,x,y;
	cin >> total;
	cin >> gap;
	x =total + gap;
	if (total<gap)
	{
		cout << "-1" << endl;
		return 0;
	}
	if ((x%2)!=0)
	{
		cout << "-1" << endl;
		return 0;
	}
	else
	{
		x = x / 2;
		y = total - x;
		cout << x << ' ' << y << endl;
	}
	return 0;
}