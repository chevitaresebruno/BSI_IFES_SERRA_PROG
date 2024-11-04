from .__init__ import *


class LoadController:
    @staticmethod
    def load_player(nome: str) -> Player:
        playe_folder: Path = DB_NAME / nome
        
        if exists(playe_folder):
            with open(playe_folder / 'data.json', 'r') as f:
                data = load(f)
            
            return Player(Atributos(*data["atributos"]), Vida(*data["vida"]), Mana(*data["mana"]), data["nivel"], data["pericias"], data["nome"])
        else:
            raise FileNotFoundError
        
        