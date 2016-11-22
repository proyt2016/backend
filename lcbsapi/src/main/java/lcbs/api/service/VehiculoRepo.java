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

import interfaces.*;
import lcbs.shares.*;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
@Remote
public class VehiculoRepo {
	
	@EJB(lookup =  "java:app/lcbsb/VehiculoCtrl!interfaces.IVehiculo")
	IVehiculo ctrVehiculo;
	
	public DataVehiculo altaVehiculo(DataVehiculo vehiculo, DataTenant tenant){
		return ctrVehiculo.altaVehiculo(vehiculo, tenant );
	}
	
	public void editarVehiculo(DataVehiculo vehiculo, DataTenant tenant){
		ctrVehiculo.editarVehiculo(vehiculo, tenant );
	}
	
	public void bajaVehiculo(String idVehiculo, DataTenant tenant){
		ctrVehiculo.bajaVehiculo(idVehiculo, tenant );
	}
	
	public List<DataMantenimientoVehiculo> mantenimientosPorVehiculo(String idVehiculo, DataTenant tenant){
		return ctrVehiculo.mantenimientosPorVehiculo(idVehiculo, tenant );
	}
	
	public DataVehiculo obtenerVehiculo(String idVehiculo, DataTenant tenant){
		return ctrVehiculo.obtenerVehiculo(idVehiculo, tenant );
	}

	public List<DataVehiculo> listarVehiculos(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrVehiculo.listarVehiculos(pagina, elementosPagina, tenant ); 
	}
	
	public void altaMantenimiento(DataMantenimientoVehiculo mante,String id, DataTenant tenant){
		 ctrVehiculo.altaMantenimiento(mante,id, tenant);
	}
	public List<DataVehiculo> obtenerVehiculosEnMantenimiento(Integer pagina, Integer elementosPagina, DataTenant tenant){
		return ctrVehiculo.obtenerVehiculosEnMantenimiento(pagina, elementosPagina, tenant);
	}
	
	public DataVehiculo obtenerVehiculoPorNumero(String numeroVehiculo, DataTenant tenant){
		return ctrVehiculo.obtenerVehiculoPorNumero(numeroVehiculo, tenant);
	}
}
