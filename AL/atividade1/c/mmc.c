long int mmc(const long int a, const long int b)
{
    register long int r, i, j;
    i = a;
    j = b;
    r = j;

    /* gcd prevent context swap */
    while (j > 0)
    {
            r = j;
            j = i % j;
            i = r;
    }
    
    return (a / r) * b;
}


long int gdc(const long int a, const long int b)
{
    register long int r, i, j;
    i = a;
    j = b;
    r = j;

    /* gcd */
    while (j > 0)
    {
            r = j;
            j = i % j;
            i = r;
    }
    
    return r;
}