package isi.died.tp.died.gui;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import isi.died.tp.died.logica.StockLogica;
import isi.died.tp.died.logica.StockLogicaDefault;
import isi.died.tp.died.logica.PlantaLogica;
import isi.died.tp.died.logica.PlantaLogicaDefault;
import isi.died.tp.died.modelo.Stock;
import isi.died.tp.died.modelo.Planta;

public class PanelStockController {
	
	private PanelStock panel;
	private StockLogica logica;
	private PlantaLogica logicaPlanta;
	
	public PanelStockController(PanelStock listener) {
		this.panel = listener;		
		this.logica = new StockLogicaDefault();
		this.logicaPlanta = new PlantaLogicaDefault(); 
		
		List<Stock> lista = logica.buscar();
		System.out.println(lista);
	}
	
	public void crearStock(Planta planta) {
			Runnable r = () -> {
				Stock s = new Stock();
				s.setPlanta(planta);
				this.logica.guardar(s);
				List<Stock> lista = logica.buscar();
				try {
					SwingUtilities.invokeAndWait(() -> {
						panel.actualizarDatosTabla(lista);
						JOptionPane.showMessageDialog((Component) panel, "Stock Creado");
					});
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}
			};
			
			Thread hilo = new Thread(r);
			
			hilo.start();
	}
		
	public void actualizarStock(Integer idstock, Planta planta) {
		Runnable r = () -> {
			Stock s = new Stock();
			s.setId(idstock);
			s.setPlanta(planta);
			this.logica.guardar(s);
			List<Stock> lista = logica.buscar();
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

	public void borrarStock(Integer idstock) {
		Runnable r = () -> {
			this.logica.borrar(idstock);
			List<Stock> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista );
					JOptionPane.showMessageDialog((Component) panel, "Stock borrado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
	
	public void actualizarTablaStock() {
		Runnable r = () -> {
			List<Stock> lista = logica.buscar();
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
	
	public void cargarComboPlantas(JComboBox<Planta> combo){
	Runnable r = () -> {
			List<Planta> lista = this.logicaPlanta.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					for(Planta p : lista){
						combo.addItem(p);
						System.out.println(p.getId());
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
