package uk.co.gregreynolds.photopastedemo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;

public class PhotoPasteDemo extends JFrame
{

  public static void main(String[] args)
  {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try
        {
          createAndShowGUI();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });

  }

  protected static void createAndShowGUI()
  {
    final JFrame frame = new JFrame("Photo Paste Demo");
    JLabel demoLabel = new JLabel("Drag or paste an image file or image from the clipboard");
    JLabel photoLabel = new JLabel();
    JPanel photoPanel = new JPanel(new BorderLayout());
    JButton pasteButton = new JButton("Paste Photo");
    
    photoPanel.add(demoLabel,BorderLayout.PAGE_START);
    photoPanel.add(photoLabel,BorderLayout.CENTER); 
    photoPanel.add(pasteButton,BorderLayout.PAGE_END);
    
    PhotoTransferHandler photoHandler = new PhotoTransferHandler();
    frame.setTransferHandler(photoHandler);
    photoPanel.setTransferHandler(photoHandler);
    JMenuBar menu = new JMenuBar();
    frame.setJMenuBar(menu);

    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");

    fileMenu.setMnemonic(KeyEvent.VK_F);
    editMenu.setMnemonic(KeyEvent.VK_E);
    menu.add(fileMenu);
    menu.add(editMenu);

    JMenuItem eMenuItem = new JMenuItem("Exit");
    eMenuItem.setMnemonic(KeyEvent.VK_E);
    eMenuItem.setToolTipText("Exit application");
    eMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        System.exit(0);
      }
    });
    eMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
    fileMenu.add(eMenuItem);


    PasteListener pasteListener = new PasteListener(photoPanel);
    JMenuItem pMenuItem = new JMenuItem("Paste Photo");
    pMenuItem.setMnemonic(KeyEvent.VK_P);    
    pMenuItem.setToolTipText("Paste");
    pMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
    pMenuItem.addActionListener(pasteListener);
    pMenuItem.setTransferHandler(photoHandler);
    editMenu.add(pMenuItem);
    
    pasteButton.addActionListener(pasteListener);
    photoHandler.addTransferObserver(new ImageLabelObserver(photoLabel));
        
    frame.add(photoPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(600,400));
    frame.pack();
    frame.setVisible(true);
  }  
}
