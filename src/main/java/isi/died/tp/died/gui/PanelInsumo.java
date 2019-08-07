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
import isi.died.tp.died.modelo.Insumo;

public class PanelInsumo extends JPanel {

	private JLabel lblNombreInsumo;
	private JLabel lblDescripcion;
	private JLabel lblUnidadDeMedida;
	private JLabel lblPeso;
	private JLabel lblCosto;
	private JLabel lblEsRefrigerado;
	private JLabel lblEsLiquido;
	private JLabel lblTituloPanel;
	private JTextField nombreInsumo;
	private JTextField descripcion;
	private JFormattedTextField pesoField;
	private JFormattedTextField costoField;
	private JComboBox comboUnidadDeMedida;
	private JComboBox comboEsRefrigerado;
	private JComboBox comboEsLiquido;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JButton btnEditar;
	private JButton btnBuscar;
	private JButton btnBorrar;
	private JTable tablaPlantas;
	
	
	GenericTableModel<Insumo> gtm ;
	
	private Integer idInsumoSeleccionado;
	private NumberFormat pesoFormat;
	private NumberFormat costoFormat;
	
	private PanelInsumoController controller;

	public PanelInsumo() {
		super();
		controller = new PanelInsumoController(this);
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

    	this.lblTituloPanel = new JLabel("Gestion de insumos");
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

    	this.lblNombreInsumo = new JLabel("Nombre: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblNombreInsumo,c);
    	   	
    	this.nombreInsumo = new JTextField(30);
    	this.nombreInsumo.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(nombreInsumo,c);
    	
    	this.lblDescripcion = new JLabel("Descripcion: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblDescripcion,c);
    	   	
    	this.descripcion = new JTextField(30);
    	this.descripcion.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(descripcion,c);
    	  	
    	this.lblUnidadDeMedida= new JLabel("Unidad de Medida: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblUnidadDeMedida,c);
    	
    	this.comboUnidadDeMedida = new JComboBox();
    	//this.controller.cargarComboAcopio(this.comboAcopio);
    	comboUnidadDeMedida.addItem("kg");
    	comboUnidadDeMedida.addItem("pieza");
    	comboUnidadDeMedida.addItem("g");
    	comboUnidadDeMedida.addItem("mt");
    	comboUnidadDeMedida.addItem("lt");
    	comboUnidadDeMedida.addItem("m3");
    	comboUnidadDeMedida.addItem("m2");
    	this.comboUnidadDeMedida.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(comboUnidadDeMedida,c);
    	
    	
    	this.lblEsLiquido= new JLabel("Liquido: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblEsLiquido,c);
    	
    	this.comboEsLiquido = new JComboBox();
    	//this.controller.cargarComboAcopio(this.comboAcopio);
    	comboEsLiquido.addItem(true);
    	comboEsLiquido.addItem(false);
    	this.comboEsLiquido.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(comboEsLiquido,c);
    	    	

    	
    	col =0;
    	fila++;
    	
    	this.lblPeso = new JLabel("Peso: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblPeso,c);
    	   	
    	this.pesoField = new JFormattedTextField(pesoFormat);
    	this.pesoField.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(pesoField,c);
    	
    	this.lblCosto = new JLabel("Costo: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblCosto,c);
    	   	
    	this.costoField = new JFormattedTextField(costoFormat);
    	this.costoField.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(costoField,c);
    	  	
    	this.lblEsRefrigerado= new JLabel("Refrigerado: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblEsRefrigerado,c);
    	
    	this.comboEsRefrigerado = new JComboBox();
    	//this.controller.cargarComboAcopio(this.comboAcopio);
    	comboEsRefrigerado.addItem(true);
    	comboEsRefrigerado.addItem(false);
    	this.comboEsRefrigerado.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(comboEsRefrigerado,c);
    	  	
    	
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
    		
    		String prySeleccionado = (String) comboUnidadDeMedida.getSelectedItem();
    		Boolean pry2Seleccionado = (Boolean) comboEsRefrigerado.getSelectedItem();
    		Boolean pry3Seleccionado = (Boolean) comboEsLiquido.getSelectedItem();
    		Double pryPeso = ((Number)pesoField.getValue()).doubleValue();
    		Double pryCosto = ((Number)costoField.getValue()).doubleValue();
    		System.out.println(pryPeso);
    		
    		if(idInsumoSeleccionado<=0) {
    			controller.crearInsumo(nombreInsumo.getText(),descripcion.getText(),prySeleccionado, pryPeso, pryCosto, pry2Seleccionado, pry3Seleccionado);
    		}
    		else {
    			controller.actualizarInsumo(idInsumoSeleccionado,nombreInsumo.getText(),descripcion.getText(),prySeleccionado, pryPeso, pryCosto, pry2Seleccionado, pry3Seleccionado);
    		}
    		
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);
    		nombreInsumo.setEnabled(false);
    		nombreInsumo.setText("");
    		descripcion.setEnabled(false);
    		btnNuevo.setEnabled(true);
    		comboUnidadDeMedida.setEnabled(true);
    		comboEsLiquido.setEnabled(true);
    		pesoField.setEnabled(false);
    		pesoField.setText("");
    		costoField.setEnabled(false);
    		costoField.setText("");
    		comboEsRefrigerado.setEnabled(true);
    	
    		
    		
    		//btnCheckBox.setEnabled(true);
    	});
    	
    	this.btnNuevo.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		nombreInsumo.setEnabled(true);
    		nombreInsumo.setText("");
    		descripcion.setEnabled(true);
    		btnNuevo.setEnabled(false);
    		comboUnidadDeMedida.setEnabled(true);
    		comboEsLiquido.setEnabled(true);
    		pesoField.setEnabled(true);
    		pesoField.setText("");
    		costoField.setEnabled(true);
    		costoField.setText("");
    		comboEsRefrigerado.setEnabled(true);
    		
    		idInsumoSeleccionado=-1;
    		//btnCheckBox.setEnabled(true);
    	});
    	
    	this.btnCancelar.addActionListener( e -> {
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);    		
    		btnNuevo.setEnabled(true);
    		comboUnidadDeMedida.setEnabled(false);
    		nombreInsumo.setEnabled(false);
    		nombreInsumo.setText("");
    		descripcion.setEnabled(false);
    		descripcion.setText("");
    		comboEsLiquido.setEnabled(false);
    		pesoField.setEnabled(false);
    		pesoField.setText("");
    		costoField.setEnabled(false);
    		costoField.setText("");
    		comboEsRefrigerado.setEnabled(false);
    		
    		idInsumoSeleccionado=-1;
    		
    	});
    	
    	this.btnBuscar.addActionListener( e -> {
    		System.out.println("BUSCAR....");
    		controller.actualizarTablaInsumo();
    	});
    	
    	this.btnEditar.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnNuevo.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		comboUnidadDeMedida.setEnabled(true);
    		comboEsLiquido.setEnabled(true);
    		nombreInsumo.setEnabled(true);
    		descripcion.setEnabled(true);
    		pesoField.setEnabled(true);
    		costoField.setEnabled(true);
    		comboEsRefrigerado.setEnabled(true);
    		
    	});
    	
