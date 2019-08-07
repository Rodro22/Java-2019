package isi.died.tp.died.gui;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import isi.died.tp.died.logica.CaminoLogica;
import isi.died.tp.died.logica.CaminoLogicaDefault;
import isi.died.tp.died.logica.PlantaLogica;
import isi.died.tp.died.logica.PlantaLogicaDefault;
import isi.died.tp.died.logica.ProyectoLogicaDefault;
import isi.died.tp.died.logica.CamionLogica;
import isi.died.tp.died.logica.CamionLogicaDefault;
import isi.died.tp.died.modelo.Camino;
import isi.died.tp.died.modelo.Planta;
import isi.died.tp.died.modelo.Camion;

public class PanelCaminoController {
	
	private PanelCamino panel;
	private CaminoLogica logica;
	private PlantaLogica logicaPlanta;
	private CamionLogica logicaCamion;
	
	public PanelCaminoController(PanelCamino listener) {
		this.panel = listener;		
		this.logica = new CaminoLogicaDefault();
		this.logicaPlanta = new PlantaLogicaDefault(); 
		this.logicaCamion = new CamionLogicaDefault(); 
		
		
		List<Camino> lista = logica.buscar();
		System.out.println(lista);
	}
	
	public void crearCamino(Planta planta_init, Planta planta_end, Camion camion, Double distancia, Double duracion, Double peso_soportado) {
			Runnable r = () -> {
				Camino c = new Camino();
				c.setPlantaInit(planta_init);
				c.setPlantaEnd(planta_end);
				c.setCamion(camion);
				c.setDistancia(distancia);
				c.setDuracion(duracion);
				c.setPesoSoportado(peso_soportado);
				this.logica.guardar(c);
				List<Camino> lista = logica.buscar();
				try {
					SwingUtilities.invokeAndWait(() -> {
						panel.actualizarDatosTabla(lista);
						JOptionPane.showMessageDialog((Component) panel, "Camino Creado");
					});
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}
			};
			
			Thread hilo = new Thread(r);
			
			hilo.start();
	}
	
	/*public void crearCamino(Integer idplanta_init, Integer idplanta_end, Integer camion_idcamion, Double distancia, Double duracion, Double peso_soportado) {
		Runnable r = () -> {
			Camino c = new Camino();
			c.setPlantaInit(idplanta_init);
			c.setPlantaEnd(idplanta_end);
			c.setCamion(camion_idcamion);
			c.setDistancia(distancia);
			c.setDuracion(duracion);
			c.setPesoSoportado(peso_soportado);
			this.logica.guardar(c);
			List<Camino> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista);
					JOptionPane.showMessageDialog((Component) panel, "Camino Creado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();
}*/
	
	public void actualizarCamino(Integer idcamino, Planta planta_init, Planta planta_end, Camion camion, Double distancia, Double duracion, Double peso_soportado) {
		Runnable r = () -> {
			Camino c = new Camino();
			c.setId(idcamino);
			c.setPlantaInit(planta_init);
			c.setPlantaEnd(planta_end);
			c.setCamion(camion);
			c.setDistancia(distancia);
			c.setDuracion(duracion);
			c.setPesoSoportado(peso_soportado);
			this.logica.guardar(c);
			List<Camino> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista);
					JOptionPane.showMessageDialog((Component) panel, "Camino actualizado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}

	public void borrarCamino(Integer idcamino) {
		Runnable r = () -> {
			this.logica.borrar(idcamino);
			List<Camino> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista );
					JOptionPane.showMessageDialog((Component) panel, "Camino borrado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
	
	public void actualizarTablaCamino() {
		Runnable r = () -> {
			List<Camino> lista = logica.buscar();
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
	
	public void cargarComboCamiones(JComboBox<Camion> combo){
	Runnable r = () -> {
			List<Camion> lista = this.logicaCamion.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					for(Camion c : lista){
						combo.addItem(c);
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
