#include "./envReader.c"
#include "./../../inc/shared.h"


FILE* createReport(const unsigned int benchmarkNumber)
{
    char benchmarkFName[sizeof(BENCHMARK_REPORT_FOLDER)+8]; /* nnn.txt = 7 + 1(\0) = 8 */
    benchmarkFName[sizeof(benchmarkFName)] = '\0';
    sprintf(benchmarkFName, "%s/%u", BENCHMARK_FILES_FOLDER, benchmarkNumber);
    return fopen(benchmarkFName, "w");
}


void printReport(FILE* f, double elapsedTime)
{
    if(f == NULL)
        return;

    fprintf(f, "%.4f\n", elapsedTime);
}


void executeBenchmark(Vetor* v, funcaoDeOrdenacao func)
{
    iterator i;
    iterator c;
    FILE* f;

    #ifdef __unix__
    struct timespec start, end;
    #elif _WIN32 || _WIN64
    clock_t start, end;
    #endif

    for(c = 1; loadDotenv(c) == true; c++)
    {
        if(readEnvorimentVriables() == false)
            continue;
     
        f = createReport(c);
        if(f == NULL)
            continue;
            
        if(v->size != gArraySize)
        {
            vetorFree(v);
            vetorBuild(gArraySize);
        }
        vetorFill(v, MAX_ELEMENT_SIZE);

        GET_TIME(start);
        for(i = 0; i < gTimes; i++)
            func(v);
        GET_TIME(end);

        printReport(f, ELAPSED_TIME(start, end));
        fclose(f);
    }
}

