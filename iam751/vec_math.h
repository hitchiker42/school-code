#ifndef VEC_MATH_H
#define VEC_MATH_H
double scalar_product(double *x, double *y,int n);
double* vec_add(double *x,double *y,double *z,int n);
double* mat_vec_mult(double **A,double *x,double *y,int n, int m);
int add_test(int argv,char **args);
int scalar_test(int argv,char **args);
int vec_mult_test(int argv,char **args);
#endif
