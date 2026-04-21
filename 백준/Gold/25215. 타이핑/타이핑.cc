#include <iostream>

using namespace std;

bool isupper(char c)
{
	return c - 'a' < 0;
}

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);
	
	string s;
	int combo = 0;
	bool capsLock = false;

	cin >> s;
	
	for (int i = 0; i < s.length(); i++)
	{
		if (isupper(s.at(i)) ^ capsLock)
		{
			if (i + 1 < s.length() && (isupper(s.at(i + 1)) ^ capsLock))
			{
				capsLock = !capsLock;
				combo++;
			}
			else if (i + 1 < s.length() && !(isupper(s.at(i + 1)) ^ capsLock))
			{
				combo++;
			}
			else if (i + 1 == s.length())
			{
				capsLock = !capsLock;
				combo++;
			}
		}
		combo++;
	}
	
	cout << combo << '\n';

	return 0;
}