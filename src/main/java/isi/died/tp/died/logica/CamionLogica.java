package isi.died.tp.died.logica;

import java.util.List;

import isi.died.tp.died.modelo.Camion;

public interface CamionLogica {

	public Camion guardar(Camion i);
	//public void agregarPlanta(Proyecto p,Tarea t);
	public void borrar(Integer idcamion);
	public Camion buscar(Integer idcamion);
	public List<Camion> buscar();
}
