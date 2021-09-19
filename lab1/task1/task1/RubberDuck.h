#pragma once
#include "Duck.h"
#include "./Fly/FlyNoWay.h"
#include "./Quack/SqueakBehavior.h"
#include "./Dance/NoDanceBehavior.h"

const string RUBBER_DUCK = "I'm rubber duck";

class RubberDuck : public Duck
{
public:
	RubberDuck()
		: Duck(make_unique<FlyNoWay>(), make_unique<SqueakBehavior>(), make_unique<NoDanceBehavior>())
	{
	}

	void Display() const override
	{
		cout << RUBBER_DUCK << endl;
	}
};
