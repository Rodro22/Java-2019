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
import isi.died.tp.died.modelo.Camino;
import isi.died.tp.died.modelo.Planta;
import isi.died.tp.died.modelo.Camion;

public class PanelCamino extends JPanel {

	private JLabel lblPlantaInicial;
	private JLabel lblPlantaFinal;
	private JLabel lblCamion;
	private JLabel lblDistancia;
	private JLabel lblDuracion;
	private JLabel lblPesoSoportado;
	private JLabel lblTituloPanel;
	private JComboBox<Planta> comboPlantaInicial;
	private JComboBox<Planta> comboPlantaFinal;
	private JComboBox<Camion> comboCamion;
	private JFormattedTextField distanciaField;
	private JFormattedTextField duracionField;
	private JFormattedTextField peso_soportadoField;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JButton btnEditar;
	private JButton btnBuscar;
	private JButton btnBorrar;
	private JTable tablaCaminos;
	
	
	GenericTableModel<Camino> gtm ;
	
	private Integer idCaminoSeleccionado;
	private NumberFormat distanciaFormat;
	private NumberFormat duracionFormat;
	private NumberFormat peso_soportadoFormat;
	
	private PanelCaminoController controller;

	public PanelCamino() {
		super();
		controller = new PanelCaminoController(this);
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

    	this.lblTituloPanel = new JLabel("Gestion de caminos");
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
    	
    	this.lblPlantaInicial= new JLabel("Planta inicial: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblPlantaInicial,c);
    	
    	this.comboPlantaInicial = new JComboBox<>();
    	this.controller.cargarComboPlantas(this.comboPlantaInicial);
    	this.comboPlantaInicial.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(comboPlantaInicial,c);
    	
    	
    	this.lblPlantaFinal= new JLabel("Planta final: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblPlantaFinal,c);
    	
    	this.comboPlantaFinal = new JComboBox<>();
    	this.controller.cargarComboPlantas(this.comboPlantaFinal);
    	this.comboPlantaFinal.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(comboPlantaFinal,c);
    	
    	this.lblCamion= new JLabel("Camion: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblCamion,c);
    	
    	this.comboCamion = new JComboBox<>();
    	this.controller.cargarComboCamiones(this.comboCamion);
    	this.comboCamion.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(comboCamion,c);
    	  	    	    	
    	col =0;
    	fila++;
    	
    	this.lblDistancia = new JLabel("Distancia: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblDistancia,c);
    	   	
    	this.distanciaField = new JFormattedTextField(distanciaFormat);
    	this.distanciaField.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(distanciaField,c);
    	
    	this.lblDuracion = new JLabel("Duracion: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblDuracion,c);
    	   	
    	this.duracionField = new JFormattedTextField(duracionFormat);
    	this.duracionField.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(duracionField,c);
    	  	
    	this.lblPesoSoportado= new JLabel("Peso soportado: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblPesoSoportado,c);
    	   	
    	this.peso_soportadoField = new JFormattedTextField(peso_soportadoFormat);
    	this.peso_soportadoField.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(peso_soportadoField,c);
    	
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
    	this.tablaCaminos = new JTable(this.gtm);
    	this.tablaCaminos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	JScrollPane scrollPane= new JScrollPane(this.tablaCaminos);
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
    		
    		Planta pry1Seleccionado = (Planta) comboPlantaInicial.getSelectedItem();
    		Planta pry2Seleccionado = (Planta) comboPlantaFinal.getSelectedItem();
    		Camion pry3Seleccionado = (Camion) comboCamion.getSelectedItem();
    		Double pryDistancia = ((Number)distanciaField.getValue()).doubleValue();
    		Double pryDuracion = ((Number)duracionField.getValue()).doubleValue();
    		Double pryPesoSoportado = ((Number)peso_soportadoField.getValue()).doubleValue();
    		
   		    		
    		if(idCaminoSeleccionado<=0) {
    			controller.crearCamino(pry1Seleccionado,pry2Seleccionado,pry3Seleccionado, pryDistancia, pryDuracion, pryPesoSoportado);
    		}
    		else {
    			controller.actualizarCamino(idCaminoSeleccionado,pry1Seleccionado,pry2Seleccionado,pry3Seleccionado, pryDistancia, pryDuracion, pryPesoSoportado);
    		}
    		
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);
    		btnNuevo.setEnabled(true);
    		comboPlantaInicial.setEnabled(true);
    		comboPlantaFinal.setEnabled(true);
    		comboCamion.setEnabled(true);
    		distanciaField.setEnabled(false);
    		distanciaField.setText("");
    		duracionField.setEnabled(false);
    		duracionField.setText("");
    		peso_soportadoField.setEnabled(false);
    		peso_soportadoField.setText("");
    	
    	});
    	
    	this.btnNuevo.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		btnNuevo.setEnabled(false);
    		comboPlantaInicial.setEnabled(true);
    		comboPlantaFinal.setEnabled(true);
    		comboCamion.setEnabled(true);
    		distanciaField.setEnabled(true);
    		distanciaField.setText("");
    		duracionField.setEnabled(true);
    		duracionField.setText("");
    		peso_soportadoField.setEnabled(true);
    		peso_soportadoField.setText("");
    		
    		idCaminoSeleccionado=-1;
    		//btnCheckBox.setEnabled(true);
    	});
    	
    	this.btnCancelar.addActionListener( e -> {
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);    		
    		btnNuevo.setEnabled(true);
    		comboPlantaInicial.setEnabled(false);
    		comboPlantaFinal.setEnabled(false);
    		comboCamion.setEnabled(false);
    		distanciaField.setEnabled(false);
    		distanciaField.setText("");
    		duracionField.setEnabled(false);
    		duracionField.setText("");
    		peso_soportadoField.setEnabled(false);
    		peso_soportadoField.setText("");
    		
    		idCaminoSeleccionado=-1;
    		
    	});
    	
    	this.btnBuscar.addActionListener( e -> {
    		System.out.println("BUSCAR....");
    		controller.actualizarTablaCamino();
    		
    	});
    	
    	this.btnEditar.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnNuevo.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		comboPlantaInicial.setEnabled(true);
    		comboPlantaFinal.setEnabled(true);
    		comboCamion.setEnabled(true);
    		distanciaField.setEnabled(true);
    		duracionField.setEnabled(true);
    		peso_soportadoField.setEnabled(true);
    		
    	});
    	
    	this.btnBorrar.addActionListener( e -> {
    		int resultado = JOptionPane.showConfirmDialog(null, "Borrar camino", "Desea borrar el camino ?", JOptionPane.YES_NO_OPTION);
    		if(resultado ==0) {
    			controller.borrarCamino(idCaminoSeleccionado);
	    		btnBorrar.setEnabled(false);
    		}
    	});
    
    	this.tablaCaminos.getSelectionModel().addListSelectionListener(lse -> {
    		if(gtm.getDatos()!=null && !gtm.getDatos().isEmpty() && lse.getFirstIndex()< gtm.getDatos().size()) {
	    		Camino aux = gtm.datos.get(lse.getFirstIndex());
	    		idCaminoSeleccionado = aux.getId();
	    		comboPlantaInicial.setSelectedItem(aux.getPlantaInit());
	    		comboPlantaFinal.setSelectedItem(aux.getPlantaEnd());
	    		comboCamion.setSelectedItem(aux.getCamion());
	    		distanciaField.setValue(new Double(aux.getDistancia()));
	    		duracionField.setValue(new Double(aux.getDuracion()));
	    		peso_soportadoField.setValue(new Double(aux.getPesoSoportado()));
	    		btnEditar.setEnabled(true);
	    		btnBorrar.setEnabled(true);
    		}
        });
    }
    
    
    private GenericTableModel<Camino> crearModeloTabla(){
    	this.gtm = new GenericTableModel<Camino>();
    	List<GenericTableColumn> lista = new ArrayList<GenericTableColumn>();
    	lista.add(new GenericTableColumn("idcamino" , "getId"));
    	lista.add(new GenericTableColumn("Planta Inicial" , "getPlantaInit"));
    	lista.add(new GenericTableColumn("Planta Final" , "getPlantaEnd"));
    	lista.add(new GenericTableColumn("Camion" , "getCamion"));
    	lista.add(new GenericTableColumn("Distancia (km)" , "getDistancia"));
    	lista.add(new GenericTableColumn("Duracion" , "getDuracion"));
    	lista.add(new GenericTableColumn("Peso soportado" , "getPesoSoportado"));
    	gtm.setColumnas(lista);
    	
    	return gtm;
    }
    
    
    /**
     * Metodo invocado por el controller para que actualice los datos de la JTable
     * @param listaPry
     */
    public void actualizarDatosTabla(List<Camino> listaPry) {
    	this.gtm.setDatos(listaPry);
    	this.gtm.fireTableDataChanged();
    }
    
}
