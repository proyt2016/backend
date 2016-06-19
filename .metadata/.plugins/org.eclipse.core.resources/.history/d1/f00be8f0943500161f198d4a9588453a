package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.ConfiguracionEmpresaLocalApi;
import lcbs.models.ConfiguracionEmpresa;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class CuponeraSrv
 */
@Stateless
public class ConfiguracionEmpresaSrv implements ConfiguracionEmpresaLocalApi {
	@Inject
    EntityManager em;
	
    private ConfiguracionEmpresaSrv(){
        
    }
    
    public ConfiguracionEmpresa getConfiguracionEmpresa(){
        //obtengo la configuracion de empresa de la bd
        Query query = em.createQuery("SELECT c FROM Cuponera c", ConfiguracionEmpresa.class);
        
        ConfiguracionEmpresa conf = (ConfiguracionEmpresa)query.getResultList().get(0);
        
        return conf;
    }
    
    public void modificarCuponera(ConfiguracionEmpresa conf){
        if(em.find(ConfiguracionEmpresa.class, conf.getId()) == null){
           throw new IllegalArgumentException("La configuracion no existe");
       }
       em.getTransaction().begin();
       em.merge(conf);
       em.getTransaction().commit();
    }
    
    public void crearConfiguracionEmpresa(ConfiguracionEmpresa conf){
        //guardo la configuracion de empresa en bd
        em.getTransaction().begin();
        em.persist(conf);
        em.getTransaction().commit();
    }
}
