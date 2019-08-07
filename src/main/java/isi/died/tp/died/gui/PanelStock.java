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
import isi.died.tp.died.modelo.Stock;
import isi.died.tp.died.modelo.Planta;

public class PanelStock extends JPanel {

	private JLabel lblTituloPanel;
	private JLabel lblPlanta;
	private JComboBox<Planta> comboPlanta;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JButton btnEditar;
	private JButton btnBuscar;
	private JButton btnBorrar;
	private JTable tablaStocks;
	
	
	GenericTableModel<Stock> gtm ;
	
	private Integer idStockSeleccionado;
	
	private PanelStockController controller;

	public PanelStock() {
		super();
		controller = new PanelStockController(this);
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

    	this.lblTituloPanel = new JLabel("Crear nuevo stock");
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
    	
    	this.lblPlanta= new JLabel("Stock de planta: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblPlanta,c);
    	
    	this.comboPlanta= new JComboBox<>();
    	this.controller.cargarComboPlantas(this.comboPlanta);
    	this.comboPlanta.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(comboPlanta,c);

   	
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
    	this.tablaStocks = new JTable(this.gtm);
    	this.tablaStocks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	JScrollPane scrollPane= new JScrollPane(this.tablaStocks);
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
    		
    		Planta pry1Seleccionado = (Planta) comboPlanta.getSelectedItem();    		
   		    		
    		if(idStockSeleccionado<=0) {
    			controller.crearStock(pry1Seleccionado);
    		}
    		else {
    			controller.actualizarStock(idStockSeleccionado,pry1Seleccionado);
    		}
    		
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);
    		btnNuevo.setEnabled(true);
    		comboPlanta.setEnabled(true);
    	
    	});
    	
    	this.btnNuevo.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		btnNuevo.setEnabled(false);
    		comboPlanta.setEnabled(true);
    		
    		idStockSeleccionado=-1;
    		//btnCheckBox.setEnabled(true);
    	});
    	
    	this.btnCancelar.addActionListener( e -> {
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);    		
    		btnNuevo.setEnabled(true);
    		comboPlanta.setEnabled(false);
    		
    		idStockSeleccionado=-1;
    		
    	});
    	
    	this.btnBuscar.addActionListener( e -> {
    		System.out.println("BUSCAR....");
    		controller.actualizarTablaStock();
    		
    	});
    	
    	this.btnEditar.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnNuevo.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		comboPlanta.setEnabled(true);
    		
    	});
    	
    	this.btnBorrar.addActionListener( e -> {
    		int resultado = JOptionPane.showConfirmDialog(null, "Borrar stock", "Desea borrar el stock ?", JOptionPane.YES_NO_OPTION);
    		if(resultado ==0) {
    			controller.borrarStock(idStockSeleccionado);
	    		btnBorrar.setEnabled(false);
    		}
    	});
    
    	this.tablaStocks.getSelectionModel().addListSelectionListener(lse -> {
    		if(gtm.getDatos()!=null && !gtm.getDatos().isEmpty() && lse.getFirstIndex()< gtm.getDatos().size()) {
	    		Stock aux = gtm.datos.get(lse.getFirstIndex());
	    		idStockSeleccionado = aux.getId();
	    		comboPlanta.setSelectedItem(aux.getPlanta());
	    		btnEditar.setEnabled(true);
	    		btnBorrar.setEnabled(true);
    		}
        });
    }
    
    
    private GenericTableModel<Stock> crearModeloTabla(){
    	this.gtm = new GenericTableModel<Stock>();
    	List<GenericTableColumn> lista = new ArrayList<GenericTableColumn>();
    	lista.add(new GenericTableColumn("idstock" , "getId"));
    	lista.add(new GenericTableColumn("Planta" , "getPlanta"));
    	gtm.setColumnas(lista);
    	
    	return gtm;
    }
    
    
    /**
     * Metodo invocado por el controller para que actualice los datos de la JTable
     * @param listaPry
     */
    public void actualizarDatosTabla(List<Stock> listaPry) {
    	this.gtm.setDatos(listaPry);
    	this.gtm.fireTableDataChanged();
    }
    
}
