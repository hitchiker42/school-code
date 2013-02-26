
#define N 3
#define M 4

c     ----------------------------------------------------------------------
      program main
c     ----------------------------------------------------------------------
      implicit none

      real A(N, M)
      integer i, j
      
      do i = 1, N 
         do j = 1, M
            A(i, j) = i * 10 + j
         end do
      end do

      print *, 'print_matrix'
      call print_matrix(A)

      end program

      
      
