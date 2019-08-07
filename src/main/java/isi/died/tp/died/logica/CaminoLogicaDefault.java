package isi.died.tp.died.logica;

import java.util.List;
import isi.died.tp.died.dao.CaminoDao;
import isi.died.tp.died.dao.CaminoDaoH2;
import isi.died.tp.died.modelo.Camino;


public class CaminoLogicaDefault implements CaminoLogica {

	//private ProyectoDao proyectoDao;	
	//private TareaLogica tareaLogica;
	private CaminoDao caminoDao;

	public CaminoLogicaDefault() {
		this.caminoDao = new CaminoDaoH2();
		//this.tareaLogica = new TareaLogicaDefault();
	}

	@Override
	public Camino guardar(Camino c) {
		// si tiene ID entonces actualizar
		if(c.getId()!= null && c.getId()>0) {
			return this.caminoDao.actualizar(c);
		} else {
			return this.caminoDao.crear(c);			
		}
	}

	/*@Override
	public void agregarTarea(Proyecto p, Tarea t) {
		t.setProyecto(p);
		this.tareaLogica.guardar(t);
	}*/

	@Override
	public void borrar(Integer idcamino) {
		this.caminoDao.borrar(idcamino);
	}

	@Override
	public Camino buscar(Integer idcamino) {
		return this.caminoDao.buscar(idcamino);
	}

	@Override
	public List<Camino> buscar() {
		return this.caminoDao.buscarTodos();
	}


}
