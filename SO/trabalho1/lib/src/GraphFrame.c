#include <stdlib.h>


typedef struct GraphFrame
{
    double* xp;
    double* yp;
    char* xlabel;
    char* ylabel;
    int size;
} GraphFrame;


GraphFrame create_GraphFrame(double* xp, double* yp, char* xlabel, char* ylabel, const int size)
{
    GraphFrame* gf = (GraphFrame*)malloc(sizeof(GraphFrame));

    if(gf == NULL)
    {
        
    }
}