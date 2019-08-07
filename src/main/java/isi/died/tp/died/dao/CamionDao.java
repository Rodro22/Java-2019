package isi.died.tp.died.dao;

import java.util.List;

import isi.died.tp.died.modelo.Camion;

public interface CamionDao {
	public Camion crear(Camion c);

	public Camion actualizar(Camion c);

	public void borrar(Integer idcamion);

	public Camion buscar(Integer idcamion);

	public List<Camion> buscarTodos();
	
	
}
