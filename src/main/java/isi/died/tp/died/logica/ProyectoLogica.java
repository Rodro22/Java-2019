package isi.died.tp.died.logica;

import java.util.List;

import isi.died.tp.died.modelo.Proyecto;
import isi.died.tp.died.modelo.Tarea;

public interface ProyectoLogica {

	public Proyecto guardar(Proyecto p);
	public void agregarTarea(Proyecto p,Tarea t);
	public void borrar(Integer id);
	public Proyecto buscar(Integer id);
	public List<Proyecto> buscar();
}
