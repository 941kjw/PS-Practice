#include <iostream>

using namespace std;

int main()
{
	int globalbuff = 0;
	string gwalho;
	int current = 0;
	int numresult = 0;
	cin >> gwalho;
	while (current<gwalho.length())
	{
		if (gwalho[current] == '(')
			globalbuff += 1;
		else if (gwalho[current] == '{')
			globalbuff += 2;
		else if (gwalho[current] == '[')
			globalbuff += 3;
		else if (gwalho[current] == ')')
			globalbuff -= 1;
		else if (gwalho[current] == '}')
			globalbuff -= 2;
		else if (gwalho[current] == ']')
			globalbuff -= 3;
		else
		{
			numresult = numresult < globalbuff ? globalbuff : numresult;
		}
		current++;
	}

	cout << numresult << endl;

	return 0;
}