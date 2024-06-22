#include<stdio.h>
int arr[13]={0,1,2,4},N,i;
int main(){
	for(i=4;i<=12;i++)arr[i]=arr[i-3]+arr[i-2]+arr[i-1];
	for(scanf("%d",&N);scanf("%d",&i)&&N--;)printf("%d\n",arr[i]);
   return 0;
}