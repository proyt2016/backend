package lcbs.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import interfaces.IEmpresa;
import lcbs.interfaces.*;
import lcbs.shares.*;

import javax.ejb.Stateless;




/**
 * Session Bean implementation class EmpresaCtrl
 */
@Stateless
public class EmpresaCtrl implements IEmpresa{
	
	@EJB(lookup="java:app/lcbsdb/ConfiguracionEmpresaSrv!lcbs.interfaces.ConfiguracionEmpresaLocalApi")
	ConfiguracionEmpresaLocalApi srvEmpresa;
	
	@Override
	public DataConfiguracionEmpresa obtenerConfiguracionEmpresa(){
		return srvEmpresa.getConfiguracionEmpresa();
	}
	
	@Override
	public void editarConfiguracionEmpresa(DataConfiguracionEmpresa empresa){
		srvEmpresa.modificarCuponera(empresa);
	}

	// TODO: agregar la parte de congiguracion de UI
}
