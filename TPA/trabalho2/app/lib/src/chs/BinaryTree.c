#include "./../nodes/DoubleNode.c"
#include "./Queue.c"


typedef struct bt BinaryTree;


struct bt
{
    DoubleNode* root;
    cmpFunc cmp;
    unsigned int size;
};


BinaryTree* newBT(cmpFunc cmp)
{
    BinaryTree* bt;
    
    if(cmp == NULL)
        return NULL;
        
    bt = (BinaryTree*)malloc(sizeof(BinaryTree)); 

    if(bt == NULL)
        return NULL;

    bt->size = 0;
    bt->root = NULL;
    bt->cmp = cmp;

    return bt;
}

void btRecFree(DoubleNode* dn, freeFunc func)
{
    if(dn != NULL)
    {
        btRecFree(dn->lft, func);
        btRecFree(dn->rgt, func);
        func(dnFree(dn));
    }
}

void btFFree(BinaryTree* bt, freeFunc func)
{
    if(bt == NULL || func == NULL)
        return;

    btRecFree(bt->root, func);
    free(bt);
}

bool btSome(BinaryTree* bt, bool(*func)(void*))
{
    SimpleLinkedQueue* q;
    DoubleNode* dn;

    if(bt == NULL || func == NULL)
        return false;

    if(bt == NULL)
        return false;

    q = newSlq();
    if(q == NULL)
        return false;

    if(slqAdd(q, bt->root) == false)
        goto ERROR_LABEL;

    dn = (DoubleNode*)slqPop(q);

    while(dn != NULL)
    {
        if(func(dn->v) == true)
            return true;
        if(dn->lft != NULL)
            if(slqAdd(q, dn->lft) == false) goto ERROR_LABEL;
        if(dn->rgt != NULL)
            if(slqAdd(q, dn->rgt) == false) goto ERROR_LABEL;   
        
        dn = slqPop(q);
    }

    slqNFree(q);
    
    ERROR_LABEL:
    slqNFree(q);
    return false;
}

/* Unsafe Function */
bool recAddV(DoubleNode* dn, void* v, cmpFunc cmpF)
{
    DoubleNode* n = dn;

    START_REC_1:

    switch (cmpF(v, n->v))
    {
        case equal:
            /* if equals, try insert in rigth, so, Fall Trough */
        case greaterThan:
            if(n->rgt == NULL)
            {
                n->rgt = newDN(v);
                if(n->rgt == NULL)
                    return false;
                return true;
            }
            else
                n = n->rgt;
            break;
        case lessThan:
            if(n->lft == NULL)
            {
                n->lft = newDN(v);
                if(n->lft == NULL)
                    return false;
                return true;
            }
            else
                n = n->lft;
            break;
    }
    
    /* Simulation of non Tail Rec */
    goto START_REC_1;
}


bool btAdd(BinaryTree* bt, void* nv)
{
    if(bt == NULL)
        return false;

    if(bt->size == 0)
    {
        bt->root = newDN(nv);
        if(bt->root == NULL) return false;
    }
    else
    {
        if(bt->root == NULL) return false;
        if(recAddV(bt->root, nv, bt->cmp) == false)
            return false;
    }

    bt->size++;
    return true;
}


DoubleNode** btPBRec(DoubleNode** dn, const void* v, cmpFunc func)
{
    DoubleNode** aux = NULL;

    if(func((*dn)->v, v) == equal)
        return dn;
    
    if((*dn)->lft != NULL)
        aux = btPBRec(&(*dn)->lft, v, func);
    if(aux != NULL)
        return aux;

    if((*dn)->rgt != NULL)
        aux = btPBRec(&(*dn)->rgt, v, func);
    return aux;
}

void* btPopBy(BinaryTree* bt, const void* v, cmpFunc func)
{
    DoubleNode* dn; 
    DoubleNode** bf;
    void* r;
    
    if(bt == NULL || bt->root == NULL || func == NULL)
        return NULL;

    bf = btPBRec(&(bt->root), v, func);
    if(bf == NULL)
        return NULL;

    dn = bt->root;

    r = (*bf)->v; /* get the value should be returned */
    if(dn->lft == NULL)
        { if(dn->rgt == NULL) { *bf = NULL; goto END; } }
    else
        if(dn->rgt == NULL) { *bf = dn->lft; goto END; }
    
    /* Only if it is a complete subtree */
    if(dn->lft->rgt == NULL)
    {
        *bf = dn->lft;
        (*bf)->rgt = dn->rgt;
        goto END;
    } 

    dn = dn->lft;
    while(dn->rgt->rgt != NULL)
        dn = dn->rgt;
    (*bf)->v = dn->rgt->v;
    
    bf = dn->rgt; 
    dn->rgt = NULL;
    dn = bf;

    END:
    dnFree(dn);
    bt->size--;
    return r;
}

