package backend.simulacionsuquilanda.DAO;

import backend.simulacionsuquilanda.Objetos.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioDAO {
    @PersistenceContext(unitName = "PostgresPU")
    private EntityManager em;

    @Transactional
    public void crearUsuario(Usuario usuario) {
        em.persist(usuario);
    }

    @Transactional
    public Usuario actualizarUsuario(Usuario usuario) {
        return em.merge(usuario);
    }
    @Transactional
    public Usuario buscarPorCedula(String cedula) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.cedula = :cedula", Usuario.class)
                    .setParameter("cedula", cedula)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Manejo del caso en el que no se encuentra el cliente
        }
    }

}
