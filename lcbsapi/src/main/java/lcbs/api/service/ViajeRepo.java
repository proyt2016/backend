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

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import interfaces.*;
import lcbs.shares.*;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
@Remote
public class ViajeRepo {
	@EJB(lookup =  "java:app/lcbsb/ViajeCtrl!interfaces.IViaje")
	IViaje ctrViaje;
	
	public List<DataPasajeConvertor> obtenerTotalPasajesVendidos(String fecha, Integer pagina, Integer elementosPagina, DataTenant tenant){
		return ctrViaje.obtenerTotalPasajesVendidos(fecha, pagina, elementosPagina, tenant);
	}
	
	public List<DataViaje> BuscarViaje(DataViaje filtro, Integer cantidadDias, Integer pagina, Integer ElementosPagina, DataTenant tenant) throws ParseException{
		return ctrViaje.BuscarViaje(filtro, cantidadDias, pagina, ElementosPagina, tenant );
	}

	public List<DataViaje> viajesPorTerminal(String idterminal, Integer pagina, Integer ElementosPagina, DataTenant tenant){
		return	ctrViaje.viajesPorTerminal(idterminal,pagina,ElementosPagina, tenant );
	}
	
	public List<DataPasajeConvertor> getPasajes(Integer pagina, Integer ElementosPagina, DataTenant tenant){
		return ctrViaje.getPasajes(pagina, ElementosPagina, tenant );
	}
	
	public DataPasaje ComprarPasaje(DataPasaje pasaje, DataTenant tenant){
		return ctrViaje.ComprarPasaje(pasaje, tenant );
	}
	
	public DataPasaje getPasajeXcodigo(Integer codigoPasaje, DataTenant tenant){
		return ctrViaje.getPasajeXCodigo(codigoPasaje, tenant );
	}
	
	public void CambiarHorarioPasaje(String idPasaje, String viaje, DataTenant tenant){
		ctrViaje.CambiarHorarioPasaje(idPasaje, viaje, tenant );
	}
	
	public DataReserva ReservarPasaje(DataReserva reserva, DataTenant tenant){
		return ctrViaje.ReservarPasaje(reserva, tenant );
	}
	
	public void TransferirPasajeComprado(String idPasaje, String idUsuario, DataTenant tenant){
		ctrViaje.TransferirPasajeComprado(idPasaje, idUsuario, tenant );
	}
	
	public void CancelarReserva(String idReserva, DataTenant tenant){
		ctrViaje.CancelarReserva(idReserva, tenant );
	}
	
	public List<DataReserva> ListarReservas(String idUsuario, DataTenant tenant){
		return ctrViaje.ListarReservas(idUsuario, tenant ); 
	}
	
	public void ProcesarPasajes(String idPasaje, DataTenant tenant){
		ctrViaje.ProcesarPasajes(idPasaje, tenant );
	}
	
	public DataParada AltaParadas(DataParada parada, DataTenant tenant){
		return ctrViaje.AltaParadas(parada, tenant );
	}
	
	public DataTerminal AltaTerminal(DataTerminal terminal, DataTenant tenant){
		return ctrViaje.AltaTerminal(terminal, tenant );
	}
	
	public void EditarParada(DataParada parada, DataTenant tenant){
		ctrViaje.EditarParada(parada, tenant );
	}
	
	public List<DataViaje> getViajes(Integer pagina,Integer elementos, DataTenant tenant){
		return ctrViaje.getViajes(pagina, elementos, tenant );
	}
	
	public void EditarTerminal(DataTerminal terminal, DataTenant tenant){
		ctrViaje.EditarTerminal(terminal, tenant );
	}
	
	public DataRecorrido CrearRecorrido(DataRecorrido recorrido, DataTenant tenant){
		return ctrViaje.CrearRecorrido(recorrido, tenant );
	}
	
	public void EditarRecorrido(DataRecorrido recorrido, DataTenant tenant){
		ctrViaje.EditarRecorrido(recorrido, tenant );
	}
	
	public DataRecorrido obtenerRecorrido(String idRecorrido, DataTenant tenant){
		return ctrViaje.obtenerRecorrido(idRecorrido, tenant );
	}
	
