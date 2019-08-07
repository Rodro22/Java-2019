package isi.died.tp.died.logica;

import java.util.List;
import isi.died.tp.died.dao.CargaStockDao;
import isi.died.tp.died.dao.CargaStockDaoH2;
import isi.died.tp.died.modelo.CargaStock;


public class CargaStockLogicaDefault implements CargaStockLogica {

	//private ProyectoDao proyectoDao;	
	//private TareaLogica tareaLogica;
	private CargaStockDao cargaStockDao;

	public CargaStockLogicaDefault() {
		this.cargaStockDao = new CargaStockDaoH2();
		//this.tareaLogica = new TareaLogicaDefault();
	}

	@Override
	public CargaStock guardar(CargaStock c) {
		// si tiene ID entonces actualizar
		if(c.getId()!= null && c.getId()>0) {
			return this.cargaStockDao.actualizar(c);
		} else {
			return this.cargaStockDao.crear(c);			
		}
	}

	/*@Override
	public void agregarTarea(Proyecto p, Tarea t) {
		t.setProyecto(p);
		this.tareaLogica.guardar(t);
	}*/

	@Override
	public void borrar(Integer idcargastock) {
		this.cargaStockDao.borrar(idcargastock);
	}

	@Override
	public CargaStock buscar(Integer idcargastock) {
		return this.cargaStockDao.buscar(idcargastock);
	}

	@Override
	public List<CargaStock> buscar() {
		return this.cargaStockDao.buscarTodos();
	}


}
