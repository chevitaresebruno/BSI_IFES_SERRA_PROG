from dataclasses import dataclass


@dataclass
class Dado:
    rolagem: int = 0
    buffs: int = 0
    critico: int = 0
    
    @property
    def total(self) -> int:
        return self.rolagem + self.buffs
    
    def __lt__(self, dado: object):
        return self.rolagem < dado.rolagem

    def __le__(self, dado: object):
        return self.rolagem <= dado.rolagem

    def __gt__(self, dado: object):
        return self.rolagem > dado.rolagem

    def __ge__(self, dado: object):
        return self.rolagem >= dado.rolagem

    def __eq__(self, dado: object):
        return self.rolagem == dado.rolagem
    
    def __add__(self, dado: object):
        match type(dado):
            case int(): return self.total + dado
            case Dado(): return self.total + dado.total
        
        raise NotImplementedError(f"O tipo Dado não pode ser somado com o tipo {type(dado)}")
    
    def to_dict(self) -> dict:
        return {"rolagem": self.rolagem, "buffs": self.buffs, "critico": self.critico, "total": self.total}