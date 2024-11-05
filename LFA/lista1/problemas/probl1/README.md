# Lista 1 -- Linguagens Formais e Autômatos -- 2024/2
**Linguagens Formais e Autômatos**
**Autores:** Bruno da Fonseca Chevitarese e Marcos Vinícius Souza dos Santos

## Problema 1
Este problema pede a elaboração de um algoritmo capaz de converter um número qualquer em base binária, octal, decimal, duodecimal, hexadecimal e vigesimal para qualquer uma dessas.

2
**Solução**
A solução adotada foi implementar uma série de funções capases de de converter um número de qualquer uma das bases anteriormente descritas para a base decimal e qualquer número da base decimal para das demais base (via algoritmo recursivo). Com isso, a conversão das demais bases são, basicamente, converter da base "x" para a base intermediária (decimal) e depois converter da base intermediária para a base "y" desejada.

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