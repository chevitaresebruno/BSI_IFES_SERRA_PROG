from flask import Blueprint, send_from_directory

from __init__ import STATIC_FOLDER


imgs = Blueprint("imgs", __name__)


@imgs.route('/favicon.ico')
def favicon():
    return send_from_directory(STATIC_FOLDER / 'imgs', 'favicon.ico')

