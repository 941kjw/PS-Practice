#include <iostream>
#include <string>
using namespace std;

int main()
{
    string horsefirst;
    string horseobjective;
    int _casenum = 0;
    int _result = 0;
    int diff = 0;
    int _horsecount = 0;
    int current = 0;
    int b2w;
    int w2b;

    cin >> _casenum;

    for (int i = 0; i < _casenum; i++)
    {
        b2w = 0;
        w2b = 0;
        cin >> _horsecount;
        cin >> horsefirst;
        cin >> horseobjective;

        for (current = 0; current < _horsecount; current++)
        {   
            if (horsefirst.at(current) != horseobjective.at(current))
            {
                if (horsefirst.at(current) == 'B')
                    b2w++;
                else
                    w2b++;
            }
        }
       _result = (b2w > w2b) ? b2w : w2b;
        cout << _result << endl;
    }

    return 0;
}