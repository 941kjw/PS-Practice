#include <iostream>
#include <map>
#include<algorithm>

using namespace std;


int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	cout.tie(NULL);

	int titlenum;
	int charnum;
	cin >> titlenum >> charnum;
	map<int, string> title_map;
	for (int i = 0; i < titlenum; i++)
	{
		int power;
		string title;
		cin >> title >> power;
		title_map.insert(make_pair(power, title));
	}
	map<int, string>::iterator iter;

	for (int i = 0; i < charnum; i++)
	{
		int power;
		cin >> power;
		iter = title_map.lower_bound(power);
		cout << iter->second << '\n';
	}

}