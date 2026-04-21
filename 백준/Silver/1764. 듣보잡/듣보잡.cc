#include <iostream>
#include <unordered_map>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int N, M;
	unordered_map<string, bool> unknown;

	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		string name;
		cin >> name;
		unknown.insert({ name,false });
	}
	vector<string> result;
	for (int i = 0; i < M; i++)
	{
		string name;
		cin >> name;
		if (unknown.find(name) != unknown.end())
		{
			result.push_back(name);
		}
	}

	sort(result.begin(), result.end());

	cout << result.size() << '\n';
	for (string i : result)
	{
		cout << i << '\n';
	}

	return 0;
}