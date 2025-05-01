#include "./../../share.h"


typedef struct db DoubleNode;


struct db
{
    DoubleNode* lft;
    DoubleNode* rgt;
    void* v;
};


DoubleNode* newDN(void* v)
{
    DoubleNode* dn = (DoubleNode*)malloc(sizeof(DoubleNode));
    if(dn == NULL)
        return NULL;
    
    dn->lft = NULL;
    dn->rgt = NULL;
    dn->v = v;

    return dn;
}


void* dnFree(DoubleNode* dn)
{
    void* v;

    if(dn == NULL)
        return NULL;

    v = dn->v;
    free(dn);

    return v;
}


void* getValue(DoubleNode* dn)
{
    if(dn == NULL)
        return NULL;

    return dn->v;
}


void* setValue(DoubleNode* dn, void* new)
{
    void* lv;
    if(dn == NULL)
        return NULL;

    lv = dn->v;
    dn->v = new;

    return lv;
}


bool addLeft(DoubleNode* dn, DoubleNode* lf)
{
    if(dn == NULL || lf == NULL)
        return false;

    dn->lft = lf;

    return true;
}


bool addRigth(DoubleNode* dn, DoubleNode* rg)
{
    if(dn == NULL || rg == NULL)
        return false;

    dn->rgt = rg;
    return true;
}

