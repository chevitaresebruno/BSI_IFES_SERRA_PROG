from dataclasses import dataclass
from math import floor


ATRIBUTOS_NOMES: tuple[str] = (
    "Força",
    "Destreza",
    "Constituição",
    "Inteligência",
    "Sabedoria",
    "Carisma"
)


@dataclass
class Atributo:
    __valor: int
    __nome: str
    
    @property
    def valor(self) -> int:
        return self.__valor
    
    @valor.setter
    def valor(self, outro: int):
        self.__valor = outro
        
    @property
    def modificador(self) -> int:
        return round((self.__valor/2)-5 - 0.5)
    
    @staticmethod
    def definir_modificador(num: int) -> int:
        return floor((num/2)-5)
    
    def __str__(self) -> str:
        return self.__nome

    def __eq__(self, value: object) -> bool:
        match value:
            case str(): return self.__nome == value
            case int(): return self.__nome == ATRIBUTOS_NOMES[value]
            case Atributo(): return (self.__nome == value.__str__() and self.__valor == value.valor)
        
        raise NotImplementedError(f"O tipo Atributo não pode ser comparado como tipo {type(value)}")
            
    def to_dict(self) -> dict:
        return {"valor": self.__valor, "nome": self.__nome}

    