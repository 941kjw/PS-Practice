#include <iostream>
#include <vector>
using namespace std;


int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	string n;
	
	while (true)
	{
		bool t = true;
		
		cin >> n;
		int c = n.size();
		if (n == "0")
			break;

		for (int i = 0; i < (c / 2); i++)
		{
			if (n[c - i -1] != n[i])
			{
				t = false;
				break;
			}
		}

		if (t)
			cout << "yes\n";
		else
			cout << "no\n";		
	}


	return 0;
}