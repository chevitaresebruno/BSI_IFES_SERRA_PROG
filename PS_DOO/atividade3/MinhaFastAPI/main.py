from __init__ import *

from controller.Operations import Operations


class App:
    def __init__(self, name: str) -> None:
        self.name = name
        self.router = APIRouter()
        
        self.router.add_api_route("/soma/", Operations.soma, methods=["POST"])
        self.router.add_api_route("/divisao/", Operations.divisao, methods=["POST"])
        
        
app = FastAPI()
calculator = App("Calculator")
app.include_router(calculator.router)
    
    