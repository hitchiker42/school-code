

#include "linear_algebra.h"

#include <assert.h>
#include <stdlib.h>

// ----------------------------------------------------------------------
// matrix_create
//
// initializes the matrix's length and sets all elements to zero

struct matrix *
matrix_create(int m, int n)
{
  struct matrix *M = calloc(1, sizeof(*M));
  //make sure that the allocation succeeded, do same this for other allocations
  assert(M!=NULL);
  // remember how many elements the matrix contains
  M->m = m;
  M->n = n;
  // allocate space for m x n elements and clear them.
  M->vals = calloc(m*n, sizeof(M->vals[0]));
  assert(M->vals!=NULL);
  return M;
}

// ----------------------------------------------------------------------
// matrix_destroy
//
// this function is called when we're done using a matrix

void
matrix_destroy(struct matrix *M)
{
  free(M->vals);
  // not strictly needed, but will make sure that we crash if we
  // access the matrix after we called matrix_destroy()
  M->vals = NULL;
  free(M);
}

// ----------------------------------------------------------------------
// matrix_is_equal
//
// checks whether two matrices are equal
// A, B: m x n matrices

bool
matrix_is_equal(struct matrix *A, struct matrix *B)
{
  // make sure the matrices have the same dimensions
  assert(A->m == B->m && A->n == B->n);

  // check whether any matrix elements are different
  for (int i = 0; i < A->m; i++) {
    for (int j = 0; j < A->n; j++) {
      if (MAT(A, i, j) != MAT(B, i, j))
    return false;
    }
  }
  return true;
}

// ----------------------------------------------------------------------
// matrix_vector_multiply
//
// performs the matrix vector multiplication y = A x
// y: result vector
// x: vector
// A: m x n matrix

void
matrix_vector_multiply(struct vector *y, struct matrix *A, const struct vector *x)
{
  assert(A->n==y->n);
  for (int i = 0; i < A->n; i++) {
    VEC(y, i) = 0.;
    for (int j = 0; j < A->m; j++) {
      VEC(y, i) += MAT(A, i, j) * VEC(x, j);
    }
  }
}

// ----------------------------------------------------------------------
// matrix_matrix_multiply
//
// performs the matrix-matrix multiplication C = A B
// C: result (m x n matrix)
// A: 1st input matrix (m x k matrix)
// B: 2nd input matrix (k x n matrix)

void
matrix_matrix_multiply(struct matrix *C, struct matrix *A, struct matrix *B)
{
  assert(C->m == A->m);
  assert(C->n == B->n);
  assert(A->n == B->m);

  for (int i = 0; i < C->m; i++) {
    for (int j = 0; j < C->n; j++) {
      MAT(C, i, j) = 0.;
      for (int k = 0; k < B->m; k++) {
        MAT(C, i, j) += MAT(A, i, k) * MAT(B, k, j);
      }
    }
  }
}
