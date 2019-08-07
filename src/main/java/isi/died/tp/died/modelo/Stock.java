package isi.died.tp.died.modelo;

import java.util.List;

public class Stock {
	private Integer idstock;
	private Planta planta;	
	
	public Stock(Integer idstock, Planta planta) {
		super();
		this.idstock = idstock;
		this.planta = planta;
	
	}
	
	public Stock() {
		super();
	}
	
	
	public Integer getId() {
		return idstock;
	}
	public void setId(Integer idstock) {
		this.idstock = idstock;
	}
	public Planta getPlanta() {
		return planta;
	}
	public void setPlanta(Planta planta) {
		this.planta = planta;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (idstock == null) {
			if (other.idstock != null)
				return false;
		} else if (!idstock.equals(other.idstock))
			return false;
		return true;
	}

	
	

}
