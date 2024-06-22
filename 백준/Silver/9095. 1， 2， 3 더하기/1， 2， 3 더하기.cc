#include <cstdio>

int dp[11] = { 0,1,2,4 };

int main()
{
	int T,x;
	scanf("%d", &T);
	for (int i = 4; i < 11; i++) {
		dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
	}
	while(T--){
		scanf("%d", &x);
		printf("%d\n", dp[x]);
	}
	return 0;
}