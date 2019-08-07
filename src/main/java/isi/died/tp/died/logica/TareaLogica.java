package isi.died.tp.died.logica;

import java.util.List;

import isi.died.tp.died.modelo.Proyecto;
import isi.died.tp.died.modelo.Tarea;

public interface TareaLogica {

	public Tarea guardar(Tarea p);
	public void borrar(Integer id);
	public Tarea buscar(Integer id);
	public List<Tarea> buscar();
}
