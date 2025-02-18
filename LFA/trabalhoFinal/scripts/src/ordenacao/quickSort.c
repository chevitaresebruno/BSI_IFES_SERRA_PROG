#include "./../../inc/shared.h"

#include "./../../inc/vetor/vetor.h"


int partition(vector v, const unsigned int l, const unsigned int h)
{
    register const unsigned int p = v[h];
    register int i = l-1;
    register iterator c = l;
    register const unsigned int _h = h;
    register unsigned int a;

    for(c; c <= _h; c++)
    {
        if(v[c] <= p)
        {
            i++;
            a = v[i];
            v[i] = v[c];
            v[c] = a;
        }
    }

    a = v[i];
    v[i] = v[h];
    v[h] = a;

    return i;
}

void recCall(vector v, const unsigned int l, const unsigned int h)
{
    unsigned int p;
    if(l < h)
    {
        p = partition(v, l, h);
        recCall(v, l, p-1);
        recCall(v, p+1, h);
    }
}

void quickSort(Vetor* v)
{
    if(v == NULL || v->data == NULL)
        return;

    recCall(v->data, 0, v->size);
}
