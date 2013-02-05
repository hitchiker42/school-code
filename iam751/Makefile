CC= gcc -O3 -std=c99
CFLAGS= -Wall
all:vec_math hello
hello: hello.o factorial.o greeting.o
vec_math: vec_math.h vec_add.o scalar_product.o mat_vec_mult.o vec_add_test.o scalar_product_test.o mat_vec_mult_test.o vec_math.h
clean:
	rm -rf *.o
	rm hello
	rm vec_math