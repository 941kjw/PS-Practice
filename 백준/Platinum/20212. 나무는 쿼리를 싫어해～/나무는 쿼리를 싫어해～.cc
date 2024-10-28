#include <bits/stdc++.h>

using namespace std;

typedef long long ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef tuple<int, int, int> tiii;

const ll INF = 1e18;

struct node {
	ll sum = 0, lazy = 0;
	node* lp = NULL, * rp = NULL;
};

struct query {
	int i, j, k, n;
};

vector<query> Q1, Q2;

auto cmp1 = [](query q1, query q2) {
	if (q1.k != q2.k)
		return q1.k < q2.k;
	return q1.n < q2.n;
	};
auto cmp2 = [](pll p1, pll p2) {
	return p1.second < p2.second;
	};

static void dynamicAddition(node* ptr) {
	if (ptr->lp == NULL) {
		node* nlp = new node;
		ptr->lp = nlp;
	}
	if (ptr->rp == NULL) {
		node* nrp = new node;
		ptr->rp = nrp;
	}
}

void  setLazy(int s, int e, node* ptr) {
	ll val = ptr->lazy;
	ptr->lazy = 0;
	ptr->sum += (e - s + 1) * val;

	if (s != e) {
		dynamicAddition(ptr);
		ptr->lp->lazy += val;
		ptr->rp->lazy += val;
	}
}

void update(int s, int e, int l, int r, node* ptr, ll k) {
	if (ptr->lazy != 0)
		setLazy(s, e, ptr);
	if (e<l || s> r)
		return;
	if (l <= s && e <= r) {
		ptr->sum += (e - s + 1) * k;
		if (s != e) {
			dynamicAddition(ptr);
			ptr->lp->lazy += k;
			ptr->rp->lazy += k;
		}
		return;
	}
	
	dynamicAddition(ptr);
	update(s, (s + e) / 2, l, r, ptr->lp, k);

	update((s + e) / 2 + 1,e, l, r, ptr->rp, k);
	ptr->sum = ptr->lp->sum + ptr->rp->sum;
}

ll getSum(int s, int e, int l, int r, node* ptr) {
	if (ptr == NULL)
		return 0;
	if (ptr->lazy != 0)
		setLazy(s, e, ptr);

	if (e<l || s> r)
		return 0;
	if (l <= s && e <= r)
		return ptr->sum;
	return getSum(s, (s + e) / 2, l, r, ptr->lp) + getSum((s + e) / 2 + 1, e, l, r, ptr->rp);
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0), cout.tie(0);

	int N;
	cin >> N;
	for (int n = 1; n <= N; n++) {
		int q, i, j, k;
		cin >> q >> i >> j >> k;
		if (q == 1)
			Q1.push_back({ i,j,k,n });
		else
			Q2.push_back({ i,j,k,n });
	}

	sort(Q2.begin(), Q2.end(), cmp1);

	node* segTree = new node;

	int idx = 0, cnt = 1;
	vector<pll> ans;
	for (query q1 : Q1) {
		update(1, int(1e9), q1.i, q1.j, segTree, q1.k);
		while (idx < Q2.size() && Q2[idx].k == cnt){
			ans.push_back({ getSum(1,int(1e9),Q2[idx].i,Q2[idx].j,segTree),Q2[idx].n });
			idx++;
		}
		cnt++;
	}
	sort(ans.begin(), ans.end(), cmp2);
	for (auto &v : ans) {
		cout << v.first << '\n';
	}
}