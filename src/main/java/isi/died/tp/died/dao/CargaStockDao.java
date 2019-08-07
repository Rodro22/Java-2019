package isi.died.tp.died.dao;

import java.util.List;

import isi.died.tp.died.modelo.CargaStock;

public interface CargaStockDao {
	public CargaStock crear(CargaStock c);

	public CargaStock actualizar(CargaStock c);

	public void borrar(Integer idcargastock);

	public CargaStock buscar(Integer idcargastock);

	public List<CargaStock> buscarTodos();
	
	
}
