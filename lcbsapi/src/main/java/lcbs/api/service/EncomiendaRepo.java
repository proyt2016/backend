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
public class EncomiendaRepo {
//  
	 
	
	@EJB(lookup =  "java:app/lcbsb/EncomiendaCtrl!interfaces.IEncomienda")
	IEncomienda ctrEncomienda;
	
	public List<DataEncomienda> ListarEncomiendas(Integer pagina, Integer elementosPagina){
		return ctrEncomienda.ListarEncomiendas(pagina, elementosPagina);
	}
	
	
	public List<DataReglaCobroEncomienda> getReglasDeCobro(Integer pagina, Integer elementosPagina){
		return ctrEncomienda.getReglasDeCobro(pagina, elementosPagina);
	}
	
	public List<DataEstadosEncomienda> getEstados(Integer pagina, Integer elementosPagina){
		return ctrEncomienda.getEstados(pagina, elementosPagina);
	}
	
	
	public void AltaReglaCobro(DataReglaCobroEncomienda drce){
		ctrEncomienda.crearReglaDeCobro(drce);
	}
	
	
	public DataReglaCobroEncomienda getReglaDeCobro(String idEncomieda){
		return ctrEncomienda.getReglaDeCobro(idEncomieda);
	}
	
	
	public DataEncomienda AltaEncomienda(DataEncomienda encomienda){
		return ctrEncomienda.AltaEncomienda(encomienda);
	}	
	
	public List<DataHistorialEstadosEncomienda> getHistorialEstado(String idEncomienda){
		return ctrEncomienda.getHistorialEstado(idEncomienda);
	}
	
	
	public DataEstadosEncomienda getUltimoEstado(String idEncomienda){
		return ctrEncomienda.getUltimoEstado(idEncomienda);
	}
	
	
	public DataEncomienda getEncomienda(String idEncomienda){
		return ctrEncomienda.getEncomienda(idEncomienda);
	}
	
	public void setEstadoEncomienda(String idEncomienda, DataEstadosEncomienda dataEstado){
		ctrEncomienda.setEstadoEncomienda(idEncomienda, dataEstado);
	}
	
	
	public List<DataEncomienda> getEncomiendasPorVehiculo(String idViaje){
		return ctrEncomienda.getEncomiendasPorVehiculo(idViaje);
	}
	
	
	public void AsignarEncomiendasVehiculo(String IdEncomienda, String idViaje){
		ctrEncomienda.AsignarEncomiendasVehiculo(IdEncomienda, idViaje);
	}
	
	
	public List<DataHistorialEstadosEncomienda> VerEstadosEncomienda(String idEncomienda){
		return ctrEncomienda.VerEstadosEncomienda(idEncomienda);
	}
	
	public void editarEncomienda(DataEncomienda encomienda) {
		ctrEncomienda.editarEncomienda(encomienda);
	}
	
	public void bajaEncomienda(String idEncomienda) {
		ctrEncomienda.bajaEncomienda(idEncomienda);
	}

	public List<DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina) {
		return ctrEncomienda.buscarEncomienda(filtro, pagina, ElementosPagina);
	}
	
	public void borrarEstadoEncomienda(String idEstadoEncomienda){
		ctrEncomienda.borrarEstadoEncomienda(idEstadoEncomienda);
	}
	public List<DataEstadosEncomienda> listarEstadoEncomienda(Integer pagina, Integer elementosPagina){
		return ctrEncomienda.listarEstadoEncomienda(pagina, elementosPagina);
	}
	public DataEstadosEncomienda crearEstadoEncomienda(DataEstadosEncomienda estado){
		return ctrEncomienda.crearEstadoEncomienda(estado);
	}


	public List<DataEncomienda> listarEncomiendasPorUsuario(String idUsuario, Integer pagina, Integer elementosPagina) {
		return ctrEncomienda.listarEncomiendasPorUsuario(idUsuario, pagina, elementosPagina);
	}


	public void EditarEstadoEncomienda(DataEstadosEncomienda dataEstado) {
		ctrEncomienda.EditarEstadoEncomienda(dataEstado);
		
	}


	public DataEstadosEncomienda getEstadoEncomienda(String idEstadoEncomienda) {
		return ctrEncomienda.getEstadoEncomienda(idEstadoEncomienda);
	}
	
}
