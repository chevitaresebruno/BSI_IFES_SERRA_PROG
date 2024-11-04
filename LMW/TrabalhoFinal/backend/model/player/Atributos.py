from model.propriedades.atributo.Atributo import Atributo


class Atributos:
    def __init__(self, forca: int = 10, des: int = 10, con: int = 10, inte: int = 10, sab: int = 10, car: int = 10) -> None:
        self.__for: Atributo = Atributo(forca, "Força")
        self.__des: Atributo = Atributo(des, "Destreza")
        self.__con: Atributo = Atributo(con, "Constituição")
        self.__int: Atributo = Atributo(inte, "Inteligência")
        self.__sab: Atributo = Atributo(sab, "Sabedoria")
        self.__car: Atributo = Atributo(car, "Carisma")
    
    
    @property
    def forca(self) -> Atributo:
        return self.__for
    
    @property
    def destreza(self) -> Atributo:
        return self.__des

    @property
    def constitucao(self) -> Atributo:
        return self.__con
    
    @property
    def inteligencia(self) -> Atributo:
        return self.__int
    
    @property
    def sabedoria(self) -> Atributo:
        return self.__sab
    
    @property
    def carisma(self) -> Atributo:
        return self.__car

    def to_dict(self) -> dict:
        return {"forca": self.__for.to_dict(), "destreza": self.__des.to_dict(), "constituicao": self.__con.to_dict(), "inteligencia": self.__int.to_dict(), "sabedoria": self.__sab.to_dict(), "carisma": self.__car.to_dict()}
    
    def to_save(self) -> tuple[int]:
        return (self.__for.valor, self.__des.valor, self.__con.valor, self.__sab.valor, self.__car.valor)
    
    