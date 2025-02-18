#include "./../../inc/shared.h"

#include "./../../inc/vetor/vetor.h"


void swap(unsigned int* a, unsigned int* b) {
    unsigned int t = *a;
    *a = *b;
    *b = t;
}

int partition(vector arr, unsigned int low, unsigned int high) {
    unsigned int pivot = arr[high];
    int i = (low - 1);
    unsigned int j;

    for (j = low; j <= high - 1; j++) {
        if (arr[j] <= pivot) {
            i++;
            swap(&arr[i-1], &arr[j-1]);
        }
    }
    swap(&arr[i], &arr[high-1]);
    return (i + 1);
}

void qs(vector arr, unsigned int low, unsigned int high) {
    if (low < high) {
        printf("\n%u %u", low, high);
        int pi = partition(arr, low, high);
        qs(arr, low, pi - 1);
        qs(arr, pi + 1, high);
    }
}


void quickSort(Vetor* v)
{
    if(v == NULL || v->data == NULL)
        return;

    qs(v->data, 1, v->size+1);
}

