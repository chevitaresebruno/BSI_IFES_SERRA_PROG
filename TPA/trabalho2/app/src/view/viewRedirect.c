#include <stdio.h>
#include "./testView.c"
#include "./mainView.c"

void viewRedirect()
{
    int opt;

    printf("Selecione uma das opcoes:\n\t1 - View Principal;\n\t2 - View de Teste Pre Pronto; e\n\t3 - Sair.\n");

    while(1)
    {
        scanf(" %d", &opt);

        switch (opt)
        {
        case 1:
            mainView();
            break;
        case 2:
            testView();
            break;
        case 3:
            return;
        default:
            printf("\nUse somente os digitos 1, 2 e 3.\n");
            break;
        }
    }
}