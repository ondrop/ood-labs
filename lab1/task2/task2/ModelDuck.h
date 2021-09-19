#pragma once
#include "Duck.h"
#include "./Fly/FlyNoWay.h"
#include "./Quack/QuackBehavior.h"
#include "./Dance/NoDanceBehavior.h"

const string MODEL_DUCK = "I'm model duck";

class ModelDuck : public Duck
{
public:
	ModelDuck()
		: Duck(make_unique<FlyNoWay>(), make_unique<QuackBehavior>(), make_unique<NoDanceBehavior>())
	{
	}
	void Display() const override
	{
		cout << MODEL_DUCK << endl;
	}
};
