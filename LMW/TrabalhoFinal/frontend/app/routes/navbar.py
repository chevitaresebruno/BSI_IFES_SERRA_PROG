from flask import Blueprint, jsonify, request, render_template, send_from_directory

from __init__ import *

navbar = Blueprint("navbar", __name__)


@navbar.route('/navbar_elements/<string:pacote>', methods=["GET"])
def navbar_elements(pacote):
    elementos = [x[:x.find(".html")] for x in listdir(NAVBAR_TEMPLATES_FOLDER / pacote)]
    return jsonify(elementos)


@navbar.route("/navbar_option/<string:pacote>/<string:file>")
def navbar_get_html(pacote, file):
    return render_template(f"navbar/{pacote}/{file}.html")

