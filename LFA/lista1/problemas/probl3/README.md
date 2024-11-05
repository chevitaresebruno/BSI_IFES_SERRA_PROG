# Lista 1 -- Linguagens Formais e Autômatos -- 2024/2
**Linguagens Formais e Autômatos**
**Autores:** Bruno da Fonseca Chevitarese e Marcos Vinícius Souza dos Santos

## Problema 3
Este problema pede a elaboração de um algoritmo capaz de verificar se um número é perfeito. Um número perfeito é tal qual a soma de seus divisores é igual ao dobro do próprio número.


**Solução**
A solução adotada foi implementar uma recursão. O caso base é o divisor inicando em 2 e o acumulador iniciando em 1. A cada chamada recursiva, verifica-se se o divisor é múltiplo do número a ser verificado. Caso seja, soma-se ao acumulador o divisor e soma-se 1 ao divisor e realiza-se uma chamda recursiva. Caso o divisor não seja divisor do número, simplesmente soma-se um ao divisor e realiza-se outra chamada recursiva. A condição de interrupção é o divisor ultrapassar metade do número prucurado (não há mais divisores possíveis) e o retorno é verificar se o resultado do acumulador é igual ao número a ser checado.

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