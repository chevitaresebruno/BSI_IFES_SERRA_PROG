from dataclasses import dataclass
from abc import ABC


@dataclass
class Recurso(ABC):
    __max: int
    __atual: int
    __extra: int = 0
    

    def add(self, valor: int):
        if valor < 0:
            raise ValueError("O valor fornecido deve ser maior que 0")
        
        self.__atual += valor
        
        if self.__atual > self.__max:
            self.__atual = self.__max
        
        return self.__atual
    
    def sub(self, valor: int) -> int:
        if valor < 0:
            raise ValueError("O valor fornecido deve ser maior que 0")
        
        self.__atual += self.__sub_extra(valor=valor)
        
        return self.__atual
    
    @property
    def atual(self) -> int:
        return self.__atual
    
    @property
    def total(self) -> int:
        return self.__atual + self.__extra
    
    @property
    def max(self) -> int:
        return self.__max
    
    @property
    def extra(self) -> int:
        return self.__extra
        
    def __sub_extra(self, valor: int) -> int:
        self.__extra -= valor
        if self.__extra < 0:
            aux = self.__extra
            self.__extra = 0
            return aux

        return 0

    def to_dict(self) -> dict:
        return {"max": self.__max, "atual": self.__atual, "extra": self.__extra}
    
    def to_save(self) -> tuple[int]:
        return (self.max, self.atual, self.extra)
    