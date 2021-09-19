#pragma once
#include "Duck.h"
#include "./Fly/FlyNoWay.h"
#include "./Quack/MuteQuackBehavior.h"
#include "./Dance/NoDanceBehavior.h"

const string DECOY_DUCK = "I'm decoy duck";

class DecoyDuck : public Duck
{
public:
	DecoyDuck()
		: Duck(make_unique<FlyNoWay>(), make_unique<MuteQuackBehavior>(), make_unique<NoDanceBehavior>())
	{
	}
	void Display() const override
	{
		cout << DECOY_DUCK << endl;
	}
};
