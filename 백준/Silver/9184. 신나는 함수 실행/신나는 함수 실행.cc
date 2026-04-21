#include <iostream>
#define MAX 21
using namespace std;

int a, b, c;
int warr[MAX][MAX][MAX];

int w(int a, int b, int c)
{
	if (a <= 0 || b <= 0 || c <= 0)
	{
		return 1;
	}
	if (a >= MAX || b >= MAX || c >= MAX)
	{
		return w(20, 20, 20);
	}
	int &result = warr[a][b][c];
	if (result != 0)
		return result;
	if (a < b && b < c)
	{
		result =  w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
	}
	else
		result =  w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
	return result;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	
	while (1)
	{
		cin >> a >> b >> c;
		if (a == -1 && b == -1 && c == -1)
			break;
		cout << "w(" << a << ", " << b << ", " << c << ") = " << w(a, b, c) << '\n';
	}

}