#include "./../nodes/SimpleNode.c"


typedef struct slq SimpleLinkedQueue;


struct slq
{
    SimpleNode* dawn;
    SimpleNode* end;
    unsigned int size;
};


SimpleLinkedQueue* newSlq()
{
    SimpleLinkedQueue* q = (SimpleLinkedQueue*)malloc(sizeof(SimpleLinkedQueue));
    if(q == NULL)
        return NULL;

    q->dawn = NULL;
    q->end = NULL;
    q->size = 0;

    return q;
}


void slqFree(SimpleLinkedQueue* q)
{
    if(q == NULL)
        return;

    free(q);
}

void slqNFree(SimpleLinkedQueue* q)
{
    SimpleNode* n;
    SimpleNode* b;

    if(q == NULL)
        return;

    n = q->dawn;
    while(n != NULL)
    {
        b = n;
        n = n->n;
        free(b);
    }
}


void slqForeach(SimpleLinkedQueue* q, foreachF func)
{
    SimpleNode* n;

    if(q == NULL || func == NULL)
        return;

    for(n = q->dawn; n != NULL; n = n->n)
        { func(n->v); }
}

bool slqFFree(SimpleLinkedQueue* q, freeFunc func)
{
    SimpleNode* n;
    SimpleNode* b;

    if(q == NULL || func == NULL)
        return false;

    n = q->dawn;
    while(n != NULL)
    {
        func(n->v);
        b = n;
        n = n->n;
        free(b);
    }

    free(q);

    return true;
}


bool slqAdd(SimpleLinkedQueue* q, void* v)
{
    SimpleNode* n;

    if(q == NULL)
        return false;

    n = newNode(v);
    if(n == NULL)
        return false;
    
    if(q->end == NULL)
        { q->dawn = n; }
    else
        { q->end->n = n; }
    q->end = n;
    
    q->size++;
    return true;
}


void* slqPop(SimpleLinkedQueue* q)
{
    void* v;
    SimpleNode* n;

    if(q == NULL || q->dawn == NULL)
        return NULL;

    n = q->dawn;
    q->dawn = q->dawn->n;
    v = n->v;

    if(q->dawn == NULL)
        { q->end = NULL; }

    q->size--;
    free(n);

    return v;
}


