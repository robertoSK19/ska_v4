package clasesid;

import java.io.Serializable;

public class EquipoSoftwareSK implements Serializable{

	private Long id_equipo;
	private Long id_historico;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_equipo == null) ? 0 : id_equipo.hashCode());
		result = prime * result + ((id_historico == null) ? 0 : id_historico.hashCode());
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
		if (id_historico == null) {
			if (other.id_historico != null)
				return false;
		} else if (!id_historico.equals(other.id_historico))
			return false;
		return true;
	}
	public Long getId_equipo() {
		return id_equipo;
	}
	public void setId_equipo(Long id_equipo) {
		this.id_equipo = id_equipo;
	}
	public Long getId_historico() {
		return id_historico;
	}
	public void setId_historico(Long id_historico) {
		this.id_historico = id_historico;
	}

	
	
}
