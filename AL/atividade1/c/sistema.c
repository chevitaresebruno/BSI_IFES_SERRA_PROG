#include "fracao.c"


typedef struct sistema
{
    Fracao*** matriz;
    unsigned int numEq;
    unsigned int numVar;
} Sistema;


Sistema* construirSistema(const unsigned int numEq, const unsigned int numVar, Fracao*(*funcaoGeracao)())
{
    register unsigned int i, j, f;
    Sistema* s = (Sistema*)malloc(sizeof(Sistema));
    if(s == NULL)
        return NULL;

    i = numEq;

    s->matriz = (Fracao***)malloc(sizeof(Fracao**)*i);
    if(s->matriz == NULL)
    {
        free(s);
        return NULL;
    }
    
    f = 0;
    for(i; i > f; i--)
    {
        s->matriz[i] = (Fracao**)malloc(sizeof(Fracao*)*j);
        if(s->matriz[i] == NULL)
        {
            FREE:
            for(j = numEq; j > i; j--)
                free(s->matriz[j]);
            free(s->matriz[i]);
            free(s->matriz);
            free(s);
            return NULL;
        }
    }
    s->matriz[f] = (int*)malloc(sizeof(int)*j);
    if(s->matriz[f] == NULL)
        goto FREE;

    for(i = numEq; i > f; i--)
    {
        for(j = numVar; j > f; j--)
        {
            s->matriz[i][j] = funcaoGeracao(); /* Vou confiar que alocou corretamente isso aqui, pq se não fudeu. */
        }
    }

    s->numEq = numEq;
    s->numVar = j;
}

