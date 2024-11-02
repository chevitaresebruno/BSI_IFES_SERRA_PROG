#pragma once

#ifndef ERROR_H
#define ERROR_H

#include "lib/include/comum.h"

typedef enum {CORRECT, MEMORY_NOT_ENOUGH, PARAMETER_WORNG, FILE_ALREDY_EXISTS} ERROR_CODE;
typedef struct Error Error;

Error* create_error(const String message, const ERROR_CODE code);

void free_error(Error* error);

#endif
