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
import javax.ws.rs.PathParam;

import interfaces.*;
import lcbs.shares.*;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
@Remote
public class ViajeRepo {
	@EJB(lookup =  "java:app/lcbsb/ViajeCtrl!interfaces.IViaje")
	IViaje ctrViaje;
	
	public List<DataViaje> BuscarViaje(DataViaje filtro, Integer pagina, Integer ElementosPagina){
		return ctrViaje.BuscarViaje(filtro, pagina, ElementosPagina);
	}

	public List<DataViaje> viajesPorTerminal(String idterminal, Integer pagina, Integer ElementosPagina){
		return	ctrViaje.viajesPorTerminal(idterminal,pagina,ElementosPagina);
	}
	
	public List<DataPasajeConvertor> getPasajes(Integer pagina, Integer ElementosPagina){
		return ctrViaje.getPasajes(pagina, ElementosPagina);
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
	
	public List<DataReserva> ListarReservas(String idUsuario){
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
	
	public List<DataViaje> getViajes(int pagina,int elementos){
		return ctrViaje.getViajes(pagina, elementos);
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
	
	public List<DataRecorrido> listarRecorridos(Integer pagina, Integer elementosPagina) {
		return ctrViaje.listarRecorridos(pagina, elementosPagina);
	}
	
	public DataPasaje ComprarPasajeReservado(DataReserva reserva){
		return ctrViaje.ComprarPasajeReservado(reserva);
	}
	
	public DataParada obtenerParada(String IdParada){
		return ctrViaje.obtenerParada(IdParada);
	}
	
	public List<DataPasaje> obtenerHistorialPasajes(String idUsuario, Integer pagina, Integer elementosPagina){
		return ctrViaje.obtenerHistorialPasajes(idUsuario, pagina, elementosPagina);
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
	
	public List<DataParada> getParadas(Integer pagina, Integer elementosPagina) {
		return ctrViaje.getParadas(pagina, elementosPagina);
	}

	public void BajaRecorrido(String idRecorrido) {
		ctrViaje.BajaRecorrido(idRecorrido);		
	}

	public DataPuntoRecorrido obtenerPuntoRecorrido(String idPunto) {
		return ctrViaje.obtenerPuntoRecorrido(idPunto);
	}

	public DataPuntoRecorrido obtenerPuntoPorCoordenada(String coord) {
		return ctrViaje.obtenerPuntoPorCoordenada(coord);
	}

	public DataViaje getViaje(String idViaje) {
		return ctrViaje.getViaje(idViaje);
	}
}
