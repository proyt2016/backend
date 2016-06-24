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

import interfaces.IEncomienda;
import interfaces.IViaje;
import lcbs.shares.*;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
@Remote
public class EncomiendaRepo {
//  
	 
	@EJB(lookup =  "java:app/lcbsb/ViajeCtrl!interfaces.IViaje")
	IViaje ctrViaje;
	@EJB(lookup =  "java:app/lcbsb/EncomiendaCtrl!interfaces.IEncomienda")
	IEncomienda ctrEncomienda;
	public String getSape(){
		
		
		DataParada nuevaParada = new DataParada();
		nuevaParada.setEliminado(false);
		nuevaParada.setNombre("Av italia y Comercio");
		nuevaParada.setUbicacionMapa("324fd234de324");
		nuevaParada = ctrViaje.AltaParadas(nuevaParada);
		
		String idInsertada = nuevaParada.getId();
		
		nuevaParada = new DataParada();
		nuevaParada.setEliminado(false);
		nuevaParada.setNombre("Propios y Comercio");
		nuevaParada.setUbicacionMapa("324fd234de324");
		nuevaParada = ctrViaje.AltaParadas(nuevaParada);
		
		nuevaParada = ctrViaje.obtenerParada(idInsertada);
		return "###"+nuevaParada.getNombre();
	}
}
