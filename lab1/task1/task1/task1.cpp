#include "ModelDuck.h"
#include "MallardDuck.h"
#include "./Fly/FlyWithWings.h"
#include "./Dance/MinuetBehavior.h"
#include "./Quack/MuteQuackBehavior.h"

using namespace std;

void DrawDuck(Duck const& duck)
{
	duck.Display();
}

void PlayWithDuck(Duck& duck)
{
	DrawDuck(duck);
	duck.Quack();
	duck.Fly();
	duck.Dance();
	cout << endl;
}

int main()
{
	ModelDuck modelDuck;
	PlayWithDuck(modelDuck);

	modelDuck.SetFlyBehavior(make_unique<FlyWithWings>());
	modelDuck.SetDanceBehavior(make_unique<MinuetBehavior>());
	modelDuck.SetQuackBehavior(make_unique<MuteQuackBehavior>());
	PlayWithDuck(modelDuck);

	MallardDuck mallardDuck;
	PlayWithDuck(mallardDuck);

	mallardDuck.SetFlyBehavior(make_unique<FlyNoWay>());
	mallardDuck.SetDanceBehavior(make_unique<NoDanceBehavior>());
	mallardDuck.SetQuackBehavior(make_unique<QuackBehavior>());
	PlayWithDuck(mallardDuck);

	mallardDuck.SetFlyBehavior(make_unique<FlyWithWings>());
	mallardDuck.SetDanceBehavior(make_unique<MinuetBehavior>());
	mallardDuck.SetQuackBehavior(make_unique<MuteQuackBehavior>());
	PlayWithDuck(mallardDuck);

	return 0;
}
