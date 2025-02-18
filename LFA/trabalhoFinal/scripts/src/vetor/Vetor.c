#include "./../../inc/shared.h"
#include "./../../inc/vetor/Vetor.h"


Vetor* vetorBuild(const unsigned int size)
{
    Vetor* v = (Vetor*)malloc(sizeof(Vetor));
    if(v == NULL)
        return NULL;

    v->data = (vector)malloc(sizeof(unsigned int)*size);
    if(v->data == NULL)
    {
        free(v);
        return NULL;
    }

    v->size = size;

    return v;
}


void vetorFree(Vetor* v)
{
    if(v == NULL)
        return;

    if(v->data != NULL)
        free(v->data);

    v->size = 0;
    free(v);
}


int vetorFill(Vetor* v, const unsigned int maxNSize)
{
    register iterator c;
    register unsigned int a;
    if(v == NULL || v->data == NULL)
        return 1;

    a = maxNSize;
    for(c = 0; c < v->size; c++)
        v->data[c] = MATRIX_ELEMENT(a);

    return 0;
}

