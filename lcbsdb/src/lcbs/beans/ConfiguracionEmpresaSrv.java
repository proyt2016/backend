package lcbs.beans;

import java.util.List;

import javax.ejb.Stateless;
import lcbs.shares.*;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import lcbs.interceptors.TenantIntercept;
import lcbs.interfaces.ConfiguracionEmpresaLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.schema.SchemaHandler;


/**
 * Session Bean implementation class CuponeraSrv
 */
@Stateless
@Interceptors ({TenantIntercept.class})
public class ConfiguracionEmpresaSrv implements ConfiguracionEmpresaLocalApi {
	@Inject
    EntityManager em;
	public ConfiguracionEmpresaSrv(){
        
    }
    
    public DataConfiguracionEmpresa getConfiguracionEmpresa(DataTenant tenant){
        //obtengo la configuracion de empresa de la bd
        Session session = (Session) em.getDelegate();
        List<ConfiguracionEmpresa> listEnc = session.createCriteria(ConfiguracionEmpresa.class).list();
        ConfiguracionEmpresa conf;
        if(listEnc.size() > 0){
        	conf = listEnc.get(0);
        }else{
        	DataConfiguracionEmpresa newConf = new DataConfiguracionEmpresa();
        	newConf.setNombre("Nueva empresa");
        	newConf = crearConfiguracionEmpresa(newConf, tenant);
        	conf = new ConfiguracionEmpresa(newConf);
        }
        
        return conf.getDatatype();
    }
    
    public void modificarCuponera(DataConfiguracionEmpresa conf, DataTenant tenant){
    	ConfiguracionEmpresa realObj = new ConfiguracionEmpresa(conf);
    	if(em.find(ConfiguracionEmpresa.class, realObj.getId()) == null){
           throw new IllegalArgumentException("La configuracion no existe");
    	}
    	em.merge(realObj);
    }
    
    public DataConfiguracionEmpresa crearConfiguracionEmpresa(DataConfiguracionEmpresa conf, DataTenant tenant){
    	ConfiguracionEmpresa realObj = new ConfiguracionEmpresa(conf);
        //guardo la configuracion de empresa en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
}
