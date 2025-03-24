package views.user_case.usuario;

import database.filters.usuario.UsuarioFilter;
import database.services.usuario.UsuarioService;
import models.usuarios.Usuario;
import views.user_case.UCGenericCRUD;


public abstract class UCGerenciarUsuarios<Model extends Usuario, ModelFilter extends UsuarioFilter<Model>, ModelService extends UsuarioService<Model>> implements UCGenericCRUD<Model, ModelFilter, ModelService>
{
    public static void imprimirUsuario(Usuario usuario)
    {
        System.out.println(String.format("Usuário %s", usuario.toString()));
    }
}

