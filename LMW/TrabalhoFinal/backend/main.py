from __init__ import *

from app.routes.roll import roll


main = Flask(__name__)
    

if __name__ == '__main__':
    CORS(main)
    
    main.register_blueprint(roll)
    main.run(debug=True, port=8000)

