from pathlib import Path
from os import listdir

from flask import Flask


TEMPLATES_FOLDER: Path = Path("templates")
STATIC_FOLDER: Path = Path("static")
NAVBAR_TEMPLATES_FOLDER: Path = TEMPLATES_FOLDER / Path("navbar")
COMPONENTS_FOLDER: Path = STATIC_FOLDER / "js/components"