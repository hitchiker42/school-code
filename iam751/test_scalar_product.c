#include <stdio.h>
#include "vec_math.h"
//#define N 3 //wrong syntax
int main(int argc, char **argv) {
  int N=3;
  double x[N] = {1.,2.,3.};
  double y[N] = {2.,3.,4.};

  printf("scalar product is %g\n", scalar_product(x, y));

  return 0;
}
