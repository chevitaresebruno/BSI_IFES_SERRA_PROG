#include "./envReader.c"
#include "./../../inc/shared.h"


FILE* createReport(const unsigned int benchmarkNumber)
{
    char benchmarkFName[sizeof(BENCHMARK_REPORT_FOLDER)+8]; /* nnn.txt = 7 + 1(\0) = 8 */
    benchmarkFName[sizeof(benchmarkFName)] = '\0';
    sprintf(benchmarkFName, "%s/%u.txt", BENCHMARK_REPORT_FOLDER, benchmarkNumber);
    return fopen(benchmarkFName, "w");
}


void printReport(FILE* f, double elapsedTime)
{
    if(f == NULL)
        return;

    fprintf(f, "%.4f\n", elapsedTime);
}


void executeBenchmark(Vetor* v, funcaoDeOrdenacao sort)
{
    iterator i;
    iterator c = 1;
    FILE* f;

    #ifdef __unix__
    struct timespec start, end;
    #elif _WIN32 || _WIN64
    clock_t start, end;
    #endif

    while(loadDotenv(c) == true)
    {
        if(readEnvorimentVriables() == false)
                continue;
         
        f = createReport(c);
        if(f == NULL)
            continue;

        if(v->size != gArraySize)
        {
            vetorFree(v);
            v = vetorBuild(gArraySize);
        }

        for(c = 1; c < gTimes; c++)
        {
    
            srand(gSeed);
            vetorFill(v, MAX_ELEMENT_SIZE);
    
            GET_TIME(start);
            sort(v);
            GET_TIME(end);
    
            printReport(f, ELAPSED_TIME(start, end));
        }

        fclose(f);
    }
}

