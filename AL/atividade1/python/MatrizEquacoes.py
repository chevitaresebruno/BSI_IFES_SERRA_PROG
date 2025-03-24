from Fracao import Fracao
from random import randint, seed
from threading import Lock
from concurrent.futures import ProcessPoolExecutor


class MatrizEquacoes:
    def __init__(self, numero_equacoes: int, numero_variaveis: int, valor_minimo: int = -5, valor_maximo: int = 5, _seed = 0):
        seed(_seed)
        lastValue: bool = Fracao.definirZeroComo1

        Fracao.definirZeroComo1 = True
        
        self.__matriz: list[list[Fracao]] = [[Fracao(randint(valor_minimo, valor_maximo), randint(valor_minimo, valor_maximo)) for x in range(numero_variaveis)] for y in range(numero_equacoes)]
        self.__numeroVariaveis: int = numero_variaveis
        self.__numeroEquacoes: int = numero_equacoes

        # Lidando com paralelismo
        self.__lock = Lock()
        self.__equacaoAnalise: list[Fracao]
        self.__fatorX: Fracao
        self.__flag: int

        Fracao.definirZeroComo1 = lastValue

    def escalonar(self, numeroThreads: int = 16) -> None:
        pivotIndex: int = 0
        equacaoAnalise: int = 0
        equacaoTroca: int
        
        for k in range(self.__numeroEquacoes-1):
            equacaoTroca = equacaoAnalise + 1

            if(self.__matriz[equacaoAnalise][pivotIndex] == 0):
                self.__matriz[equacaoAnalise], self.__matriz[equacaoTroca] = self.__matriz[equacaoTroca], self.__matriz[equacaoAnalise]
                equacaoTroca += 1
            
            pivot: Fracao = self.__matriz[equacaoAnalise][pivotIndex]

            for j in range(equacaoAnalise, self.__numeroEquacoes-1):
                with ProcessPoolExecutor(max_workers=min(numeroThreads, self.__numeroEquacoes - equacaoAnalise)) as executor:        
                    self.__equacaoSubtrair = j+1
                    elemAbaixo: Fracao = self.__matriz[self.__equacaoSubtrair][pivotIndex]

                    self.__equacaoAnalise = self.__matriz[equacaoAnalise]
                    self.__fatorX = elemAbaixo.oposto()/pivot
                    self.__flag = equacaoAnalise

                    executor.submit(self.__subtrairEquacao)
            
            pivotIndex += 1
            equacaoAnalise += 1
    
    def __subtrairEquacao(self) -> None:
        eqSubtrairIndice: int = self.__flag
        i: int

        while(eqSubtrairIndice > self.__flag):
            with self.__lock:
                if(self.__equacaoSubtrair > self.__flag):
                    eqSubtrairIndice = self.__equacaoSubtrair
                    self.__equacaoSubtrair-=1
                else:
                    return
                
            equacaoSubtrair: list[Fracao] = self.__matriz[eqSubtrairIndice]
            for i in range(self.__numeroVariaveis):
                if(self.__matriz[self.__equacaoSubtrair][i] == 0):
                    continue
                equacaoSubtrair[i] += self.__equacaoAnalise[i]*self.__fatorX

    def irredutibilizar(self) -> None:
        for equacao in self.__matriz:
            for variavel in equacao:
                if(variavel == 0):
                    continue

                variavel.irredutivel()

    def print(self) -> None:
        for x in self.__matriz:
            for y in x:
                print(y, end=" ")
            print("")
        