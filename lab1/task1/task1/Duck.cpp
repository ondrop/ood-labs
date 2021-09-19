#include "Duck.h"

const string SWIMMING = "I'm swimming";

Duck::Duck(unique_ptr<IFlyBehavior>&& flyBehavior,
	unique_ptr<IQuackBehavior>&& quackBehavior,
	unique_ptr<IDanceBehavior>&& danceBehavior
)
{
	SetFlyBehavior(move(flyBehavior));
	SetQuackBehavior(move(quackBehavior));
	SetDanceBehavior(move(danceBehavior));
}

void Duck::Quack() const
{
	m_quackBehavior->Quack();
}

void Duck::Swim()
{
	cout << SWIMMING << endl;
}

void Duck::Fly()
{
	m_flyBehavior->Fly();
}

void Duck::Dance()
{
	m_danceBehavior->Dance();
}

void Duck::SetFlyBehavior(unique_ptr<IFlyBehavior>&& flyBehavior)
{
	assert(flyBehavior);
	m_flyBehavior = move(flyBehavior);
}

void Duck::SetQuackBehavior(unique_ptr<IQuackBehavior>&& quackBehavior)
{
	assert(quackBehavior);
	m_quackBehavior = move(quackBehavior);
}

void Duck::SetDanceBehavior(unique_ptr<IDanceBehavior>&& danceBehavior)
{
	assert(danceBehavior);
	m_danceBehavior = move(danceBehavior);
}
