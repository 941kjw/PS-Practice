#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool order(string a, string b)
{
	if (a.length() == b.length())
		return a < b;
	else
		return a.length() < b.length();
}

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int n;
	string s;
	cin >> n;

	vector<string> words = vector<string>(n);
	for(int i=0;i<n;i++)
	{
		cin >> s;
		words[i] = s;
	}

	sort(words.begin(), words.end(), order);

	cout << words[0] << '\n';
	for (int i = 1; i < n; i++)
	{
		if (words[i] == words[i - 1])
			continue;
		cout << words[i] << '\n';
	}
	return 0;
}