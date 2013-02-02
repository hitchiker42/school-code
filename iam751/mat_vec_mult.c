#include <math.h>
#include "vec_math.h"
double* mat_vec_mult(double **A,double *x,double *y,int n, int m){
  //y=Ax, given A&x solve for y
  //double y[n];
  for (int i=0;i<n;i++){
    y[i]=0;
    for (int j=0;j<m;j++){
      y[i]+=x[i]*A[j][i];
    }
  }
  return y;
}
