
#include "config.h"
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

#define N 3
#define M 4

#define print_matrix_F77 F77_FUNC_(print_matrix, PRINT_MATRIX)

void print_matrix_F77(float *);

void
print_matrix(float A[N][M])
{
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      printf(" %02g", A[i][j]);
    }
    printf("\n");
  }
}

void
print_matrix_linear(float *A, int n)
{
  for (int i = 0; i < n; i++) {
    printf(" %02g", A[i]);
  }
  printf("\n");
}

void
print_matrix_double_pointer(float **A)
{
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      printf(" %02g", A[i][j]);
    }
    printf("\n");
  }
}

int
main(int argc, char **argv)
{
  float A[N][M];
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      A[i][j] = i * 10 + j;
    }
  }

  printf("print_matrix:\n");
  print_matrix(A);
  printf("\n");

  printf("print_matrix_linear:\n");
  print_matrix_linear((float *) A, N*M);
  printf("\n");
  float** x=malloc(N*sizeof(float*));
  if(x==NULL){
    fprintf(stderr,"Memory Allocation Errors");
    raise(SIGSEGV);
  }
  for(int i=0;i<M;i++){
    x[i]=&A[i][0];
  }
  printf("print_matrix_double_pointer:\n");
  print_matrix_double_pointer(x);

  printf("print_matrix (fortran):\n");
  print_matrix_F77((float *) A);
  printf("\n");
  free(x);

  printf("why do we keep getting errors?\n");
  return 0;
}
