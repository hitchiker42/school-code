#include <stdio.h>
#include "vec_math.h"
int main(int argv,char** args){
  int N=3;
  if (argv == 4){
    //test and set to args
  } else{
    double x[N] = {1.,2.,3.};
    double y[N] = {2.,3.,4.};
  }
  double z[N]=vec_add(x,y,N);
  printf();//print z
  return 0;
}
