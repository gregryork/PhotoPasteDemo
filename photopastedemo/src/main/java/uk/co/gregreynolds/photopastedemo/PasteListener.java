package uk.co.gregreynolds.photopastedemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.TransferHandler;

public class PasteListener implements ActionListener
{
  private JComponent component;    

  public PasteListener(JComponent component)
  {
    super();
    this.component = component;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    TransferHandler th = component.getTransferHandler();
    Action action = th.getPasteAction();
    action.actionPerformed(new ActionEvent(component,e.getID(),e.getActionCommand()));   
  }

}


