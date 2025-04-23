from dataclasses import dataclass


@dataclass
class OperatorRequest:
    value1: float
    value2: float
    

@dataclass
class OperatorResponse:
    result: float
    errorMessage: str = ""
    
    
    def someErrorOccured(self) -> bool:
        return len(self.errorMessage) > 0
    
    