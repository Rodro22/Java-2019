package isi.died.tp.died.gui;

import java.awt.Color;
import java.awt.Font;

public class ServiceLocator {

	private static ServiceLocator _INSTANCIA = null;
	private PanelProyecto panelProyecto = null;
	private PanelTarea panelTarea = null;
	private GrafoPanel panelGrafo = null;
	private PanelPlanta panelPlanta = null;
	private PanelInsumo panelInsumo = null;
	private PanelCamion panelCamion = null;
	private PanelCamino panelCamino = null;
	private PanelStock panelStock = null;
	private PanelCargaStock panelCargaStock = null;
	public static final Font FUENTE_TITULO = new Font("Calibri",Font.BOLD,18);
	public static final Color COLOR_TITULO = new Color(5,85,244);
	
	private ServiceLocator() {
	}
	
	public static ServiceLocator getInstance() {
		if(_INSTANCIA == null ) _INSTANCIA = new ServiceLocator();
		return _INSTANCIA;
	}

	public PanelProyecto getPanelProyecto() {
		if(this.panelProyecto == null) this.panelProyecto = new PanelProyecto();
		return panelProyecto;
	}
	
	public PanelTarea getPanelTarea() {
		if(this.panelTarea == null) this.panelTarea = new PanelTarea();
		return panelTarea;
	}
	
	public GrafoPanel getPanelGrafo() {
		if(this.panelGrafo == null) this.panelGrafo = new GrafoPanel();
		return panelGrafo;
	}	  
	
	public PanelPlanta getPanelPlanta() {
		if(this.panelPlanta == null) this.panelPlanta = new PanelPlanta();
		return panelPlanta;
	}
	
	public PanelInsumo getPanelInsumo() {
		if(this.panelInsumo == null) this.panelInsumo = new PanelInsumo();
		return panelInsumo;
	}
	
	public PanelCamion getPanelCamion() {
		if(this.panelCamion == null) this.panelCamion = new PanelCamion();
		return panelCamion;
	}
	
	public PanelCamino getPanelCamino() {
		if(this.panelCamino == null) this.panelCamino= new PanelCamino();
		return panelCamino;
	}
	
	public PanelStock getPanelStock() {
		if(this.panelStock == null) this.panelStock = new PanelStock();
		return panelStock;
	}
	public PanelCargaStock getPanelCargaStock() {
		if(this.panelCargaStock == null) this.panelCargaStock = new PanelCargaStock();
		return panelCargaStock;
	}
	
}
