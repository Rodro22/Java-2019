package isi.died.tp.died.logica;

import java.util.List;

import isi.died.tp.died.modelo.Insumo;

public interface InsumoLogica {

	public Insumo guardar(Insumo i);
	//public void agregarPlanta(Proyecto p,Tarea t);
	public void borrar(Integer idinsumo);
	public Insumo buscar(Integer idinsumo);
	public List<Insumo> buscar();
}
