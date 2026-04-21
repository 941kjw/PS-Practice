#include <iostream>
using namespace std;
int main()
{
	int date, month, n,c;
	int q = 31, w = 28, e = 31, r = 30, t = 31, y = 30, u = 31, i = 31, o = 30, p = 31, a = 30;
	cin >> month;
	cin >> date;
	if (month == 1)
	{
		n = date-1;
	}	
	else if (month == 2)
	{
		n = date+q-1;
	}	
	else if (month == 3)
	{
		n = date+q+w-1;
	}	
	else if (month == 4)
	{
		n = date+q+w+e-1;
	}	
	else if (month == 5)
	{
		n = date+q+w+e+r-1;
	}	
	else if (month == 6)
	{
		n = date+q+w+e+r+t-1;
	}	
	else if (month == 7)
	{
		n = date+q+w+e+r+t+y-1;
	}	if (month == 8)
	{
		n = date+q+w+e+r+t+y+u-1;
	}	
	else if (month == 9)
	{
		n = date+q+w+e+r+t+y+u+i-1;
	}	
	else if (month ==10)
	{
		n = date+q + w + e + r + t + y + u + i+o-1;
	}	
	else if (month == 11)
	{
		n = date+q + w + e + r + t + y + u + i+o+p-1;
	}
	else if (month == 12)
	{
		n = date+q + w + e + r + t + y + u + i+o+p+a-1;
	}
	c = n % 7;
	if (c == 0)
	{
		cout << "MON" << endl;
	}
	else if (c == 1)
	{
		cout << "TUE" << endl;
	}
	else if (c == 2)
	{
		cout << "WED" << endl;
	}
	else if (c == 3)
	{
		cout << "THU" << endl;
	}
	else if (c == 4)
	{
		cout << "FRI" << endl;
	}
	else if (c == 5)
	{
		cout << "SAT" << endl;
	}
	else if (c == 6)
	{
		cout << "SUN" << endl;
	}
	return 0;
}