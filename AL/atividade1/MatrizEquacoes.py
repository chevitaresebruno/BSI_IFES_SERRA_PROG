from Fracao import Fracao
from random import randint, seed


class MatrizEquacoes:
    def __init__(self, numero_equacoes: int, numero_variaveis: int, valor_minimo: int = -5, valor_maximo: int = 5, _seed = 0):
        seed(_seed)
        lastValue: bool = Fracao.definirZeroComo1

        Fracao.definirZeroComo1 = True
        
        self.__matriz = [[Fracao(randint(valor_minimo, valor_maximo), randint(valor_minimo, valor_maximo)) for x in range(numero_variaveis)] for y in range(numero_equacoes)]

        Fracao.definirZeroComo1 = lastValue

    def print(self):
        for x in self.__matriz:
            for y in x:
                print(y, end=" ")
            print("")
        