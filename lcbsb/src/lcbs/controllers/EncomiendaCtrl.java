package lcbs.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import interfaces.IEncomienda;
import lcbs.interfaces.EncomiendaLocalApi;
import lcbs.interfaces.EstadosEncomiendaLocalApi;
import lcbs.interfaces.RecorridoLocalApi;
import lcbs.interfaces.ReglaCobroEncomiendaLocalApi;
import lcbs.interfaces.TerminalLocalApi;
import lcbs.interfaces.UsuarioLocalApi;
import lcbs.interfaces.VehiculoLocalApi;
import lcbs.interfaces.ViajeLocalApi;
import lcbs.shares.DataEncomienda;
import lcbs.shares.DataEstadosEncomienda;
import lcbs.shares.DataHistorialEstadosEncomienda;
import lcbs.shares.DataRecorrido;
import lcbs.shares.DataReglaCobroEncomienda;
import lcbs.shares.DataTerminal;
import lcbs.shares.DataUsuario;
import lcbs.shares.DataVehiculo;
import lcbs.shares.DataViaje;


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
	@EJB(lookup="java:app/lcbsdb/ReglaCobroEncomiendaSrv!lcbs.interfaces.ReglaCobroEncomiendaLocalApi")
	ReglaCobroEncomiendaLocalApi srvReglaCobro;
	@EJB(lookup="java:app/lcbsdb/RecorridoSrv!lcbs.interfaces.RecorridoLocalApi")
	RecorridoLocalApi srvRecorrido;
	@EJB(lookup="java:app/lcbsdb/EstadosEncomiendaSrv!lcbs.interfaces.EstadosEncomiendaLocalApi")
	EstadosEncomiendaLocalApi srvEstadosEncomienda;
	@EJB(lookup="java:app/lcbsdb/ViajeSrv!lcbs.interfaces.ViajeLocalApi")
	ViajeLocalApi srvViaje;
	

	@Override
	public List<DataEncomienda> ListarEncomiendas() {
		List<DataEncomienda> listaEncomiendas = new ArrayList( srvEncomienda.obtenerEncomiendas().values());
		return listaEncomiendas;
		
	}

	@Override
	public List<DataTerminal> ListarTerminales() {
		@SuppressWarnings("unchecked")
		List<DataTerminal> listaTerminales = new ArrayList( srvTerminal.obtenerTerminals().values());
		return listaTerminales;
	}

	@Override
	public List<DataVehiculo> ListarVehiculos() {
	    @SuppressWarnings("unchecked")
		List<DataVehiculo> listaVehiculos = new ArrayList( srvVehiculo.obtenerVehiculos().values());
		return listaVehiculos;
	}

	@Override
	public List<DataUsuario> ListarUsuarios() {
		@SuppressWarnings("unchecked")
		List<DataUsuario> listaUsuarios = new ArrayList(srvUsuario.obtenerUsuarios().values());
		return listaUsuarios;
	}

	@Override
	public List<DataReglaCobroEncomienda> getReglasDeCobro() {
		@SuppressWarnings("unchecked")
		List<DataReglaCobroEncomienda> listaReglaCobro = new ArrayList(srvReglaCobro.obtenerReglaCobroEncomiendas().values());
		return listaReglaCobro;
	}

	@Override
	public DataReglaCobroEncomienda getReglaDeCobro(String idEncomieda) {
		DataReglaCobroEncomienda dataReglaCobro = srvReglaCobro.getReglaCobroEncomienda(idEncomieda);
		return dataReglaCobro;
	}

	@Override
	public void AltaEncomienda(DataEncomienda encomienda) {
		srvEncomienda.crearEncomienda(encomienda);
	}

	@Override
	public List<DataRecorrido> getRecorridos() {
		List<DataRecorrido> listaRecorridos = new ArrayList(srvRecorrido.obtenerRecorridos().values());
		return listaRecorridos;
	}

	@Override
	public List<DataHistorialEstadosEncomienda> getHistorialEstado(String idEncomienda) {
		List<DataHistorialEstadosEncomienda> listaHistoriales = new ArrayList(srvEstadosEncomienda.obtenerEstadosEncomienda().values());
		return listaHistoriales;
		
	}

	@Override
	public DataEstadosEncomienda getUltimoEstado(String idEncomienda) {
		DataEncomienda dataEncomienda = srvEncomienda.getEncomienda(idEncomienda);
		return dataEncomienda.getEstadoActual();
	}

	@Override
	public DataVehiculo getVehiculo(String idVehiculo) {
		return srvVehiculo.getVehiculo(idVehiculo);
	}

	@Override
	public DataEncomienda getEncomienda(String idEncomienda) {
		return srvEncomienda.getEncomienda(idEncomienda);
	}

	@Override
	public DataTerminal getTerminal(String idTerminal) {
		return srvTerminal.getTerminal(idTerminal);
	}

	@Override
	public void setEstadoEncomienda(String idEncomienda, DataEstadosEncomienda dataEstado) {
		Date fecha = new Date();
		DataEncomienda encomienda = srvEncomienda.getEncomienda(idEncomienda);
		encomienda.setEstadoActual(dataEstado);
		DataHistorialEstadosEncomienda historialEstados = new DataHistorialEstadosEncomienda();
		historialEstados.setEstado(dataEstado);
		historialEstados.setFecha(fecha);
		encomienda.getEstados().add(historialEstados);
		srvEncomienda.modificarEncomienda(encomienda);
	}

	@Override
	public List<DataEncomienda> getEncomiendasPorVehiculo(String idVehiculo, String idViaje) {
		List<DataEncomienda> listaEncomiendas = new ArrayList<DataEncomienda>();
		DataViaje viaje = srvViaje.getViaje(idViaje);
		for(DataEncomienda de : viaje.getEncomiendas()){
			if(viaje.getCoche().getId() == idVehiculo){
				listaEncomiendas.add(de);
			}
		}
		return listaEncomiendas;
	}

	@Override
	public void AsignarEncomiendasVehiculo(String idVehiculo, String IdEncomienda,String idViaje) {
		DataViaje viaje = srvViaje.getViaje(idViaje);
		DataEncomienda encomienda = srvEncomienda.getEncomienda(IdEncomienda);
		if(viaje.getCoche().getId()==idVehiculo){
			viaje.getEncomiendas().add(encomienda);
		}
	}

	@Override
	public List<DataHistorialEstadosEncomienda> VerEstadosEncomienda(String idEncomienda) {
		DataEncomienda encomienda = srvEncomienda.getEncomienda(idEncomienda);
		return encomienda.getEstados();
	}

	@Override
	public void SetPrecioEncomienda(String idEncomienda, DataReglaCobroEncomienda dataCobro) {
		DataEncomienda encomienda = srvEncomienda.getEncomienda(idEncomienda);
		encomienda.setReglaCobro(dataCobro);
		srvEncomienda.modificarEncomienda(encomienda);
	}
  


}
