CC= gcc -O3
CFLAGS= -Wall
all:vec_math test #hello
#hello: hello.o factorial.o greeting.o
test:vec_math_test
vec_math: vec_add.o scalar_product.o mat_vec_mult.o
vec_math_test: vec_add_test.o scalar_product_test.o mat_vec_mult_test.o
clean:
	rm -rf *.o
