#include "./shared.c"


void testView()
{
    BinaryTreeAvl* bt;
    SimpleLinkedQueue* q;
    Aluno* a;
    Aluno* aux;
    int i;

    srand(0);

    bt = newBTAvl(alnCmpAln);
    if(bt == NULL)
        goto MEMORY_ERROR_LABEL;

    printf("Gerando Alunos\n");
    for(i = 0; i < 10; i++)
    {
        a = getAlunoRandom(rand());
        if(a == NULL || btaAdd(bt, a) == false)
            goto MEMORY_ERROR_LABEL;
    }

    q = btWInOrder(bt);
    if(q == NULL)
        goto MEMORY_ERROR_LABEL;

    slqForeach(q, printAlunoStatus);
    slqNFree(q);

    aux = getAlunoRandom(38);
    a = btSearch(bt, aux);
    alnFree(aux);
    aux = getAlunoRandom(0);
    a = btSearch(bt, aux);
    alnFree(aux);

    btaFFree(bt, alnFree);
    return;

    MEMORY_ERROR_LABEL:
    btaFFree(bt, alnFree);
    printf("Memory Allocation Error\n");
}