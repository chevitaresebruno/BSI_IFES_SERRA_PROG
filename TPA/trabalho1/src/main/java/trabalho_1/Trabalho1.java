/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package trabalho_1;

import java.io.File;
import java.util.Scanner;

import trabalho_1.impuro.LerArquivos;
import trabalho_1.input.gerador_arquivos.GeradorArquivosBalanceados;
import trabalho_1.input.gerador_arquivos.GeradorArquivosOrdenados;
import trabalho_1.listas.Lista;
import trabalho_1.listas.ListaOrdenada;
import trabalho_1.tipos.Aluno;
import trabalho_1.tipos.ComparatorAluno;
import trabalho_1.tipos.SearchIsntanceController;

/**
 *
 * @author Bruno
 */
public class Trabalho1
{
    private static Lista<Aluno> listaDesordenada;
    private static ListaOrdenada<Aluno> listaOrdenada;

    public static void main(String[] args)
    {
        criarArquivos(false);

        SearchIsntanceController.initialize();
        executarBenchmark(GeradorArquivosBalanceados.getNomeArquivo()); // Benchmark with balanced data
        executarBenchmark(GeradorArquivosOrdenados.getNomeArquivo()); // Benchmark with ordered data
    }

    private static void benchmark()
    {
        Trabalho1.benchmark("");
    }

    private static void benchmark(String someVariable)
    {
        try
        {
            Scanner s;

            if(someVariable.length() > 0)
                { s = new Scanner(someVariable); }
            else
                { s = new Scanner(System.in); }

            String opc;
            int opcao = 0;
            long startTime, endTime;
            Aluno al;
            
            System.out.println("1 - buscar na lista desordenada;\n2 - buscar na lista ordenada\n3 - sair");
            
            while(opcao != 3)
            {
                opc = s.nextLine();
                
                try
                { opcao = Integer.parseInt(opc); }
                catch(NumberFormatException e)
                { System.out.println("Escreva somente números inteiros."); continue; }
                
                switch (opcao)
                {
                    case 1 ->
                    {
                        System.out.println("Matrícula: ");
                        opc = s.next();
                        startTime = System.nanoTime();
                        al = Trabalho1.listaDesordenada.pesquisar(SearchIsntanceController.searchByMatricula(opc));
                    }
                    case 2 ->
                    {
                        System.out.println("Matrícula: ");
                        opc = s.next();
                        startTime = System.nanoTime();
                        al = Trabalho1.listaOrdenada.pesquisar(SearchIsntanceController.searchByMatricula(opc));
                    }
                    case 3 -> { return; }
                    default -> { System.out.println("Opção não configurada"); continue; }
                }
                endTime = System.nanoTime();

                if(al == null)
                    { System.out.print("Aluno não iedntificado"); }
                else
                    { System.out.print(al); }
                
                System.out.print(String.format("; Time Elapsed: %f s\n", (endTime-startTime)/1000000000.0));
            }

            s.close();
        }
        catch(Error e)
            {}
    }

    private static void executarBenchmark(String nomeDoAquivo)
    {
        long startTime;
        long finalTime;
        Trabalho1.listaDesordenada =  new Lista<>();
        Trabalho1.listaOrdenada = new ListaOrdenada<>(ComparatorAluno.comparadorPadrao());

        System.out.println("Lendo alunos");
        startTime = System.nanoTime();
        LerArquivos.InstanciarAlunos(nomeDoAquivo, Trabalho1.listaDesordenada, Trabalho1.listaOrdenada);
        finalTime = System.nanoTime();

        System.out.println(String.format("Time Spent to Read file and fill the lists: %f s", (finalTime-startTime)/1000000000.0));

        benchmark();
    }

    private static void criarArquivos(boolean reset)
    {
        if(reset)
        {
            GeradorArquivosBalanceados.gerarArquivo();
            GeradorArquivosOrdenados.gerarArquivo();
            return;
        }

        File f = new File(GeradorArquivosBalanceados.getNomeArquivo());
        if(!f.exists())
            { GeradorArquivosBalanceados.gerarArquivo(); }
        f = new File(GeradorArquivosOrdenados.getNomeArquivo());
        if(!f.exists())
            { GeradorArquivosOrdenados.gerarArquivo(); }
    }
}
