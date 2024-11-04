from random import randint

from model.game.Dado import Dado


class DadoController:
    @staticmethod
    def rolar(maximo: int = 20, buffs: int = 0, critico: int = 20) -> Dado:
        if critico > maximo or critico == 0:
            critico = maximo
        
        d: Dado = Dado(randint(1, maximo), buffs)
        if d.rolagem >= critico:
            d.critico = 1
        elif d.rolagem == 1:
            d.critico = -1
        
        return d
    
    @staticmethod
    def rolar_nvezes(vezes: int = 1, maximo: int = 20, buffs: int = 0, critico: int = 20) -> list[Dado]:
        return [DadoController.rolar(maximo, buffs, critico) for x in range(vezes)]
    

    @staticmethod
    def rolar_str(comando: str) -> list[Dado]:
        resultados: list = []
        
        critico = comando[comando.find("c")+1:]
        critico = 0 if critico == "" else int(critico)
        
        _ = comando[:comando.find("c")].split("+")
        
        i = 0        
        while i < len(_):
            if 'd' in _[i]:
                vezes, maximo = _.pop(i).split("d")
                vezes = 1 if vezes == "" or not vezes.isnumeric() else int(vezes)
                
                if vezes > 1:
                    for r in DadoController.rolar_nvezes(vezes, int(maximo), critico=critico): resultados.append(r)
                else:
                    resultados.append(DadoController.rolar(int(maximo), critico=critico)) 
            else: i += 1
            
        resultados.append(Dado(buffs=sum([int(x) for x in _])))    
        
        return resultados
    
    @staticmethod
    def Vantagem(dados: list[Dado]) -> Dado:
        maior: Dado = dados.pop(0)
        
        for dado in dados:
            if dado > maior: maior = dado
        
        return maior

    @staticmethod
    def Desvantagem(dados: list[Dado]) -> Dado:
        menor: Dado = dados.pop(0)
        
        for dado in dados:
            if dado < menor: menor = dado
        
        return menor
    
    @staticmethod
    def somar_lista_de_dados(lista: list[Dado]) -> int:
        total: int = 0
        for dado in lista:
            total += dado.total
        
        return total
    
    