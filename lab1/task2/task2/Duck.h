#pragma once
#include <cassert>
#include <iostream>
#include <memory>
#include <string>
#include "./Fly/IFlyBehavior.h";
#include "./Quack/IQuackBehavior.h";
#include "./Dance/IDanceBehavior.h";

using namespace std;

class Duck
{
public:
	Duck(unique_ptr<IFlyBehavior>&& flyBehavior,
		unique_ptr<IQuackBehavior>&& quackBehavior,
		unique_ptr<IDanceBehavior>&& danceBehavior
	);

	void Quack() const;
	void Swim();
	void Fly();
	void Dance();
	void SetFlyBehavior(unique_ptr<IFlyBehavior>&& flyBehavior);
	void SetQuackBehavior(unique_ptr<IQuackBehavior>&& quackBehavior);
	void SetDanceBehavior(unique_ptr<IDanceBehavior>&& danceBehavior);

	virtual void Display() const = 0;
	virtual ~Duck() = default;

private:
	unique_ptr<IFlyBehavior> m_flyBehavior;
	unique_ptr<IQuackBehavior> m_quackBehavior;
	unique_ptr<IDanceBehavior> m_danceBehavior;
};

