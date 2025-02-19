#include "./../../inc/shared.h"


typedef struct MergeSortArgs {
    vector v;
    unsigned int l;
    unsigned int h;
    unsigned int tT; /* threads total */
} MergeSortArgs;


void merge(vector v, const int l, int m, const int h) 
{
    int n1 = m - l + 1;
    int n2 = h - m;
    int* p1 = (int*)malloc(sizeof(int ) * n1); 
    int* p2 = (int*)malloc(sizeof(int ) * n2); 
    register iterator i, j, index;

    for (i = 0; i < n1; i++) {
        p1[i] = v[l + i];  
    }
    for (j = 0; j < n2; j++) {
        p2[j] = v[m + 1 + j];  
    }

    i = 0;
    j = 0;
    index = l;
    while (i < n1 && j < n2) {
        if (p1[i] < p2[j]) {
            v[index] = p1[i];
            i++;
            index++;
        } 
        else {
            v[index] = p2[j];
            j++;
            index++;
        }
    }

    while (i < n1) {
        v[index] = p1[i];
        i++;
        index++;
    }

    while (j < n2) {
        v[index] = p2[j];
        j++;
        index++;
    }

}


void* ms(void* args)
{
    MergeSortArgs* arg = (MergeSortArgs*) args;
    pthread_t* workers;
    register iterator i;

    if (arg->l < arg->h)
    {
        int m = arg->l + (arg->h - arg->l) / 2;
        MergeSortArgs* argLeft = (MergeSortArgs*)malloc(sizeof(MergeSortArgs));
        MergeSortArgs* argRight = (MergeSortArgs*)malloc(sizeof(MergeSortArgs));

        *argLeft = *arg;  
        *argRight = *arg; 

        argLeft->l = m;
        argRight->h = m+1; 
        
        if (arg->tT < gThreadsNumber)
        {
            workers = (pthread_t*)malloc(sizeof(pthread_t)*2);
            arg->tT += 2;
            pthread_create(&workers[0], NULL, ms, argLeft);
            pthread_create(&workers[1], NULL, ms, argRight);

            for(i = 0; i < 2; i++)
                pthread_join(workers[i], NULL);  
        }
        else
        {
            ms(argLeft);
            ms(argRight);
        }
        merge(arg->v, arg->l, m,arg->h);
    }

    return NULL;
}


MergeSortArgs* msInitArgs(Vetor* vec)
{
    MergeSortArgs* args;
    if(vec->data == NULL || vec->size <= 0) 
        return NULL;
    
    args = (MergeSortArgs*)malloc(sizeof(MergeSortArgs));
    if(args == NULL)
        return NULL;
    
    args->v = vec->data;
    args->l = 0;
    args->h = vec->size;
    args->tT = 0;

    return args;
}


void mergeSort(void* v)
{
    MergeSortArgs* args;
    if(v == NULL)
        return;

    args = msInitArgs((Vetor*)v);
    if(args == NULL)
        return;

    ms(args);
}
