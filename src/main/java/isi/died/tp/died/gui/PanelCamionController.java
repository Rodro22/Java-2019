package isi.died.tp.died.gui;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import isi.died.tp.died.logica.CamionLogica;
import isi.died.tp.died.logica.CamionLogicaDefault;
import isi.died.tp.died.modelo.Camion;

public class PanelCamionController {
	
	private PanelCamion panel;
	private CamionLogica logica;
	
	public PanelCamionController(PanelCamion listener) {
		this.panel = listener;		
		this.logica = new CamionLogicaDefault();
		
		
		List<Camion> lista = logica.buscar();
		System.out.println(lista);
	}
	
	public void crearCamion(String marca, String modelo, String dominio, Integer anio, Double costoXKm, Boolean transportaLiquido, Double capacidad) {
			Runnable r = () -> {
				Camion c = new Camion();
				c.setMarca(marca);
				c.setModelo(modelo);
				c.setDominio(dominio);
				c.setAnio(anio);
				c.setCostoXKm(costoXKm);
				c.setTransportaLiquido(transportaLiquido);
				c.setCapacidad(capacidad);
				this.logica.guardar(c);
				List<Camion> lista = logica.buscar();
				try {
					SwingUtilities.invokeAndWait(() -> {
						panel.actualizarDatosTabla(lista);
						JOptionPane.showMessageDialog((Component) panel, "Camion Creado");
					});
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}
			};
			
			Thread hilo = new Thread(r);
			
			hilo.start();
	}
	
	public void actualizarCamion(Integer idcamion, String marca, String modelo, String dominio, Integer anio, Double costoXKm, Boolean transportaLiquido, Double capacidad) {
		Runnable r = () -> {
			Camion c = new Camion();
			c.setId(idcamion);
			c.setMarca(marca);
			c.setModelo(modelo);
			c.setDominio(dominio);
			c.setAnio(anio);
			c.setCostoXKm(costoXKm);
			c.setTransportaLiquido(transportaLiquido);
			c.setCapacidad(capacidad);
			this.logica.guardar(c);
			List<Camion> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista);
					JOptionPane.showMessageDialog((Component) panel, "Camion "+ marca +" Actualizado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}

	public void borrarCamion(Integer idcamion) {
		Runnable r = () -> {
			this.logica.borrar(idcamion);
			List<Camion> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista );
					JOptionPane.showMessageDialog((Component) panel, "Camion borrado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
	
	public void actualizarTablaCamion() {
		Runnable r = () -> {
			List<Camion> lista = logica.buscar();
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
