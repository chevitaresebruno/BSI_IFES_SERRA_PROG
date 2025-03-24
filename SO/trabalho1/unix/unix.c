#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <math.h>  /* sqrt :p */
#include <pthread.h>

#ifdef __unix__
#include <unistd.h>
#define DIRETORIO_RELATORIOS "relatorios/linux/%d.txt"
#define TAMANHO_VETOR_NOME_RELATORIO 25
#define GET_TIME(variable) (clock_gettime(CLOCK_MONOTONIC, &variable))
#define TIME(start, end) ((double)((end.tv_nsec)-(start.tv_nsec)) / 100000000.0)
#define ELAPSED_TIME(start, end) TIME(start, end) < 0 ? -TIME(start, end) : TIME(start, end)
#define CORES_NUMBER sysconf(_SC_NPROCESSORS_ONLN)
#elif _WIN32 || _WIN64
#include <windows.h>
#define DIRETORIO_RELATORIOS "relatorios/windows/%d.txt"
#define TAMANHO_VETOR_NOME_RELATORIO 27
#define GET_TIME(variable) ((variable) = clock())
#define ELAPSED_TIME(start, end) ((double)((end)-(start)) / CLOCKS_PER_SEC)
SYSTEM_INFO sysinfo;
#define CORES_NUMBER sysinfo.dwNumberOfProcessors
#endif


/* Alayas para uma semântica mais bonitinha */
typedef enum {false, true, neither, both, maybe, falseish, trueish, itDepends, ocillating, itsComplicated, doubleTrue, possible, probaly, whyYouContinueReadingThis} megabool;
typedef enum {correctFill, matrixNull, iLessThanOne, jLessThanOne, someVectorNull} matrixFillErros;
typedef enum {readEnvorimentFile, allocMatrixMemory, deallocMatrixMemory, fillMatrix, saveMatrix, readMatrix, sSearch, pSearch, benchmark, pSearchSpeedUp, quit} menuOptions;

typedef char* string;
typedef int** matrix;
typedef int* vector;
typedef unsigned int matrixElement;

typedef long unsigned int iterator;
typedef long signed int siterator;

/* Macros Úteis */
#define ARREDONDAR_PARA_BAIXO(n) ((int)n)
#define MATRIX_ELEMENT (rand() % gMatrixElemMaxN)
#define MAX_LINE_LENGHT 1024

/* Macros de Configuração */
/* no linux a seed 1 e a seed 0 são, aparentemente, a mesma, então cuidado :p */
#define SEED 1
#define NUMERO_DE_COLUNAS 20000
#define NUMERO_DE_LINHAS 20000
#define MACRO_BLOCO_NUMERO_DE_COLUNAS 13
#define MACRO_BLOCO_NUMERO_DE_LINHAS 13
#define MAX_MATRIX_ELEMENT_SIZE 65535 + 1
#define MATRIX_ELEMENT (rand() % MAX_MATRIX_ELEMENT_SIZE)
#define NUM_THREADS 6

/* Variáveis Globais */
matrix m = NULL;
long unsigned int tPrimos = 0;
long unsigned int macrobloco = 0;
pthread_mutex_t mutex;

/* Variáveis Globais de Configuração */
long unsigned int gMatrixRows;
long unsigned int gMatrixColumns;
long unsigned int gMblockRows;
long unsigned int gMblockColumns;
long unsigned int gMatrixElemMaxN;
long unsigned int gThreadsNumber;
unsigned int gTimes;
unsigned int gSerialSearch;
unsigned int gSeed;

/* Para a definição de ambiente */
/* Eu não ia importar a string.h para uma função tão simples assim :p */
megabool strcomp(string s, const string o)
{
    register iterator i = 0;

    while(s[i] != '\0')
    {
        if(s[i] != o[i])
            return false;
        i++;
    }
    
    return true;
}


unsigned int strFindChar(const string l, const char c)
{
    register unsigned int i = 0;

    while(l[i] != c && l[i] != '\0')
        { i++; }

    return i;
}


string strSplit(const string src, const unsigned int start, const unsigned int end)
{
    unsigned int size = end-start;
    string s = (string)malloc(sizeof(char)*(size+1));
    if(s == NULL)
        return NULL;

    for(register iterator i = 0; i < size; i++)
        { s[i] = src[i+start]; }
    s[size] = '\0';

    return s;
}


