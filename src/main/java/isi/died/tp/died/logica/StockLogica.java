package isi.died.tp.died.logica;

import java.util.List;

import isi.died.tp.died.modelo.Stock;

public interface StockLogica {

	public Stock guardar(Stock s);
	//public void agregarPlanta(Proyecto p,Tarea t);
	public void borrar(Integer idstock);
	public Stock buscar(Integer idstock);
	public List<Stock> buscar();
}
