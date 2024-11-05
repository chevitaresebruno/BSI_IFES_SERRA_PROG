# Lista 1 -- Linguagens Formais e Autômatos -- 2024/2
**Linguagens Formais e Autômatos**
**Autores:** Bruno da Fonseca Chevitarese e Marcos Vinícius Souza dos Santos

## Problema 2
Este problema pede a elaboração de um algoritmo capaz de verificar se uma string possui parênteses balanceados, isto é, para cada parênteses aberto, há um análogo que o fecha.

2
**Solução**
A solução adotada foi adicionar cada caractere "(" a uma pilha e conferir se há um análogo ")". Se no final da execução a pilha não estiver vazia ou ao encontrar um caractere ")" com a pilha vazia, entende-se que e equação está desbalanceada.

**Execução**
Para executar a função no prompt do Scala execute, por exemplo:
```scala
Main()
```

Para executar usando o SBT use:
```bash
sbt run
```

Caso o código ainda não esteja compilado, recomenda-se utilizar:
```bash
sbt compile
```