#include <iostream>
#include <vector>
using namespace std;
int k, n;
vector<int> lans;

int divide_finder(long long left, long long right, long long maxlength)
{
	if (left > right)
		return maxlength;
	long long mid = (left + right) / 2;
	int sum = 0;
	for (int i = 0; i < k; i++)
		sum += lans[i] / mid;
	if (n <= sum)
		return divide_finder(mid + 1, right, maxlength < mid ? mid : maxlength);
	else
		return divide_finder(left, mid - 1, maxlength);	
}

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);
	
	cin >> k >> n;
	lans = vector<int>(k, 0);

	long long max = 0;
	for (int i = 0; i < k; i++)
	{
		cin >> lans[i];
		max = lans[i] > max ? lans[i] : max;
	}


	cout << divide_finder(1, max,0) << '\n';

	return 0;
}