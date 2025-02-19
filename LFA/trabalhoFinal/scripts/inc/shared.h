#ifndef SHARED_H 
#define SHARED_H 1 
 
#include <stdlib.h> 
#include <stdio.h>

#ifdef __unix__
#include <unistd.h>
#include <time.h>
#include <pthread.h>

#define GET_TIME(variable) (clock_gettime(CLOCK_MONOTONIC, &variable))
#define TIME(start, end) ((double)((end.tv_nsec)-(start.tv_nsec)) / 100000000.0)
#define ELAPSED_TIME(start, end) TIME(start, end) < 0 ? -TIME(start, end) : TIME(start, end)
#define CORES_NUMBER sysconf(_SC_NPROCESSORS_ONLN)
#elif _WIN32 || _WIN64
#include <windows.h>
#include <time.h>
#include <pthread.h>

#define GET_TIME(variable) ((variable) = clock())
#define ELAPSED_TIME(start, end) ((double)((end)-(start)) / CLOCKS_PER_SEC)
SYSTEM_INFO sysinfo;
#define CORES_NUMBER sysinfo.dwNumberOfProcessors
#endif

#define PRINT_TIME(start, end)(printf("\n%.4f", ELAPSED_TIME(start, end)))

#include "./semantic.h"
#include "./conf.h"

#include "./ordenacao/ordenacao.h"

#include "./../src/vetor/Vetor.c"
#include "./../src/benchmark/benchmark.c"
#include "./../src/ordenacao/quickSort.c"


#endif 
