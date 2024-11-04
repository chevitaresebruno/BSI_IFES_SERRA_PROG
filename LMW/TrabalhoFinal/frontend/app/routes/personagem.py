from flask import Blueprint, render_template


personagem = Blueprint("persongem", __name__)


@personagem.route('/atributos')
def atributos():
    return render_template("atributo/atributos.html", data={"Força": 10, "Destreza": 10, "Constituição": 10, "Inteligência": 10, "Sabedoria": 10, "Carisma": 10,})

