package isi.died.tp.died.gui;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import isi.died.tp.died.logica.InsumoLogica;
import isi.died.tp.died.logica.InsumoLogicaDefault;
import isi.died.tp.died.modelo.Insumo;

public class PanelInsumoController {
	
	private PanelInsumo panel;
	private InsumoLogica logica;
	
	public PanelInsumoController(PanelInsumo listener) {
		this.panel = listener;		
		this.logica = new InsumoLogicaDefault();
		
		
		List<Insumo> lista = logica.buscar();
		System.out.println(lista);
	}
	
	public void crearInsumo(String nombreInsumo, String descripcion, String unidadDeMedida, Double peso, Double costo, Boolean esRefrigerado, Boolean esLiquido) {
			Runnable r = () -> {
				Insumo i = new Insumo();
				i.setNombre(nombreInsumo);
				i.setDescripcion(descripcion);
				i.setUnidadDeMedida(unidadDeMedida);
				i.setPeso(peso);
				i.setCosto(costo);
				i.setEsRefrigerado(esRefrigerado);
				i.setEsLiquido(esLiquido);
				this.logica.guardar(i);
				List<Insumo> lista = logica.buscar();
				try {
					SwingUtilities.invokeAndWait(() -> {
						panel.actualizarDatosTabla(lista);
						JOptionPane.showMessageDialog((Component) panel, "Insumo Creado");
					});
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}
			};
			
			Thread hilo = new Thread(r);
			
			hilo.start();
	}
	
	public void actualizarInsumo(Integer idinsumo , String nombreInsumo, String descripcion, String unidadDeMedida, Double peso, Double costo, Boolean esRefrigerado, Boolean esLiquido) {
		Runnable r = () -> {
			Insumo i = new Insumo();
			i.setNombre(nombreInsumo);
			i.setId(idinsumo);
			i.setDescripcion(descripcion);
			i.setUnidadDeMedida(unidadDeMedida);
			i.setPeso(peso);
			i.setCosto(costo);
			i.setEsRefrigerado(esRefrigerado);
			i.setEsLiquido(esLiquido);
			this.logica.guardar(i);
			List<Insumo> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista);
					JOptionPane.showMessageDialog((Component) panel, "Insumo "+ nombreInsumo +" Actualizado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}

	public void borrarInsumo(Integer idinsumo) {
		Runnable r = () -> {
			this.logica.borrar(idinsumo);
			List<Insumo> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista );
					JOptionPane.showMessageDialog((Component) panel, "Insumo borrado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
	
	public void actualizarTablaInsumo() {
		Runnable r = () -> {
			List<Insumo> lista = logica.buscar();
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
