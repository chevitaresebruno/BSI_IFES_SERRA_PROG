# Trabalho Final de LFA - Implementação do QuickSort Paralelo

## Introdução
Este projeto implementa o algoritmo QuickSort nas versões serial e paralela, utilizando a linguagem de programação C. O objetivo é avaliar o desempenho da paralelização utilizando threads e medir o speedup obtido em diferentes cenários.

## Estrutura do Projeto
O projeto está organizado da seguinte forma:

```
LFA/
|-- trabalhofinal/
|   |-- src/
|   |   |-- quicksort.c
|   |   |-- main.c
|   |-- inc/
|   |   |-- shared.h
|   |-- out/
|   |   |-- relatorios/
|   |-- Makefile
```

## Compilação e Execução
Para compilar e executar o programa, siga os seguintes passos:

1. Navegue até o diretório `LFA/trabalhofinal/`:
   ```bash
   cd LFA/trabalhofinal/
   ```
2. Compile o código usando `make`:
   ```bash
   make all
   ```
3. Execute o programa:
   ```bash
   ./main
   ```
4. Os benchmarks serão salvos no diretório `out/relatorios/`, contendo os tempos de execução das versões serial e paralela.

## Implementação
A implementação segue o conceito clássico do QuickSort, com uma versão paralela baseada na criação de threads para chamadas recursivas. A estrutura principal do código está contida nos seguintes arquivos:

- **quicksort.c**: Contém a implementação do QuickSort serial e paralelo.
- **main.c**: Ponto de entrada do programa, onde os testes são realizados.
- **shared.h**: Contém definições e estruturas de dados compartilhadas entre os arquivos.

A versão paralela utiliza `pthread` para dividir o trabalho entre várias threads, distribuindo as chamadas recursivas para diferentes núcleos de processamento.

## Resultados e Discussão
Os testes foram realizados em um notebook Avell com as seguintes configurações:
- **Processador**: Intel i7-13620H, 2.40 GHz
- **Memória RAM**: DDR5 5200 MT/s, 32 GB em Dual Channel
- **Sistema Operacional**: Windows 11 Home

Os testes consistiram em ordenar um vetor de 500.000 elementos aleatórios, variando o número de threads entre 1, 2, 4, 6, 10 e 16. O speedup foi calculado para medir o ganho de desempenho proporcionado pela paralelização.

### Speedup Observado

| Número de Threads | Speedup |
|------------------|---------|
| 2                | 1.26    |
| 4                | 1.58    |
| 6                | 1.70    |
| 10               | 2.95    |
| 16               | 2.97    |

Os resultados mostram que a implementação paralela não escala de forma ideal devido à forma como as threads são criadas. A implementação atual gera threads a cada chamada recursiva, o que pode levar a um aumento excessivo de threads concorrentes, resultando em um uso ineficiente dos recursos do processador.

## Conclusão
O QuickSort paralelo apresentou ganhos de desempenho, mas com limitações impostas pela implementação utilizada. Uma abordagem mais eficiente poderia incluir:
- Melhor gestão da criação de threads.
- Uso de filas de tarefas para distribuição balanceada da carga de trabalho.
- Ajuste dinâmico do número de threads baseado no tamanho do vetor.

Apesar das limitações, o projeto demonstrou na prática os conceitos de paralelismo e a importância da escolha de uma estratégia eficiente ao implementar algoritmos paralelos.

## Licença
Este projeto é distribuído sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

---
**Autores:** 
- Bruno Da Fonseca Chevitarese (20231BSI0082)
- Henrique de Souza Lima (20231BSI0139)
- Marcos Vinícius Souza dos Santos (20222BSI0156)

