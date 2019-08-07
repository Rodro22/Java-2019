package isi.died.tp.died.logica;

import java.util.List;

import isi.died.tp.died.modelo.Planta;

public interface PlantaLogica {

	public Planta guardar(Planta p);
	//public void agregarPlanta(Proyecto p,Tarea t);
	public void borrar(Integer idplanta);
	public Planta buscar(Integer idplanta);
	public List<Planta> buscar();
}
