#include<stdio.h>
int main()
{
	int i, j, x, y, n, m, k, sum=0,t,x2,y2;
	int num[300][300] = { {0},{0} };
	scanf("%d%d", &x, &y);
	for (i = 0; i < x; i++)
	{
		for (j = 0; j < y; j++)
		{
			scanf("%d", &num[i][j]);
		}
	}
	scanf("%d", &k);
	for (t = 0; t < k; t++)
	{
		scanf("%d%d%d%d", &x2, &y2, &n, &m);
		for (i = x2 - 1; i < n; i++)
		{
			for (j = y2 - 1; j < m; j++)
			{
				sum += num[i][j];
			}
		}
		printf("%d\n", sum);
		sum = 0;
	}
}