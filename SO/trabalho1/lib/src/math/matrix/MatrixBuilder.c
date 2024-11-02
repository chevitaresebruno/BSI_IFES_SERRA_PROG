#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include "lib/include/shared/Response.h"
#include "lib/include/math/matrix/Matrix.h"
#include "lib/include/file_manager/file_handler.h"


Response* build_matrix_random(const unsigned int i, const unsigned int j, const unsigned int mint)
{  
    Response* aux;
    MatrixFrame mf;
    
    if(i < 1 || j < i)
        return create_response(NULL, create_error("build_matrix_random, i and j must be grather than 0", PARAMETER_WORNG));
    
    Response* aux = create_matrix(i, j);

    if(response_has_error(aux))
        return aux;

    mf = get_matrix_frame_ptr((Matrix*)get_response_data(aux));

    for(unsigned int c = 0; c < i; c++)
    {
        for(register unsigned int z = 0; z < j; z++)
            mf[c][z] = rand()%mint;
    }

    return aux;
}


Response* build_matrix_from_file()
{
    
}


Response* build_matrix_to_file(const String fname, Matrix* m)
{
    FILE* f;

    if(file_exist(fname))
        return create_response(NULL, create_error(strcat("FILE NAME: ", fname), FILE_ALREDY_EXISTS));

    for(unsigned int i = 0; i < get_matrix_lines_num(m); i++)
    {
        for(register unsigned int j = 0; j < get_matrix_lines_num(m); i++)
        {
            int aux = get_matrix_cell(m, i, j);
            if(aux == -1)
                return create_response(NULL, create_error("THE PARAMETER i OR h ARE GRETAER THAN m->i OR m->j", -1));

            fprintf(f, "%d ", aux);
        }
    }

    return create_response(NULL, NULL);
}

