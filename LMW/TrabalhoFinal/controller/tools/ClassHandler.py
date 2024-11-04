class ClassHandller:
    @staticmethod
    def get_metodos(classe: type) -> list[str]:
        return [m for m in classe.__dict__ if not m.startswith("__")]
    
    