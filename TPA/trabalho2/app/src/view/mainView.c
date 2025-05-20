#include "./shared.c"

void mainView()
{
    String* alnNome = NULL;
    int matricula;
    double nota;
    int aux; /* option / numero de alunos */
    int i;
    Aluno* aln = NULL;
    Aluno* alnAux = NULL;
    BinaryTreeAvl* bt = NULL;
    SimpleLinkedQueue* q = NULL;

    bt = newBTAvl(alnCmpAln);
    if(bt == NULL)
        goto MEMORY_ERROR_LABEL;
    alnNome = newStr(20);
    if(alnNome == NULL)
        goto MEMORY_ERROR_LABEL;

    printf("Selecione uma das opcoes:\n\t1 - Adicionar Alunos;\n\t2 - Exibir Resultados (Ordenar Por Matricula);\n\t 3 - Buscar Aluno (Matricula); \n\t4 - Buscar Aluno (Nome) e\n\t5 - Sair.\n");
    while(1)
    {
        scanf(" %d", &aux);

        switch (aux)
        {
            case 1:
                printf("\nNumero de Alunos: ");
                scanf(" %d", &aux);
                for(i = 0; i < aux; i++)
                {
                    printf("\nAluno%d nome: ", i);
                    strScan(alnNome);
                    printf("\nAluno%d matricula: ", i);
                    scanf(" %d", &matricula);
                    printf("\nAluno%d nota: ", i);
                    scanf(" %lf", &nota);

                    aln = newAln(alnNome, matricula, nota/100);
                    if(aln == NULL)
                    {
                        printf("Erro de memoria. Os alunos anteriores foram inseridos.");
                        break;
                    }
                    btaAdd(bt, aln);
                    alnNome = newStr(20);
                    if(alnNome == NULL)
                        goto MEMORY_ERROR_LABEL;
                }
                break;
            case 2:
                q = btWInOrder(bt);
                if(q == NULL)
                    break;
                slqForeach(q, printAlunoStatus);
                slqNFree(q);
                break;

            case 3:
                scanf(" %d", &matricula);
                alnAux = getAlunoRandom(matricula);
                if(alnAux == NULL)
                    goto MEMORY_ERROR_LABEL;

                aln = btSearch(bt, alnAux);
                alnFree(alnAux);
                if(aln == NULL)
                    printf("Aluno nao encontrado\n");
                else
                    printAlunoStatus(aln);
                break;
            case 4:
                scanf(" %s", alnNome);
                aln = btSearchBy(bt, alnNome, alnCmpNome);
                if(aln == NULL)
                    printf("Aluno nao encontrado\n");
                else
                    printAlunoStatus(aln);
                break;
            case 5:
                return;
            default:
                printf("Use somente os digitos 1, 2, 3, 4 ou 5.\n");
                break;
        }
    }

    MEMORY_ERROR_LABEL:
    btaFFree(bt, alnFree);
    if(q != NULL)
        slqNFree(q);

    printf("Memory Allocation Error\n");
}