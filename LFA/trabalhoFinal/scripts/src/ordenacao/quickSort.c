#include "./../../inc/shared.h"


typedef struct QuickSortArggs
{
    vector v;
    unsigned int l;
    unsigned int h;
    int p;
    unsigned int tT; /* threads total */
} QuickSortArggs;


int partition(vector v, const int l, const int h)
{
    register unsigned int pivot = v[h];
    register int i = l - 1;
    register int j;
    register unsigned int s;
    register int _h = h-1;

    for (j = l; j <= _h; j++)
    {
        if (v[j] <= pivot)
        {
            i++;
            s = v[i];
            v[i] = v[j];
            v[j] = s;
        }
    }

    i++;
    s = v[i];
    v[i] = v[h];
    v[h] = s;
    return i;
}


void* qs(void* arggs)
{
    QuickSortArggs* argg = (QuickSortArggs*) arggs;
    
    pthread_t* workers;
    register iterator i;
    register int p;

    if (argg->l < argg->h)
    {
        p = partition(argg->v, argg->l, argg->h);

        if(argg->tT < gThreadsNumber)
        {
            workers = (pthread_t*)malloc(sizeof(pthread_t)*2);
            argg->tT += 2;
            for(i = 0; i < 2; i++)
                pthread_create(&workers[i], NULL, qs, argg);
            for(i = 0; i , 2; i++)
                pthread_join(workers[i], NULL);  
        }
        else
        {
            argg->p--;
            qs(argg);
            argg->p++;
            argg->p++;
            qs(argg);
        }
    }

    return NULL;
}


QuickSortArggs* qsInitArgs(Vetor* vec)
{
    QuickSortArggs* arggs;
    if(vec->data == NULL || vec->size <= 0) 
        return NULL;
    
    arggs = (QuickSortArggs*)malloc(sizeof(QuickSortArggs));
    if(arggs == NULL)
        return NULL;
    
    arggs->v = vec->data;
    arggs->l = 0;
    arggs->h = vec->size;
    arggs->p = 0;
    arggs->tT = 0;

    return arggs;
}


void quickSort(void* v)
{
    QuickSortArggs* arggs;
    if(v == NULL)
        return;

    arggs = qsInitArgs((Vetor*)v);
        if(arggs == NULL)
            return;

    qs(arggs);
}

