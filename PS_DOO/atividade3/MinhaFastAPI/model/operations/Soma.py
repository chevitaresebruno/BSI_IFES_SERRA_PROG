from .IOperation import IOperation
from ..dto.OperatorData import OperatorRequest, OperatorResponse


class Soma(IOperation):
    def __init__(self) -> None:
        pass
    
    def operate(self, value: OperatorRequest) -> OperatorResponse:
        return OperatorResponse(value.value1 + value.value2)
    
    