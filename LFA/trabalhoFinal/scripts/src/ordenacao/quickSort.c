#include "./../../inc/shared.h"

#include "./../../inc/vetor/vetor.h"


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


void qs(vector v, const int l, const int h)
{
    if (l < h)
    {
        register int p = partition(v, l, h);
        qs(v, l, p - 1);
        qs(v, p + 1, h);
    }
}


void quickSort(Vetor* v)
{
    if(v == NULL || v->data == NULL)
        return;

    qs(v->data, 0, v->size);
}

