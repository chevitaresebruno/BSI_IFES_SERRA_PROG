#ifndef SHARED_H
#define SHARED_H 0

/* Dependencies */
#include <stdlib.h>

/* Semantincs */
typedef signed char ssInt;

/* Enums */
typedef enum {false, true} bool;
typedef enum {lessThan, equal, greaterThan, nullPointer} compareResult;

typedef enum {correctOperation, nullPointerException, memoryNotEnough} exceptions;

/* Generic Functions */
typedef compareResult(*cmpFunc)(const void*, const void*);
typedef void(*foreachF)(void*);
typedef void(*freeFunc)(void*);

#endif