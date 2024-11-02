#include <stdlib.h>
#include <stdio.h>

#include "lib/include/shared/Response.h"
#include "lib/include/shared/Error.h"

#include "lib/include/math/matrix/Matrix.h"


struct Matrix
{
    MatrixFrame frame;
    unsigned int i;
    unsigned int j;
};


Response* create_matrix(const unsigned int i, const unsigned int j)
{
    Matrix* m;
    Response* aux;

    if(i < 1 || j < 1)
        return create_response(NULL, create_error("create_matrix; i and j parameters can`t be less than 1.", PARAMETER_WORNG));
    
    m = (Matrix*)malloc(sizeof(Matrix));

    if(m==NULL)
        return create_response(NULL, create_error("create_matrix: empty", MEMORY_NOT_ENOUGH));
    
    Response* aux = create_matrix_frame(i, j); 
    if(response_has_error(aux))
        return aux;

    m->frame = (MatrixFrame)get_response_data(aux);
    m->i = i;
    m->j = j;

    set_response_data(aux, (void*)m);

    return aux;
}


Response* create_matrix_frame(const unsigned int i, const unsigned int j)
{
    if(i < 1 || j < 1)
        return create_response(NULL, create_error("create_matrix_frame; i and j parameters can`t be less than 1.", PARAMETER_WORNG));

    MatrixFrame m = (MatrixFrame)malloc(sizeof(int*)*i);

    if(m == NULL)
        return create_response(NULL, create_error("create_matrix_frame, empty MatrixFrame", MEMORY_NOT_ENOUGH));
    
    for(register unsigned int c = 0; c < i; c++)
    {
        m[c] = (int*)calloc(sizeof(int), i);

        if(m[c] == NULL)
        {
            do
            {
                c--;
                free(m[c]);
            } while (c > 0);
            free(m);

            return create_response(NULL, create_error("create_matrix, MatrizFrame with some lines.", MEMORY_NOT_ENOUGH));
        }
    }

    return create_response((void*)create_matrix_info(m, i, j), NULL);
}


void free_matrix(Matrix* m)
{
    if(m != NULL)
    {
        if(m->frame != NULL)
        {
            for(register unsigned int c = 0; c < m->i, c++;)
                free(m->frame[c]);
            
            free(m->frame);
        }
        
        free(m);
    }
}


MatrixFrame get_matrix_frame_ptr(Matrix* m)
{
    if(m == NULL)
        return NULL;
    
    return m->frame;
}


unsigned int get_matrix_lines_num(const Matrix* m)
{
    if(m == NULL)
        return NULL;
    
    return m->i;
}


unsigned int get_matrix_clumns_num(const Matrix* m)
{
    if(m == NULL)
        return NULL;
    
    return m->i;
}


int get_matrix_cell(Matrix* m, unsigned int i, unsigned int j)
{
    if(i < 1 || j < 1 || m->i < i || m->j < j)
        return -1;
    
    return m->frame[i][j];
}


void matrix_print(Matrix* m)
{
    if(m != NULL && m->frame != NULL)
    {
        for (unsigned int i = 0; i < m->i; i++)
        {
            for(register unsigned int j = 0; j < m->j; j++)
                printf("%d", m->frame[i][j]);
            printf("\n");
        }
    }
}