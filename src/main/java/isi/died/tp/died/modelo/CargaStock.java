package isi.died.tp.died.modelo;

import java.util.List;

public class CargaStock {
	
	private Integer idcargastock;
	private Insumo insumo;
	private Stock stock;
	private Double cantidadRequerida;
	private Double stockDisponible;
	
	
	public CargaStock(Integer idcargastock, Insumo insumo, Stock stock, Double cantidadRequerida, Double stockDisponible) {
		super();
		
		this.idcargastock = idcargastock;
		this.insumo = insumo;
		this.stock = stock;
		this.cantidadRequerida = cantidadRequerida;
		this.stockDisponible = stockDisponible;	
	}
	
	public CargaStock() {
		super();
	}
	
	public Integer getId() {
		return idcargastock;
	}
	public void setId(Integer idcargastock) {
		this.idcargastock = idcargastock;
	}
	public Insumo getInsumo() {
		return insumo;
	}
	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	public Double getCantidadRequerida() {
		return cantidadRequerida;
	}
	public void setCantidadRequerida(Double cantidadRequerida) {
		this.cantidadRequerida = cantidadRequerida;
	}
	public Double getStockDisponible() {
		return stockDisponible;
	}
	public void setStockDisponible(Double stockDisponible) {
		this.stockDisponible = stockDisponible;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CargaStock other = (CargaStock) obj;
		if (idcargastock == null) {
			if (other.idcargastock != null)
				return false;
		} else if (!idcargastock.equals(other.idcargastock))
			return false;
		return true;
	}
	
	

}
