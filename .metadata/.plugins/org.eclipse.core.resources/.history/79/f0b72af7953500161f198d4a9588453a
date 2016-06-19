package lcbs.controllers;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import lcbs.interfaces.EncomiendaLocalApi;
import lcbs.models.Encomienda;

/**
 * Session Bean implementation class EncomiendaSrv
 */
@Stateless
public class EncomiendaCtrl implements LocalApi{
	
	@EJB(lookup="java:app/lcbsdb/EncomiendaSrv!lcbs.interfaces.EncomiendaLocalApi")
	EncomiendaLocalApi srv;
  
    public String getSape(){
    	srv.crearEncomienda(new Encomienda());
    	return "sape";
    }

}
