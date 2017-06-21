% gcc oblique-projection.c

% ./a.out > projection.dat

% gnuplot

gnuplot> plot "projection.dat" u 2:3 w l
