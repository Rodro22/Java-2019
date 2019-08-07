package isi.died.tp.died.modelo;

import java.util.List;

public class Camion {
	private Integer idcamion;
	private String marca;
	private String modelo;
	private String dominio;
	private Integer anio;
	private Double costoXKm;
	private Boolean transportaLiquido;
	private Double capacidad;
	
	
	
	public Camion(Integer idcamion, String marca, String modelo, String dominio, Integer anio, Double costoXKm, Boolean transportaLiquido, Double capacidad) {
		super();
		this.idcamion = idcamion;
		this.marca = marca;
		this.modelo = modelo;
		this.dominio = dominio;
		this.anio = anio;
		this.costoXKm = costoXKm;
		this.transportaLiquido = transportaLiquido;
		this.capacidad = capacidad;
	}
	
	public Camion() {
		super();
	}
	
	
	public Integer getId() {
		return idcamion;
	}
	public void setId(Integer idcamion) {
		this.idcamion = idcamion;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca= marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio= anio;
	}
	public Double getCostoXKm() {
		return costoXKm;
	}
	public void setCostoXKm(Double costoXKm) {
		this.costoXKm = costoXKm;
	}
	public Boolean getTransportaLiquido() {
		return transportaLiquido;
	}
	public void setTransportaLiquido(Boolean transportaLiquido) {
		this.transportaLiquido= transportaLiquido;
	}
	public Double getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Double capacidad) {
		this.capacidad= capacidad;
	}
	
	

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camion other = (Camion) obj;
		if (idcamion == null) {
			if (other.idcamion != null)
				return false;
		} else if (!idcamion.equals(other.idcamion))
			return false;
		return true;
	}
	@Override
	public String toString() {
		//return "Proyecto [id=" + id + ", nombre=" + nombre + ", tareas=" + tareas + "]";
		return this.marca;
	}
	
	

}
