#include <iostream>
using namespace std;
int main()
{
	int x, y, w, h, i, min;
	int arr[4];

	cin >> x >> y >> w >> h;
	arr[0] = x;
	arr[1] = w - x;
	arr[2] = y;
	arr[3] = h - y;
	for (i = 0; i < 3; i++)
	{
		if (arr[i] <= arr[i + 1])
		{
			min = arr[i];
			arr[i + 1] = min;
		}
		else
		{
			min = arr[i + 1];
		}
	}
	cout << min << endl;
	return 0;
}