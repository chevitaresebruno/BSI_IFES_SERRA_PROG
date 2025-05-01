package trabalho_1.impuro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import trabalho_1.listas.Lista;
import trabalho_1.listas.ListaOrdenada;
import trabalho_1.tipos.Aluno;


public final class LerArquivos
{
    public static void InstanciarAlunos(String arquivo, Lista<Aluno> listaDesornedada, ListaOrdenada<Aluno> listaOrdenada )
    {
        File file = new File(arquivo);

        try(Scanner s = new Scanner(file);)
        {
            String data;
            String[] split;
            Aluno a;
            
            data = s.nextLine();  // Simplesmente lendo a primeira linha e ignorando ela :p

            while(s.hasNextLine())  // Sem tratamento de erro robusto aqui, pois, a entrada é padronizada.
            {
                data = s.nextLine();
                split = data.split(";");

                a = new Aluno(split[1], split[0], Float.parseFloat(split[2]));

                listaOrdenada.adicionar(a);
                listaDesornedada.adicionar(a);
            }

            s.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("O Arquivo passado não existe");
        }
    }
}
