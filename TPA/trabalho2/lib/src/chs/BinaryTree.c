#include "./../nodes/DoubleNode.c"
#include "./Queue.c"


typedef struct bt BinaryTree;


struct bt
{
    DoubleNode* root;
    unsigned int size;
};


BinaryTree* newBT()
{
    BinaryTree* bt = (BinaryTree*)malloc(sizeof(BinaryTree)); 

    if(bt == NULL)
        return NULL;

    bt->size = 0;
    bt->root = NULL;
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


bool btAdd(BinaryTree* bt, void* nv, cmpFunc cmpF)
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
        if(recAddV(bt->root, nv, cmpF) == false)
            return false;
    }

    bt->size++;
    return true;
}


void* btPop(BinaryTree* bt, const void* v, cmpFunc func)
{
    DoubleNode* dn; 
    DoubleNode** bf;
    void* r;
    
    if(bt == NULL || bt->root == NULL || func == NULL)
        return NULL;

    dn = bt->root;
    while(dn != NULL)
    {
        switch(func(v, dn->v))
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


void* btSeacrh(BinaryTree* bt, void* v, cmpFunc cmpF)
{
    DoubleNode* dn;

    if(bt == NULL || bt->root == NULL)
        return NULL;

    dn = bt->root;

    while(dn != NULL)
    {
        switch(cmpF(dn->v, v))
        {
            case equal:
                return dn->v;
            case lessThan:
                dn = dn->lft;
                break;
            case greaterThan:
                dn = dn->rgt;
                break;
            case nullPointer:
                return NULL;
        }   
    }

    return NULL;
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

    if(h1 > h2)
        return h1;
    return h2;
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

