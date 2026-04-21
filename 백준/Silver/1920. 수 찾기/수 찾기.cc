#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> numbers;

int binarySearch(int left, int right, int target)
{
	if (left > right)
		return 0;
	else
	{
		int mid = (left + right) / 2;
		if (numbers[mid] == target)
			return 1;
		else if (numbers[mid] > target)
			return binarySearch(left, mid - 1, target);
		else
			return binarySearch(mid + 1, right, target);


	}
}

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int numc;
	cin >> numc;
	for (int i = 0; i < numc; i++)
	{
		int num;
		cin >> num;
		numbers.push_back(num);
	}
	sort(numbers.begin(), numbers.end());
	int fnumc;
	cin >> fnumc;
	for (int i = 0; i < fnumc; i++)
	{
		int num;
		cin >> num;
		cout << binarySearch(0, numc - 1, num) << '\n';
	}
	return 0;
}