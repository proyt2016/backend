package lcbs.controllers;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import lcbs.interfaces.EncomiendaLocalApi;
import lcbs.shares.*;

/**
 * Session Bean implementation class EncomiendaSrv
 */
@Stateless
public class EncomiendaCtrl implements LocalApi{
	
	@EJB(lookup="java:app/lcbsdb/EncomiendaSrv!lcbs.interfaces.EncomiendaLocalApi")
	EncomiendaLocalApi srv;
  
    public String getSape(){
    	DataEncomienda aCrear = new DataEncomienda();
    	aCrear.setCiEmisor("4223232-2");
    	
    	srv.crearEncomienda(aCrear);
    	System.out.println("#################################################################################### "+aCrear.getId());
    	return "sape";
    }

}
