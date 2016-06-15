package lcbs.controllers;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import lcbs.interfaces.EntityLocalApi;

/**
 * Session Bean implementation class EncomiendaSrv
 */
@Stateless
public class EncomiendaCtrl implements LocalApi{
	
	//@EJB(lookup="java:app/lcbsdb/EncomiendaSrv!lcbs.interfaces.EntityLocalApi")
	//EntityLocalApi srv;
    /**
     * Default constructor. 
     */
    public EncomiendaCtrl() {
        // TODO Auto-generated constructor stub
    	System.out.println("sape");
    
    }
    public String getSape(){
    	return "";  //srv.GetSape();
    }

}
