from dataclasses import dataclass

from .Atributos import Atributos

from model.propriedades.atributo.Atributo import Atributo
from model.propriedades.recurso.Vida import Vida
from model.propriedades.recurso.Mana import Mana


@dataclass
class Player:
    __atributos: Atributos
    __vida: Vida
    __mana: Mana
    __nivel: int
    __pericias: list[int]
    __nome: str = "Generico"
    
    def patamar_str(self) -> str:
        match self.patamar():
            case 1:
                return "Iniciante"
            case 2:
                return "Veterano"
            case 3:
                return "Campeão"
            case 4:
                return "Lenda"
    
    def patamar(self) -> int:
        if self.__nivel <= 4:
            return 1
        elif self.__nivel <= 10:
            return 2
        elif self.__nivel <= 16:
            return 3

        return 4    
        
    
    @property
    def treinamento(self) -> int:
        if self.__nivel <= 6:
            return 2
        elif self.__nivel <= 14:
            return 4

        return 6
    
    @property
    def nivel(self) -> int:
        return self.__nivel
    
    def add_pericia(self, conjunto: set[int]):
        self.__pericias = tuple(conjunto.union(self.__pericias))
        
    @property
    def pericias(self) -> tuple[int]:
        return self.__pericias
    
    @property
    def atributos(self) -> Atributos:
        return self.__atributos
    
    @property
    def nome(self) -> str:
        return self.__nome
    
    def to_dict(self) -> dict:
        return {"nivel": self.__nivel, "vida": self.__vida.to_dict(), "mana": self.__mana.to_dict(), "atributos": self.__atributos.to_dict(), "pericias": self.__pericias, "nome": self.__nome}
    
    def to_save(self) -> dict:
        return {"nivel": self.__nivel, "vida": self.__vida.to_save(), "mana": self.__mana.to_save(), "atributos": self.__atributos.to_save(), "pericias": self.__pericias, "nome": self.__nome}