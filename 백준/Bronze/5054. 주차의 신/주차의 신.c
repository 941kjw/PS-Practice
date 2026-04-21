#include <stdio.h>
int main()
{
	int t;
	scanf("%d", &t);
	for (int c = 0; c < t; c++)
	{
		int n, max = -1, min = 100, shop[20];
		scanf("%d", &n);
		for (int i = 0; i < n; ++i)
		{
			scanf("%d", &shop[i]);
			max = (shop[i] > max) ? shop[i] : max;
			min = (shop[i] < min) ? shop[i] : min;
		}
		printf("%d\n", (max - min) * 2);
	}
}