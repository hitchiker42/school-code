#include <stdio.h>
#include "vec_math.h"
#include <stdlib.h>
#define N 3
#define M 3
int vec_mult_test(int argv,char **args){
  if (argv == 4){
    char * q;
    int n=strtol(args[2],&q,10);
    int m=strtol(args[3],&q,10);
    double **X=(double **)malloc(m * sizeof(double*));
    if (X==NULL){
      return 5;
    }
    for (int i=0;i<m;i++){
      X[i]=(double *)malloc(n * sizeof(double));
      if (X[i] == NULL){
        return 6;
      }
    }
    double *a=(double *)malloc(n * sizeof(double));
    double *b=(double *)malloc(n * sizeof(double));
    b=mat_vec_mult(X,a,b,n,m);
    for (int i=0;i<n;i++){
      printf("|%6.3f ",A[i][0]);
      for (int j=1;j<m-1;j++){
        printf(" %6.3f ",A[i][j]);
      }
      printf("%6.3f| ",A[i][m-1]);
      if (i==n/2){
        printf("* |%6.3f| = |%6.3f|\n",x[i],y[i]);
      } else {
        printf("  |%6.3f|   |%6.3f|\n",x[i],y[i]);
      }
    }
  } else {
    double B[M][N]={{1.,2.,3.},{4.,5.,6.},{7.,8.,9.}};
    double *A[M]={B[0],B[1],B[2]};
    double x[N]={3.,2.,1.,};
    double *y=(double*)malloc(N*sizeof(double));
    y=mat_vec_mult(A,x,y,N,M);
    for (int i=0;i<N;i++){
      printf("|%6.3f ",A[i][0]);
      for (int j=1;j<M-1;j++){
        printf(" %6.3f ",A[i][j]);
      }
      printf("%6.3f| ",A[i][M-1]);
      if (i==n/2){
        printf("* |%6.3f| = |%6.3f|\n",x[i],y[i]);
      } else {
        printf("  |%6.3f|   |%6.3f|\n",x[i],y[i]);
      }
    }
  }
  //might be a bit overkill there
  return 0;
}
