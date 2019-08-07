package isi.died.tp.died.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import isi.died.tp.died.gui.util.GenericTableColumn;
import isi.died.tp.died.gui.util.GenericTableModel;
import isi.died.tp.died.modelo.Camion;

public class PanelCamion extends JPanel {

	private JLabel lblMarca;
	private JLabel lblModelo;
	private JLabel lblDominio;
	private JLabel lblAnio;
	private JLabel lblCostoXKm;
	private JLabel lblTransportaLiquido;
	private JLabel lblCapacidad;
	private JLabel lblTituloPanel;
	private JTextField marca;
	private JTextField modelo;
	private JTextField dominio;
	private JFormattedTextField anioField;
	private JFormattedTextField costoXKmField;
	private JComboBox comboTransportaLiquido;
	private JFormattedTextField capacidadField;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JButton btnEditar;
	private JButton btnBuscar;
	private JButton btnBorrar;
	private JTable tablaCamiones;
	
	
	GenericTableModel<Camion> gtm ;
	
	private Integer idCamionSeleccionado;
	private NumberFormat anioFormat;
	private NumberFormat costoXKmFormat;
	private NumberFormat capacidadFormat;
	
	private PanelCamionController controller;

	public PanelCamion() {
		super();
		controller = new PanelCamionController(this);
		this.armar();
		this.configurarEventos();
		
	}

	public Dimension getPreferredSize() {
        return new Dimension(550,450);
    }

    public void armar() {
    	this.setLayout(new GridBagLayout());
    	
    	int fila = 0;
    	int col = 0;
    	
    	GridBagConstraints c = new GridBagConstraints();   

    	this.lblTituloPanel = new JLabel("Gestion de camiones");
    	this.lblTituloPanel.setFont(ServiceLocator.FUENTE_TITULO);
    	this.lblTituloPanel.setForeground(ServiceLocator.COLOR_TITULO);
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=3;
    	c.anchor=GridBagConstraints.CENTER;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblTituloPanel,c);
    	// reinicio variables de celda
    	col =0;
    	fila++;

