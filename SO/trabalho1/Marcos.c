#pragma once
#define HAVE_STRUCT_TIMESPEC
#define _WINSOCK_DEPRECATED_NO_WARNINGS 1
#define _CRT_SECURE_NO_WARNING 1
#pragma comment(lib, "pthreadVC2.lib")
#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <time.h>
#define MACRO_LINHAS 200
#define MACRO_COLUNAS 200
#define QNT_THREADS 8
#define SEMENTE 42
#define LINHAS 20000
#define COLUNAS 20000

pthread_mutex_t mutex_primos;
pthread_mutex_t mutex_indice;
int qtdPrimos = 0;
int indice = 0;
int** matriz;


// Aloca memória para a matriz
void alocaMatriz() {
    int i;

    if (LINHAS < 1 || COLUNAS < 1) {
        printf("** Erro: quantidade de linhas/colunas invalidas **\n");
        exit(1);
    }

    matriz = malloc(LINHAS * sizeof(int*));

    if (matriz == NULL) {
        printf("** Erro: Memoria Insuficiente para alocar *Matriz **");
        exit(1);
    }

    for (i = 0; i < LINHAS; i++) {
        matriz[i] = malloc(COLUNAS * sizeof(int));
        if (matriz[i] == NULL) {
            printf("** Erro: Memoria Insuficiente para alocar **Matriz **");
            exit(1);
        }
    }
}


int** liberaMatriz() {
    int i;
    if (matriz == NULL) return (NULL);
    if (LINHAS < 1 || COLUNAS < 1) {
        printf("** Erro: Parametro invalido **\n");
        return (matriz);
    }

    for (i = 0; i < LINHAS; i++) free(matriz[i]);
    free(matriz);
    return (NULL);
}


criaMatriz() {
    alocaMatriz();
    srand(SEMENTE);

    for (int i = 0; i < LINHAS; i++) {
        for (int j = 0; j < COLUNAS; j++) {
            matriz[i][j] = rand() % 32000;
        }
    }
}


void printMatriz() {
    for (int i = 0; i < LINHAS; i++) {
        for (int j = 0; j < COLUNAS; j++) {
            printf("%d ", matriz[i][j]);
        }
        printf("\n");
    }
    printf("\n \n");
}


int ehPrimo(int n) {
    if (n <= 1) {
        return 0;
    }
    else if (n == 2) {
        return 1;
    }

    int raiz = sqrt(n);
    for (int i = 2; i <= raiz; i++) {
        if (n % i == 0) {
            return 0;
        }
    }

    return 1;
}


// Realiza a busca serial de primos na matriz
int buscaSerial() {
    int qtdPrimosInterno = 0;
    for (int i = 0; i < LINHAS; i++) {
        for (int j = 0; j < COLUNAS; j++) {
            if (ehPrimo(matriz[i][j]) == 1) {
                qtdPrimosInterno++;
            }
        }
    }
    return qtdPrimosInterno;
}


// Verifica se a quantidade de macroblocos é válida
void verificaMacroblocoAntiga(int* resultado) {
    resultado[0] = 0;
    resultado[1] = 0;
    int num_macro_blocos;

    for (int i = 1; i <= LINHAS; i++) {
        if (LINHAS % i == 0) { // Verificar se i é um divisor válido de linhas
            for (int j = 1; j <= COLUNAS; j++) {
                if (COLUNAS % j == 0) { // Verificar se j é um divisor válido de colunas
                    // num_macro_blocos = (linhas * colunas) / (i * j);
                    num_macro_blocos = i * j;
                    int QNT_MACROBLOCOS = (LINHAS / MACRO_LINHAS) * (COLUNAS / MACRO_COLUNAS);
                    if (num_macro_blocos == QNT_MACROBLOCOS) {
                        resultado[0] = i;
                        resultado[1] = j;
                        return;
                    }
                }
            }
        }
    }
}


void verificaMacrobloco(int* tamanhos) {
    if (LINHAS % MACRO_LINHAS != 0) {
        printf("Tamanho invalido, quantidade de linhas no macrobloco nao compativel com a matriz.\n");
        exit(1);
    }
    if (COLUNAS % MACRO_COLUNAS != 0) {
        printf("Tamanho invalido, quantidade de colunas no macrobloco nao compativel com a matriz.\n");
        exit(1);
    }

    tamanhos[0] = MACRO_LINHAS;
    tamanhos[1] = MACRO_COLUNAS;
}


// Recebe a quantidade de blocos verticais, horizontais, o macrobloco que será percorrido e retorna a quantidade de primos encontrados
int percorrerMacrobloco(int m, int qnt_x_blocs, int qnt_y_blocs) {
    int qtdPrimosInterno = 0;

    int y_heigth = MACRO_LINHAS;
    int x_length = MACRO_COLUNAS;

    for (int i = (m / qnt_x_blocs) * y_heigth; (i < ((m / qnt_x_blocs) * y_heigth) + y_heigth); i++) {
        for (int j = (m % qnt_x_blocs) * x_length; (j < ((m % qnt_x_blocs) * x_length) + x_length); j++) {
            if (ehPrimo(matriz[i][j]) == 1) {
                qtdPrimosInterno++;
            }
        }
    }
    return qtdPrimosInterno;
}


