#include "vec_math.h"
#include <math.h>
//add vectors x and y of size n and store the result in z
double* vec_add(double *x,double *y,int n){
  double z[n]={0};//check syntax
  for (int i=0;i<n;i++){
    z[i]=x[n]+y[n];
      }
  return z;
}
