package lcbs.beans;

import java.util.List;

import javax.ejb.Stateless;
import lcbs.shares.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import lcbs.interfaces.ConfiguracionEmpresaLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Encomienda;


/**
 * Session Bean implementation class CuponeraSrv
 */
@Stateless
public class ConfiguracionEmpresaSrv implements ConfiguracionEmpresaLocalApi {
	@Inject
    EntityManager em;
	
    private ConfiguracionEmpresaSrv(){
        
    }
    
    public DataConfiguracionEmpresa getConfiguracionEmpresa(){
        //obtengo la configuracion de empresa de la bd
        Session session = (Session) em.getDelegate();
        List<ConfiguracionEmpresa> listEnc = session.createCriteria(ConfiguracionEmpresa.class).list();
        
        ConfiguracionEmpresa conf = listEnc.get(0);
        
        return conf.getDatatype();
    }
    
    public void modificarCuponera(DataConfiguracionEmpresa conf){
    	ConfiguracionEmpresa realObj = new ConfiguracionEmpresa(conf);
    	if(em.find(ConfiguracionEmpresa.class, realObj.getId()) == null){
           throw new IllegalArgumentException("La configuracion no existe");
    	}
    	em.merge(realObj);
    }
    
    public DataConfiguracionEmpresa crearConfiguracionEmpresa(DataConfiguracionEmpresa conf){
    	ConfiguracionEmpresa realObj = new ConfiguracionEmpresa(conf);
        //guardo la configuracion de empresa en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
}
