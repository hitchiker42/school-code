#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "vec_math.h"
#define N 3
int add_test(int argv,char** args){
  if (argv == 3){
    char * q;
    int n=strtol(args[2],&q,10);
    double *a=(double*)malloc(n*sizeof(double));
    double *b=(double*)malloc(n*sizeof(double));
    double *c=(double*)malloc(n*sizeof(double));
    if (a == NULL || b == NULL){
      exit(5);
    } else {
      for (int i=0; i<n; i++){
        a[i]=(double)i;
        b[i]=(double)(n+i);
      }
    }
    c=vec_add(a,b,c,n);
    for (int i=0;i<n;i++){
      if(i==N/2){
        printf("|%6.3f| + |%6.3f| = |%6.3f|\n",a[i],b[i],c[i]);
      } else{
        printf("|%6.3f|   |%6.3f|   |%6.3f|\n",a[i],b[i],c[i]);
      }
    }
    return 0;
  }
  else{
    double x[N] = {1.,2.,3.};
    double y[N] = {2.,3.,4.};
    double *z=malloc(3*sizeof(double));
    z=vec_add(x,y,z,N);
    assert(z[0]==3 && z[1]==5 && z[2]==7);
    for (int i=0;i<N;i++){
      if(i==N/2){
        printf("|%6.3f| + |%6.3f| = |%6.3f|\n",x[i],y[i],z[i]);
      } else{
        printf("|%6.3f|   |%6.3f|   |%6.3f|\n",x[i],y[i],z[i]);
      }
    }
    return 0;
  }
}
