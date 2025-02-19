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


QuickSortArggs* qsInitArgs(vector vec, const unsigned int size)
{
    QuickSortArggs* arggs = (QuickSortArggs*)malloc(sizeof(QuickSortArggs));
    if(arggs == NULL)
        return NULL;
    
    arggs->v = vec;
    arggs->l = 0;
    arggs->h = size;
    arggs->p = 0;
    arggs->tT = 0;

    return arggs;
}


void* pqs(void* arggs)
{
    QuickSortArggs* argg = (QuickSortArggs*) arggs;
    
    pthread_t* workers;
    register iterator i;
    QuickSortArggs* arg1;
    QuickSortArggs* arg2;

    if (argg->l < argg->h)
    {
        argg->p = partition(argg->v, argg->l, argg->h);

        if(argg->tT < gThreadsNumber)
        {
            workers = (pthread_t*)malloc(sizeof(pthread_t)*2);

            arg1 = qsInitArgs(argg->v, argg->p-1);
            if(arg1 == NULL)
            {
                free(workers);
                goto LABEL;
            }
            arg2 = qsInitArgs(argg->v, argg->h);
            if(arg2 == NULL)
            {
                free(workers);
                free(arg1);
                goto LABEL;
            }

            argg->tT += 2;
            arg1->l = argg->l;
            arg1->tT = argg->tT;
            arg2->l = argg->p+1;
            arg2->tT = argg->tT;

            pthread_create(&workers[0], NULL, pqs, (void*)arg1);
            pthread_create(&workers[1], NULL, pqs, (void*)arg2);

            for(i = 0; i < 2; i++)
                pthread_join(workers[i], NULL);  
            
            free(workers);
            free(arg1);
            free(arg2);
        }
        else
        {
            LABEL:
            arg1 = qsInitArgs(argg->v, argg->p-1);
            if(arg1 == NULL)
                return NULL;
            arg2 = qsInitArgs(argg->v, argg->h);
            if(arg2 == NULL)
            {
                free(arg1);
                return NULL;
            }

            arg1->l = argg->l;
            arg1->tT = argg->tT;
            arg2->l = argg->p+1;
            arg2->tT = argg->tT;

            pqs(arg1);
            pqs(arg2);
            
            free(arg1);
            free(arg2);
        }
    }

    return NULL;
}

void* qs(void* arggs)
{
    return pqs(arggs);
}


void quickSort(Vetor* v)
{
    QuickSortArggs* arggs;
    if(v == NULL || v->data == NULL || v->size <= 0)
        return;

    arggs = qsInitArgs(v->data, v->size-1);
    if(arggs == NULL)
        return;

    qs(arggs);
    free(arggs);
}

