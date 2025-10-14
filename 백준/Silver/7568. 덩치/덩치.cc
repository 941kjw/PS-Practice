#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int ranklarge(vector<vector<int>>& a, int atwhere, int total)
{
	int larger = 1;
	for (int i = 0; i < total; i++)
	{
		if (i == atwhere) continue;
		if ((a[i][0] > a[atwhere][0]) && (a[i][1] > a[atwhere][1]))
			larger += 1;
	}
	return larger;
}

int main()
{
	int peoplecount;

	cin >> peoplecount;
	vector<vector<int>> arr(peoplecount, vector<int>(2, 0));

	for (int i = 0; i < peoplecount; i++) {
		cin >> arr[i][0];
		cin >> arr[i][1];
	}
	for (int i = 0; i < peoplecount; i++) {
		cout << ranklarge(arr, i, peoplecount) << ' ';
	}


	return 0;
}
