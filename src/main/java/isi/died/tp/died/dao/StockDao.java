package isi.died.tp.died.dao;

import java.util.List;

import isi.died.tp.died.modelo.Stock;

public interface StockDao {
	public Stock crear(Stock s);

	public Stock actualizar(Stock s);

	public void borrar(Integer idstock);

	public Stock buscar(Integer idstock);

	public List<Stock> buscarTodos();
	
	
}
