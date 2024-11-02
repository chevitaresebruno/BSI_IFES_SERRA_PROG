#pragma once
#ifndef MATRIX_H
#define MATRIX_H

#include "lib/include/comum.h"


typedef int** MatrixFrame;
typedef struct Matrix Matrix;

Response* create_matrix(const unsigned int i, const unsigned int j);

MatrixFrame get_matrix_frame_ptr(Matrix* m);
unsigned int get_matrix_lines_num(const Matrix* m);
unsigned int get_matrix_clumns_num(const Matrix* m);
int get_matrix_cell(Matrix* m, unsigned int i, unsigned int j);

#endif