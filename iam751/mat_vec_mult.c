#include <math.h>
#include "vec_math.h"
double* mat_vec_mult(double **A,double *x,int n, int m){
  //y=Ax, given A&x solve for y
  double y[n]={0}//check syntax
  for (int i=0;i<n;i++){
    for (int j=0;j<m;j++){
      y[i]+=x[i]*A[j][i];
    }
  }
  return y;
}
