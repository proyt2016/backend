package lcbs.beans;

import javax.ejb.LocalBean;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class EntityManagerProducer
 */
@ApplicationScoped
@LocalBean
public class EntityManagerProducer {

	@PersistenceContext(unitName = "lcbsdb")
    private EntityManager em;

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return em;
    }
}