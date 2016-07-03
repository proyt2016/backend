/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lcbs.api.service;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import interfaces.*;
import lcbs.shares.*;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
@Remote
public class ViajeRepo {
	@EJB(lookup =  "java:app/lcbsb/ViajeCtrl!interfaces.IViaje")
	IViaje ctrViaje;
	
	public Map<String, DataViaje> BuscarViaje(DataViaje filtro, Integer pagina, Integer ElementosPagina){
		return ctrViaje.BuscarViaje(filtro, pagina, ElementosPagina);
	}
	
	public DataPasaje ComprarPasaje(DataPasaje pasaje){
		return ctrViaje.ComprarPasaje(pasaje);
	}
	
	public void CambiarHorarioPasaje(String idPasaje, String viaje){
		ctrViaje.CambiarHorarioPasaje(idPasaje, viaje);
	}
	
	public DataReserva ReservarPasaje(DataReserva reserva){
		return ctrViaje.ReservarPasaje(reserva);
	}
	
	public void TransferirPasajeComprado(String idPasaje, String idUsuario){
		ctrViaje.TransferirPasajeComprado(idPasaje, idUsuario);
	}
	
	public void CancelarReserva(String idReserva){
		ctrViaje.CancelarReserva(idReserva);
	}
	
	public Map<String, DataReserva> ListarReservas(String idUsuario){
		return ctrViaje.ListarReservas(idUsuario);
	}
	
	public void ProcesarPasajes(String idPasaje){
		ctrViaje.ProcesarPasajes(idPasaje);
	}
	
	public DataParada AltaParadas(DataParada parada){
		return ctrViaje.AltaParadas(parada);
	}
	
	public DataTerminal AltaTerminal(DataTerminal terminal){
		return ctrViaje.AltaTerminal(terminal);
	}
	
	public void EditarParada(DataParada parada){
		ctrViaje.EditarParada(parada);
	}
	
	public void EditarTerminal(DataTerminal terminal){
		ctrViaje.EditarTerminal(terminal);
	}
	
	public DataRecorrido CrearRecorrido(DataRecorrido recorrido){
		return ctrViaje.CrearRecorrido(recorrido);
	}
	
	public void EditarRecorrido(DataRecorrido recorrido){
		ctrViaje.EditarRecorrido(recorrido);
	}
	
	public DataRecorrido obtenerRecorrido(String idRecorrido){
		return ctrViaje.obtenerRecorrido(idRecorrido);
	}
	
	public DataPasaje ComprarPasajeReservado(DataReserva reserva){
		return ctrViaje.ComprarPasajeReservado(reserva);
	}
	
	public DataParada obtenerParada(String IdParada){
		return ctrViaje.obtenerParada(IdParada);
	}
	
	public Map<String, DataPasaje> obtenerHistorialPasajes(String idUsuario){
		return ctrViaje.obtenerHistorialPasajes(idUsuario);
	}
	
	public DataPasaje verDetallePasaje(String idPasaje){
		return ctrViaje.verDetallePasaje(idPasaje);
	}
	
	public DataTerminal obtenerTerminal(String IdTerminal){
		return ctrViaje.obtenerTerminal(IdTerminal);
	}
	
	public void crearViaje(DataViaje viaje){
		ctrViaje.crearViaje(viaje);
	}
	
	public void editarViaje(DataViaje viaje){
		ctrViaje.editarViaje(viaje);
	}
	
	public void eliminarViaje(String idViaje){
		ctrViaje.eliminarViaje(idViaje);
	}
	public List<DataTerminal> getTerminales(int pagina, int elementos){
		return ctrViaje.getTerminales(pagina, elementos);
	}
}
