package backend.simulacionsuquilanda.Gestion;

import backend.simulacionsuquilanda.DAO.ConsumoDAO;
import backend.simulacionsuquilanda.Objetos.Consumo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ConsumoGestion {
    @Inject
    private ConsumoDAO consumoDAO;
    @PersistenceContext
    private EntityManager em;


    public void crearConsumo(Consumo consumo) {
        consumoDAO.crearConsumo(consumo);
    }

    public List<Consumo> listarConsumos(String cedula) {
        return consumoDAO.listarConsumos(cedula);

    }
    @Transactional
    public List<Consumo> listarConsumosPorUsuarioId(Long usuarioId) {
        return em.createQuery("SELECT c FROM Consumo c WHERE c.usuario.id = :usuarioId", Consumo.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }

}

