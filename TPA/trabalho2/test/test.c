#include <stdlib.h>
#include <stdio.h>

typedef struct x
{
    struct x* next;
    int val;
} X;


int add(X* x);

int main()
{
    X* x = (X*)malloc(sizeof(X));
    x->val = 5;
    x->next = (X*)malloc(sizeof(X));
    x->next->val = 4;
    x->next->next = NULL;

    printf("%d, %d\n", x->val, x->next->val);
    printf("%d\n", add(x));

    return 0;
}

