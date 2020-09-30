package clasesid;

import java.io.Serializable;

public class EquipoSoftwareSK implements Serializable{

	private Long id_equipo;
	private Long id_software;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_equipo == null) ? 0 : id_equipo.hashCode());
		result = prime * result + ((id_software == null) ? 0 : id_software.hashCode());
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
		EquipoSoftwareSK other = (EquipoSoftwareSK) obj;
		if (id_equipo == null) {
			if (other.id_equipo != null)
				return false;
		} else if (!id_equipo.equals(other.id_equipo))
			return false;
		if (id_software == null) {
			if (other.id_software != null)
				return false;
		} else if (!id_software.equals(other.id_software))
			return false;
		return true;
	}
	public Long getId_equipo() {
		return id_equipo;
	}
	public void setId_equipo(Long id_equipo) {
		this.id_equipo = id_equipo;
	}
	public Long getId_software() {
		return id_software;
	}
	public void setId_software(Long id_software) {
		this.id_software = id_software;
	}
	
	
}
