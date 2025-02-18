#ifndef VETOR_H
#define VETOR_H 1

#define MATRIX_ELEMENT(maxSize) (rand()%(maxSize))


typedef struct Vetor
{
    vector data;
    unsigned int size;
} Vetor;


#endif