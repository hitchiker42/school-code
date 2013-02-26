
// include guards: an idiom which ensures that it's okay to include
// the header more than once -- the 2nd time around, LINEAR_ALGEBRA_H will
// be defined already and so nothing will be included anymore.

#ifndef LINEAR_ALGEBRA_H
#define LINEAR_ALGEBRA_H

#include <stdbool.h>
#include <stdlib.h>
#include <assert.h>

#define BOUNDSCHECK

// ----------------------------------------------------------------------
// struct vector

struct vector {
  double *vals;
  int n;
};

#ifdef BOUNDSCHECK
#define VEC(v, i) (*({				\
	assert((i) >= 0 && (i) < (v)->n);	\
	&((v)->vals[(i)]);			\
      })) 
#else
#define VEC(v, i) ((v)->vals[i])
#endif

struct vector *vector_create(int n);
void vector_destroy(struct vector *v);
bool vector_is_equal(struct vector *v1, struct vector *v2);
void vector_add(struct vector *x, struct vector *y, struct vector *z);
double vector_dot(struct vector *x, struct vector *y);

// ----------------------------------------------------------------------
// struct matrix

struct matrix {
  double *vals;
  int m, n;
};

#ifdef BOUNDSCHECK
#define MAT(M, i, j) (*({						\
	assert((i) >= 0 && (i) < (M)->m);				\
	assert((j) >= 0 && (j) < (M)->n);				\
	&((M)->vals[(j) * (M)->m + (i)]);				\
      })) 
#else
#define MAT(M, i, j) ((M)->vals[(j) * (M)->m + (i)])
#endif

struct matrix *matrix_create(int m, int n);
void matrix_destroy(struct matrix *M);
void matrix_vector_multiply(struct vector *y, struct matrix *M, const struct vector *x);
void matrix_matrix_multiply(struct matrix *C, struct matrix *A, struct matrix *B);
bool matrix_is_equal(struct matrix *A, struct matrix *B);

// ----------------------------------------------------------------------
// other useful stuff

#include <sys/time.h>

static inline double
WTime(void)
{
  struct timeval tv;
  gettimeofday(&tv, NULL);
  return tv.tv_sec + tv.tv_usec / 1e6;
}

// ----------------------------------------------------------------------

#include <stdio.h>

#define HERE fprintf(stderr, "HERE at %s:%d (%s)\n", __FILE__, __LINE__, __FUNCTION__) 

#endif
