#pragma once
#include <iostream>
#include <string>
#include "IQuackBehavior.h"

using namespace std;

const string SQUEEK = "Squeek!!!";

class SqueakBehavior : public IQuackBehavior
{
public:
	void Quack() override
	{
		cout << SQUEEK << endl;
	}
};
