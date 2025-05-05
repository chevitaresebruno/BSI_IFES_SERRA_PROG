#include "./../nodes/SimpleNode.c"

typedef struct stack Stack;


struct stack
{
    unsigned int size;
    SimpleNode* top;
};


Stack* newStk()
{
    Stack* s = (Stack*)malloc(sizeof(Stack));
    
    if(s == NULL)
        return NULL;

    s->top = NULL;
    s->size = 0;

    return s;
}


exceptions stkFree(Stack* s)
{
    if(s == NULL)
        return nullPointerException;
    
    free(s);
    return correctOperation;
}


exceptions stkNFree(Stack* s)
{
    SimpleNode* n;
    SimpleNode* b;
    if(s == NULL)
        return nullPointerException;

    b = s->top;
    while(b != NULL)
    {
        n = b->n;
        free(b);
        b = n;
    }

    free(s);

    return correctOperation;
}

exceptions stkFFree(Stack* s, freeFunc func)
{
    SimpleNode* n;
    SimpleNode* b;
    if(s == NULL || func == NULL)
        return nullPointerException;

    b = s->top;
    while(b != NULL)
    {
        n = b->n;
        func(b->v);
        free(b);
        b = n;
    }

    free(s);

    return correctOperation;
}


void* stkPop(Stack* s)
{
    SimpleNode* n;
    void* v;

    if(s == NULL || s->top == NULL)
        return NULL;

    n = s->top;
    v = n->v;

    s->top = n->n;
    free(n);
    s->size--;
    return v;
}


exceptions stkAdd(Stack* s, void* v)
{
    SimpleNode* n;
    if(s == NULL)
        return nullPointerException;

    n = newNode(v);
    if(n == NULL)
        return memoryNotEnough;
    
    n->n = s->top;
    s->top = n;
    s->size++;
}


void stkForeach(Stack* s, foreachF func)
{
    SimpleNode* n;

    if(s == NULL || func == NULL)
        return;

    for(n = s->top; n != NULL; n = n->n)
        { func(n->v); }
}

