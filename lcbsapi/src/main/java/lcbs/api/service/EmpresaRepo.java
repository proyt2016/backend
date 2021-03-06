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

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import interfaces.*;
import lcbs.shares.*;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
@Remote
public class EmpresaRepo {
	
	@EJB(lookup =  "java:app/lcbsb/EmpresaCtrl!interfaces.IEmpresa")
	IEmpresa ctrEmpresa;
	
	public void altaConfiguracionEmpresa(DataConfiguracionEmpresa empresa, DataTenant tenant) {
		ctrEmpresa.altaConfiguracionEmpresa(empresa, tenant);
	}
	
	public DataConfiguracionEmpresa obtenerConfiguracionEmpresa(DataTenant tenant){
		return ctrEmpresa.obtenerConfiguracionEmpresa(tenant);

	}
	
	public void editarConfiguracionEmpresa(DataConfiguracionEmpresa empresa, DataTenant tenant){
		ctrEmpresa.editarConfiguracionEmpresa(empresa, tenant);
	}
}