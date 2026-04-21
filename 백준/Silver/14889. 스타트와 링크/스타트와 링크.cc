#include <iostream>
#include <vector>

using namespace std;

bool chosen[20] = {0, };
int stats[20][20];
int playercount;
int result = 999999;

void dedenchi(int idx, int cnt)
{
	vector<int> start;
	vector<int> link;
	int start_stat_sum = 0;
	int link_stat_sum = 0;

	if (cnt == (playercount / 2))
	{
		for (int i = 0; i < playercount; i++)
		{
			if (chosen[i])
				start.push_back(i);
			else
				link.push_back(i);
		}
		for (int i = 0; i < (playercount / 2); i++)
		{
			for (int j = 0; j < (playercount / 2); j++)
			{
				start_stat_sum += stats[start[i]][start[j]];
				link_stat_sum += stats[link[i]][link[j]];
			}

		}
		if (abs(start_stat_sum - link_stat_sum) < result)
		{
			result = abs(start_stat_sum - link_stat_sum);
		}
		return;
	}

	for (int i = idx; i < playercount; i++)
	{
		if (chosen[i])
			continue;
		else
		{
			chosen[i] = true;
			dedenchi(i, cnt + 1);
			chosen[i] = false;
		}
	}
}

int main()
{
	cin >> playercount;
	for (int i = 0; i < playercount; i++)
	{
		for (int j = 0; j < playercount; j++)
		{
			cin >> stats[i][j];
		}
	}
	dedenchi(0, 0);
	cout << result<<endl;
}