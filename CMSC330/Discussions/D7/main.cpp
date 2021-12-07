#include<iostream>

using namespace std;

class Car {
  public:
    Car(string name)  { cout << "Car" << endl;   }
};
  
class Electric : virtual public Car {
  public:
    Electric(string model):Car(model)   { cout << "Electric" << endl;}
};
  
class Gas : virtual public Car {
  public:
    Gas(string model):Car(model) { cout << "Gas" << endl; }
};
  
class Hybrid : public Gas, public Electric  {
  public:
    Hybrid(string model):Gas(model), Electric(model)   { cout<<"Hybrid"<< endl; }
};
  
int main()  {
    Hybrid Hybrid("Sonata Hybrid");
}
