#include <math.h>
#include <stdio.h>
#include "vec_math.h"
double* mat_vec_mult(double **A,double *x,double *y,int m, int n){
  //y=Ax, given A&x solve for y
  //double y[n];
  double temp;
  for (int i=0;i<m;i++){
    //printf("%d\n",i);
    y[i]=0;
    temp=0;
    for (int j=0;j<n;j++){
      //printf("\t%d\n",j);
      temp+=x[j]*A[i][j];
    }
    y[i]=temp;
    //printf("%f\n",y[i]);
  }
  return y;
}
