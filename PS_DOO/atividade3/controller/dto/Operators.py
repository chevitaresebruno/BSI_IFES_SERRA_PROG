from dataclasses import dataclass
from pydantic import BaseModel


class OperationRequest(BaseModel):
    value1: float
    value2: float
    
@dataclass
class OperationResponse:
    msg: str
    result: float
    
    def toJsonResponse(self) -> dict:
        return {"msg": self.msg, "result": self.result}
    
    