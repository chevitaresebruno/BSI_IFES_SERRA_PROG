from abc import ABC, abstractmethod

from ..dto.OperatorData import OperatorRequest, OperatorResponse


class IOperation(ABC):
    def __init__(self) -> None:
        super.__init__()
    
    @abstractmethod
    def operate(self, value: OperatorRequest) -> OperatorResponse: pass
        
    