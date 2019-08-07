package isi.died.tp.died;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import isi.died.tp.died.gui.PanelProyecto;
import isi.died.tp.died.gui.PanelPlanta;
import isi.died.tp.died.gui.PanelInsumo;
import isi.died.tp.died.gui.PanelCamion;
import isi.died.tp.died.gui.ServiceLocator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	SwingUtilities.invokeLater(new Runnable() {
    	    public void run() {
    	        createAndShowGUI();
    	    }
    	});
    }
    
    private static void createAndShowGUI() {
    	  JFrame f = new JFrame("Sistema de Gestión de Logística");
          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          //JPanel panel = new PanelProyecto();          
          f.setSize(1024, 600);
          f.setMinimumSize(new Dimension(1024, 600));
          JMenuBar mb;
          JMenu menu1;
          JMenuItem mi1,mi2,mi3,mi4,mi5,mi6,mi7,mi8,mi9;
          
          mb=new JMenuBar();
          f.setJMenuBar(mb);
          menu1=new JMenu("Opciones");
          mb.add(menu1);
          mi1=new JMenuItem("Tareas");
          mi1.addActionListener(e -> { 
        	  f.getContentPane().removeAll();
        	  f.getContentPane().add( ServiceLocator.getInstance().getPanelTarea());
        	  f.pack();
          });
          menu1.add(mi1);
          mi2=new JMenuItem("Proyectos");
          mi2.addActionListener(e -> { 
        	  f.getContentPane().removeAll();
        	  f.getContentPane().add( ServiceLocator.getInstance().getPanelProyecto());
        	  f.pack();
          });
          menu1.add(mi2);
          mi3=new JMenuItem("Grafo");
          mi3.addActionListener(e -> { 
        	  f.getContentPane().removeAll();
        	  f.getContentPane().add( ServiceLocator.getInstance().getPanelGrafo());
        	  f.pack();
          });
          menu1.add(mi3);
          mi4=new JMenuItem("Plantas");
          mi4.addActionListener(e -> { 
        	  f.getContentPane().removeAll();
        	  f.getContentPane().add( ServiceLocator.getInstance().getPanelPlanta());
        	  f.pack();
          });
          menu1.add(mi4);
          mi5=new JMenuItem("Insumos");
          mi5.addActionListener(e -> { 
        	  f.getContentPane().removeAll();
        	  f.getContentPane().add( ServiceLocator.getInstance().getPanelInsumo());
        	  f.pack();
          });
          menu1.add(mi5);
          mi6=new JMenuItem("Camiones");
          mi6.addActionListener(e -> { 
        	  f.getContentPane().removeAll();
        	  f.getContentPane().add( ServiceLocator.getInstance().getPanelCamion());
        	  f.pack();
          });
          menu1.add(mi6);
          mi7=new JMenuItem("Caminos");
          mi7.addActionListener(e -> { 
        	  f.getContentPane().removeAll();
        	  f.getContentPane().add( ServiceLocator.getInstance().getPanelCamino());
        	  f.pack();
          });
          menu1.add(mi7);
          mi8=new JMenuItem("Stock");
          mi8.addActionListener(e -> { 
        	  f.getContentPane().removeAll();
        	  f.getContentPane().add( ServiceLocator.getInstance().getPanelStock());
        	  f.pack();
          });
          menu1.add(mi8);
          mi9=new JMenuItem("Cargar stock");
          mi9.addActionListener(e -> { 
        	  f.getContentPane().removeAll();
        	  f.getContentPane().add( ServiceLocator.getInstance().getPanelCargaStock());
        	  f.pack();
          });
          menu1.add(mi9);
          
          f.pack();
          f.setVisible(true);
    }
    
}
