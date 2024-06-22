#include <bits/stdc++.h>

using namespace std;

int N;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int oldFruit = 0, newFruit = 0, left = 0, right = 0, answer = 0, prev = 0;

	cin >> N;

	for (int i = 0; i < N; i++) {
		int x;
		cin >> x;
		if (oldFruit != x && newFruit != x) {// 전혀 새로운 과일
			answer = max(answer, i - left); // 현재 구간 갯수 저장
			oldFruit = newFruit;
			newFruit = x; // 과일 종류 변경
			prev = x;
			left = right;
			right = i; // 구간 재지정
		}
		else if (prev != x) { //이전에 나온 과일이긴 한데 직전은 아님 ex) 1212 ...<< 1
			prev = x;
			right = i;
			swap(oldFruit, newFruit); // 만약 전혀 새로운 과일이 다음에 나온다면 방금 받은 녀석을 포함하겠음.
		}
	}
	answer = max(answer, N - left); // 마지막 결과 저장
	cout<<answer;
	return 0;
}