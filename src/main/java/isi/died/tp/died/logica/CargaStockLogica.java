package isi.died.tp.died.logica;

import java.util.List;

import isi.died.tp.died.modelo.CargaStock;

public interface CargaStockLogica {

	public CargaStock guardar(CargaStock c);
	//public void agregarPlanta(Proyecto p,Tarea t);
	public void borrar(Integer idcargastock);
	public CargaStock buscar(Integer idcargastock);
	public List<CargaStock> buscar();
}
