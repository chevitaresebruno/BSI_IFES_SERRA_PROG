from .dto.Operators import OperationRequest, OperationResponse

from model.dto.OperatorData import OperatorRequest
from model.operations.IOperation import IOperation
from model.operations.Soma import Soma
from model.operations.Divisao import Divisao


class Operations:
    __operation: IOperation
    
    @staticmethod
    def soma(values: OperationRequest):
        Operations.__operation = Soma()
        return Operations.__baseOperation(values=values)
    
    @staticmethod
    def divisao(values: OperationRequest):
        Operations.__operation = Divisao()
        return Operations.__baseOperation(values=values)
    
    @staticmethod
    def __baseOperation(values: OperatorRequest) -> dict:
        operateResponse = Operations.__operation.operate(OperatorRequest(values.value1, values.value2))
        response = OperationResponse(operateResponse.errorMessage, operateResponse.result)
        
        return response.toJsonResponse()
        
        