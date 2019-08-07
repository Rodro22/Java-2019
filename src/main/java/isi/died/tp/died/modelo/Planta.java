package isi.died.tp.died.modelo;

import java.util.List;

public class Planta {
	private Integer idplanta;
	private String nombre_planta;
	private Boolean esAcopio;
	
	
	
	public Planta(Integer idplanta, String nombre_planta, Boolean acopio) {
		super();
		this.idplanta = idplanta;
		this.nombre_planta = nombre_planta;
		this.esAcopio = acopio;
	}
	
	public Planta() {
		super();
	}
	
	
	public Integer getId() {
		return idplanta;
	}
	public void setId(Integer idplanta) {
		this.idplanta = idplanta;
	}
	public String getNombre() {
		return nombre_planta;
	}
	public void setNombre(String nombre_planta) {
		this.nombre_planta = nombre_planta;
	}
	public Boolean getEsAcopio() {
		return esAcopio;
	}
	public void setEsAcopio(Boolean acopio) {
		this.esAcopio = acopio;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planta other = (Planta) obj;
		if (idplanta == null) {
			if (other.idplanta != null)
				return false;
		} else if (!idplanta.equals(other.idplanta))
			return false;
		return true;
	}
	@Override
	public String toString() {
		//return "Proyecto [id=" + id + ", nombre=" + nombre + ", tareas=" + tareas + "]";
		return this.nombre_planta;
	}
	
	

}
