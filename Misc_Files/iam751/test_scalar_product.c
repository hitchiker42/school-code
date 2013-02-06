#include <stdio.h>
#include "vec_math.h"
int main(int argc, char **argv) {
  double x[N] = { 1., 2., 3. };
  double y[N] = { 2., 3., 4. };

  printf("scalar product is %g\n", scalar_product(x, y));

  return 0;
}