void* btPop(BinaryTree* bt, const void* v)
{
    DoubleNode* dn; 
    DoubleNode** bf;
    void* r;
    
    if(bt == NULL || bt->root == NULL || bt->cmp == NULL)
        return NULL;

    bf = &(bt->root);
    dn = bt->root;
    while(dn != NULL)
    {
        switch(bt->cmp(v, dn->v))
        {
            case equal:
                goto CONTINUE;
            case lessThan:
                bf = &(dn->lft);
                dn = dn->lft;
                break;
            case greaterThan:
                bf = &(dn->rgt);
                dn = dn->rgt;
                break;
            case nullPointer:
                return NULL;
        }
    }
    return NULL;

    CONTINUE:
    r = dn->v; /* get the value should be returned */
    if(dn->lft == NULL)
        { if(dn->rgt == NULL) { *bf = NULL; goto END; } }
    else
        if(dn->rgt == NULL) { *bf = dn->lft; goto END; }
    
    /* Only if it is a complete subtree */
    if(dn->lft->rgt == NULL)
    {
        *bf = dn->lft;
        (*bf)->rgt = dn->rgt;
        goto END;
    } 

    dn = dn->lft;
    while(dn->rgt->rgt != NULL)
        dn = dn->rgt;
    (*bf)->v = dn->rgt->v;
    
    bf = dn->rgt; 
    dn->rgt = NULL;
    dn = bf;

    END:
    dnFree(dn);
    bt->size--;
    return r;
}

unsigned int btSize(const BinaryTree* bt)
{
    if(bt == NULL)
        return 0;

    return bt->size;
}


void* btSearch(BinaryTree* bt, void* v)
{
    DoubleNode* dn;
    int cmpR;

    if(bt == NULL || bt->root == NULL || bt->cmp == NULL)
        return NULL;

    dn = bt->root;

    while(dn != NULL)
    {
        switch(bt->cmp(dn->v, v))
        {
            case equal:
                return dn->v;
            case lessThan:
                dn = dn->rgt;
                break;
            case greaterThan:
                dn = dn->lft;
                break;
            case nullPointer:
                return NULL;
        }   
    }

    return NULL;
}

void* btSRec(DoubleNode* dn, void* v, cmpFunc func)
{
    void* r = NULL;
    if(func(dn->v, v) == equal)
        return dn->v;

    if(dn->lft != NULL)
        r = btSRec(dn->lft, v, func);
    if(r != NULL)
        return r;
    if(dn->rgt != NULL)
        r = btSRec(dn->rgt, v, func);

    return r;
}


void* btSearchBy(BinaryTree* bt, void* v, cmpFunc func)
{
    if(bt == NULL || bt->root == NULL || v == NULL || func == NULL)
        return NULL;
    return btSRec(bt->root, v, func);
}


unsigned int recH(const DoubleNode* dn, unsigned int hg)
{
    unsigned int h1 = 0, h2 = 0;
    if(dn->lft == NULL && dn->rgt == NULL)
        return hg;
    if(dn->lft != NULL)
        h1 = recH(dn->lft, hg+1);
    if(dn->rgt != NULL)
        h2 = recH(dn->rgt, hg+1);

    return h1 > h2 ? h1 : h2;
}


unsigned int btHeight(const BinaryTree* bt)
{
    if(bt == NULL || bt->root == NULL)
        return 0;

    return recH(bt->root, 0);    
}


SimpleLinkedQueue* btWInLevel(const BinaryTree* bt)
{
    SimpleLinkedQueue* q; /* Fila de DoubleNodes* */
    SimpleLinkedQueue* r; /* Fila de void*  */
    DoubleNode* dn;

    if(bt == NULL)
        return NULL;

    q = newSlq();
    if(q == NULL)
        return NULL;

    r = newSlq();
    if(r == NULL)
    {
        slqFree(q);
        return NULL;
    }

    if(slqAdd(q, bt->root) == false)
        goto ERROR_LABEL;

    dn = (DoubleNode*)slqPop(q);

    while(dn != NULL)
    {
        if(dn->lft != NULL)
            if(slqAdd(q, dn->lft) == false) goto ERROR_LABEL;
        if(dn->rgt != NULL)
            if(slqAdd(q, dn->rgt) == false) goto ERROR_LABEL;   
        
        slqAdd(r, dn->v);
        dn = slqPop(q);
    }

    slqNFree(q);
    
    return r;

    ERROR_LABEL:
    slqNFree(q);
    slqNFree(r);
    return NULL;
}

void btWIORec(SimpleLinkedQueue* q, const DoubleNode* dn)
{
    if(dn == NULL)
        return;

    btWIORec(q, dn->lft);
    slqAdd(q, dn->v);
    btWIORec(q, dn->rgt);
}


SimpleLinkedQueue* btWInOrder(const BinaryTree* bt)
{
    SimpleLinkedQueue* r;
    
    if(bt == NULL)
        return NULL;

    r = newSlq();
    if(r == NULL)
        return NULL;

    btWIORec(r, bt->root);

    return r;
}