void strRemoveBrkl(string src)
{
    for(register iterator i = 0; src[i] != '\n'; i++)
    {
        if(src[i] == '\n')
        {
            src[i] = '\0';
            return;
        }
    }
}

/* Se der tempo eu explico essa bomba */
megabool loadDotenv(const unsigned int envNumber)
{    
    FILE* f;

    if(envNumber == 0)
    {
        f = fopen("./.env", "r");
        if(f == NULL)
        {
            printf("ERRO FATAL, arquivo de ambiente não identificado");
            f = fopen("./.env", "w+");
            if(f == NULL)
            {
                printf("ERRO FATAL, PERMISSÃO PARA CRIAÇÂO DO ARQUIVO .env NEGADO");
                return false;
            }

            fprintf(f, "SEED=1\nLINHAS_DA_MATRIZ=10000\nCOLUNAS_DA_MATRIZ=10000\nLINHAS_MACROBLOCO=1");
            fprintf(f, "\nCOLUNAS_MACROBLOCO=1\nTAMANHO_MAXIMO_ELEMENTO_MATRIX=65536\nNUMERO_DE_THREADS=1\nTIMES=30\nSERIAL_SEARCH=1");
        }
    }
    else
    {
        if(envNumber < 0)
        {
            printf("O número do .env não pode ser menor do que 0");
            return false;
        }
        if(envNumber > 999)
        {
            printf("Limite de benchmarks atingido: 999");
            return false;
        }

        char envfile[20];
        envfile[sizeof(envfile)] = '\0';
        sprintf(envfile, "./benchmark/%d.env", envNumber);

        f = fopen(envfile, "r");
        if(f == NULL)
        {
            printf("Atenção, o benchmar número %d não existe.", envNumber);
            return itsComplicated;
        }
    }

    char line[256];
    while (fgets(line, sizeof(line), f) != NULL)
    {
        if(line[0] == '\0' || line[0] == '\n' || line[0] == '#')
            continue;
        
        strRemoveBrkl(line);
        unsigned int equalsSing = strFindChar(line, '=');
        string key = strSplit(line, 0, equalsSing);
        string value = strSplit(line, equalsSing+1, strFindChar(line, '\n')+1);

        if(key == NULL || value == NULL)
        {
            fclose(f);
            printf("ERRO FATAL, arquivo .env formatado incorretamente");
            return false;
        }

        setenv(key, value, true);
    }   

    fclose(f);
    return true;
}


megabool readEnvorimentVriables()
{
    string _seed = getenv("SEED");
    if(strcomp(_seed, "default"))
        gSeed = 1;
    if(strcomp(_seed, "random"))
        gSeed = time(NULL);
    else
    {
        if(atoi(_seed) == 0)
            goto ATOI_ERROR;
        gSeed = atoi(_seed);
    }

    srand(gSeed);

    gMatrixRows = atoi(getenv("LINHAS_DA_MATRIZ"));
    if(gMatrixRows == 0)
        goto ATOI_ERROR;
    
    gMatrixColumns = atoi(getenv("COLUNAS_DA_MATRIZ"));
    if(gMatrixColumns == 0)
        goto ATOI_ERROR;
    
    gMblockRows = atoi(getenv("LINHAS_MACROBLOCO"));
    if(gMblockRows == 0)
        goto ATOI_ERROR;
    
    gMblockColumns = atoi(getenv("COLUNAS_MACROBLOCO"));
    if(gMblockColumns == 0)
        goto ATOI_ERROR;
    
    gMatrixElemMaxN = atoi(getenv("TAMANHO_MAXIMO_ELEMENTO_MATRIX"));
    if(gMatrixElemMaxN == 0)
        goto ATOI_ERROR;
    
    gThreadsNumber = atoi(getenv("NUMERO_DE_THREADS"));
    if(gThreadsNumber == 0)
        goto ATOI_ERROR;
    
    gTimes = atoi(getenv("TIMES"));
    if(gTimes == 0)
        goto ATOI_ERROR;

    gSerialSearch = atoi(getenv("SERIAL_SEARCH"));

    /*
    Exibir variáveis lidas
    printf("%d, %d, %d, %d, %d, %d", atoi(_seed), gMatrixRows, gMatrixColumns, gMblockRows, gMblockColumns, gMatrixElemMaxN, gThreadsNumber);
    */
    printf("Arquivo de ambiente lido com sucesso.");
    return true;

    ATOI_ERROR:
        printf("Oh sua porta, o que eu disse sobre escrever número menores ou iguais a 0 e sobre texto?");
        return false;
}


