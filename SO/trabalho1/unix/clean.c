#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <math.h>  /* sqrt :p */
#include <pthread.h>

#ifdef __unix__
#include <unistd.h>
#define GET_TIME(variable) (clock_gettime(CLOCK_MONOTONIC, &variable))
#define TIME(start, end) ((double)((end.tv_nsec)-(start.tv_nsec)) / 10000000.0)
#define ELAPSED_TIME(start, end) TIME(start, end) < 0 ? -TIME(start, end) : TIME(start, end)
#elif _WIN32 || _WIN64
#include <windows.h>
#define GET_TIME(variable) ((variable) = clock())
#define ELAPSED_TIME(start, end) (((double)((end)-(start))) / CLOCKS_PER_SEC)
#endif

/* Alayas para uma semântica mais bonitinha */
typedef enum {false, true, neither, both, maybe, falseish, trueish, itDepends, ocillating, itsComplicated, doubleTrue, possible, probaly, whyYouContinueReadingThis} megabool;
typedef enum {correctFill, matrixNull, iLessThanOne, jLessThanOne, someVectorNull} matrixFillErros;
typedef enum {empty, sSearch, pSearch, quit} menuOptions;

typedef char* string;
typedef int** matrix;
typedef int* vector;
typedef unsigned int matrixElement;

typedef long unsigned int iterator;

/* Macros de Configuração */
/* no linux a seed 1 e a seed 0 são, aparentemente, a mesma, então cuidado :p */
#define SEED 52
#define NUMERO_DE_COLUNAS 10000
#define NUMERO_DE_LINHAS 10000
#define MACRO_BLOCO_NUMERO_DE_COLUNAS 50
#define MACRO_BLOCO_NUMERO_DE_LINHAS 50
#define MAX_MATRIX_ELEMENT_SIZE 65535 + 1
#define NUMERO_THREADS 3    

/* Macros Úteis */
#define ARREDONDAR_PARA_BAIXO(n) ((int)n)
#define MATRIX_ELEMENT (rand() % MAX_MATRIX_ELEMENT_SIZE)

/* Variáveis Globais */
matrix m = NULL;
unsigned int tPrimos = 0;
int quantidade_macrobloco = 0;
pthread_mutex_t mutex;
unsigned int M;


/* Funções para o trabalho. Documentação no arquivo unix.h */
megabool ehPrimo(const unsigned int n)
{
    if(n == 2) /* por definição, 2 é um número primo */
        return true;

    if(n <= 1) /* por definição, 0, 1 e múltiplos de 2 não são números primos */
        return false;

    unsigned int flag = ARREDONDAR_PARA_BAIXO(sqrt(n));
    
    for(iterator i = 2; i <= flag; i++) /* verifica se o n é múltiplo de cada número até a sua raiz quadrada; utilizou-se o type cast para forçar a utilização da ALU ao invés da FPU */
    {
        if(n%i == 0) /* por definição, um número x é múltiplo de um número n quando o resto da divisão de x por y é 0; essa verificação pode ser otimizada como !n%i, mas preferiu-se a notação em questão por questões de legibilidade e garantir que o código funcione em múltiplos compiladores.  */
            return false;
    }

    return true;
}


matrix matrixAlloc()
{
    m = (matrix)malloc(sizeof(vector)*NUMERO_DE_LINHAS);
    if(m == NULL)  /* Erro na alocação, memória insuficiente */
        return NULL;
    for(register iterator c = 0; c < NUMERO_DE_LINHAS; c++)
    {
        m[c] = (vector)malloc(sizeof(matrixElement)*NUMERO_DE_COLUNAS);

        if(m[c] == NULL) /* Verifica se cada coluna foi alocada corretamente e, caso não tenha sido, libera a memória de cada coluna até então alocada e da matriz inteira */
        {
            printf("%lu\n", c);
            for(c; c > 0; c--)
                { free(m[c]); }
            free(m[0]); /* Para antes do 0, pois c não pode ser negativo */
            free(m);
            m = NULL;
            return NULL;
        }
    }

    return m;
}


void matrixDealloc()
{
    if(m == NULL) /* verifica se a matriz não é nula */
        return;

    for(register iterator c = 0; c < NUMERO_DE_LINHAS; c++)
    {
        if(m[c] != NULL)  /* verifica se cada linha é nula e, caso não seja, desaloca a linha */
            free(m[c]);
    }
    free(m);
    m = NULL;
}


matrixFillErros matrixFill()
{  
    if(m == NULL)
        return matrixNull;
    if(NUMERO_DE_LINHAS < 1)
        return iLessThanOne;
    if(NUMERO_DE_COLUNAS < 1)
        return jLessThanOne;

    srand(SEED);

    for(iterator c = 0; c < NUMERO_DE_LINHAS; c++) 
    {
        if(m[c] == NULL)
        {
            return someVectorNull;
        }
        for(register iterator l = 0; l < NUMERO_DE_COLUNAS; l++)
            {  m[c][l] = MATRIX_ELEMENT; }
    }

    return correctFill;
}


