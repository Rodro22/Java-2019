package isi.died.tp.died.dao;

import java.util.List;

import isi.died.tp.died.modelo.Planta;

public interface PlantaDao {
	public Planta crear(Planta p);

	public Planta actualizar(Planta p);

	public void borrar(Integer idplanta);

	public Planta buscar(Integer idplanta);

	public List<Planta> buscarTodos();
	
	
}