/* Funções para o trabalho. Documentação no arquivo unix.h */
megabool ehPrimo(const unsigned int n)
{
    if(n == 2) /* por definição, 2 é um número primo */
        return true;

    if(n <= 1) /* por definição, 0, 1 e múltiplos de 2 não são números primos */
        return false;

    unsigned int flag = ARREDONDAR_PARA_BAIXO(sqrt(n));
    
    for(register iterator i = 2; i <= flag; i+=2) /* verifica se o n é múltiplo de cada número até a sua raiz quadrada; utilizou-se o type cast para forçar a utilização da ALU ao invés da FPU */
    {
        if(n%i == 0) /* por definição, um número x é múltiplo de um número n quando o resto da divisão de x por y é 0; essa verificação pode ser otimizada como !n%i, mas preferiu-se a notação em questão por questões de legibilidade e garantir que o código funcione em múltiplos compiladores.  */
            return false;
    }

    return true;
}


matrix matrixAlloc()
{
    m = (matrix)malloc(sizeof(vector)*gMatrixRows);
    if(m == NULL)  /* Erro na alocação, memória insuficiente */
        return NULL;
    for(register iterator c = 0; c < gMatrixRows; c++)
    {
        m[c] = (vector)malloc(sizeof(matrixElement)*gMatrixColumns);

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

    for(register iterator c = 0; c < gMatrixRows; c++)
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
    if(gMatrixRows < 1)
        return iLessThanOne;
    if(gMatrixColumns < 1)
        return jLessThanOne;

    srand(gSeed);

    for(iterator c = 0; c < gMatrixRows; c++) 
    {
        if(m[c] == NULL)
        {
            gMatrixRows = c;
            return someVectorNull;
        }
        for(register iterator l = 0; l < gMatrixColumns; l++)
            {  m[c][l] = MATRIX_ELEMENT; }
    }

    return correctFill;
}


void matrixSaveInFile()
{
    // Abrindo o arquivo "matriz.txt" para escrita
    FILE *arquivo = fopen("matriz.txt", "w");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo para escrita.\n");
        return;
    }

    // Percorrendo a matriz e escrevendo no arquivo
    for (int i = 0; i < gMatrixRows; i++) {
        for (int j = 0; j < gMatrixColumns; j++) {
            fprintf(arquivo, "%d ", m[i][j]);
        }
        fprintf(arquivo, "\n"); // Nova linha para cada linha da matriz
    }

    // Fechando o arquivo
    fclose(arquivo);
    printf("Matriz salva no arquivo matriz.txt com sucesso.\n");
}


void matrixReadFromFile()
{
    // Abrindo o arquivo "matriz.txt" para leitura
    FILE *arquivo = fopen("matriz.txt", "r");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo para leitura.\n");
        return;
    }

    // Lendo os dados do arquivo e preenchendo a matriz
    for (int i = 0; i < gMatrixRows; i++) {
        for (int j = 0; j < gMatrixColumns; j++) {
            fscanf(arquivo, "%d", &m[i][j]);
        }
    }

    // Fechando o arquivo
    fclose(arquivo);
    printf("Matriz lida do arquivo matriz.txt com sucesso.\n");
}


void serialSearch()
{
    for(register iterator i = 0; i < gMatrixColumns; i++)
    {
        for(register iterator j = 0; j < gMatrixColumns; j++)
            { if(ehPrimo(m[i][j])) { tPrimos++; } }
    }
}


