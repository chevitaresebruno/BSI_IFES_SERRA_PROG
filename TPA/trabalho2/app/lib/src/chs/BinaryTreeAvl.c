#include "./BinaryTree.c"

typedef struct bt BinaryTreeAvl;

BinaryTreeAvl* newBTAvl(cmpFunc cmp)
{
    return (BinaryTreeAvl*)newBT(cmp);
}


void btaFFree(BinaryTreeAvl* bta, freeFunc func)
{
    btFFree((BinaryTree*)bta, func);
}

void rttLft(DoubleNode** bf)
{
    DoubleNode* dn = (*bf);
    (*bf) = dn->rgt;
    dn->rgt = dn->rgt->lft;
    (*bf)->lft = dn;
}

void rttRgt(DoubleNode** bf)
{
    DoubleNode* dn = (*bf);
    (*bf) = dn->lft;
    dn->lft = dn->lft->rgt;
    (*bf)->rgt = dn;
}

int recAddAvl(DoubleNode** bf, void* v, cmpFunc func, int* f)
{
    unsigned int hl = 0, hr = 0;
    int bfactor;
    DoubleNode* dn = (*bf);

    switch (func(v, dn->v))
    {
        case equal:
            /* if equals, try insert in rigth, so, Fall Trough */
        case greaterThan:
            if(dn->rgt == NULL)
            {
                dn->rgt = newDN(v);
                if(dn->rgt == NULL)
                    return -1;
                hr = 1;
            }
            else
                hr = recAddAvl(&(dn->rgt), v, func, f);
            
            if(dn->lft != NULL)
                hl = recH(dn->lft, 1);
            break;
            
        case lessThan:
            if(dn->lft == NULL)
            {
                dn->lft = newDN(v);
                if(dn->lft == NULL)
                    return -1;
                hl = 1;
            }
            else
                hl = recAddAvl(&(dn->lft), v, func, f);
            
            if(dn->rgt != NULL)
                hr = recH(dn->rgt, 1);
            break;
    }

    if(hl == -1 || hr == -1)
        return -1;

    bfactor = hr - hl;

    if(bfactor > 1)
    {    
        if(*f < 0)
            rttRgt(&(dn->rgt));
        rttLft(bf);
        hr--;
    } 
    else if(bfactor < -1)
    {
        if(*f > 0)
            rttLft(&(dn->lft));
        rttRgt(bf);
        hl--;
    }

    (*f) = hr-hl;
    return (hl > hr ? hl : hr)+1;
}

bool btaAdd(BinaryTreeAvl* bta, void* nv)
{
    int aux = 0;
    if(bta == NULL)
        return false;

    if(bta->size == 0)
    {
        bta->root = newDN(nv);
        if(bta->root == NULL) return false;
    }
    else
    {
        if(bta->root == NULL) return false;
        if(recAddAvl(&(bta->root), nv, bta->cmp, &(aux)) == -1)
            return false;
    }

    bta->size++;
    return true;
}

void* btaPRec(DoubleNode** nr, void* v, cmpFunc func, int* h, int* bf)
{
    DoubleNode* dn = NULL;
    void* r = NULL;
    int hl = 0, hr = 0, fb;
    
    if((*nr) == NULL)
        return NULL;

    switch (func((*nr)->v, v))
    {
        case equal:
            dn = (*nr);

            r = dn->v; /* get the value should be returned */
            if(dn->lft == NULL)
            {
                if(dn->rgt == NULL) { *nr = NULL; goto DEALLOC; }
                hr = recH(dn->rgt, 1);
            }
            else
            {
                hl = recH(dn->lft, 1);
                if(dn->rgt == NULL)
                {
                    *nr = dn->lft;
                    goto DEALLOC;
                }
                hr = recH(dn->rgt, 1);
            }

            /* Only if it is a complete subtree */
            if(dn->lft->rgt == NULL)
            {
                *nr = dn->lft;
                (*nr)->rgt = dn->rgt;
                goto END;
            } 

            dn = dn->lft;
            while(dn->rgt->rgt != NULL)
                dn = dn->rgt;
            (*nr)->v = dn->rgt->v;
            
            nr = dn->rgt; 
            dn->rgt = NULL;
            dn = nr;

            DEALLOC:
            dnFree(dn);

            goto END;
        case greaterThan: /* if node->v greather than search value, search in left way */
            r = btaPRec(&((*nr)->lft), v, func, &hl, bf);
            if((*nr)->rgt != NULL)
                hr = recH((*nr)->rgt, 1);
            break;
        case lessThan: /* if node->v less than search value, search in right way */
            r = btaPRec(&((*nr)->rgt), v, func, &hr, bf);
            if((*nr)->lft != NULL)
                hl = recH((*nr)->lft, 1);
            break;
        case nullPointer:
            return NULL;
    }

    fb = hr - hl;
    
    if(fb > 1)
    {    
        if(*bf < 0)
            rttRgt(&(dn->rgt));
        rttLft(nr);
        hr--;
    } 
    else if(fb < -1)
    {
        if(*bf > 0)
            rttLft(&(dn->lft));
        rttRgt(nr);
        hl--;
    }

    END:
    *h = (hr > hl ? hr : hl) + 1;
    *bf = hr - hl;

    return r;
}

