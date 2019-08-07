package isi.died.tp.died.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import isi.died.tp.died.modelo.Planta;

public class PanelPlanta extends JPanel {

	private JLabel lblNombrePlanta;
	private JLabel lblTituloPanel;
	private JLabel lblAcopio;
	private JTextField nombrePlanta;
	private JComboBox comboAcopio;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JButton btnEditar;
	private JButton btnBuscar;
	private JButton btnBorrar;
	private JTable tablaPlantas;
	
	
	GenericTableModel<Planta> gtm ;
	
	private Integer idPlantaSeleccionada;
	
	
	private PanelPlantaController controller;

	public PanelPlanta() {
		super();
		controller = new PanelPlantaController(this);
		this.armar();
		this.configurarEventos();
		
	}

	public Dimension getPreferredSize() {
        return new Dimension(450,250);
    }

    public void armar() {
    	this.setLayout(new GridBagLayout());
    	
    	int fila = 0;
    	int col = 0;
    	
    	GridBagConstraints c = new GridBagConstraints();    	

    	this.lblTituloPanel = new JLabel("Gestion de plantas");
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

    	this.lblNombrePlanta = new JLabel("Nombre: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblNombrePlanta,c);
    	   	
    	this.nombrePlanta = new JTextField(30);
    	this.nombrePlanta.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(nombrePlanta,c);
    	
    	this.lblAcopio= new JLabel("Acopio: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblAcopio,c);
    	
    	this.comboAcopio = new JComboBox();
    	//this.controller.cargarComboAcopio(this.comboAcopio);
    	comboAcopio.addItem(true);
    	comboAcopio.addItem(false);
    	this.comboAcopio.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(comboAcopio,c);
    	    	
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

    	
    	// reinicio variables de celda
    	c.gridwidth=col;
    	col =0;
    	fila++;
    	
    	c.gridx=col++;
    	c.gridy=fila;
    	c.fill=GridBagConstraints.BOTH;
    	c.weighty=1.0;
    	c.weightx=1.0;
    	
    	this.gtm = crearModeloTabla();
    	this.tablaPlantas = new JTable(this.gtm);
    	this.tablaPlantas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	JScrollPane scrollPane= new JScrollPane(this.tablaPlantas);
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
    		
    		Boolean prySeleccionado = (Boolean) comboAcopio.getSelectedItem();
    		if(idPlantaSeleccionada<=0) {
    			controller.crearPlanta(nombrePlanta.getText(),prySeleccionado);
    		}
    		else {
    			controller.actualizarPlanta(idPlantaSeleccionada,nombrePlanta.getText(),prySeleccionado);
    		}
    		
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);
    		nombrePlanta.setEnabled(false);
    		btnNuevo.setEnabled(true);
    		comboAcopio.setEnabled(true);
    		nombrePlanta.setText("");
    		
    		//btnCheckBox.setEnabled(true);
    	});
    	
    	this.btnNuevo.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		nombrePlanta.setEnabled(true);
    		btnNuevo.setEnabled(false);
    		comboAcopio.setEnabled(true);
    		nombrePlanta.setText("");
    		idPlantaSeleccionada=-1;
    		//btnCheckBox.setEnabled(true);
    	});
    	
    	this.btnCancelar.addActionListener( e -> {
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);    		
    		btnNuevo.setEnabled(true);
    		comboAcopio.setEnabled(false);
    		nombrePlanta.setEnabled(false);
    		nombrePlanta.setText("");
    		idPlantaSeleccionada=-1;
    		
    	});
    	
    	this.btnBuscar.addActionListener( e -> {
    		System.out.println("BUSCAR....");
    		controller.actualizarTablaPlanta();
    	});
    	
    	this.btnEditar.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnNuevo.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		comboAcopio.setEnabled(true);
    		nombrePlanta.setEnabled(true);
    	});
    	
    	this.btnBorrar.addActionListener( e -> {
    		int resultado = JOptionPane.showConfirmDialog(null, "Borrar proyecto", "Desea borrar el proyecto "+nombrePlanta.getText()+ "?", JOptionPane.YES_NO_OPTION);
    		if(resultado ==0) {
    			controller.borrarPlanta(idPlantaSeleccionada);
	    		btnBorrar.setEnabled(false);
    		}
    	});
    
    	this.tablaPlantas.getSelectionModel().addListSelectionListener(lse -> {
    		if(gtm.getDatos()!=null && !gtm.getDatos().isEmpty() && lse.getFirstIndex()< gtm.getDatos().size()) {
	    		Planta aux = gtm.datos.get(lse.getFirstIndex());
	    		idPlantaSeleccionada = aux.getId();
	    		nombrePlanta.setText(aux.getNombre());
	    		comboAcopio.setSelectedItem(aux.getEsAcopio());
	    		btnEditar.setEnabled(true);
	    		btnBorrar.setEnabled(true);
	    		System.out.println(aux.getNombre());
    		}
        });
    }
    
    
    private GenericTableModel<Planta> crearModeloTabla(){
    	this.gtm = new GenericTableModel<Planta>();
    	List<GenericTableColumn> lista = new ArrayList<GenericTableColumn>();
    	lista.add(new GenericTableColumn("idplanta" , "getId"));
    	lista.add(new GenericTableColumn("nombre_planta" , "getNombre"));
    	lista.add(new GenericTableColumn("acopio" , "getEsAcopio"));
    	gtm.setColumnas(lista);
    	
    	return gtm;
    }
    
    
    /**
     * Metodo invocado por el controller para que actualice los datos de la JTable
     * @param listaPry
     */
    public void actualizarDatosTabla(List<Planta> listaPry) {
    	this.gtm.setDatos(listaPry);
    	this.gtm.fireTableDataChanged();
    }
    
}
