#include "lib/src/chs/BinaryTree.c"

#include <stdio.h>


compareResult cmpInts(const void* i1, const void* i2)
{
    register int a;
    register int b;

    if(i1 == NULL || i2 == NULL)
        return nullPointer;
    
    a = *((int*)i1);
    b = *((int*)i2);

    if(a > b)
        return greaterThan;
    if(a < b)
        return lessThan;
    return equal;
}


void iPrint(void* i)
{
    if(i == NULL)
        return;

    printf("%d\n", *(int*)i);
}


int main()
{
    BinaryTree* bt = newBT();
    SimpleLinkedQueue* q;
    void* v;

    if(bt == NULL)
        return 1;

    int i[] = {5, 3, 4, 1, 2, 7, 6, 9};

    btAdd(bt, &i[0], cmpInts);
    btAdd(bt, &i[1], cmpInts);
    btAdd(bt, &i[2], cmpInts);
    btAdd(bt, &i[3], cmpInts);
    btAdd(bt, &i[4], cmpInts);
    btAdd(bt, &i[5], cmpInts);
    btAdd(bt, &i[6], cmpInts);
    btAdd(bt, &i[7], cmpInts);

    v = btPop(bt, &i[1], cmpInts);

    q = btWInOrder(bt);
    slqForeach(q, iPrint);

    if(v != NULL)
        printf("\n\nRESULT: %d", *(int*)v);
    printf("\nHeight: %d", btHeight(bt));

    return 0;
}


