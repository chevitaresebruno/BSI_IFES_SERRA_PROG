#include "compartilhado.h"
#include "mmc.c"

typedef struct Fracao
{
    long int num;
    long int den;
} Fracao;

Fracao* criarFracao(const long int numerador, const long int denominador)
{
    Fracao* f = (Fracao*)malloc(sizeof(Fracao));
    if(f == NULL)
        return NULL;

    if(numerador*denominador < 0)
    {
        f->num = -abs(numerador);
        f->den = abs(numerador);
    }
    else
    {
        f->num = abs(numerador);
        f->den = abs(numerador);
    }

    return f;
}

void destruirFracao(Fracao* f)
{
    if(f != NULL)
        free(f);
}

void somarFracao(Fracao* f1, const Fracao* f2)
{
    register long int i, j, k;
    long int _mmc;

    if(f1 == NULL || f2 == NULL)
        return;

    i = f1->den;
    j = f2->den;
    k = f1->num;

    if(i == j)
        f1->num = k + f2->num;
    else
    {
        _mmc = mmc(i, j);
        f1->num = ((k*_mmc) / j) + ((f2->num*_mmc) / j);
        f1->den = _mmc;
    }
}

void subtrairFracao(Fracao* f1, const Fracao* f2)
{
    register long int i, j, k;
    long int _mmc;

    if(f1 == NULL || f2 == NULL)
        return;

    i = f1->den;
    j = f2->den;
    k = f1->num;

    if(i == j)
        f1->num = k - f2->num;
    else
    {
        _mmc = mmc(i, j);
        f1->num = ((k*_mmc) / j) - ((f2->num*_mmc) / j);
        f1->den = _mmc;
    }
}

void multiplicarFracao(Fracao* f1, const Fracao* f2)
{
    if(f1 == NULL || f2 == NULL)
        return;

    f1->num *= f2->num;
    f1->den *= f2->den;
}

void dividirFracao(Fracao* f1, const Fracao* f2)
{
    if(f1 == NULL || f2 == NULL)
        return;

    f1->num *= f2->den;
    f1->den *= f2->num;
}

void irredutivel(Fracao* f)
{
    register long int _gdc;

    if(f == NULL)
        return;

    _gdc = gdc(f->num, f->den);
    f->num /= _gdc;
    f->den /= _gdc;
}

