package lcbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataReglaCobroEncomienda;
import lcbs.shares.DataTenant;

@Local
public interface ReglaCobroEncomiendaLocalApi {
	public List<DataReglaCobroEncomienda> obtenerReglaCobroEncomiendas(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataReglaCobroEncomienda modificarReglaCobroEncomienda(DataReglaCobroEncomienda rce, DataTenant tenant);
	public DataReglaCobroEncomienda getReglaCobroEncomienda(String rce, DataTenant tenant);
	public DataReglaCobroEncomienda crearReglaCobroEncomienda(DataReglaCobroEncomienda rce, DataTenant tenant);
	public void borrarReglaCobroEncomienda(String idRce, DataTenant tenant);
	public Float getPrecioDeEncomienda(String codigoReglaCobro, Float monto, DataTenant tenant);
}
