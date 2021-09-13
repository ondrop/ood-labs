#include <iostream>
#include "SimUDuck.cpp"

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
	PlayWithDuck(modelDuck);

	return 0;
}
