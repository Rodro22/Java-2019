package isi.died.tp.died.dao;

import java.util.List;

import isi.died.tp.died.modelo.Tarea;

public interface TareaDao {
	public Tarea crear(Tarea t);

	public Tarea actualizar(Tarea t);

	public void borrar(Integer id);

	public Tarea buscar(Integer id);

	public List<Tarea> buscarTodos();
	
	
}