void* btaPop(BinaryTreeAvl* bta, void* v)
{
    void* r;
    int aux1 = 0, aux2 = 0;

    
    if(bta == NULL || bta->root == NULL || bta->cmp == NULL)
        return NULL;

    r = btaPRec(&(bta->root), v, bta->cmp, &aux1, &aux2);
    if(r == NULL)
        return NULL;

    bta->size--;
    return r;
}

void* btaPBRec(DoubleNode** nr, void* v, cmpFunc func, int* h, int* bf)
{
    DoubleNode* dn = NULL;
    void* r = NULL;
    int hl = 0, hr = 0, fb;
    
    if((*nr) == NULL)
        return NULL;

    switch (func((*nr)->v, v))
    {
        case equal:
            dn = (*nr);

            r = dn->v; /* get the value should be returned */
            if(dn->lft == NULL)
            {
                if(dn->rgt == NULL) { *nr = NULL; goto DEALLOC; }
                hr = recH(dn->rgt, 1);
            }
            else
            {
                hl = recH(dn->lft, 1);
                if(dn->rgt == NULL)
                {
                    *nr = dn->lft;
                    goto DEALLOC;
                }
                hr = recH(dn->rgt, 1);
            }

            /* Only if it is a complete subtree */
            if(dn->lft->rgt == NULL)
            {
                *nr = dn->lft;
                (*nr)->rgt = dn->rgt;
                goto END;
            } 

            dn = dn->lft;
            while(dn->rgt->rgt != NULL)
                dn = dn->rgt;
            (*nr)->v = dn->rgt->v;
            
            nr = dn->rgt; 
            dn->rgt = NULL;
            dn = nr;

            DEALLOC:
            dnFree(dn);

            goto END;
        case greaterThan: /* if node->v greather than search value, search in left way */
            r = btaPRec(&((*nr)->lft), v, func, &hl, bf);
            if((*nr)->rgt != NULL)
                hr = recH((*nr)->rgt, 1);
            break;
        case lessThan: /* if node->v less than search value, search in right way */
            r = btaPRec(&((*nr)->rgt), v, func, &hr, bf);
            if((*nr)->lft != NULL)
                hl = recH((*nr)->lft, 1);
            break;
        case nullPointer:
            return NULL;

        default:
            r = btaPBRec(&((*nr)->lft), v, func, &hl, bf);
            if(r == NULL)
            {
                r = btaPBRec(&((*nr)->rgt), v, func, &hr, bf);
                if((*nr)->lft != NULL)
                    hl = recH((*nr)->lft, 1);
            }
            else
                if((*nr)->rgt != NULL)
                    hr = recH((*nr)->rgt, 1);
                
    }

    fb = hr - hl;
    
    if(fb > 1)
    {    
        if(*bf < 0)
            rttRgt(&(dn->rgt));
        rttLft(nr);
        hr--;
    } 
    else if(fb < -1)
    {
        if(*bf > 0)
            rttLft(&(dn->lft));
        rttRgt(nr);
        hl--;
    }

    END:
    *h = (hr > hl ? hr : hl) + 1;
    *bf = hr - hl;

    return r;
}

void* batPopBy(BinaryTreeAvl* bta, void* v, cmpFunc func)
{
    void* r;
    int aux1 = 0, aux2 = 0;

    
    if(bta == NULL || bta->root == NULL || bta->cmp == NULL)
        return NULL;

    r = btaPBRec(&(bta->root), v, func, &aux1, &aux2);
    if(r == NULL)
        return NULL;

    bta->size--;
    return r;
}