from .__init__ import *


class SaveController:
    @staticmethod
    def db_check_folder(db_name: Path = DB_NAME) -> bool:        
        if not exists(db_name):
            mkdir(db_name)
            return False
        
        return True
    
    @staticmethod
    def save_player(player: Player, ovewrite: bool = True, db_name: Path = DB_NAME) -> bool:
        player_folder: Path = db_name / player.nome
        
        if not exists(player_folder):
            SaveController.__create_player_folder(player_folder)
            
        else:
            if not ovewrite: return False
        
        with open(player_folder / "data.json", "w") as f:
            dump(player.to_save(), f)
        
        return True
                
    
    @staticmethod
    def __create_player_folder(folder_name: Path):
        mkdir(folder_name)
        
        with open(folder_name / "data.json", 'w+'):
            pass
        
        