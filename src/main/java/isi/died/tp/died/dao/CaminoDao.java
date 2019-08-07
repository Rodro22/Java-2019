package isi.died.tp.died.dao;

import java.util.List;

import isi.died.tp.died.modelo.Camino;

public interface CaminoDao {
	public Camino crear(Camino c);

	public Camino actualizar(Camino c);

	public void borrar(Integer idcamino);

	public Camino buscar(Integer idcamino);

	public List<Camino> buscarTodos();
	
	
}
