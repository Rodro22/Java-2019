package isi.died.tp.died.logica;

import java.util.List;
import isi.died.tp.died.dao.PlantaDao;
import isi.died.tp.died.dao.PlantaDaoH2;
import isi.died.tp.died.modelo.Planta;


public class PlantaLogicaDefault implements PlantaLogica {

	//private ProyectoDao proyectoDao;	
	//private TareaLogica tareaLogica;
	private PlantaDao plantaDao;

	public PlantaLogicaDefault() {
		this.plantaDao = new PlantaDaoH2();
		//this.tareaLogica = new TareaLogicaDefault();
	}

	@Override
	public Planta guardar(Planta p) {
		// si tiene ID entonces actualizar
		if(p.getId()!= null && p.getId()>0) {
			return this.plantaDao.actualizar(p);
		} else {
			return this.plantaDao.crear(p);			
		}
	}

	/*@Override
	public void agregarTarea(Proyecto p, Tarea t) {
		t.setProyecto(p);
		this.tareaLogica.guardar(t);
	}*/

	@Override
	public void borrar(Integer idplanta) {
		this.plantaDao.borrar(idplanta);
	}

	@Override
	public Planta buscar(Integer idplanta) {
		return this.plantaDao.buscar(idplanta);
	}

	@Override
	public List<Planta> buscar() {
		return this.plantaDao.buscarTodos();
	}


}
