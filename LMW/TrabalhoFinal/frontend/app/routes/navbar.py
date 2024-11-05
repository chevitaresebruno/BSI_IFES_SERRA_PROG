from flask import Blueprint, jsonify, request, render_template, send_from_directory

from __init__ import *

navbar = Blueprint("navbar", __name__)


@navbar.route("/navbar_elements", methods=["POST"])
def navbar_elements():
    pacote = request.json["pacote"]

    elementos = [x for x in listdir(NAVBAR_TEMPLATES_FOLDER / pacote)  if not x.endswith(".html")]

    return jsonify(elementos)


@navbar.route("/navbar_option", methods=["POST"])
def navbar_get_html():
    pacote, file = request.json["pacote"], request.json["file"]
    
    json = {"pre_navbar": "", "pos_navbar": ""}

    for f in listdir(NAVBAR_TEMPLATES_FOLDER / f"{pacote}/{file}"):
        with open(NAVBAR_TEMPLATES_FOLDER / f"{pacote}/{file}/{f}", "r", encoding='utf-8') as reader:
            json[f[:f.find(".html")]] = reader.read()

    return jsonify(json)

