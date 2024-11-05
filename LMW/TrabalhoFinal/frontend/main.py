from __init__ import *


from app.routes.home import home
from app.routes.imgs import imgs
from app.routes.personagem import personagem
from app.routes.navbar import navbar


main = Flask(__name__, template_folder=TEMPLATES_FOLDER, static_folder=STATIC_FOLDER)
    

if __name__ == '__main__':
    main.register_blueprint(home)
    main.register_blueprint(imgs)
    main.register_blueprint(personagem)
    main.register_blueprint(navbar)

    main.run(debug=True, host=HOST, port=PORT)

