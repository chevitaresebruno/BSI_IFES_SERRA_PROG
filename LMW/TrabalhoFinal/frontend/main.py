from __init__ import *


from app.routes.home import home
from app.routes.imgs import imgs
from app.routes.personagem import personagem


main = Flask(__name__, template_folder=TEMPLATES_FOLDER, static_folder=STATIC_FOLDER)
    

if __name__ == '__main__':
    main.register_blueprint(home)
    main.register_blueprint(imgs)
    main.register_blueprint(personagem)

    main.run(debug=True)

