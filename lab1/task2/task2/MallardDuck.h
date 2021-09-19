#pragma once
#include "Duck.h"
#include "./Fly/FlyWithWings.h"
#include "./Quack/QuackBehavior.h"
#include "./Dance/WaltzBehavior.h"

const string MALLARD_DUCK = "I'm mallard duck";

class MallardDuck : public Duck
{
public:
	MallardDuck()
		: Duck(make_unique<FlyWithWings>(), make_unique<QuackBehavior>(), make_unique<WaltzBehavior>())
	{
	}

	void Display() const override
	{
		cout << MALLARD_DUCK << endl;
	}
};
