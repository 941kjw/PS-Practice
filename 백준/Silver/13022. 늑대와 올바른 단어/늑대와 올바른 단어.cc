#include <iostream>
#include <string>

using namespace std;

int main()
{

	string wolves;

	int _result = 0;
	int current = 0;
	int tcount = 0;
	int ccount = 0;


	cin >> wolves;

	while (current < wolves.length())
	{
		start1:
		while (wolves[current] == 'w')
		{
			tcount++;
			current++;
		}
		if (wolves[current-1] == 'w')
		{
			if (wolves[current] != 'o')
				break;
		}
		while (wolves[current] == 'o')
		{
			ccount++;
			current++;
		}
		if (ccount != tcount)
			break;
		ccount = 0;
		if (wolves[current - 1] == 'o')
		{
			if (wolves[current] != 'l')
				break;
		}
		while (wolves[current] == 'l')
		{
			ccount++;
			current++;
		}
		if (ccount != tcount)
			break;
		ccount = 0;
		if (wolves[current - 1] == 'l')
		{
			if (wolves[current] != 'f')
				break;
		}
		while (wolves[current] == 'f')
		{
			ccount++;
			current++;
		}
		if (ccount != tcount)
			break;	
		ccount = 0;
		if (wolves[current-1] == 'f' && current < wolves.length())
		{
			if (wolves[current] == 'w')
			{
				tcount = 0;
				ccount = 0;
				goto start1;
			}
			else
				break;
		}
		_result = 1;
	}

	cout << _result << endl;

	return 0;
}