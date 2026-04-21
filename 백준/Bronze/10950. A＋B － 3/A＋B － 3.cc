#include <iostream>

using namespace std;

int main()
{
    int total = 0;
    int A;
    int B;
        cin >> total;
    for (int i = 0; i < total; i++) {
        cin >> A;
        cin >> B;
        cout << A + B << endl;
    }
    return 0;
}