// Função que será executada pelas threads
void* thread_function(void* arg) {
    while (1) {
        int indiceInterno, qntPrimosInterno;

        int* args = (int*)arg;
        int qnt_x_blocs = COLUNAS / MACRO_COLUNAS;
        int qnt_y_blocs = LINHAS / MACRO_LINHAS;
        int QNT_MACROBLOCOS = qnt_x_blocs * qnt_y_blocs;

        pthread_mutex_lock(&mutex_indice);
        if (indice >= QNT_MACROBLOCOS) {
            pthread_mutex_unlock(&mutex_indice);
            break;
        }
        else {
            indiceInterno = indice;
            indice++;
            pthread_mutex_unlock(&mutex_indice);

            qntPrimosInterno = percorrerMacrobloco(indiceInterno, qnt_x_blocs, qnt_y_blocs);

            pthread_mutex_lock(&mutex_primos);
            qtdPrimos += qntPrimosInterno;
            pthread_mutex_unlock(&mutex_primos);
        }
    }
}


void executaOpcao(int opcao) {

    // Variáveis para medir o tempo
    clock_t startS, endS, startP, endP;
    double timeS, timeP;

    // Variaveis usadas nas buscas
    int qtdSerial;
    int resultado[2];
    pthread_t workers[QNT_THREADS];


    // Verifica a opção escolhida
    switch (opcao) {
    case 1: // Busca serial e paralela

        // inicializa mutexes
        pthread_mutex_init(&mutex_primos, NULL);
        pthread_mutex_init(&mutex_indice, NULL);

        // Verifica se o tamanho do macrobloco é válido
        verificaMacrobloco(resultado);

        // Inicializa as threads e mede o tempo da busca paralela
        startP = clock();
        for (int i = 0; i < QNT_THREADS; i++) {
            pthread_create(&workers[i], NULL, thread_function, resultado);
        }

        for (int i = 0; i < QNT_THREADS; i++) {
            pthread_join(workers[i], NULL);
        }
        endP = clock();
        timeP = ((double)(endP - startP)) / CLOCKS_PER_SEC;

        pthread_mutex_destroy(&mutex_primos);
        pthread_mutex_destroy(&mutex_indice);

        // Mede o tempo da busca serial
        startS = clock();
        qtdSerial = buscaSerial();
        endS = clock();
        timeS = ((double)(endS - startS)) / CLOCKS_PER_SEC;


        printf("\nQuantidade de primos em paralelo: %d, em %f seg\n", qtdPrimos, timeP);
        printf("\nQuantidade de primos em serial: %d, em %f seg\n\n", qtdSerial, timeS);
        printf("\nSpeedup = %f\n\n ", timeS / timeP);
        break;

    case 2: // Busca serial

        // Mede o tempo da busca serial
        startS = clock();
        qtdSerial = buscaSerial();
        endS = clock();
        timeS = ((double)(endS - startS)) / CLOCKS_PER_SEC;


        printf("\nQuantidade de primos em serial: %d, em %f seg\n\n", qtdSerial, timeS);

        break;

    case 3: // Busca paralela

        // inicializa mutexes
        pthread_mutex_init(&mutex_primos, NULL);
        pthread_mutex_init(&mutex_indice, NULL);

        // Verifica se a quantidade de macroblocos é válida
        verificaMacrobloco(resultado);

        // Inicializa as threads e mede o tempo da busca paralela
        startP = clock();
        for (int i = 0; i < QNT_THREADS; i++) {
            pthread_create(&workers[i], NULL, thread_function, resultado);
        }

        for (int i = 0; i < QNT_THREADS; i++) {
            pthread_join(workers[i], NULL);
        }
        endP = clock();
        timeP = ((double)(endP - startP)) / CLOCKS_PER_SEC;

        pthread_mutex_destroy(&mutex_primos);
        pthread_mutex_destroy(&mutex_indice);


        printf("\nQuantidade de primos em paralelo: %d, em %f seg\n\n", qtdPrimos, timeP);

        break;

    case 4: // Sair

        liberaMatriz();
        exit(0);
        break;

    default:
        printf("Opcao invalida\n");
        break;
    }
}

int main(void) {
    criaMatriz();

    // Menu
    int opcao = 0;
    while (opcao != 5) {
        printf("---------- Menu ----------\n");
        printf("1 - Busca serial e paralela\n");
        printf("2 - Busca serial\n");
        printf("3 - Busca paralela\n");
        printf("4 - Sair \n");

        printf("Digite a opcao desejada: ");
        scanf_s("%d", &opcao);
        printf("--------------------------\n");
        executaOpcao(opcao);
    }

    return 0;
}
