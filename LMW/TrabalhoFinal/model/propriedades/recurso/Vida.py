from .Recurso import Recurso

class Vida(Recurso):
    def morreu(self) -> bool:
        return super().atual < min(-1*super().max/2, -10)

