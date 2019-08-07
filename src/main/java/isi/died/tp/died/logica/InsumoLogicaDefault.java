package isi.died.tp.died.logica;

import java.util.List;
import isi.died.tp.died.dao.InsumoDao;
import isi.died.tp.died.dao.InsumoDaoH2;
import isi.died.tp.died.modelo.Insumo;


public class InsumoLogicaDefault implements InsumoLogica {

	//private ProyectoDao proyectoDao;	
	//private TareaLogica tareaLogica;
	private InsumoDao insumoDao;

	public InsumoLogicaDefault() {
		this.insumoDao = new InsumoDaoH2();
		//this.tareaLogica = new TareaLogicaDefault();
	}

	@Override
	public Insumo guardar(Insumo i) {
		// si tiene ID entonces actualizar
		if(i.getId()!= null && i.getId()>0) {
			return this.insumoDao.actualizar(i);
		} else {
			return this.insumoDao.crear(i);			
		}
	}

	/*@Override
	public void agregarTarea(Proyecto p, Tarea t) {
		t.setProyecto(p);
		this.tareaLogica.guardar(t);
	}*/

	@Override
	public void borrar(Integer idinsumo) {
		this.insumoDao.borrar(idinsumo);
	}

	@Override
	public Insumo buscar(Integer idinsumo) {
		return this.insumoDao.buscar(idinsumo);
	}

	@Override
	public List<Insumo> buscar() {
		return this.insumoDao.buscarTodos();
	}


}