	public List<DataRecorrido> listarRecorridos(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrViaje.listarRecorridos(pagina, elementosPagina, tenant );
	}
	
	public DataPasaje ComprarPasajeReservado(DataReserva reserva, DataTenant tenant){
		return ctrViaje.ComprarPasajeReservado(reserva, tenant );
	}
	
	public DataParada obtenerParada(String IdParada, DataTenant tenant){
		return ctrViaje.obtenerParada(IdParada, tenant );
	}
	
	public List<DataPasajeConvertor> obtenerHistorialPasajes(String idUsuario, Integer pagina, Integer elementosPagina, DataTenant tenant){
		return ctrViaje.obtenerHistorialPasajes(idUsuario, pagina, elementosPagina, tenant );
	}
	
	public DataPasaje verDetallePasaje(String idPasaje, DataTenant tenant){
		return ctrViaje.verDetallePasaje(idPasaje, tenant );
	}
	
	public DataTerminal obtenerTerminal(String IdTerminal, DataTenant tenant){
		return ctrViaje.obtenerTerminal(IdTerminal, tenant );
	}
	
	public void crearViaje(DataViaje viaje, DataTenant tenant){
		ctrViaje.crearViaje(viaje, tenant );
	}
	
	public void editarViaje(DataViaje viaje, DataTenant tenant){
		ctrViaje.editarViaje(viaje, tenant );
	}
	
	public void eliminarViaje(String idViaje, DataTenant tenant){
		ctrViaje.eliminarViaje(idViaje, tenant );
	}
	public List<DataTerminal> getTerminales(Integer pagina, Integer elementos, DataTenant tenant){
		return ctrViaje.getTerminales(pagina, elementos, tenant );
	}
	
	public List<DataParada> getParadas(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrViaje.getParadas(pagina, elementosPagina, tenant );
	}

	public void BajaRecorrido(String idRecorrido, DataTenant tenant) {
		ctrViaje.BajaRecorrido(idRecorrido, tenant );		
	}

	public DataPuntoRecorrido obtenerPuntoRecorrido(String idPunto, DataTenant tenant) {
		return ctrViaje.obtenerPuntoRecorrido(idPunto, tenant );
	}

	public DataPuntoRecorrido obtenerPuntoPorCoordenada(String coord, DataTenant tenant) {
		return ctrViaje.obtenerPuntoPorCoordenada(coord, tenant );
	}

	public DataViaje getViaje(String idViaje, DataTenant tenant) {
		return ctrViaje.getViaje(idViaje, tenant );
	}

	public List<DataRecorrido> BuscarRecorrido(DataRecorrido filtro, Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrViaje.BuscarRecorrido(filtro, pagina, elementosPagina, tenant );
	}

	public DataReserva obtenerReserva(String idReserva, DataTenant tenant) {
		return ctrViaje.obtenerReserva(idReserva, tenant );
	}
	
	public void crearHorarioRecorrido(DataGrupoHorario horario, String idRecorrido, DataTenant tenant){
		ctrViaje.crearHorarioRecorrido(horario, idRecorrido, tenant);
	}

	public void editarHorarioRecorrido(DataGrupoHorario horario, String idRecorrido, DataTenant tenant){
		ctrViaje.editarHorarioRecorrido(horario, idRecorrido, tenant);
	}

	public void borrarHorarioRecorrido(String idRecorrido, String idHorario, DataTenant tenant){
		ctrViaje.borrarHorarioRecorrido(idRecorrido, idHorario, tenant);
	}

	public Float getPrecioDePasaje(String codigoOrigen, String codigoDestino, String codigoRecorrido, DataTenant tenant) {
		return ctrViaje.getPrecioDePasaje(codigoOrigen, codigoDestino, codigoRecorrido, tenant);
	}
	
	public void crearViajesNuevoRecorrido(String recorridoId, DataTenant tenant){
		ctrViaje.crearViajesNuevoRecorrido(recorridoId, tenant);
	}
	
	public void crearViajesParaRecorridos(DataTenant tenant) throws ParseException{
		ctrViaje.crearViajesParaRecorridos(tenant);
	}
}
