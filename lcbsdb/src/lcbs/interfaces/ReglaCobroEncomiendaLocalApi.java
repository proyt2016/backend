package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataReglaCobroEncomienda;

@Local
public interface ReglaCobroEncomiendaLocalApi {
	public Map<String,DataReglaCobroEncomienda> obtenerReglaCobroEncomiendas();
	public void modificarReglaCobroEncomienda(DataReglaCobroEncomienda rce);
	public DataReglaCobroEncomienda getReglaCobroEncomienda(String rce);
	public DataReglaCobroEncomienda crearReglaCobroEncomienda(DataReglaCobroEncomienda rce);
	public void borrarReglaCobroEncomienda(DataReglaCobroEncomienda rce);
}
