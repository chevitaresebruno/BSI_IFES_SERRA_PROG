#include "./../dependencies.h"
#include "./ordenacao.h"


void mergeSort(void* arr, const unsigned int left, const unsigned int right, SwapFunction func)
{
    register int l = left;
    register int r = right;
    register int m;

    if(l < r)
    {
        m = l + (r-l)/2;

        mergeSort(arr, l, m, func);
        mergeSort(arr, m, l+1, func);

        for(l; l < r; l++)
            func(arr, l, l+1);
    }
}

