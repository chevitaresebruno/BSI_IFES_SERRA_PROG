#include "./../../share.h"


typedef struct sn SimpleNode;


struct sn
{
    void* v;
    SimpleNode* n;
};


SimpleNode* newNode(void* v)
{
    SimpleNode* n = (SimpleNode*)malloc(sizeof(SimpleNode));
    if(n == NULL)
        return NULL;

    n->v = v;
    n->n = NULL;

    return n;
}


void* freeNode(SimpleNode* n)
{
    void* v;

    if(n == NULL)
        return NULL;

    v = n->v;
    free(n);

    return v;
}


void* nodeGetV(SimpleNode* n)
{
    if(n == NULL)
        return NULL;

    return n->v;
}

