package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataReglaCobroEncomienda;

@Local
public interface ReglaCobroEncomiendaLocalApi {
	public List<DataReglaCobroEncomienda> obtenerReglaCobroEncomiendas(Integer pagina, Integer elementosPagina);
	public DataReglaCobroEncomienda modificarReglaCobroEncomienda(DataReglaCobroEncomienda rce);
	public DataReglaCobroEncomienda getReglaCobroEncomienda(String rce);
	public DataReglaCobroEncomienda crearReglaCobroEncomienda(DataReglaCobroEncomienda rce);
	public void borrarReglaCobroEncomienda(String idRce);
	public Float getPrecioDeEncomienda(String codigoReglaCobro, Float monto);
}