    	this.btnBorrar.addActionListener( e -> {
    		int resultado = JOptionPane.showConfirmDialog(null, "Borrar insumo", "Desea borrar el insumo "+nombreInsumo.getText()+ "?", JOptionPane.YES_NO_OPTION);
    		if(resultado ==0) {
    			controller.borrarInsumo(idInsumoSeleccionado);
	    		btnBorrar.setEnabled(false);
    		}
    	});
    
    	this.tablaPlantas.getSelectionModel().addListSelectionListener(lse -> {
    		if(gtm.getDatos()!=null && !gtm.getDatos().isEmpty() && lse.getFirstIndex()< gtm.getDatos().size()) {
	    		Insumo aux = gtm.datos.get(lse.getFirstIndex());
	    		idInsumoSeleccionado = aux.getId();
	    		nombreInsumo.setText(aux.getNombre());
	    		descripcion.setText(aux.getDescripcion());
	    		comboUnidadDeMedida.setSelectedItem(aux.getUnidadDeMedida());
	    		comboEsLiquido.setSelectedItem(aux.getEsLiquido());
	    		pesoField.setValue(new Double(aux.getPeso()));
	    		costoField.setValue(new Double(aux.getCosto()));
	    		comboEsRefrigerado.setSelectedItem(aux.getEsRefrigerado());
	    		btnEditar.setEnabled(true);
	    		btnBorrar.setEnabled(true);
	    		//System.out.println(aux.getNombre());
    		}
        });
    }
    
    
    private GenericTableModel<Insumo> crearModeloTabla(){
    	this.gtm = new GenericTableModel<Insumo>();
    	List<GenericTableColumn> lista = new ArrayList<GenericTableColumn>();
    	lista.add(new GenericTableColumn("idinsumo" , "getId"));
    	lista.add(new GenericTableColumn("Nombre" , "getNombre"));
    	lista.add(new GenericTableColumn("Descripcion" , "getDescripcion"));
    	lista.add(new GenericTableColumn("Unidad de Medida" , "getUnidadDeMedida"));
    	lista.add(new GenericTableColumn("Peso" , "getPeso"));
    	lista.add(new GenericTableColumn("Costo" , "getCosto"));
    	lista.add(new GenericTableColumn("Refrigerado" , "getEsRefrigerado"));
    	lista.add(new GenericTableColumn("Liquido" , "getEsLiquido"));
    	gtm.setColumnas(lista);
    	
    	return gtm;
    }
    
    
    /**
     * Metodo invocado por el controller para que actualice los datos de la JTable
     * @param listaPry
     */
    public void actualizarDatosTabla(List<Insumo> listaPry) {
    	this.gtm.setDatos(listaPry);
    	this.gtm.fireTableDataChanged();
    }
    
}
