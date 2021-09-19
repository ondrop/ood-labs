#pragma once
#include "Duck.h"
#include "./Fly/FlyWithWings.h"
#include "./Quack/QuackBehavior.h"
#include "./Dance/MinuetBehavior.h"

const string REDHEAD_DUCK = "I'm redhead duck";

class RedheadDuck : public Duck
{
public:
	RedheadDuck() : Duck(make_unique<FlyWithWings>(), make_unique<QuackBehavior>(), make_unique<MinuetBehavior>())
	{
	}

	void Display() const override
	{
		cout << REDHEAD_DUCK << endl;
	}
};
