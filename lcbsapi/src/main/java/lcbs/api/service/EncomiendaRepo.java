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

	@EJB(lookup = "java:app/lcbsb/EncomiendaCtrl!interfaces.IEncomienda")
	IEncomienda ctrEncomienda;

	public List<DataEncomiendaConvertor> ListarEncomiendas(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrEncomienda.ListarEncomiendas(pagina, elementosPagina, tenant);
	}

	public List<DataReglaCobroEncomienda> getReglasDeCobro(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrEncomienda.getReglasDeCobro(pagina, elementosPagina, tenant);
	}

	public List<DataEstadosEncomienda> getEstados(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrEncomienda.getEstados(pagina, elementosPagina, tenant);
	}

	public void AltaReglaCobro(DataReglaCobroEncomienda drce, DataTenant tenant) {
		ctrEncomienda.crearReglaDeCobro(drce, tenant);
	}

	public DataReglaCobroEncomienda getReglaDeCobro(String idEncomieda, DataTenant tenant) {
		return ctrEncomienda.getReglaDeCobro(idEncomieda, tenant);
	}

	public DataEncomienda AltaEncomienda(DataEncomienda encomienda, DataTenant tenant) {
		return ctrEncomienda.AltaEncomienda(encomienda, tenant);
	}

	public List<DataHistorialEstadosEncomienda> getHistorialEstado(String idEncomienda, DataTenant tenant) {
		return ctrEncomienda.getHistorialEstado(idEncomienda, tenant);
	}

	public DataEstadosEncomienda getUltimoEstado(String idEncomienda, DataTenant tenant) {
		return ctrEncomienda.getUltimoEstado(idEncomienda, tenant);
	}

	public DataEncomienda getEncomienda(String idEncomienda, DataTenant tenant) {
		return ctrEncomienda.getEncomienda(idEncomienda, tenant);
	}

	public void setEstadoEncomienda(String idEncomienda, DataEstadosEncomienda dataEstado, DataTenant tenant) {
		ctrEncomienda.setEstadoEncomienda(idEncomienda, dataEstado, tenant);
	}

	public List<DataEncomienda> getEncomiendasPorVehiculo(String idVehiculo, DataTenant tenant) {
		return ctrEncomienda.getEncomiendasPorVehiculo(idVehiculo, tenant);
	}

	public void AsignarEncomiendasVehiculo(String IdEncomienda, String idViaje, String idCoche, DataTenant tenant) {
		ctrEncomienda.AsignarEncomiendasVehiculo(IdEncomienda, idViaje, idCoche, tenant);
	}

	public List<DataHistorialEstadosEncomienda> VerEstadosEncomienda(String idEncomienda, DataTenant tenant) {
		return ctrEncomienda.VerEstadosEncomienda(idEncomienda, tenant);
	}

	public void editarEncomienda(DataEncomienda encomienda, DataTenant tenant) {
		ctrEncomienda.editarEncomienda(encomienda, tenant);
	}

	public void bajaEncomienda(String idEncomienda, DataTenant tenant) {
		ctrEncomienda.bajaEncomienda(idEncomienda, tenant);
	}

	public List<DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina, DataTenant tenant) {
		return ctrEncomienda.buscarEncomienda(filtro, pagina, ElementosPagina, tenant);
	}

	public void borrarEstadoEncomienda(String idEstadoEncomienda, DataTenant tenant) {
		ctrEncomienda.borrarEstadoEncomienda(idEstadoEncomienda, tenant);
	}

	public List<DataEstadosEncomienda> listarEstadoEncomienda(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrEncomienda.listarEstadoEncomienda(pagina, elementosPagina, tenant);
	}

	public DataEstadosEncomienda crearEstadoEncomienda(DataEstadosEncomienda estado, DataTenant tenant) {
		return ctrEncomienda.crearEstadoEncomienda(estado, tenant);
	}

	public List<DataEncomienda> listarEncomiendasPorUsuario(String idUsuario, Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrEncomienda.listarEncomiendasPorUsuario(idUsuario, pagina, elementosPagina, tenant);
	}

	public void EditarEstadoEncomienda(DataEstadosEncomienda dataEstado, DataTenant tenant) {
		ctrEncomienda.EditarEstadoEncomienda(dataEstado, tenant);

	}

	public DataEstadosEncomienda getEstadoEncomienda(String idEstadoEncomienda, DataTenant tenant) {
		return ctrEncomienda.getEstadoEncomienda(idEstadoEncomienda, tenant);
	}

	public DataEncomienda getEncomiendaXcodigo(Integer codigoEncomienda, DataTenant tenant) {
		return ctrEncomienda.getEncomiendaXcodigo(codigoEncomienda, tenant);
	}

	public DataReglaCobroEncomienda editarReglaCobroEncomienda(DataReglaCobroEncomienda dataRegla, DataTenant tenant) {
		return ctrEncomienda.editarReglaCobroEncomienda(dataRegla, tenant);

	}

	public void borrarReglaCobroEncomienda(String idReglaCobro, DataTenant tenant) {
		ctrEncomienda.borrarReglaCobroEncomienda(idReglaCobro, tenant);

	}

	public Float getPrecioDeEncomienda(String codigoReglaCobro, Float monto, DataTenant tenant) {
		return ctrEncomienda.getPrecioDeEncomienda(codigoReglaCobro, monto, tenant);
	}
	
	public List<DataEncomiendaConvertor> getEncomiendasPagas(String fecha, Integer pagina, Integer elemPagina, DataTenant tenant){
		return ctrEncomienda.getEncomiendasPagas(fecha, pagina, elemPagina, tenant);
	}
}
