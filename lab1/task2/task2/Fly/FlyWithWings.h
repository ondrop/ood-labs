#pragma once
#include <iostream>
#include <string>
#include "IFlyBehavior.h"

using namespace std;

const string FLYING_WITH_WINGS = "I'm flying with wings!!";

class FlyWithWings: public IFlyBehavior
{
public:
	void Fly() override
	{
		m_countFly++;
		cout << FLYING_WITH_WINGS << endl;
		cout << DEPARTURE << m_countFly << endl;
	}
};
