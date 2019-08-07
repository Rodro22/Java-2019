package isi.died.tp.died.logica;

import java.util.List;

import isi.died.tp.died.modelo.Camino;

public interface CaminoLogica {

	public Camino guardar(Camino c);
	//public void agregarPlanta(Proyecto p,Tarea t);
	public void borrar(Integer idcamino);
	public Camino buscar(Integer idcamino);
	public List<Camino> buscar();
}
