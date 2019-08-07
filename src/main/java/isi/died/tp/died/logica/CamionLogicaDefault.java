package isi.died.tp.died.logica;

import java.util.List;
import isi.died.tp.died.dao.CamionDao;
import isi.died.tp.died.dao.CamionDaoH2;
import isi.died.tp.died.modelo.Camion;


public class CamionLogicaDefault implements CamionLogica {

	//private ProyectoDao proyectoDao;	
	//private TareaLogica tareaLogica;
	private CamionDao camionDao;

	public CamionLogicaDefault() {
		this.camionDao = new CamionDaoH2();
		//this.tareaLogica = new TareaLogicaDefault();
	}

	@Override
	public Camion guardar(Camion c) {
		// si tiene ID entonces actualizar
		if(c.getId()!= null && c.getId()>0) {
			return this.camionDao.actualizar(c);
		} else {
			return this.camionDao.crear(c);			
		}
	}

	/*@Override
	public void agregarTarea(Proyecto p, Tarea t) {
		t.setProyecto(p);
		this.tareaLogica.guardar(t);
	}*/

	@Override
	public void borrar(Integer idcamion) {
		this.camionDao.borrar(idcamion);
	}

	@Override
	public Camion buscar(Integer idcamion) {
		return this.camionDao.buscar(idcamion);
	}

	@Override
	public List<Camion> buscar() {
		return this.camionDao.buscarTodos();
	}


}
