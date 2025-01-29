package backend.simulacionsuquilanda.Gestion;

import backend.simulacionsuquilanda.DAO.UsuarioDAO;
import backend.simulacionsuquilanda.Objetos.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioGestion {

    @Inject
    private UsuarioDAO usuarioDAO;

    public void crearUsuario(Usuario usuario) {
        usuarioDAO.crearUsuario(usuario);
    }
    public Usuario obtenerUsuarioPorCedula(String cedula) {
        return usuarioDAO.buscarPorCedula(cedula);
    }


}
