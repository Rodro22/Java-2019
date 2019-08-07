package isi.died.tp.died.logica;

import java.util.List;
import isi.died.tp.died.dao.StockDao;
import isi.died.tp.died.dao.StockDaoH2;
import isi.died.tp.died.modelo.Stock;


public class StockLogicaDefault implements StockLogica {

	//private ProyectoDao proyectoDao;	
	//private TareaLogica tareaLogica;
	private StockDao stockDao;

	public StockLogicaDefault() {
		this.stockDao = new StockDaoH2();
		//this.tareaLogica = new TareaLogicaDefault();
	}

	@Override
	public Stock guardar(Stock s) {
		// si tiene ID entonces actualizar
		if(s.getId()!= null && s.getId()>0) {
			return this.stockDao.actualizar(s);
		} else {
			return this.stockDao.crear(s);			
		}
	}

	/*@Override
	public void agregarTarea(Proyecto p, Tarea t) {
		t.setProyecto(p);
		this.tareaLogica.guardar(t);
	}*/

	@Override
	public void borrar(Integer idstock) {
		this.stockDao.borrar(idstock);
	}

	@Override
	public Stock buscar(Integer idstock) {
		return this.stockDao.buscar(idstock);
	}

	@Override
	public List<Stock> buscar() {
		return this.stockDao.buscarTodos();
	}


}
