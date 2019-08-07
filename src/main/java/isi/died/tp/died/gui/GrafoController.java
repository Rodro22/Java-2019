package isi.died.tp.died.gui;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.SwingUtilities;

import isi.died.tp.died.logica.ProyectoLogica;
import isi.died.tp.died.logica.ProyectoLogicaDefault;
import isi.died.tp.died.logica.TareaLogica;
import isi.died.tp.died.logica.TareaLogicaDefault;
import isi.died.tp.died.modelo.Tarea;

public class GrafoController {

	private GrafoPanel panel;
	private TareaLogica logicaTareas;
	private ProyectoLogica logicaProyecto;
	//private PlantaLogica logicaPlanta;
		
	public GrafoController(GrafoPanel listener) {
		this.panel = listener;
		this.logicaTareas = new TareaLogicaDefault();
		this.logicaProyecto = new ProyectoLogicaDefault(); 
	}
	
	public void inicalizarVertices() {
		Runnable r = () -> {
			List<Tarea> lista = this.logicaTareas.buscar();
			int y = 100;
			int x = 0;
			int i = 0;
			Color c = null;
			for(Tarea p : lista){
				i++;
				x +=30; 
				if( i % 2 == 0 ) {
					y = 100;
					c = Color.BLUE;
				} else {
					y = 200;
					c = Color.RED;
				}
				
				VerticeView v = new VerticeView(x,y,c);
				v.setId(p.getId());
				v.setNombre(p.getDescripcion());
				panel.agregar(v);
			}
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.repaint();
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
}
