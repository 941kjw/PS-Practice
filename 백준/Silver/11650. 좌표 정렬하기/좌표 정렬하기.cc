#include <iostream>
#include <algorithm>

using namespace std;

typedef pair<int, int> Point2D;

ostream& operator<<(ostream& os, const Point2D& a)
{
	os << a.first << " " << a.second;
	return os;
}

int main()
{
	int casecount;
	int x;
	int y;
	cin >> casecount;

	Point2D* point;
	point = new Point2D[casecount];

	for (int i = 0; i < casecount; i++)
	{
		cin >> x;
		cin >> y;
		point[i] = { x,y };
	}
	sort(point, point + casecount, [](const Point2D& p1, const Point2D& p2) -> bool
		{
			if (p1.first < p2.first)
				return true;
			else if (p1.first == p2.first)
            {
                if (p1.second < p2.second)
                    return true;
                else
                    return false;
            }
            else
                return false;
                

		}
	);
	for (int i=0;i<casecount;i++)
	{
		cout << point[i] << '\n';
	}

	return 0;
}