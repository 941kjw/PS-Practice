#include <iostream>
#include <vector>
#include <unordered_map>
#include <string>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int N, M;
	cin >> N >> M;

	unordered_map<string, int> dogam;
	vector<string> dogam2 = vector<string>(N + 2);
	string name;
	int code;

	for (int i = 0; i < N; i++)
	{
		cin >> name;
		dogam[name] = i + 1;
		dogam2[i + 1] = name;
	}

	for (int i = 0; i < M; i++)
	{
		cin >> name;
		if (name[0] >= '0' && name[0] <= '9'){
			code = stoi(name);
			cout << dogam2[code] << '\n';
		}
		else {
			cout << dogam[name] << '\n';
		}
	}

	return 0;
}