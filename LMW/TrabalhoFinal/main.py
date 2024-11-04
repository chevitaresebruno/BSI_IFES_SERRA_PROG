from flask import Flask

from view.app.app import TEMPLATES_FOLDER, STATIC_FOLDER
from view.app.routes import app



main = Flask(__name__, template_folder=TEMPLATES_FOLDER, static_folder=STATIC_FOLDER)
    
if __name__ == '__main__':
    main.register_blueprint(app)
    main.run(debug=True)


"""
if __name__ == '__main__':
    from model.player.Player import Player
    from controller.testes.PericiaHandler import PericiaHandler
    from model.propriedades.recurso.Vida import Vida
    from model.propriedades.recurso.Mana import Mana
    from model.player.Atributos import Atributos
    from model.propriedades.pericia.Pericia import PERICIAS
    from controller.testes.DadoController import DadoController
    
    
    player = Player(Atributos(), Vida(20, 20, 0), Mana(10, 10, 0), 5, {0, 1, 2})
    
    save = DadoController.rolar_str("d20+15+5+d6c6")
    print(save)
    print(DadoController.somar_lista_de_dados(save))
"""