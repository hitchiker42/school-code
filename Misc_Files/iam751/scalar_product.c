#include <stdio.h>
#include "vec_math.h"
// ----------------------------------------------------------------------
// scalar_product
//
// calculates the scalar product of the two vectors
// x: first vector
// y: second vector

double scalar_product(double *x, double *y) {
  double sum = 0.f;
  for (int i = 0; i < N; i++) {
    sum += x[i] * y[i];
  }
  return sum;
}


