package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import lcbs.interfaces.EntityLocalApi;
import lcbs.models.Encomienda;

/**
 * Session Bean implementation class EncomiendaSrv
 */
@Stateless
public class EncomiendaSrv implements EntityLocalApi{

    @Inject
    EntityManager em;
    
     public EncomiendaSrv(){
    //	 Session s = (Session)em;
    //	 s.persist(new Encomienda());
     }
     public String GetSape(){
    	 Encomienda enc = new Encomienda();
    	 enc.setCiEmisor("43783306");
    	 em.persist(enc);
    	 
    	 
     	return  String.valueOf(enc.getId());
     }
// 	public void CreateEncomienda(EncomiendaDT dt){}
}
