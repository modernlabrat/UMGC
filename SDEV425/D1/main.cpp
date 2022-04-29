#include <iostream>
using namespace std;

int main() {
  int terms;
  int first = 0, second = 1, next = 0;

  cout << "Terms: ";
  cin >> terms;

  for (int i = 1; i <= terms; ++i) {
    if (i == 1) {
      cout << first << " ";
      continue;
    }

    if (i == 2) {
      cout << second << " ";
      continue;
    }

    next = first + second;
    first = second;
    second = next;

    cout << next << " ";
  }

  return 0;
}