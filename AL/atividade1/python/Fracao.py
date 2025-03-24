from math import lcm


class Fracao:
    definirZeroComo1: bool = False


    def __init__(self, numerador: int, denominador: int = 1):
        if(denominador == 0):
            if(Fracao.definirZeroComo1):
                self.__den: int = 1
            else:
                raise ZeroDivisionError("Atenção, você não pode iniciar uma fração com o denominador igual a 0.")
        else:
            self.__den: int = denominador
        
        self.__num: int = numerador

        if(self.__num < 0 and self.__den < 0):
            self.__num = abs(self.__num)
            self.__den = abs(self.__den)
        elif(self.__num * self.__den < 0):
            self.__num = -abs(self.__num)
            self.__den = abs(self.__den)

    def irredutivel(self):
        cont: int = 2

        while(cont <= abs(self.__den) and cont <= abs(self.__num)):
            if(self.__den % cont == 0 and self.__num % cont == 0):
                self.__den /= cont
                self.__num /= cont
            else:
                cont += 1

        self.__den = int(self.__den)
        self.__num = int(self.__num)

        return self
    
    def oposto(self):
        return Fracao(self.__num*-1, self.__den)

    @property
    def mista(self) -> str:
        if(self.__den == 0):
            return "Indefinido"
        if(self.__den == 1):
            return f"{self.__num}"
        
        intPart: int = int(self.__num/self.__den)
        if(intPart == 0):
            return f"{self.__num}/{self.__den}"
        
        newNum: int = self.__num - intPart*self.__den
        if(newNum == 0):
            return intPart.__str__()
        return f"{intPart} + {newNum}/{self.__den}"
    
    @property
    def inversa(self):
        return Fracao(self.__den, self.__num)
    
    @property
    def decimal(self) -> float:
        return self.__num / self.__den

    def __add__(self, other: int | float):
        otherType = type(other)

        if(otherType == int):
            return Fracao(self.__num + other*self.__den, self.__den)
        elif(otherType == float):
            return self + Fracao.criarDeDecimal(other)
        elif(otherType == type(self)):
            mmc: int = lcm(self.__den, other.__den)
            return Fracao((self.__num*mmc//self.__den) + int(other.__num*mmc//other.__den), mmc)

        raise NotImplementedError(f"Operações de soma entre {otherType} e Fração não foi definida")

    def __sub__(self, other: int | float):
        otherType = type(other)

        if(otherType == int):
            return Fracao(self.__num - other*self.__den, self.__den)
        elif(otherType == float):
            return self - Fracao.criarDeDecimal(other)
        elif(otherType == type(self)):
            mmc: int = lcm(self.__den, other.__den)
            return Fracao((self.__num*mmc//self.__den) - int(other.__num*mmc//other.__den), mmc)

        raise NotImplementedError(f"Operações de subtração entre {otherType} e Fração não foi definida")
    
    def __mul__(self, other: int | float):
        otherType = type(other)

        if(otherType == int):
            if(self.__den % other == 0):
                return Fracao(self.__num, int(self.__den/other))
            return Fracao(self.__num*other, self.__den)
        elif(otherType == float):
            return self * Fracao.criarDeDecimal(other)
        elif(otherType == type(self)):
            return Fracao(int(self.__num * other.__num), int(self.__den * other.__den))

        raise NotImplementedError(f"Operações de multiplicação entre {otherType} e Fração não foi definida")
    
    def __truediv__(self, other: int | float):
        otherType = type(other)

        if(otherType == int):
            if(self.__num % other == 0):
                return Fracao(self.__num / other, self.__den)
            return Fracao(self.__num, self.__den*other)
        elif(otherType == float):
            return self * Fracao.criarDeDecimal(other).inversa
        elif(otherType == type(self)):
            return self * other.inversa

        raise NotImplementedError(f"Operações de divisão entre {otherType} e Fração não foi definida")
    
    def __eq__(self, other) -> bool:
        return self.decimal == other

    def __str__(self) -> str:
        if(self.__den == 0):
            return "Indefinido"
        if(self.__den == 1):
            return self.__num.__str__()
        if(self.__num == 0):
            return "0"

        return f"{self.__num}/{self.__den}"        

    @staticmethod
    def criarDeDecimal(numero: float):
        frac: Fracao = Fracao(0)

        frac.__num = int(numero)  # parte inteira do número
        decPart: float = numero-frac.__num  # parte decimal do número
        mulTimes: int = 0  # quantidade de shifts

        while(decPart != int(decPart)):
            decPart *= 10
            mulTimes += 1

        frac.__den = 10**mulTimes
        frac.__num *= frac.__den
        frac.__num += decPart

        return frac.irredutivel()
    
