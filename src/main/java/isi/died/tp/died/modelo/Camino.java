package isi.died.tp.died.modelo;

import java.util.List;

public class Camino {
	private Integer idcamino;
	private Planta planta_init;
	private Planta planta_end;
	private Camion camion;
	private Double distancia;
	private Double duracion;
	private Double peso_soportado;
	
	
	public Camino(Integer idcamino, Planta planta_init, Planta planta_end, Camion camion, Double distancia, Double duracion, Double peso_soportado) {
		super();
		this.idcamino = idcamino;
		this.planta_init = planta_init;
		this.planta_end = planta_end;
		this.camion = camion;
		this.distancia = distancia;
		this.duracion = duracion;
		this.peso_soportado = peso_soportado;
	
	}
	
	public Camino() {
		super();
	}
	
	
	public Integer getId() {
		return idcamino;
	}
	public void setId(Integer idcamino) {
		this.idcamino = idcamino;
	}
	public Planta getPlantaInit() {
		return planta_init;
	}
	public void setPlantaInit(Planta planta_init) {
		this.planta_init = planta_init;
	}
	public Planta getPlantaEnd() {
		return planta_end;
	}
	public void setPlantaEnd(Planta planta_end) {
		this.planta_end= planta_end;
	}
	public Camion getCamion() {
		return camion;
	}
	public void setCamion(Camion camion) {
		this.camion= camion;
	}
	public Double getDistancia() {
		return distancia;
	}
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	public Double getDuracion() {
		return duracion;
	}
	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}
	public Double getPesoSoportado() {
		return peso_soportado;
	}
	public void setPesoSoportado(Double peso_soportado) {
		this.peso_soportado= peso_soportado;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camino other = (Camino) obj;
		if (idcamino == null) {
			if (other.idcamino != null)
				return false;
		} else if (!idcamino.equals(other.idcamino))
			return false;
		return true;
	}

	
	

}
