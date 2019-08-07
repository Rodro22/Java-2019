package isi.died.tp.died.logica;

import java.util.List;

import isi.died.tp.died.dao.TareaDao;
import isi.died.tp.died.dao.TareaDaoH2;
import isi.died.tp.died.modelo.Proyecto;
import isi.died.tp.died.modelo.Tarea;

public class TareaLogicaDefault implements TareaLogica {

	private TareaDao tareaDao;
	
	public TareaLogicaDefault() {
		this.tareaDao = new TareaDaoH2();
	}

	@Override
	public Tarea guardar(Tarea p) {
		// si tiene ID entonces actualizar
		if(p.getId()!= null && p.getId()>0) {
			return this.tareaDao.actualizar(p);
		} else {
			return this.tareaDao.crear(p);			
		}
	}

	@Override
	public void borrar(Integer id) {
		this.tareaDao.borrar(id);
	}

	@Override
	public Tarea buscar(Integer id) {
		return this.tareaDao.buscar(id);
	}

	@Override
	public List<Tarea> buscar() {
		return this.tareaDao.buscarTodos();
	}


}
