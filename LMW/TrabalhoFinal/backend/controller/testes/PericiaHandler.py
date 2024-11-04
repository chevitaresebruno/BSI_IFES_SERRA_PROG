from .DadoController import DadoController
from model.player.Player import Player
from model.propriedades.pericia.Pericia import Pericia
from controller.tools.ClassHandler import ClassHandller

class PericiaHandler:
    @staticmethod
    def rolar(player: Player, pericia: Pericia) -> tuple[int, bool]:
        nivel = player.nivel
        treino = player.treinamento if PericiaHandler.possui_treinamento(player.pericias, pericia) else 0
        mod: int = 0
        
        for m in ClassHandller.get_metodos(player.atributos):
            if(pericia == getattr(player.atributos, m)):
                mod: int = getattr(player.atributos, m).modificador
                break
        
        return DadoController.d20(PericiaHandler.buffs(nivel, mod, treino))
        
    @staticmethod
    def buffs(nivel: int, atributo_mod: int, treino: int, outros: int = 0) -> int:
        return nivel + atributo_mod + outros + treino
    
    @staticmethod
    def possui_treinamento(pericias: set[int], pericia: Pericia) -> bool:
        for p in pericias:
            if p == pericia: return True
        
        return False
    
    