#include <stdlib.h>
#include <stdio.h>

typedef struct SLN SimpleLinkedNode;
typedef struct SLLH SimpleLinkedListHeader;


struct SLN
{
    void* value;
    SimpleLinkedNode* next; 
};


struct SLLH
{
    SimpleLinkedNode* start;
    SimpleLinkedNode* end;
    unsigned int size;
};


SimpleLinkedNode* slnCreate()
{
    SimpleLinkedNode* node;
    
    node = (SimpleLinkedNode*)malloc(sizeof(SimpleLinkedNode));

    if(node == NULL)
        return NULL;
    
    node->value = NULL;
    node->next = NULL;

    return node;
}


SimpleLinkedListHeader* sllhCreate()
{
    SimpleLinkedListHeader* sllh;

    sllh = (SimpleLinkedListHeader*)malloc(sizeof(SimpleLinkedListHeader));

    if(sllh == NULL)
        return NULL;

    sllh->start = NULL;
    sllh->end = NULL;
    sllh->size = 0;

    return sllh;
}


void sllhForeachS(SimpleLinkedListHeader* lh, void(*function)(void*))
{
    SimpleLinkedNode* node;

    if(lh == NULL)
        return;

    for(node = lh->start; node != NULL; node=node->next)
    {
        if(node->value != NULL)
            function(node->value);
    }
}


void sllhForeach(SimpleLinkedListHeader* lh, void(*function)(void*))
{
    SimpleLinkedNode* node;

    if(lh == NULL)
        return;

    for(node = lh->start; node != NULL; node=node->next)
            function(node->value);
}

void sllhForeachIndex(SimpleLinkedListHeader* lh, void(*function)(void*, const unsigned int))
{
    SimpleLinkedNode* node;
    register unsigned int i;

    if(lh == NULL)
        return;

    i = 0;
    for(node = lh->start; node != NULL; node=node->next)
    {
        function(node->value, i);
        i++;
    }
}

int sllhAddS(SimpleLinkedListHeader* lh, void* value)
{
    SimpleLinkedNode* node;

    if(lh == NULL || value == NULL)
        return 0;

    node = slnCreate();

    if(node == NULL)
        return 1;

    node->value = value;

    if(lh->size == 0)
        lh->start = node;
    else
        lh->end->next = node;

    lh->end = node;
    lh->size++;
    return 2;
}


int sllhAdd(SimpleLinkedListHeader* lh, void* value)
{
    SimpleLinkedNode* node;

    if(lh == NULL)
        return 0;

    node = slnCreate();

    if(node == NULL)
        return 1;

    node->value = value;

    if(lh->size == 0)
        lh->start = node;
    else
        lh->end->next = node;

    lh->end = node;
    lh->size++;
    return 2;
}


void* sllhPop(SimpleLinkedListHeader* lh)
{
    SimpleLinkedNode* node;
    void* value;

    if(lh == NULL || lh->start == NULL)
        return NULL;
    
    if(lh->size == 1)
        lh->end = NULL;

    node = lh->start;
    lh->start = node->next;
    lh->size--;

    value = node->value;
    free(node);

    return value;
}


void* sllhGetAt(SimpleLinkedListHeader* sllh, const unsigned int index)
{
    register unsigned int i;
    register unsigned int j;
    SimpleLinkedNode* node;

    if(sllh == NULL || index > sllh->size)
        return NULL;

    node = sllh->start;
    j = index;
    for(i = 1; i <= j; i++)
        node = node->next;
    
    return node->value;
}

void* sllhGetFirst(SimpleLinkedListHeader* sllh)
{
    if(sllh == NULL || sllh->start == NULL)
        return NULL;
    return sllh->start->value;
}

void* sllhGetLast(SimpleLinkedListHeader* sllh)
{
    if(sllh == NULL || sllh->end == NULL)
        return NULL;
    return sllh->end->value;
}

unsigned int sllhGetSize(SimpleLinkedListHeader* sllh)
{
    if(sllh == NULL)
        return 0;
    return sllh->size;
}

int sllhDestroy(SimpleLinkedListHeader* sllh, void(*valueDestroy)(void*))
{
    SimpleLinkedNode* last;
    SimpleLinkedNode* next;

    if(sllh == NULL || valueDestroy == NULL)
        return 0;

    last = sllh->start;

    do
    {
        next = last->next;
        printf("\n%d", *(int*)last->value);
        if(last->value != NULL)
            valueDestroy(last->value);
        free(last);
        last = next;
    }
    while(last != NULL);

    free(sllh);
    return 1;
}

