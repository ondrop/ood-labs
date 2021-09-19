#pragma once
#include <iostream>
#include <string>
#include "IDanceBehavior.h"

using namespace std;

const string DANCING_MINUET = "I'm dancing minuet!!!";

class MinuetBehavior : public IDanceBehavior
{
public:
	void Dance() override
	{
		cout << DANCING_MINUET << endl;
	}
};
