package trabalho_1.listas;

import java.util.Comparator;

public class ListaOrdenada<T extends Comparable<T>> extends Lista<T>
{
    private final Comparator<T> comparador;

    public ListaOrdenada(Comparator<T> comparador)
    {
        super();
        this.comparador = comparador;
    }

    @Override
    public T pesquisar(T elemento)
    {
        NoSimples<T> n = this.prim;
        int comp;

        while (n != null)
        {
            comp = n.elem.compareTo(elemento);

            if(comp == 0)
                { return n.elem; }
            else if(comp > 0)
                { break; }
        }

        return null;
    }

    public T pesquisar(T elemento, Comparator<T> comparador)
    {
        NoSimples<T> n = this.prim;
        int comp;

        while (n != null)
        {
            comp = comparador.compare(elemento, n.elem);

            if(comp == 0)
                { return n.elem; }
            else if(comp > 0)
                { break; }
        }

        return null;
    }

    @Override
    public void adicionar(T elemento)
    {
        if(super.estouVaiza())
        {
            super.adicionar(elemento);
            return;
        }

        NoSimples<T> anterior = null;
        NoSimples<T> atual = this.prim;

        while(atual != null)
        {
            if(this.comparador.compare(elemento, atual.elem) < 0)
                { break; }
            anterior = atual;
            atual = atual.prox;
        }

        super.adicionar(anterior, atual, elemento);
    }
}
