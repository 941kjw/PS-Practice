#include <iostream>
#define MAX 9
using namespace std;

int n, m;
int arr[MAX] = { 0, };
bool visited[MAX] = { 0, };

void dfs(int count)
{
	if (count == m) // 출력예정칸이 다 차면? 출력.
	{
		for (int i = 0; i < m; i++)
		{
			cout << arr[i] << ' ';
		}
		cout << '\n';
		return;
	}
	for (int i = 1; i <= n; i++) // n까지만, 무조건 첫번째 숫자보다 커야함.
	{

			visited[i] = true; //visited에 넣고
			arr[count] = i; //출력 예정값에 넣기
			dfs(count + 1); // 카운트 증가
			visited[i] = false; // dfs호출후 돌아오면서 false로 바꾸기, visited는 전역변수이기 때문에 돌아올때 영향을 줌.(순서 바꾸기)
	}
}

int main()
{
	cin >> n;
	cin >> m;
	dfs(0); 
}
