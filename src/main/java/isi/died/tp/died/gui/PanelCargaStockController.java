package isi.died.tp.died.gui;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import isi.died.tp.died.logica.CargaStockLogica;
import isi.died.tp.died.logica.CargaStockLogicaDefault;
import isi.died.tp.died.logica.InsumoLogica;
import isi.died.tp.died.logica.InsumoLogicaDefault;
import isi.died.tp.died.logica.StockLogica;
import isi.died.tp.died.logica.StockLogicaDefault;

import isi.died.tp.died.modelo.CargaStock;
import isi.died.tp.died.modelo.Insumo;
import isi.died.tp.died.modelo.Stock;

public class PanelCargaStockController {
	
	private PanelCargaStock panel;
	private CargaStockLogica logica;
	private InsumoLogica logicaInsumo;
	private StockLogica logicaStock;
	
	public PanelCargaStockController(PanelCargaStock listener) {
		this.panel = listener;		
		this.logica = new CargaStockLogicaDefault();
		this.logicaInsumo = new InsumoLogicaDefault(); 
		this.logicaStock = new StockLogicaDefault(); 
		
		
		List<CargaStock> lista = logica.buscar();
		System.out.println(lista);
	}
	
	public void crearCargaStock(Insumo insumo, Stock stock, Double cantidadRequerida, Double stockDisponible) {
			Runnable r = () -> {
				CargaStock c = new CargaStock();
				c.setInsumo(insumo);
				c.setStock(stock);
				c.setCantidadRequerida(cantidadRequerida);
				c.setStockDisponible(stockDisponible);
				this.logica.guardar(c);
				List<CargaStock> lista = logica.buscar();
				try {
					SwingUtilities.invokeAndWait(() -> {
						panel.actualizarDatosTabla(lista);
						JOptionPane.showMessageDialog((Component) panel, "Insumo agregado al stock");
					});
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}
			};
			
			Thread hilo = new Thread(r);
			
			hilo.start();
	}
	
	public void actualizarCargaStock(Integer idcargastock, Insumo insumo, Stock stock , Double cantidadRequerida, Double stockDisponible) {
		Runnable r = () -> {
			CargaStock c = new CargaStock();
			c.setId(idcargastock);
			c.setInsumo(insumo);
			c.setStock(stock);
			c.setCantidadRequerida(cantidadRequerida);
			c.setStockDisponible(stockDisponible);
			this.logica.guardar(c);
			List<CargaStock> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista);
					JOptionPane.showMessageDialog((Component) panel, "Stock actualizado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}

	public void borrarCargaStock(Integer idcargastock) {
		Runnable r = () -> {
			this.logica.borrar(idcargastock);
			List<CargaStock> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista );
					JOptionPane.showMessageDialog((Component) panel, "Insumo borrado del stock");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
	
	public void actualizarTablaCargaStock() {
		Runnable r = () -> {
			List<CargaStock> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista);
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
	
	public void cargarComboStocks(JComboBox<Stock> combo){
	
	Runnable r = () -> {
			List<Stock> lista = this.logicaStock.buscar();
			System.out.println(lista);
			try {
				SwingUtilities.invokeAndWait(() -> {
					for(Stock s : lista){
						combo.addItem(s);
						//System.out.println(s.getPlanta());
					}
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
	
	public void cargarComboInsumos(JComboBox<Insumo> combo){
	Runnable r = () -> {
			List<Insumo> lista = this.logicaInsumo.buscar();
			//System.out.println(lista);
			try {
				SwingUtilities.invokeAndWait(() -> {
					for(Insumo i : lista){
						combo.addItem(i);
					}
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
	

}
