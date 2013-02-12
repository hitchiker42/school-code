#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <getopt.h>
#include "vec_math.h"
#define N 3
#define M 4
//Write argparser here
int main(int argv,char** args){
  //Argparse stuff
  int c;
  int result;
  //  file output:
  opterr = 0;
  while ((c = getopt (argv, args, "asmh/*f:*/")) != -1){
    switch(c) {
      //case 'f':
      //umm..remember how to do output
    case 'a':
      result=add_test(argv,args);
      return result;
      break;//totally unnecessary but eh
    case 's':
      result=scalar_test(argv,args);
      return result;
      break;
    case 'm':
      result=vec_mult_test(argv,args);
      return result;
      break;
    case 'h':
      printf("usage:\-f file,write output to given file\n-a,vector additon\n-s,dot product\n-m,matrix multiplication\n-h,print this help and exit\n");
      return 0;
    case '?':
      printf ("Unknown option `-%c'\n", optopt);
      return 2;
      break;
    default:
      return 3;
    }
  }
  printf ("usage:\n-a,vector additon\n-s,dot product\n-m,matrix multiplication\n-h,print this help and exit\n");
  printf ("call each option with no further parameters to run the tests");
  return 99;
}
