package isi.died.tp.died.gui;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import isi.died.tp.died.logica.PlantaLogica;
import isi.died.tp.died.logica.PlantaLogicaDefault;
import isi.died.tp.died.modelo.Planta;
import isi.died.tp.died.modelo.Proyecto;

public class PanelPlantaController {
	
	private PanelPlanta panel;
	private PlantaLogica logica;
	
	public PanelPlantaController(PanelPlanta listener) {
		this.panel = listener;		
		this.logica = new PlantaLogicaDefault();
		
		
		List<Planta> lista = logica.buscar();
		System.out.println(lista);
	}
	
	public void crearPlanta(String nombre_planta, Boolean esAcopio) {
			Runnable r = () -> {
				Planta p = new Planta();
				p.setNombre(nombre_planta);
				p.setEsAcopio(esAcopio);
				//p.setEsAcopio(acopio);
				this.logica.guardar(p);
				List<Planta> lista = logica.buscar();
				try {
					SwingUtilities.invokeAndWait(() -> {
						panel.actualizarDatosTabla(lista);
						JOptionPane.showMessageDialog((Component) panel, "Planta Creada");
					});
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}
			};
			
			Thread hilo = new Thread(r);
			
			hilo.start();
	}
	
	public void actualizarPlanta(Integer idplanta , String nombre_planta, Boolean esAcopio) {
		Runnable r = () -> {
			Planta p = new Planta();
			p.setNombre(nombre_planta);
			p.setId(idplanta);
			p.setEsAcopio(esAcopio);
			this.logica.guardar(p);
			List<Planta> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista);
					JOptionPane.showMessageDialog((Component) panel, "Planta "+ nombre_planta +" Actualizado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}

	public void borrarPlanta(Integer idplanta) {
		Runnable r = () -> {
			this.logica.borrar(idplanta);
			List<Planta> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista );
					JOptionPane.showMessageDialog((Component) panel, "Planta borrada");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
	
	public void actualizarTablaPlanta() {
		Runnable r = () -> {
			List<Planta> lista = logica.buscar();
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
	

}
