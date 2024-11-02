#include <stdlib.h>
#include <string.h>
#include <stdio.h>


#include "Error.h"


struct Error
{
    ERROR_CODE code;
    char* mess;
};


Error* create_error(const String m, const ERROR_CODE code)
{
    Error* e;
    
    if(m == NULL)
    {
        printf("PARAMETER ERROR");  
        return NULL;
    }

    e = (Error*)malloc(sizeof(Error));

    if(e == NULL)
        return e;
    
    e->mess = (char*)malloc(sizeof(char)*strlen(m));
    strcpy(e->mess, m);

    e->code = code;

    return e;
}


void free_error(Error* e)
{
    if(e != NULL)
    {
    free(e->mess);
    free(e);
    }
}


void free_error_response(void* e)
{
    free_error((Error*) e);
}


void ec_default_mess(const ERROR_CODE ec)  // errorCode_default_message
{
    switch (ec)
    {
    case MEMORY_NOT_ENOUGH:
        printf("Memory not Enought.");
        break;
    
    case PARAMETER_WORNG:
        printf("Some Parameter was Worng");
        break;

    case FILE_ALREDY_EXISTS:
        printf("The file alredy exists");
        break;

    default:
        printf("Error code not implemented");
        break;
    }
}


void print_error(Error* e)
{
    printf("\nERROR! SOMETHING WENT WRONG!\n");
    printf("ERROR MESSAGE: %s\n", e->mess);
    printf("ERROR CODE: %d; ", e->code);
    ec_default_mess(e->code);
}

