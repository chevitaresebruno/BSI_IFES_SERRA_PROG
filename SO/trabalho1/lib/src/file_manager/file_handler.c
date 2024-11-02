#include <stdlib.h>
#include <stdio.h>

#include "lib/include/file_manager/file_handler.h"


BOOL file_exist(const String fname)
{
    FILE* f = fopen(fname, "r");

    if(f == NULL)
        return FALSE;
    
    fclose(f);

    return TRUE;
}
