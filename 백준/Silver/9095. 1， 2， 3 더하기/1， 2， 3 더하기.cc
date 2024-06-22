#include <stdio.h>
int dp[11] = { 0,1,2,4 },T,x;
int main(){
	for (int i = 4; i <= 10; i++)dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
	for(scanf("%d", &T); scanf("%d", &x) && T--;) printf("%d\n", dp[x]);
	return 0;
}