#include <iostream>
using namespace std;
int main()
{
	double q, w, e, r, u, sum, A, B, C, D, E, t;
	cout << fixed;
	cout.precision(2);
	e = 1;
	w = 0;
	r = 0;
	u = 0;
	q = 0;
	A = 350.34;
	B = 230.90;
	C = 190.55;
	D = 125.30;
	E = 180.90;
	sum = 0;
	cin >> q;
	while (w < q)
	{
		while (u < 5)
		{
			cin >> r;
			if (e == 1)
			{
				sum = sum + r*A;
				e++;
			}
			else if (e == 2)
			{
				sum = sum + r*B;
				e++;
			}
			else if (e == 3)
			{
				sum = sum + r*C;
				e++;
			}
			else if (e == 4)
			{
				sum = sum + r*D;
				e++;
			}
			else if (e == 5)
			{
				sum = sum + r*E;
				e++;
			}
			u++;

		}
	cout << '$' << sum << endl;
	e = 1;
	u = 0;
	sum = 0;
	r = 0;
	w++;
	}

}