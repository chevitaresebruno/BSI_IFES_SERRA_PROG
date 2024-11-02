#pragma once
#ifndef RESPONSE_H
#define REPONSE_H

#include "lib/include/comum.h"
#include "lib/include/shared/Error.h"

typedef struct Response Response;


Response* create_response(void* data, Error* e);

void free_response(Response* r, void (*free_data)(void*));


void set_response_data(Response* obj, void* data);

void* get_response_data(Response* obj);

BOOL response_has_error(Response* obj);

#endif