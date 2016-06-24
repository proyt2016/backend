package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.PasajeLocalApi;
import lcbs.models.Pasaje;
import lcbs.shares.DataPasaje;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class PasajeSrv
 */
@Stateless
public class PasajeSrv implements PasajeLocalApi {
	@Inject
    EntityManager em;
	
    private PasajeSrv(){
        
    }
    
    public Map<String,DataPasaje> obtenerPasajes(){
    	Map<String,DataPasaje> Pasajes = new HashMap();
        //obtengo todos los Pasajes de la bd
        Query query = em.createQuery("SELECT p FROM Pasaje p", Pasaje.class);
        
        List<Pasaje> listPsj = query.getResultList();
        listPsj.stream().forEach((psj) -> {
        	Pasajes.put(psj.getId(), psj.getDatatype());
        });
        return Pasajes;
    }
    
    public void modificarPasaje(DataPasaje psj){
        Pasaje realObj = new Pasaje(psj);
    	if(em.find(Pasaje.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El Pasaje no existe");
       }
       em.merge(realObj);
    }
    
    public DataPasaje getPasaje(String id){
        return this.obtenerPasajes().get(id);
    }
    
    public DataPasaje crearPasaje(DataPasaje psj){
    	Pasaje realObj = new Pasaje(psj);
        //guardo el Pasaje en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void darBajaPasaje(DataPasaje psj){
        psj.setEliminado(true);
        this.modificarPasaje(psj);
    }

	@Override
	public Map<String, DataPasaje> obtenerPasajesPorPersona(String idUsuario) {
		Map<String,DataPasaje> Pasajes = new HashMap();
        //obtengo todos los Pasajes de la bd
        Query query = em.createQuery("SELECT p FROM Pasaje p", Pasaje.class);
        //TODO: Listar por usuario
        
        List<Pasaje> listPsj = query.getResultList();
        listPsj.stream().forEach((psj) -> {
        	Pasajes.put(psj.getId(), psj.getDatatype());
        });
        return Pasajes;
	}
}
