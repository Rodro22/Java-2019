package isi.died.tp.died.modelo;

import java.util.List;

public class Insumo {
	private Integer idinsumo;
	private String nombreInsumo;
	private String descripcion;
	private String unidadDeMedida;
	private Double peso;
	private Double costo;
	private Boolean esRefrigerado;
	private Boolean esLiquido;
	
	
	
	public Insumo(Integer idinsumo, String nombreInsumo, String descripcion, String unidadDeMedida, Double peso, Integer stock, Double costo, Boolean esRefrigerado, Boolean esLiquido) {
		super();
		this.idinsumo = idinsumo;
		this.nombreInsumo = nombreInsumo;
		this.descripcion = descripcion;
		this.unidadDeMedida = unidadDeMedida;
		this.peso = peso;
		this.costo = costo;
		this.esRefrigerado = esRefrigerado;
		this.esLiquido = esLiquido;
	}
	
	public Insumo() {
		super();
	}
	
	
	public Integer getId() {
		return idinsumo;
	}
	public void setId(Integer idinsumo) {
		this.idinsumo = idinsumo;
	}
	public String getNombre() {
		return nombreInsumo;
	}
	public void setNombre(String nombreInsumo) {
		this.nombreInsumo = nombreInsumo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public Boolean getEsRefrigerado() {
		return esRefrigerado;
	}
	public void setEsRefrigerado(Boolean esRefrigerado) {
		this.esRefrigerado = esRefrigerado;
	}
	public Boolean getEsLiquido() {
		return esLiquido;
	}
	public void setEsLiquido(Boolean esLiquido) {
		this.esLiquido = esLiquido;
	}
	
	

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insumo other = (Insumo) obj;
		if (idinsumo == null) {
			if (other.idinsumo != null)
				return false;
		} else if (!idinsumo.equals(other.idinsumo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		//return "Proyecto [id=" + id + ", nombre=" + nombre + ", tareas=" + tareas + "]";
		return this.nombreInsumo;
	}
	
	

}
