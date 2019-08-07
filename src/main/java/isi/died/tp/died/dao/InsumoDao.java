package isi.died.tp.died.dao;

import java.util.List;

import isi.died.tp.died.modelo.Insumo;

public interface InsumoDao {
	public Insumo crear(Insumo i);

	public Insumo actualizar(Insumo i);

	public void borrar(Integer idinsumo);

	public Insumo buscar(Integer idinsumo);

	public List<Insumo> buscarTodos();
	
	
}
