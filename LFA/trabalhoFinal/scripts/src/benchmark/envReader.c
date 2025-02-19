#include "../../inc/shared.h"
#include "./envReader.h"



bool strcomp(string s, const string o)
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
    register iterator i;
    string s = (string)malloc(sizeof(char)*(size+1));
    if(s == NULL)
        return NULL;

    for(i = 0; i < size; i++)
        { s[i] = src[i+start]; }
    s[size] = '\0';

    return s;
}


void strRemoveBrkl(string src)
{
    register iterator i;
    for(i = 0; src[i] != '\n'; i++)
    {
        if(src[i] == '\n')
        {
            src[i] = '\0';
            return;
        }
    }
}


bool loadDotenv(const unsigned int envNumber)
{    
    FILE* f;
    char envfile[32];
    char line[256];
    unsigned int equalsSing;
    string key;
    string value;
    int i;

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

            fprintf(f, "ARRAY_SIZE=10000000\nTHREADS_NUMBER=1\nTIMES=1");
        }
    }
    else
    {
        if(envNumber > 999)
        {
            printf("Limite de benchmarks atingido: 999");
            return false;
        }
        envfile[sizeof(envfile)] = '\0';
        sprintf(envfile, "%s/%d.env", BENCHMARK_FILES_FOLDER, envNumber);

        f = fopen(envfile, "r");
        if(f == NULL)
        {
            printf("Atenção, o benchmar número %d não existe.", envNumber);
            return false;
        }
    }

    while(true)
    {
        if(line == NULL)
            break;
        if(sizeof(line) < 256)
            break;
        if(f == NULL)
            break;
        if(fgets(line, sizeof(line), f) == NULL)
            break;

        if(line[0] == '\0' || line[0] == '\n' || line[0] == '#')
            continue;

        strRemoveBrkl(line);
        equalsSing = strFindChar(line, '=');
        key = strSplit(line, 0, equalsSing);
        value = strSplit(line, equalsSing+1, strFindChar(line, '\n')+1);

        if(key == NULL || value == NULL)
        {
            fclose(f);
            printf("ERRO FATAL, arquivo .env formatado incorretamente");
            return false;
        }

        setenv(key, value, true);
    }   

    fclose(f);
    return 1;
}


bool readEnvorimentVriables()
{
    string _seed = getenv("SEED");
    unsigned int aux;

    if(strcomp(_seed, "default"))
        srand(1);
    if(strcomp(_seed, "random"))
        srand(time(NULL));
    else
    {
        if(atoi(_seed) == 0)
            goto ATOI_ERROR;
        srand(atoi(_seed));
    }

    aux = atoi(getenv("ARRAY_SIZE"));
    if(aux == 0)
        goto ATOI_ERROR;
    if(gArraySize != aux)
        gArraySize = aux;
    
    aux = atoi(getenv("THREADS_NUMBER"));
    if(aux == 0)
        goto ATOI_ERROR;
    if(gThreadsNumber != aux)
        gThreadsNumber = aux;

    aux = atoi(getenv("TIMES"));
    if(aux == 0)
        goto ATOI_ERROR;
    if(gTimes != aux)
        gTimes = aux;

    return true;

    ATOI_ERROR:
        return false;
}