#include "vec_math.h"
#include <math.h>
#include <stdio.h>
//add vectors x and y of size n and store the result in z
vector vec_add(vector x,vector y){
  if(x.len == y.len){
    double* sum=malloc(x.len*sizeof(double));
    malloc_test(sum==NULL);
    vector z={sum,x.len};
    for (int i=0;i<x.len;i++){
      z.vals[i]=0;
      z.vals[i]=x.vals[i]+y.vals[i];
    }
    return z;
  }
  else{
    printf("Error: vectors must be same size");
    exit(-1);
  }
}
