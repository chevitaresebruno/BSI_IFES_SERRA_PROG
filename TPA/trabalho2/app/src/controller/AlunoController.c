#include "./../model/Aluno.c"


Aluno* getAlunoRandom(const int matricula)
{
    return newAln(newStrRand(10), matricula, (rand() % 100)/100.0);
}


char* getAlunoStatus(Aluno* aln)
{
    if(aln == NULL)
        return NULL;

    return alnAprovado(aln) == true ? "Aprovado" : "Reprovado";
}

