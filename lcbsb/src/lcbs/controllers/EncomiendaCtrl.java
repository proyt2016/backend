package lcbs.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import interfaces.IEncomienda;
import lcbs.beans.EncomiendaSrv;
import lcbs.interfaces.EncomiendaLocalApi;
import lcbs.models.Encomienda;
import lcbs.shares.DataEncomienda;
import lcbs.shares.DataEstadosEncomienda;
import lcbs.shares.DataHistorialEstadosEncomienda;
import lcbs.shares.DataReglaCobroEncomienda;

/**
 * Session Bean implementation class EncomiendaSrv
 */
@Stateless
public class EncomiendaCtrl implements LocalApi{
	
	@EJB(lookup="java:app/lcbsdb/EncomiendaSrv!lcbs.interfaces.IEncomienda")
	EncomiendaSrv serviceEncomienda;

	@Override
	public String getSape() {
		// TODO Auto-generated method stub
		return null;
	}
      


}
