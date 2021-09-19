#pragma once
#include <string>

using namespace std;

const string DEPARTURE = "Departure: ";

class IFlyBehavior
{
public:
	virtual ~IFlyBehavior() {};
	virtual void Fly() = 0;

protected:
	int m_countFly = 0;
};
