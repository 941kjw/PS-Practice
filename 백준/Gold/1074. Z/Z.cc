#include <iostream>

using namespace std;

int N, r, c, ans;

void find(int y, int x, int length)
{
	if (y == r && x == c)
	{
		cout << ans;
		return;
	}

	if (r < y + length && r >= y && c < x + length && c >= x)
	{
		find(y, x, length / 2);
		find(y, x + length / 2, length / 2);
		find(y + length / 2, x, length / 2);
		find(y + length / 2, x + length / 2, length / 2);
	}
	else
	{
		ans += length * length;
	}
}

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);
	
	cin >> N >> r >> c;

	find(0, 0, (1 << N));

	return 0;
}