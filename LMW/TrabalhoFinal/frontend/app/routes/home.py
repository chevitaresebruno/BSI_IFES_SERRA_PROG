from flask import Blueprint, render_template


home = Blueprint("home", __name__)


@home.route('/')
def landpage():
    return render_template("landpage.html")


@home.route("/personagens")
def personagens():
    return render_template("navbar/home/Meus Personagens.html")

