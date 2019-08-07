package isi.died.tp.died.dao;

import java.util.List;

import isi.died.tp.died.modelo.Proyecto;

public interface ProyectoDao {
	public Proyecto crear(Proyecto t);

	public Proyecto actualizar(Proyecto t);

	public void borrar(Integer id);

	public Proyecto buscar(Integer id);

	public List<Proyecto> buscarTodos();
	
	
}
