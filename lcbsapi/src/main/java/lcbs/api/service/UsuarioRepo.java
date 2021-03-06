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

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.PathParam;

import interfaces.IUsuario;
import lcbs.exceptions.UserException;
import lcbs.shares.DataEmpleado;
import lcbs.shares.DataNotificacion;
import lcbs.shares.DataPerfil;
import lcbs.shares.DataTenant;
import lcbs.shares.DataUsuario;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
@Remote
public class UsuarioRepo {
	@EJB(lookup = "java:app/lcbsb/UsuarioCtrl!interfaces.IUsuario")
	IUsuario ctrUsuario;

	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant) throws UserException {
		return ctrUsuario.AltaUsuario(usuario, tenant);
	}

	public void ModificarUsuario(DataUsuario usuario, DataTenant tenant) throws UserException {
		ctrUsuario.ModificarUsuario(usuario, tenant);
	}

	public void BajaUsuario(String idUsuario, DataTenant tenant) throws UserException {
		ctrUsuario.BajaUsuario(idUsuario, tenant);
	}

	public DataEmpleado AltaEmpleado(DataEmpleado empleado, DataTenant tenant) throws UserException {
		return ctrUsuario.AltaEmpleado(empleado, tenant);
	}

	public void ModificarEmpleado(DataEmpleado empleado, DataTenant tenant) throws UserException {
		ctrUsuario.ModificarEmpleado(empleado, tenant);
	}

	public void BajaEmpleado(String idEmpleado, DataTenant tenant) throws UserException {
		ctrUsuario.BajaEmpleado(idEmpleado, tenant);
	}

	public void CargarSaldoCuponera(String idUsuario, Float saldo, DataTenant tenant) throws UserException {
		ctrUsuario.CargarSaldoCuponera(idUsuario, saldo, tenant);
	}

	public List<DataNotificacion> listarNotificaciones(String idUsuario, DataTenant tenant) throws UserException {
		return ctrUsuario.listarNotificaciones(idUsuario, tenant);
	}

	public DataPerfil AltaPerfil(DataPerfil perfil, DataTenant tenant) throws UserException {
		return ctrUsuario.AltaPerfil(perfil, tenant);
	}

	public void EditarPerfil(DataPerfil perfil, DataTenant tenant) throws UserException {
		ctrUsuario.EditarPerfil(perfil, tenant);
	}

	public void EliminarPerfil(String idPerfil, DataTenant tenant) throws UserException {
		ctrUsuario.EliminarPerfil(idPerfil, tenant);
	}

	public void AsignarPerfil(String idEmpleado, String perfil, DataTenant tenant) throws UserException {
		ctrUsuario.AsignarPerfil(idEmpleado, perfil, tenant);
	}

	public DataUsuario loginUsuario(String usuario, String clave, DataTenant tenant) throws UserException {
		return ctrUsuario.loginUsuario(usuario, clave, tenant);
	}

	public DataUsuario getUsuario(String idUsuario, DataTenant tenant) throws UserException {
		return ctrUsuario.getUsuario(idUsuario, tenant);
	}

	public DataEmpleado loginEmpleado(String usuario, String clave, DataTenant tenant) throws UserException {
		return ctrUsuario.loginEmpleado(usuario, clave, tenant);
	}

	public DataEmpleado getEmpleado(String idEmpleado, DataTenant tenant) throws UserException {
		return ctrUsuario.getEmpleado(idEmpleado, tenant);
	}

	public List<DataEmpleado> listarEmpleados(Integer pagina, Integer elementosPagina, DataTenant tenant)
			throws UserException {
		return ctrUsuario.listarEmpleados(pagina, elementosPagina, tenant);
	}

	public DataPerfil getPerfil(String idPerfil, DataTenant tenant) throws UserException {
		return ctrUsuario.getPerfil(idPerfil, tenant);
	}

	public List<DataPerfil> listarPerfiles(Integer pagina, Integer elementosPagina, DataTenant tenant)
			throws UserException {
		return ctrUsuario.listarPerfiles(pagina, elementosPagina, tenant);
	}

	public List<DataUsuario> listarUsuarios(@PathParam("pagina") final Integer pagina,
			@PathParam("elementosAMostrar") final Integer elementosPagina, DataTenant tenant) throws UserException {
		return ctrUsuario.listarUsuarios(pagina, elementosPagina, tenant);
	}

	public DataUsuario buscarUsuarioPorMail(String mailUsuario, DataTenant tenant) throws UserException {
		return ctrUsuario.buscarUsuarioPorMail(mailUsuario, tenant);
	}

	public void guardarTokenUsuario(String idUsuario, String token, Integer ultimosDigitosTarjeta, DataTenant tenant)
			throws UserException {
		ctrUsuario.guardarTokenUsuario(idUsuario, token, ultimosDigitosTarjeta, tenant);
	}
	
	public void CargarSaldoCuponeraStripe(String idUsuario, Float saldo, DataTenant tenant) throws UserException{
		ctrUsuario.CargarSaldoCuponeraStripe(idUsuario, saldo, tenant);
	}
}
