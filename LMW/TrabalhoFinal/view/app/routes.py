from flask import Blueprint, render_template, send_from_directory, request, jsonify

from .app import STATIC_FOLDER

from controller.testes.DadoController import DadoController
from model.propriedades.atributo.Atributo import Atributo


app = Blueprint("main", __name__)


@app.route('/favicon.ico')
def favicon():
    return send_from_directory(STATIC_FOLDER / 'imgs', 'favicon.ico')

    
@app.route('/')
def landpage():
    return render_template("landpage.html")


@app.route('/attributes')
def hello():
    return render_template("atributo/atributos.html", data={"Força": 10, "Destreza": 10, "Constituição": 10, "Inteligência": 10, "Sabedoria": 10, "Carisma": 10,})


@app.route('/roll_attribute', methods=["POST"])
def roll_attribute():
    data = request.json['numero']
    
    resultado = DadoController.rolar(buffs=Atributo.definir_modificador(data))
    
    return jsonify(resultado.to_dict())


@app.route("/personagens")
def personagens():
    return render_template("navbar/Meus Personagens.html")

