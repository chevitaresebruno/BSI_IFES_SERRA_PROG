package views.menu;

import database.Sistema;
import models.usuarios.Usuario;

public interface IMenuView<Model extends Usuario>
{
    public void menu(Model u, Sistema s);    
}

