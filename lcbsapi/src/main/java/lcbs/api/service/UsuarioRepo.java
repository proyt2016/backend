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
public class UsuarioRepo {
	@EJB(lookup =  "java:app/lcbsb/UsuarioCtrl!interfaces.IUsuario")
	IUsuario ctrUsuario;
	
	public DataUsuario AltaUsuario(DataUsuario usuario){
		return ctrUsuario.AltaUsuario(usuario);
	}
	
	public void ModificarUsuario(DataUsuario usuario){
		ctrUsuario.ModificarUsuario(usuario);
	}
	
	public void BajaUsuario(String idUsuario){
		ctrUsuario.BajaUsuario(idUsuario);
	}
	
	public DataEmpleado AltaEmpleado(DataEmpleado empleado){
		return ctrUsuario.AltaEmpleado(empleado);
	}
	
	public void ModificarEmpleado(DataEmpleado empleado){
		ctrUsuario.ModificarEmpleado(empleado);
	}
	
	public void BajaEmpleado(String idEmpleado){
		ctrUsuario.BajaEmpleado(idEmpleado);
	}
	
	public void CargarSaldoCuponera(String idUsuario, Float saldo){
		ctrUsuario.CargarSaldoCuponera(idUsuario, saldo);
	}
	
	public List<DataNotificacion> listarNotificaciones(String idUsuario){
		return ctrUsuario.listarNotificaciones(idUsuario);
	}
	
	public void AltaPerfil(DataPerfil perfil){
		ctrUsuario.AltaPerfil(perfil);
	}
	
	public void EditarPerfil(DataPerfil perfil){
		ctrUsuario.EditarPerfil(perfil);
	}
	
	public void EliminarPerfil(String idPerfil){
		ctrUsuario.EliminarPerfil(idPerfil);
	}
	
	public void AsignarPerfil(String idEmpleado, String perfil){
		ctrUsuario.AsignarPerfil(idEmpleado, perfil);
	}
	
	public boolean loginUsuario(String usuario, String clave) {
		return ctrUsuario.loginUsuario(usuario, clave);
	}

	public DataUsuario getUsuario(String idUsuario) {
		return ctrUsuario.getUsuario(idUsuario);
	}

	public boolean loginEmpleado(String usuario, String clave) {
		return ctrUsuario.loginEmpleado(usuario, clave);
	}

	public DataEmpleado getEmpleado(String idEmpleado) {
		return ctrUsuario.getEmpleado(idEmpleado);
	}

	public DataPerfil getPerfil(String idPerfil) {
		return ctrUsuario.getPerfil(idPerfil);
	}

	public Map<String, DataPerfil> listarPerfiles(Integer pagina, Integer elementosPagina) {
		return ctrUsuario.listarPerfiles(pagina, elementosPagina);
	}
	
}
