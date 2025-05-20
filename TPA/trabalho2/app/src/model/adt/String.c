#ifndef ATD_STRING_C
#define ATD_STRING_C 0


#include <stdlib.h>
#include <string.h>

typedef struct str String;


struct str
{
    char* v;
    unsigned int size;
};


String* newStr(const unsigned int size)
{
    String* s = (String*)malloc(sizeof(String));
    if(s == NULL)
        return NULL;

    s->v = (char*)calloc(sizeof(char), size);
    if(s->v == NULL)
    {
        free(s);
        return NULL;
    }

    s->size = size;
}

void strScan(String* s)
{
    if(s == NULL)
        return;

    scanf(" %s", s->v);
}

String* newStrRand(const unsigned int size)
{
    register unsigned int i;
    register char j;
    String* s = newStr(size);
    if(s == NULL)
        return NULL;

    for(i = 0; i < size; i++)
    {
        j = rand() % 256;
        if(j == '\0' || j == '\n')
            i--;
        else
            s->v[i] = j;
    }

    return s;
}


void strFree(String* s)
{
    if(s == NULL)
        return;

    if(s->v != NULL)
        free(s->v);

    free(s);
}   


void strSet(String* s, const char* str)
{
    register unsigned int i;
    if(s == NULL || str == NULL)
        return;

    for(i = 0; i < s->size; i++)
        s->v[i] = str[i];
    
    i--;
    if(s->v[i] != '\0')
        s->v[i] = '\0';
}


#endif