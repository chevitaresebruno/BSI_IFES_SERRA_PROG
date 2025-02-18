#ifndef SHARED_H 
#define SHARED_H 1 
 
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

#include "./semantic.h"
#include "./conf.h"

#endif 