void serialSearch()
{
    for(register iterator i = 0; i < NUMERO_DE_COLUNAS; i++)
    {
        for(register iterator j = 0; j < NUMERO_DE_COLUNAS; j++)
            { if(ehPrimo(m[i][j])) { tPrimos++; } }
    }
}


void* parallelSearch(void* s)
{
    unsigned int block, row, col;
    unsigned int cIP = 0; /* contador interno de primos */

    START:
    pthread_mutex_lock(&mutex);
    if(quantidade_macrobloco <= 0) { goto END; }
    quantidade_macrobloco--;
    block = quantidade_macrobloco;
    pthread_mutex_unlock(&mutex);

    col = (((block % M) + 1) * MACRO_BLOCO_NUMERO_DE_COLUNAS);
    row = (((block / M) + 1) * MACRO_BLOCO_NUMERO_DE_LINHAS);

    for(register iterator i = row - MACRO_BLOCO_NUMERO_DE_LINHAS; i < row; i++)
    {
        for(register iterator j = col - MACRO_BLOCO_NUMERO_DE_COLUNAS; j < col; j++)
        {
            if(ehPrimo(m[i][j]))
                cIP++;
        }
    }
    goto START;

    END:
    tPrimos += cIP;
    pthread_mutex_unlock(&mutex);
    
    pthread_exit(0);
}


/* MENU */
void mSerialSearch()
{
    #ifdef __unix__
    struct timespec start, end;
    #elif _WIN32 || _WIN64
    clock_t start, end;
    #endif

    if(m == NULL)
    {
        printf("Atenção, você não pode realizar essa operação sem alocar a memória para matriz antes.");
        return;
    }

    tPrimos = 0;

    printf("Iniciando Busca");
    GET_TIME(start);
    serialSearch();
    GET_TIME(end);

    printf("\nBusca serial concluída.\nTotal de Primos: %u\nTempo decorrido: %.4lf", tPrimos, ELAPSED_TIME(start, end));
}


void mParallelSearch()
{
    #ifdef __unix__
    struct timespec start, end;
    #elif _WIN32 || _WIN64
    clock_t start, end;
    #endif

    double time_controller; /* Salva o tempo de execução do código com 1 Thread */
    pthread_t worker;
    pthread_t workers[NUMERO_THREADS];
    
    if(m == NULL)
    {
        printf("Atenção, você não pode realizar essa operação sem alocar a memória para matriz antes.");
        return;
    }

    tPrimos = 0;
    quantidade_macrobloco = (NUMERO_DE_COLUNAS / MACRO_BLOCO_NUMERO_DE_COLUNAS) * (NUMERO_DE_LINHAS / MACRO_BLOCO_NUMERO_DE_LINHAS);
    M = NUMERO_DE_COLUNAS / MACRO_BLOCO_NUMERO_DE_COLUNAS;

    printf("\nIniciando busca com uma Thread");
    GET_TIME(start);
    pthread_create(&worker, NULL, parallelSearch, NULL);
    pthread_join(worker, NULL);
    GET_TIME(end);
    time_controller = ELAPSED_TIME(start, end);

    printf("\nPreparando busca com Múltiplas Threads");
    tPrimos = 0;
    quantidade_macrobloco = (NUMERO_DE_COLUNAS / MACRO_BLOCO_NUMERO_DE_COLUNAS) * (NUMERO_DE_LINHAS / MACRO_BLOCO_NUMERO_DE_LINHAS);

    GET_TIME(start);
    
    for(register iterator i = 0; i < NUMERO_THREADS; i++)
        { pthread_create(&workers[i], NULL, parallelSearch, NULL); }

    printf("\nTrabalhadores prontos, iniciando busca paralela.");

    for(register iterator i = 0; i < NUMERO_THREADS; i++)
        { pthread_join(workers[i], NULL); }
    
    GET_TIME(end);

    printf("\nBusca paralela encerrada.\nTotal de Primos: %u\nSpeed Up: %.4lf\nTempo com 1 Thread: %.4lf\nTempo no MultiThread  %.4lf", tPrimos, time_controller/ (ELAPSED_TIME(start, end)), time_controller, ELAPSED_TIME(start, end));
}


void menu()
{
    int input;
    #ifdef __unix__
    struct timespec start, end;
    #elif _WIN32 || _WIN64
    clock_t start, end;
    #endif

    printf("\nMenu: \n[1] - Busca Serial;\n[2] - Busca Paralela;\n[3] - Sair");

    while (true)
    {        
        printf("\n");
        scanf("%d", &input);

        switch (input)
        {
        case sSearch:
            mSerialSearch();
            break;
        case pSearch:
            mParallelSearch();
            break;
        case quit:
            return;
        default:
            printf("\nCódigo não definido.");
            break;
        }
    }
}


int main(int agrc, char* argv[])
{
    pthread_mutex_init(&mutex, NULL);

    if(matrixAlloc() == NULL)
    {
        printf("\nMemória insuficiente para alocar a matriz.");
        return 1;
    }
    else if(matrixFill() != correctFill)
    {
        printf("\nErro ao preencher a matriz");
        return 1;
    }
    else
        printf("\nMatriz pronta para uso");

    menu(); /* Loop de execução está aqui dentro */
    
    if(m != NULL)
        matrixDealloc();

    return 0;
}

