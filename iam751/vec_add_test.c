#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "vec_math.h"
int add_test(int N){
    vector a=(malloc(N*sizeof(double)),N);
    double *b=malloc(N*sizeof(double));
    double *c=malloc(N*sizeof(double));
    malloc_test(a.vals==NULL || b.vals==NULL || c.vals==NULL);
    } else {
      for (int i=0; i<n; i++){
        a[i]=(double)i;
        b[i]=(double)(n+i);
      }
    }
    c=vec_add(a,b,c,n);
    for (int i=0;i<n;i++){
      if(i==n/2){
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
