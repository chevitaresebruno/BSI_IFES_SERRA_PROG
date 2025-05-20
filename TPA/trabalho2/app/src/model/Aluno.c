#include "./adt/String.c"
#include "./../../lib/share.h"


typedef struct aln
{
    String* nome;
    int mtc;
    double nota;    
} Aluno;


Aluno* newAln(String* nome, const int matricula, const double nota)
{
    Aluno* a;

    if(nome == NULL)
        return NULL;

    a = (Aluno*)malloc(sizeof(Aluno));
    if(a == NULL)
        return NULL;

    a->nome = nome;
    a->mtc = matricula;
    a->nota = nota;

    return a;
}

void alnFree(void* a)
{
    if(a == NULL)
        return;

    strFree(((Aluno*)a)->nome);
    free(a);
}


bool alnValido(Aluno* aln)
{
    if(aln == NULL || aln->nome == NULL || aln->nome->v == NULL)
        return false;

    return true;
}

char* alnGetNome(Aluno* aln)
{
    if(aln == NULL || aln->nome == NULL || aln->nome->v == NULL)
        return NULL;

    return aln->nome->v;
}


int alnGetMatricula(Aluno* aln)
{
    if(aln == NULL)
        return 0;

    return aln->mtc;
}


compareResult alnCmpNome(const void* aln, const void* nome)
{
    int r;

    if(alnValido((Aluno*)aln) == false)
        return greaterThan;

    
    r = strcmp(alnGetNome((Aluno*)aln), (char*)nome);

    if(r > 0)
        return greaterThan;
    if(r < 0)
        return lessThan;

    return equal;
}

compareResult alnCmpMatricula(const void* aln, const void* mtc)
{
    if(aln == NULL || mtc == NULL)
        return greaterThan;

    
    if(((Aluno*)aln)->mtc > *(int*)mtc)
        return greaterThan;
    if(((Aluno*)aln)->mtc < *(int*)mtc)
        return lessThan;

    return equal;
}

compareResult alnCmpAln(const void* aln, const void* aln2)
{
    if(aln == NULL || aln2 == NULL)
        return greaterThan;

    return alnCmpMatricula(aln, &((Aluno*)aln2)->mtc);    
}

double alnGetNota(const Aluno* a)
{
    if(a == NULL)
        return 0;
    return a->nota;
}

bool alnAprovado(void* aln)
{
    if(aln == NULL)
        return false;

    return ((Aluno*)aln)->nota >= 0.6 ? true : false;
}

