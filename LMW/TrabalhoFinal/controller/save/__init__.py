from pathlib import Path
from json import dump, load
from os.path import exists
from os import mkdir

from model.player.Player import Player

from model.player.Atributos import Atributos
from model.propriedades.recurso.Vida import Vida
from model.propriedades.recurso.Mana import Mana

DB_NAME: Path = Path("db")
