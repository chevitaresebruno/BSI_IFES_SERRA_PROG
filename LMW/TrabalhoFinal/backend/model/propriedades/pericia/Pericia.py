from dataclasses import dataclass

from model.propriedades.atributo.Atributo import ATRIBUTOS_NOMES, Atributo


@dataclass
class Pericia:
    __nome: str
    __atributo: int
    __treino: bool = False
    __penalidade: bool = False
    
    def __str__(self) -> str:
        return self.__nome 
    
    def __eq__(self, value: object) -> bool:
        match value:
            case str(): return self.__nome == value
            case int(): return self.__nome == PERICIAS[value]
            case Atributo(): return self.__atributo == value            
        
        raise NotImplemented(f"O tipo Pericia não pode ser compara com o tipo: {type(value)}")

    @property
    def atributo(self) -> int:
        return self.__atributo
    

PERICIAS: tuple[Pericia] = (
    Pericia("Acrobacia", 1, False, True),
    Pericia("Adestramento", 5, True),
    Pericia("Atletismo", 0),
    Pericia("Atuação", 5),
    Pericia("Cavalgar", 1),
    Pericia("Conhecimento", 3),
    Pericia("Cura", 4),
    Pericia("Diplomacia", 5),
    Pericia("Enganação", 5),
    Pericia("Fortitude", 2),
    Pericia("Furtividade", 1, False, True),
    Pericia("Guerra", 3, True),
    Pericia("Iniciativa", 1),
    Pericia("Intimidação", 5),
    Pericia("Intuição", 4),
    Pericia("Investigação", 3),
    Pericia("Jogatina", 5, True),
    Pericia("Ladinagem", 1, True, True),
    Pericia("Luta", 0),
    Pericia("Misticismo", 3, True),
    Pericia("Nobreza", 3, True),
    Pericia("Percepção", 4),
    Pericia("Pilotagem", 1, True),
    Pericia("Pontaria", 1),
    Pericia("Reflexos", 1),
    Pericia("Religião", 4, True),
    Pericia("Sobrevivência", 4),
    Pericia("Vontade", 4)
)

