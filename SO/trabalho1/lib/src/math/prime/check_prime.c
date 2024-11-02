#include <math.h>

#include "lib/include/comum.h"


BOOL dummy_is_prime(const unsigned int n)
{
    for(register unsigned int i = 2; i <= n/2; i++)
    {
        if(n % i == 0)
            return FALSE;
    }

    return TRUE;
}


BOOL smart_is_prime(const unsigned int n)
{    
    if(n % 2 == 0)
        return FALSE;
    if(n%6 != 1 && n%6 != 5)
        return FALSE;

    for(register unsigned int c = 6; c < (int)sqrt(n); c+=2)
    {
        if(n % c == 0)
            return FALSE;
    }

    return TRUE;
}

