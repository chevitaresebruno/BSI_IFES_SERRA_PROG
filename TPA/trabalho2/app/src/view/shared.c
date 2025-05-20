#ifndef VIEW_SHARED_C
#define VIEW_SHARED_C 0


#include <stdio.h>
#include "./../../lib/inc/chs/BinaryTreeAvl.h"
/* #include "./../../lib/src/chs/BinaryTreeAvl.c" */
#include "./../controller/AlunoController.c"

void printAlunoStatus(void* aln)
{
    Aluno* a = (Aluno*)aln;
    if(alnValido(a) == false)
        return;

    // printf("%d - %s - %.2f (%s)\n", alnGetMatricula(a), alnGetNome(a), alnGetNota(a), getAlunoStatus(a));
    printf("%d - ", alnGetMatricula(a));
    printf("%s - ", alnGetNome(a));
    printf("%.2f ", alnGetNota(a)*100);
    printf("(%s)\n", getAlunoStatus(a));
}


#endif