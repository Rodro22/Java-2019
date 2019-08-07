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
import isi.died.tp.died.modelo.CargaStock;
import isi.died.tp.died.modelo.Stock;
import isi.died.tp.died.modelo.Insumo;

public class PanelCargaStock extends JPanel {

	private JLabel lblStock;
	private JLabel lblInsumo;
	private JLabel lblCantidadRequerida;
	private JLabel lblStockDisponible;
	private JLabel lblTituloPanel;
	private JComboBox<Stock> comboStock;
	private JComboBox<Insumo> comboInsumo;
	private JFormattedTextField cantidadRequeridaField;
	private JFormattedTextField stockDisponibleField;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JButton btnEditar;
	private JButton btnBuscar;
	private JButton btnBorrar;
	private JTable tablaCargaStocks;
	
	
	GenericTableModel<CargaStock> gtm ;
	
	private Integer idCargaStockSeleccionado;
	private NumberFormat cantidadRequeridaFormat;
	private NumberFormat stockDisponibleFormat;
	
	private PanelCargaStockController controller;

	public PanelCargaStock() {
		super();
		controller = new PanelCargaStockController(this);
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

    	this.lblTituloPanel = new JLabel("Carga de stock");
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
    	
    	this.lblStock= new JLabel("Stock: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblStock,c);
    	
    	this.comboStock = new JComboBox<>();
    	this.controller.cargarComboStocks(this.comboStock);
    	this.comboStock.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(comboStock,c);
    	
    	
    	this.lblInsumo= new JLabel("Insumo: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblInsumo,c);
    	
    	this.comboInsumo = new JComboBox<>();
    	this.controller.cargarComboInsumos(this.comboInsumo);
    	this.comboInsumo.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(comboInsumo,c);
    	    	  	    	    	
    	col =0;
    	fila++;
    	
    	this.lblCantidadRequerida = new JLabel("Cantidad requerida: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblCantidadRequerida,c);
    	   	
    	this.cantidadRequeridaField = new JFormattedTextField(cantidadRequeridaFormat);
    	this.cantidadRequeridaField.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(cantidadRequeridaField,c);
    	
    	this.lblStockDisponible = new JLabel("Stock disponible: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblStockDisponible,c);
    	   	
    	this.stockDisponibleField = new JFormattedTextField(stockDisponibleFormat);
    	this.stockDisponibleField.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(stockDisponibleField,c);
    	
    	  	   	
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
    	this.tablaCargaStocks = new JTable(this.gtm);
    	this.tablaCargaStocks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	JScrollPane scrollPane= new JScrollPane(this.tablaCargaStocks);
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
    		
    		Stock pry1Seleccionado = (Stock) comboStock.getSelectedItem();
    		Insumo pry2Seleccionado = (Insumo) comboInsumo.getSelectedItem();
    		Double pryCantidadRequerida = ((Number)cantidadRequeridaField.getValue()).doubleValue();
    		Double pryStockDisponible = ((Number)stockDisponibleField.getValue()).doubleValue();
    		
   		    		
    		if(idCargaStockSeleccionado<=0) {
    			controller.crearCargaStock(pry2Seleccionado,pry1Seleccionado,pryCantidadRequerida, pryStockDisponible);
    		}
    		else {
    			controller.actualizarCargaStock(idCargaStockSeleccionado,pry2Seleccionado,pry1Seleccionado,pryCantidadRequerida, pryStockDisponible);
    		}
    		
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);
    		btnNuevo.setEnabled(true);
    		comboStock.setEnabled(true);
    		comboInsumo.setEnabled(true);
    		cantidadRequeridaField.setEnabled(false);
    		cantidadRequeridaField.setText("");
    		stockDisponibleField.setEnabled(false);
    		stockDisponibleField.setText("");
    	
    	});
    	
    	this.btnNuevo.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		btnNuevo.setEnabled(false);
    		comboStock.setEnabled(true);
    		comboInsumo.setEnabled(true);
    		cantidadRequeridaField.setEnabled(true);
    		cantidadRequeridaField.setText("");
    		stockDisponibleField.setEnabled(true);
    		stockDisponibleField.setText("");
    		
    		idCargaStockSeleccionado=-1;
    		//btnCheckBox.setEnabled(true);
    	});
    	
    	this.btnCancelar.addActionListener( e -> {
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);    		
    		btnNuevo.setEnabled(true);
    		comboStock.setEnabled(false);
    		comboInsumo.setEnabled(false);
    		cantidadRequeridaField.setEnabled(false);
    		cantidadRequeridaField.setText("");
    		stockDisponibleField.setEnabled(false);
    		stockDisponibleField.setText("");
    		
    		idCargaStockSeleccionado=-1;
    		
    	});
    	
    	this.btnBuscar.addActionListener( e -> {
    		System.out.println("BUSCAR....");
    		controller.actualizarTablaCargaStock();
    		
    	});
    	
    	this.btnEditar.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnNuevo.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		comboStock.setEnabled(true);
    		comboInsumo.setEnabled(true);
    		cantidadRequeridaField.setEnabled(true);
    		stockDisponibleField.setEnabled(true);
    		
    	});
    	
    	this.btnBorrar.addActionListener( e -> {
    		int resultado = JOptionPane.showConfirmDialog(null, "Quitar insumo", "Desea quitar el insumo del stock?", JOptionPane.YES_NO_OPTION);
    		if(resultado ==0) {
    			controller.borrarCargaStock(idCargaStockSeleccionado);
	    		btnBorrar.setEnabled(false);
    		}
    	});
    
    	this.tablaCargaStocks.getSelectionModel().addListSelectionListener(lse -> {
    		if(gtm.getDatos()!=null && !gtm.getDatos().isEmpty() && lse.getFirstIndex()< gtm.getDatos().size()) {
	    		CargaStock aux = gtm.datos.get(lse.getFirstIndex());
	    		idCargaStockSeleccionado = aux.getId();
	    		comboStock.setSelectedItem(aux.getStock());
	    		comboInsumo.setSelectedItem(aux.getInsumo());
	    		cantidadRequeridaField.setValue(new Double(aux.getCantidadRequerida()));
	    		stockDisponibleField.setValue(new Double(aux.getStockDisponible()));
	    		btnEditar.setEnabled(true);
	    		btnBorrar.setEnabled(true);
    		}
        });
    }
    
    
    private GenericTableModel<CargaStock> crearModeloTabla(){
    	this.gtm = new GenericTableModel<CargaStock>();
    	List<GenericTableColumn> lista = new ArrayList<GenericTableColumn>();
    	lista.add(new GenericTableColumn("idcargastock" , "getId"));
    	lista.add(new GenericTableColumn("Stock" , "getStock"));
    	lista.add(new GenericTableColumn("Insumo" , "getInsumo"));
    	lista.add(new GenericTableColumn("Cantidad requerida", "getCantidadRequerida"));
    	lista.add(new GenericTableColumn("Stock disponible" , "getStockDisponible"));
    	gtm.setColumnas(lista);
    	
    	return gtm;
    }
    
    
    /**
     * Metodo invocado por el controller para que actualice los datos de la JTable
     * @param listaPry
     */
    public void actualizarDatosTabla(List<CargaStock> listaPry) {
    	this.gtm.setDatos(listaPry);
    	this.gtm.fireTableDataChanged();
    }
    
}
