
#include "linear_algebra.h"

#include <stdio.h>
#include <assert.h>

// ----------------------------------------------------------------------
// setup_test_vectors

static void
setup_test_vectors(struct vector *x, struct vector *y_ref)
{
  for (int i = 0; i < x->n; i++) {
    VEC(x, i) = i;
  }
  int len = y_ref->n;
  if (x->n < len) {
    len = x->n;
  }
  for (int i = 0; i < len; i++) {
    VEC(y_ref, i) = i * i;
  }
}

// ----------------------------------------------------------------------
// setup_test_matrix

static void
setup_test_matrix(struct matrix *A)
{
  // the test matrix is diagonal, which isn't really good,
  // a more general case would be better.
  for (int i = 0; i < A->m; i++) {
    for (int j = 0; j < A->n; j++) {
      if (i == j) {
        MAT(A, i, j) = i;
      } else {
        MAT(A, i, j) = 0;
      }
    }
  }
}

// ----------------------------------------------------------------------
// main
//
// test the mat_vec_mul() function

int
main(int argc, char **argv)
{
  const int m = 10000, n = 10000;

  double tbeg, tend;

  tbeg = WTime();

  struct vector *x = vector_create(n);
  struct vector *y = vector_create(m);
  struct vector *y_ref = vector_create(m);
  struct matrix *A = matrix_create(m, n);

  // setup values in our test vector and in our reference result
  setup_test_vectors(x, y_ref);

  // build a test matrix
  setup_test_matrix(A);

  tend = WTime();
  printf("setup took %g sec\n", tend - tbeg);

  // calculate y = Ax
  tbeg = WTime();
  matrix_vector_multiply(y, A, x);
  tend = WTime();
  printf("matrix_vector_multiply() took %g sec\n", tend - tbeg);

  // the resulting vector for this test should equal our reference result
  tbeg = WTime();
  assert(vector_is_equal(y, y_ref));
  tend = WTime();
  printf("checking result took %g sec\n", tend - tbeg);

  // clean up
  vector_destroy(x);
  vector_destroy(y);
  vector_destroy(y_ref);
  matrix_destroy(A);

  return 0;
}
