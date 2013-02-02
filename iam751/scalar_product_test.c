#include <stdio.h>
#include "vec_math.h"
#define N 3
//#define N 3 //wrong syntax
int scalar_test(int argc, char **argv) {
  //int N=3;
  double x[N] = {1.,2.,3.};
  double y[N] = {4.,5.,6.};
  double z=scalar_product(x, y,N);
  int q;
  //printf("scalar product is %g\n", scalar_product(x, y,N));
  for (int i=0;i<N;i++){
    if(i==N/2){
      printf("|%6.3f| . |%6.3f| =  %6.3f \n",x[i],y[i],z);
    } else{
      printf("|%6.3f|   |%6.3f|    %6n   \n",x[i],y[i],&q);
    }
  }
  return 0;
}
