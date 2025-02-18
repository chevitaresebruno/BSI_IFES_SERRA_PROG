#ifndef SEMANTIC_H 
#define SEMANTIC_H 1 

typedef enum {false, true} bool;
typedef enum {correctFill, matrixNull, iLessThanOne, jLessThanOne, someVectorNull} matrixFillErros;
typedef enum {readEnvorimentFile, allocMatrixMemory, deallocMatrixMemory, fillMatrix, saveMatrix, readMatrix, sSearch, pSearch, benchmark, pSearchSpeedUp, quit} menuOptions;

typedef char* string;
typedef int** matrix;
typedef unsigned int* vector;
typedef unsigned int matrixElement;

typedef long unsigned int iterator;
typedef long signed int siterator;


#endif