void* parallelSearch(void* s)
{
    unsigned int block, row, col;
    unsigned int cIP = 0; /* contador interno de primos */

    unsigned int N, M;
    N = gMatrixRows / gMblockRows;
    M = gMatrixColumns / gMblockColumns;

    START:
    pthread_mutex_lock(&mutex);
    if(macrobloco <= 0) { goto END; }
    macrobloco--;
    block = macrobloco;
    pthread_mutex_unlock(&mutex);

    col = (((block % M) + 1) * gMblockColumns) - 1;
    row = (((block % M) + 1) * gMblockRows) - 1;

    int aux1 = row - gMblockRows;
    int aux2 = col - gMblockColumns;


    for(register long int i = row; i > aux1; i--)
    {
        for(register int j = col; j > aux2; j--)
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
void mMatrixAlloc()
{
    if(m != NULL)
        matrixDealloc();

    if(matrixAlloc() == NULL)
    {
        printf("Memória insuficiente para alocar a matriz");
        return;
    }

    printf("Matriz criada com sucesso");
}


void mMatrixDealloc()
{
    if(m == NULL)
    {
        printf("Atenção, você não pode desalocar a memória sem tê-lá alocada anteriormente");
        return;
    }

    matrixDealloc();
    printf("Memória da matriz liberada com sucesso");
}


void mFillMatrix()
{
    if(m == NULL)
        m = matrixAlloc();

    if(m == NULL)
    {
        printf("Memória insuficiente para alocar a matriz");
        return;
    }

    switch (matrixFill())
    {
    case correctFill:
        printf("Matriz preenchida com sucesso");
        break;
    case matrixNull:
        printf("ERRO: matriz nula, esse erro não deveria ocorrer, depure o código");
        break;
    case iLessThanOne:
        printf("ERRO: o valor fornecido para número de linhas deve ser maior ou igual a 1");
        break;
    case jLessThanOne:
        printf("ERRO: o valor fornecido para número de linhas deve ser maior ou igual a 1");
        break;
    case someVectorNull:
        printf("ERRO: memória insuficiente para alocar a linha %lu", gMatrixRows);
        gMatrixRows = atoi(getenv("LINHAS_DA_MATRIZ"));
        break;
    default:
        printf("ERRO: erro indeterminado, depure o código para compreender o erro.");
        break;
    }
}


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

    printf("\nBusca serial concluída.\nTotal de Primos: %lu\nTempo decorrido: %.4f", tPrimos, ELAPSED_TIME(start, end));
}


void mParallelSearch()
{
    #ifdef __unix__
    struct timespec start, end;
    #elif _WIN32 || _WIN64
    clock_t start, end;
    #endif

    GET_TIME(start);
    
    if(m == NULL)
    {
        printf("Atenção, você não pode realizar essa operação sem alocar a memória para matriz antes.");
        return;
    }

    tPrimos = 0;
    macrobloco = (gMatrixColumns / gMblockColumns) * (gMatrixRows / gMblockRows);

    pthread_t workers[gThreadsNumber];

    for(register iterator i = 0; i < gThreadsNumber; i++)
        { pthread_create(&workers[i], NULL, parallelSearch, NULL); }

    printf("\nTrabalhadores prontos, iniciando busca paralela.");

    for(register iterator i = 0; i < gThreadsNumber; i++)
        { pthread_join(workers[i], NULL); }
    
    GET_TIME(end);

    printf("Busca paralela encerrada.\nTotal de Primos: %lu\nTempo decorrido: %.4f", tPrimos, ELAPSED_TIME(start, end));
}


void mReadEnvorimentFile()
{
    if(loadDotenv(0) == false)
    {
        printf("Erro ao carregar o arquivo padrão");
        return;   
    }
    if(m != NULL)
    {
        matrixDealloc();
        printf("Matriz Desalocada\n");
    }
    if(readEnvorimentVriables() == false)
    {
        printf("Erro ao ler as informações do arquivo padrão");
        return;
    }
}


void execBenchmark()
{
    register int i = 1;

    #ifdef __unix__
    struct timespec start, end;
    #elif _WIN32 || _WIN64
    clock_t start, end;
    #endif

    while (loadDotenv(i) != false)
    {
        printf("\nBENCHMARK %d\n", i);
        if(gMatrixRows != atoi(getenv("LINHAS_DA_MATRIZ")) || gMatrixColumns != atoi(getenv("COLUNAS_DA_MATRIZ")) || gSeed != atoi(getenv("SEED")) || gMatrixElemMaxN != atoi("NUMERO_DE_THREADS"))
        {
            matrixDealloc();
            readEnvorimentVriables();
            if(matrixAlloc() == NULL)
            {
                ERROR_LABEL:
                printf("Benchmark %d interrompido. Memória insuficiente.", i);
                i++;
                continue;
            }
            if(matrixFill() != correctFill)
            {
                printf("Correct fill");
                goto ERROR_LABEL;
            }
        }
        else 
            { readEnvorimentVriables(); }

        char benchmarkFName[TAMANHO_VETOR_NOME_RELATORIO];
        benchmarkFName[sizeof(benchmarkFName)] = '\0';
        sprintf(benchmarkFName, DIRETORIO_RELATORIOS, i);
        FILE* f = fopen(benchmarkFName, "w");

        for(register iterator j = 0; j < gTimes; j++)
        {
            if(gSerialSearch == 1)
            {
                tPrimos = 0;
                GET_TIME(start);
                serialSearch();
                GET_TIME(end);
            }
            else
            {
                tPrimos = 0;
                macrobloco = (gMatrixColumns / gMblockColumns) * (gMatrixRows / gMblockRows);

                pthread_t workers[gThreadsNumber];

                GET_TIME(start);
                for(register iterator k = 0; k < gThreadsNumber; k++)
                    { pthread_create(&workers[k], NULL, parallelSearch, NULL); }

                for(register iterator k = 0; k < gThreadsNumber; k++)
                    { pthread_join(workers[k], NULL); }
                GET_TIME(end);
            }

            fprintf(f, "%f\n", ELAPSED_TIME(start, end));
        }

        fclose(f);
        i++;
    }

    printf("\nBenchmark executado com sucesso!");   
}

void mParallelSearchSpeedUp()
{
    gThreadsNumber = 1;
    long int coresNmber = CORES_NUMBER;
    #ifdef __unix__
    struct timespec start, end;
    #elif _WIN32 || _WIN64
    clock_t start, end;
    #endif

    double baseSpeed;

    do
    {
        tPrimos = 0;
        macrobloco = (gMatrixColumns / gMblockColumns) * (gMatrixRows / gMblockRows);

        pthread_t workers[gThreadsNumber];

        GET_TIME(start);
        for(register iterator k = 0; k < gThreadsNumber; k++)
            { pthread_create(&workers[k], NULL, parallelSearch, NULL); }

        for(register iterator k = 0; k < gThreadsNumber; k++)
            { pthread_join(workers[k], NULL); }
        GET_TIME(end);

        if(gThreadsNumber == 1)
        {
            baseSpeed = ELAPSED_TIME(start, end);
            printf("\n1 - TEMPO: %.2lf", baseSpeed);
        }
        else
            printf("\n%lu - TEMPO %.2lf; SPEED UP: %.2lf", gThreadsNumber, ELAPSED_TIME(start, end), baseSpeed/ELAPSED_TIME(start, end));
        gThreadsNumber++;
    }
    while(gThreadsNumber <= coresNmber);
    gThreadsNumber = atoi(getenv("NUMERO_DE_THREADS"));
}


void menu()
{
    int input;
    #ifdef __unix__
    struct timespec start, end;
    #elif _WIN32 || _WIN64
    clock_t start, end;
    #endif
    printf("\nMenu: \n[0] - Ler .env;\n[1] - Alocar Memória para a Matriz;\n[2] - Liberar Memória da Matriz;\n[3] - Construir Matriz;\n[4] - Salvar Matrix;\n[5] - Ler Matrix;\n[6] - Busca Serial;\n[7] - Busca Paralela;\n[8] - Realizar Benchmark\n[9] - Busca Parallela com SpeedUp\n[10] - Sair");

    while (true)
    {        
        printf("\n");
        scanf("%d", &input);

        switch (input)
        {
        case readEnvorimentFile:
            mReadEnvorimentFile();
            break;
        case allocMatrixMemory:
            mMatrixAlloc();
            break;
        case deallocMatrixMemory:
            mMatrixDealloc();
            break;
        case fillMatrix:
            mFillMatrix();
            break;
        case saveMatrix:
            matrixSaveInFile();
            break;
        case readMatrix:
            matrixReadFromFile();
            break;
        case sSearch:
            mSerialSearch();
            break;
        case pSearch:
            mParallelSearch();
            break;
        case benchmark:
            execBenchmark();
            break;
        case pSearchSpeedUp:
            mParallelSearchSpeedUp();
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
    #ifdef _WIN32
    GetSystemInfo(&sysinfo);
    #elif _WIN64
    GetSystemInfo(&sysinfo);
    #endif

    if(loadDotenv(0) == false)
    {
        printf("ERRO AO CARREGAR AQUIVO PADRÃO");
        return false;
    }

    if(readEnvorimentVriables() == false)
        return 1;

    pthread_mutex_init(&mutex, NULL);

    if(matrixAlloc() == NULL)
        printf("\nMemória insuficiente para alocar a matriz.");
    else if(matrixFill() != correctFill)
    {
        printf("\nErro ao preencher a matriz");
        gMatrixRows = atoi(getenv("LINHAS_DA_MATRIZ"));
    }
    else
        printf("\nMatriz pronta para uso");

    menu(); /* Loop de execução está aqui dentro */
    
    if(m != NULL)
        matrixDealloc();

    return 0;
}

