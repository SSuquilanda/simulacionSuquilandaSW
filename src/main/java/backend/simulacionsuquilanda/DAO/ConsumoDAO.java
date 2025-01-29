package backend.simulacionsuquilanda.DAO;

import backend.simulacionsuquilanda.Objetos.Consumo;
import backend.simulacionsuquilanda.Objetos.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ConsumoDAO {
    @PersistenceContext(unitName = "PostgresPU")
    private EntityManager em;

    @Transactional
    public void crearConsumo(Consumo consumo) {
        em.persist(consumo);
    }

    @Transactional
    public List<Consumo> listarConsumos(String cedula) {
        return em.createQuery("SELECT c FROM Consumo c WHERE c.usuario.cedula = :cedula", Consumo.class)
                .setParameter("cedula", cedula)
                .getResultList();
    }
}
