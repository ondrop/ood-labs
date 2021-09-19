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
	modelDuck.SetQuackBehavior(make_unique<MuteQuackBehavior>());
	PlayWithDuck(modelDuck);

	modelDuck.SetFlyBehavior(make_unique<FlyWithWings>());
	PlayWithDuck(modelDuck);
	PlayWithDuck(modelDuck);
	PlayWithDuck(modelDuck);
	PlayWithDuck(modelDuck);

	modelDuck.SetFlyBehavior(make_unique<FlyNoWay>());
	PlayWithDuck(modelDuck);

	modelDuck.SetFlyBehavior(make_unique<FlyWithWings>());
	PlayWithDuck(modelDuck);

	return 0;
}
