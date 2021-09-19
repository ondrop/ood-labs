#pragma once
#include <iostream>
#include <string>
#include "IQuackBehavior.h"

using namespace std;

const string QUACK = "Quack Quack!!!";

class QuackBehavior : public IQuackBehavior
{
public:
	void Quack() override
	{
		cout << QUACK << endl;
	}
};