#include <stdlib.h>
#include <stdio.h>

#include "lib/include/shared/Response.h"


struct Response
{
    void* data;
    Error* error;
};


Response* create_response(void* data, Error* error)
{
    Response* r;

    r = (Response*)malloc(sizeof(Response));
    
    if(r == NULL)
        return NULL;


    r->data = data;
    r->error = error;

    return r;
}


void free_response(Response* r, void (*free_data)(void*))
{
    if(r != NULL && free_data != NULL)
    {
        if(r->data != NULL)
            free_data(r->data);
        if(r->error != NULL)
            free_error(r->error);
    }
}


void set_response_data(Response* r, void* data)
{
    r->data = data;
}


void* get_response_data(Response* r)
{
    return r->data;
}



Error* get_response_error(Response* r)
{
    return r->error;
}


BOOL response_has_error(Response* r)
{
    return r != NULL;
}

