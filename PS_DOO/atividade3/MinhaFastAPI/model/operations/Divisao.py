from .IOperation import IOperation

from ..dto.OperatorData import OperatorRequest, OperatorResponse


class Divisao(IOperation):
    def __init__(self) -> None:
        pass
    
    
    def operate(self, value: OperatorRequest):
        if(value.value1 + value.value2 == 0):
            return OperatorResponse(0, "Valor Indeterminado")
        elif(value.value2 == 0):
            return OperatorResponse(0, "Valor Indefinido")
        
        return OperatorResponse(value.value1/value.value2)
    
    