#include "./sll/sll.c"
#include "./ordenacao/mergesort.c"

int i = 0;

void myPrintf(void* v, const unsigned int i)
{
    printf("Elemento %u - %d\n", i, *(int*)v);
}

void* genFunc()
{
    int* aux = (int*)malloc(sizeof(int));
    if(aux == NULL)
        return NULL;
    switch (i)
    {
    case 0:
        *aux = 0;
        break;
    case 1:
        *aux = 1;
        break;
    case 2:
        *aux = 2;
        break;
    default:
        *aux = 0;
        break;
    }

    i++;
    return (void*)aux;
}

int compareFunction(void* el1, void* el2)
{
    int* aux = (int*)el1;
    int* aux2 = (int*)el2;

    if(*aux > *aux2)
        return 1;
    return 0;
}

void swapFunction(void* arr, const unsigned int _1, const unsigned int _2)
{
    sllhSwapBody(arr, _1, _2, compareFunction);
}


int main()
{
    SimpleLinkedListHeader* l = sllhCreate();

    sllhAddByFunc(l, 3, genFunc);
    sllhForeachIndex(l, myPrintf);
    mergeSort((void*)l, 0, sllhGetSize(l), swapFunction);
    sllhForeachIndex(l, myPrintf);

    return 0;
}