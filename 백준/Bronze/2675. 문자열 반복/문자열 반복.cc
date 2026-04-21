#include <iostream>
#include <cstring>

using namespace std;

int main()
{
    int T;
    int R;
    char S[20];

    cin >> T;

    for (int tst = 0; tst < T; tst++) {
        cin >> R;
        cin >> S;
        for (int i = 0; i < strlen(S); i++)
        {
            for (int rep = 0; rep < R; rep++)
            {
                cout << S[i];
            }
        }
        cout << endl;
    }

    return 0;
}