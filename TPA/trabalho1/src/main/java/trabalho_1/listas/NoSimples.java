package trabalho_1.listas;

public class NoSimples<T>
{
    protected NoSimples<T> prox;
    protected T elem;

    protected NoSimples(T elemento)
    {
        this.prox = null;
        this.elem = elemento;
    }
}
