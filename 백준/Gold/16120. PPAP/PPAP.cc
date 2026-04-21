#include <iostream>
#include <string>

using namespace std;

int main()
{
	string ppap;
	int pcount = 0;
	cin >> ppap;


	if (ppap == "P")
	{
		cout << "PPAP" << endl;
		return 0;
	}
	if(ppap.find("A")==string::npos)
	{
		cout << "NP" << endl;
		return 0;
	}
	for (int i = 0; ppap[i] != '\0'; i++)
	{
		if (ppap[i] == 'P')
		{
			pcount++;
			continue;
		}
		if (ppap[i] == 'A')
		{
			if (pcount < 2)
			{
				cout << "NP" << endl;
				return 0;
			}
			if (ppap[i + 1] == '\0')
			{
				cout << "NP" << endl;
				return 0;
			}
			if (ppap[i + 1] == 'A')
			{
				cout << "NP" << endl;
				return 0;
			}
			pcount -= 2;
		}
	}
	cout << "PPAP" << endl;
	return 0;
}