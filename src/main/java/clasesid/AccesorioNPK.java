package clasesid;

import java.io.Serializable;

public class AccesorioNPK implements Serializable{
	private Long id_accesorio;
	private Long id_asignacion;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_accesorio == null) ? 0 : id_accesorio.hashCode());
		result = prime * result + ((id_asignacion == null) ? 0 : id_asignacion.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccesorioNPK other = (AccesorioNPK) obj;
		if (id_accesorio == null) {
			if (other.id_accesorio != null)
				return false;
		} else if (!id_accesorio.equals(other.id_accesorio))
			return false;
		if (id_asignacion == null) {
			if (other.id_asignacion != null)
				return false;
		} else if (!id_asignacion.equals(other.id_asignacion))
			return false;
		return true;
	}
	public Long getId_accesorio() {
		return id_accesorio;
	}
	public void setId_accesorio(Long id_accesorio) {
		this.id_accesorio = id_accesorio;
	}
	public Long getId_asignacion() {
		return id_asignacion;
	}
	public void setId_asignacion(Long id_asignacion) {
		this.id_asignacion = id_asignacion;
	}
	
	
}
