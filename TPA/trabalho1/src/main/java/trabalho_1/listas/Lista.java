package trabalho_1.listas;

public class Lista<T extends Comparable<T>>
{
    protected NoSimples<T> prim;
    private NoSimples<T> ulti;
    private int tam;
    
    public Lista()
    {
        this.prim = null;
        this.ulti = null;
        this.tam= 0;
    }

    public int getTamanho()
    {
        return this.tam;
    }

    public boolean estouVaiza()
    {
        return this.tam == 0;
    }

    public void adicionar(T elemento)
    {
        NoSimples<T> no = new NoSimples<>(elemento);

        if(this.prim == null)
            { this.prim = no; }
        if(this.ulti != null)
            { this.ulti.prox = no; }
        this.ulti = no;
        this.tam++;
    }

    protected void adicionar(NoSimples<T> anterior, NoSimples<T> atual, T elemento)
    {
        if(anterior == null)
            { return; }

        NoSimples<T> novo = new NoSimples<>(elemento);

        anterior.prox = novo;
        novo.prox = atual;

        if(atual == null)
            { this.ulti = novo; }

        this.tam++;
    }

    public T pesquisar(T elemento)
    {
        NoSimples<T> n = this.prim;

        while (n != null)
        {
            if(n.elem.compareTo(elemento) == 0)
                { return n.elem; }
            n = n.prox;
        }

        return null;
    }

    public void foreachPrint()
    {
        NoSimples<T> n = this.prim;

        while(n != null)
        {
            System.out.println(n.elem);
            n = n.prox;
        }
    }
}
