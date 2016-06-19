package lcbs.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import interfaces.IEncomienda;
import lcbs.beans.EncomiendaSrv;
import lcbs.interfaces.EncomiendaLocalApi;
import lcbs.interfaces.TerminalLocalApi;
import lcbs.interfaces.UsuarioLocalApi;
import lcbs.interfaces.VehiculoLocalApi;
import lcbs.shares.DataEncomienda;
import lcbs.shares.DataEstadosEncomienda;
import lcbs.shares.DataHistorialEstadosEncomienda;
import lcbs.shares.DataPuntoRecorrido;
import lcbs.shares.DataReglaCobroEncomienda;
import lcbs.shares.DataTerminal;
import lcbs.shares.DataUsuario;
import lcbs.shares.DataVehiculo;


/**
 * Session Bean implementation class EncomiendaSrv
 */
@Stateless
public class EncomiendaCtrl implements IEncomienda{
	

	@EJB(lookup="java:app/lcbsdb/EncomiendaSrv!lcbs.interfaces.EncomiendaLocalApi")
	EncomiendaLocalApi srvEncomienda;
	@EJB(lookup="java:app/lcbsdb/TerminalSrv!lcbs.interfaces.TerminalLocalApi")
	TerminalLocalApi srvTerminal;
	@EJB(lookup="java:app/lcbsdb/UsuarioSrv!lcbs.interfaces.UsuarioLocalApi")
	UsuarioLocalApi srvUsuario;
	@EJB(lookup="java:app/lcbsdb/VehiculoSrv!lcbs.interfaces.VehiculoLocalApi")
	VehiculoLocalApi srvVehiculo;
	

	@Override
	public List<DataEncomienda> ListarEncomiendas() {
		List<DataEncomienda> listaEncomiendas = (List<DataEncomienda>) srvEncomienda.obtenerEncomiendas();
		return listaEncomiendas;
		
	}

	@Override
	public List<DataTerminal> ListarTerminales() {
		List<DataTerminal> listaTerminales = (List<DataTerminal>) srvTerminal.obtenerTerminals();
		return listaTerminales;
	}

	@Override
	public List<DataVehiculo> ListarVehiculos() {
	    List<DataVehiculo> listaVehiculos = (List<DataVehiculo>) srvVehiculo.obtenerVehiculos();
		return listaVehiculos;
	}

	@Override
	public List<DataUsuario> ListarUsuarios() {
		List<DataUsuario> listaUsuarios = (List<DataUsuario>) srvUsuario.obtenerUsuarios();
		return listaUsuarios;
	}

	@Override
	public List<DataReglaCobroEncomienda> getReglasDeCobro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataReglaCobroEncomienda getReglaDeCobro(String idEncomieda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void AltaEncomienda(DataEncomienda encomienda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DataPuntoRecorrido> getRecorridos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataHistorialEstadosEncomienda> getHistorialEstado(String idEncomienda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataEstadosEncomienda getUltimoEstado(String idEncomienda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVehiculo getVehiculo(String idVehiculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataEncomienda getEncomienda(String idEncomienda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTerminal getTerminal(String idTerminal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setEstadoEncomiendaIndividual(String idEncomienda, DataEstadosEncomienda dataEstado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DataEncomienda> getEncomiendasPorVehiculo(String idVehiculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setEstadoEncomiendaPorVehiculo(String idEncomienda, DataEstadosEncomienda estado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void AsignarEncomiendasVehiculo(DataVehiculo vehiculo, String IdEncomienda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DataHistorialEstadosEncomienda> VerEstadoEncomienda(String idEncomienda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SetPrecioEncomienda(String idEncomienda, DataReglaCobroEncomienda dataCobro) {
		// TODO Auto-generated method stub
		
	}
  


}
