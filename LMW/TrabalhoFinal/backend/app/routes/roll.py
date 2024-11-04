from flask import Blueprint, request, jsonify

from __init__ import *

roll = Blueprint("main", __name__)

@roll.route('/roll_attribute', methods=["POST"])
def roll_attribute():
    data = request.json['numero']
    
    resultado = DadoController.rolar(buffs=Atributo.definir_modificador(data))
    
    return jsonify(resultado.to_dict())

