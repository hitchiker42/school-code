
ACLOCAL_AMFLAGS = -I m4
AM_FFLAGS=-std=f95
bin_PROGRAMS = \
	test_vector_dot \
	test_vector_add \
	test_matrix_vector_multiply \
	test_matrix_matrix_multiply \
	test_data_layout \
	test_data_layout_fortran

test_data_layout_SOURCES = test_data_layout.c print_matrix_fortran.F
test_data_layout_fortran_SOURCES = test_data_layout_fortran.F print_matrix_fortran.F

lib_LIBRARIES = liblinalg.a
liblinalg_a_SOURCES = \
	linear_algebra.h \
	vector.c \
	vector_add.c \
	vector_dot.c \
	matrix.c

LDADD = liblinalg.a

TESTS = \
	test_vector_dot \
	test_vector_add \
	test_matrix_vector_multiply \
	test_matrix_matrix_multiply
