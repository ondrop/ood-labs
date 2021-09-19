#pragma once
#include <iostream>
#include <string>
#include "IDanceBehavior.h"

using namespace std;

const string DANCING_WALTZ = "I'm dancing waltz!!!";

class WaltzBehavior : public IDanceBehavior
{
public:
	void Dance() override
	{
		cout << DANCING_WALTZ << endl;
	}
};