    	this.lblMarca = new JLabel("Marca: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblMarca,c);
    	   	
    	this.marca = new JTextField(30);
    	this.marca.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(marca,c);
    	
    	this.lblModelo = new JLabel("Modelo: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblModelo,c);
    	   	
    	this.modelo = new JTextField(30);
    	this.modelo.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(modelo,c);
    	  	
    	this.lblDominio= new JLabel("Dominio: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblDominio,c);
    	
    	this.dominio= new JTextField(30);
    	this.dominio.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(dominio,c);

    	
    	this.lblCapacidad = new JLabel("Capacidad: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblCapacidad,c);
    	   	
    	this.capacidadField = new JFormattedTextField(capacidadFormat);
    	this.capacidadField.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(capacidadField,c);    	
    	
    	col =0;
    	fila++;
    	
    	this.lblCostoXKm = new JLabel("Costo (km): ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblCostoXKm,c);
    	   	
    	this.costoXKmField = new JFormattedTextField(costoXKmFormat);
    	this.costoXKmField.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(costoXKmField,c);
    	
    	this.lblTransportaLiquido= new JLabel("Transporta liquido: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblTransportaLiquido,c);
    	
    	this.comboTransportaLiquido= new JComboBox();
    	//this.controller.cargarComboAcopio(this.comboAcopio);
    	comboTransportaLiquido.addItem(true);
    	comboTransportaLiquido.addItem(false);
    	this.comboTransportaLiquido.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(comboTransportaLiquido,c);

    	
    	this.lblAnio= new JLabel("Año: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblAnio,c);
    	
    	this.anioField = new JFormattedTextField(anioFormat);
    	this.anioField.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(anioField,c);
    	  	
   	  	
    	this.btnGuardar = new JButton("Guardar");
    	this.btnGuardar.setEnabled(false);
    	c.gridx=col++;
    	c.weightx=0.0;
    	c.fill=GridBagConstraints.NONE;
    	this.add(btnGuardar,c);
    	
    	this.btnCancelar = new JButton("Cancelar");
    	this.btnCancelar.setEnabled(false);
    	c.gridx=col++;
    	c.weightx=0.2;
    	c.anchor = GridBagConstraints.LINE_START;
    	c.fill=GridBagConstraints.NONE;
    	this.add(btnCancelar,c);

    	
    	//////////////////////////////////////////////////////////////////
    	// reinicio variables de celda
    	c.gridwidth=col;
    	col =0;
    	fila++;
    	
    	c.gridx=col++;
    	c.gridy=fila;
    	c.fill=GridBagConstraints.BOTH;
    	c.weighty=1.0;
    	c.weightx=1.0;
    	/////////////////////////////////////////////////////////////////
    	 	
    	
    	this.gtm = crearModeloTabla();
    	this.tablaCamiones = new JTable(this.gtm);
    	this.tablaCamiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	JScrollPane scrollPane= new JScrollPane(this.tablaCamiones);
    	this.add(scrollPane,c);
    	
    	col =0;
    	fila++;
    	JPanel panelBotones = new JPanel();

    	this.btnNuevo = new JButton("Nuevo");
    	this.btnEditar = new JButton("Editar");
    	this.btnBuscar = new JButton("Buscar");
    	this.btnBorrar = new JButton("Eliminar");
    	this.btnEditar.setEnabled(false);
    	this.btnBorrar.setEnabled(false);
    	panelBotones.add(btnBuscar);
    	panelBotones.add(btnNuevo);
    	panelBotones.add(btnEditar);
    	panelBotones.add(btnBorrar);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.NONE;
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=3;
    	c.fill=GridBagConstraints.BOTH;
    	c.weighty=0.0;
    	c.weightx=1.0;
    	this.add(panelBotones,c);
    	
    }
    
    private void configurarEventos() {
    	this.btnGuardar.addActionListener( e -> {
    		
    		Integer pryAnio = ((Number)anioField.getValue()).intValue();
    		Double pryCostoXKm = ((Number)costoXKmField.getValue()).doubleValue();
    		Boolean prySeleccionado = (Boolean) comboTransportaLiquido.getSelectedItem();
    		Double pryCapacidad = ((Number)capacidadField.getValue()).doubleValue();
    		
    		if(idCamionSeleccionado<=0) {
    			controller.crearCamion(marca.getText(),modelo.getText(),dominio.getText(),pryAnio, pryCostoXKm, prySeleccionado, pryCapacidad);
    		}
    		else {
    			controller.actualizarCamion(idCamionSeleccionado,marca.getText(),modelo.getText(),dominio.getText(),pryAnio, pryCostoXKm, prySeleccionado, pryCapacidad);
    		}
    		
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);
    		marca.setEnabled(false);
    		marca.setText("");
    		modelo.setEnabled(false);
    		modelo.setText("");
    		dominio.setEnabled(false);
    		dominio.setText("");
    		btnNuevo.setEnabled(true);
    		comboTransportaLiquido.setEnabled(true);
    		anioField.setEnabled(false);
    		anioField.setText("");
    		costoXKmField.setEnabled(false);
    		costoXKmField.setText("");
    		capacidadField.setEnabled(false);
    		capacidadField.setText("");
    	
    		
    		
    		//btnCheckBox.setEnabled(true);
    	});
    	
    	this.btnNuevo.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		marca.setEnabled(true);
    		marca.setText("");
    		modelo.setEnabled(true);
    		modelo.setText("");
    		dominio.setEnabled(true);
    		dominio.setText("");
    		btnNuevo.setEnabled(false);
    		comboTransportaLiquido.setEnabled(true);
    		anioField.setEnabled(true);
    		anioField.setText("");
    		costoXKmField.setEnabled(true);
    		costoXKmField.setText("");
    		capacidadField.setEnabled(true);
    		capacidadField.setText("");
    		
    		idCamionSeleccionado=-1;
    		//btnCheckBox.setEnabled(true);
    	});
    	
    	this.btnCancelar.addActionListener( e -> {
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);    		
    		btnNuevo.setEnabled(true);
    		comboTransportaLiquido.setEnabled(false);
    		marca.setEnabled(false);
    		marca.setText("");
    		modelo.setEnabled(false);
    		modelo.setText("");
    		dominio.setEnabled(false);
    		dominio.setText("");
    		anioField.setEnabled(false);
    		anioField.setText("");
    		costoXKmField.setEnabled(false);
    		costoXKmField.setText("");
    		capacidadField.setEnabled(false);
    		capacidadField.setText("");
    		
    		idCamionSeleccionado=-1;
    		
    	});
    	
    	this.btnBuscar.addActionListener( e -> {
    		System.out.println("BUSCAR....");
    		controller.actualizarTablaCamion();
    	});
    	
    	this.btnEditar.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnNuevo.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		comboTransportaLiquido.setEnabled(true);
    		marca.setEnabled(true);
    		modelo.setEnabled(true);
    		dominio.setEnabled(true);
    		anioField.setEnabled(true);
    		costoXKmField.setEnabled(true);
    		capacidadField.setEnabled(true);
    		
    		
    	});
    	
    	this.btnBorrar.addActionListener( e -> {
    		int resultado = JOptionPane.showConfirmDialog(null, "Borrar camion", "Desea borrar el insumo "+marca.getText()+ "?", JOptionPane.YES_NO_OPTION);
    		if(resultado ==0) {
    			controller.borrarCamion(idCamionSeleccionado);
	    		btnBorrar.setEnabled(false);
    		}
    	});
    
    	this.tablaCamiones.getSelectionModel().addListSelectionListener(lse -> {
    		if(gtm.getDatos()!=null && !gtm.getDatos().isEmpty() && lse.getFirstIndex()< gtm.getDatos().size()) {
	    		Camion aux = gtm.datos.get(lse.getFirstIndex());
	    		idCamionSeleccionado = aux.getId();
	    		marca.setText(aux.getMarca());
	    		modelo.setText(aux.getModelo());
	    		dominio.setText(aux.getDominio());
	    		comboTransportaLiquido.setSelectedItem(aux.getTransportaLiquido());
	    		anioField.setValue(new Double(aux.getAnio()));
	    		costoXKmField.setValue(new Double(aux.getCostoXKm()));
	    		capacidadField.setValue(new Double(aux.getCapacidad()));
	    		btnEditar.setEnabled(true);
	    		btnBorrar.setEnabled(true);
	    		//System.out.println(aux.getNombre());
    		}
        });
    }
    
    
    private GenericTableModel<Camion> crearModeloTabla(){
    	this.gtm = new GenericTableModel<Camion>();
    	List<GenericTableColumn> lista = new ArrayList<GenericTableColumn>();
    	lista.add(new GenericTableColumn("idcamion" , "getId"));
    	lista.add(new GenericTableColumn("Marca" , "getMarca"));
    	lista.add(new GenericTableColumn("Modelo" , "getModelo"));
    	lista.add(new GenericTableColumn("Dominio" , "getDominio"));
    	lista.add(new GenericTableColumn("Año" , "getAnio"));
    	lista.add(new GenericTableColumn("Costo (km)" , "getCostoXKm"));
    	lista.add(new GenericTableColumn("Transporta Liquido" , "getTransportaLiquido"));
    	lista.add(new GenericTableColumn("Capacidad" , "getCapacidad"));
    	gtm.setColumnas(lista);
    	
    	return gtm;
    }
    
    
    /**
     * Metodo invocado por el controller para que actualice los datos de la JTable
     * @param listaPry
     */
    public void actualizarDatosTabla(List<Camion> listaPry) {
    	this.gtm.setDatos(listaPry);
    	this.gtm.fireTableDataChanged();
    }
    
}
