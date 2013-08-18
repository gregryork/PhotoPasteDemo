package uk.co.gregreynolds.photopastedemo;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ImageLabelObserver implements Observer
{

  private JLabel targetLabel;

  public ImageLabelObserver(JLabel targetLabel)
  {
    this.targetLabel = targetLabel;
  }

  @Override
  public void update(Observable o,
      Object arg)
  {
    Image image = null;
    if (arg instanceof File)
    {
      File file = (File)arg;
      try
      {
        image = ImageIO.read(file);
      }
      catch (IOException e)
      {
        JOptionPane.showMessageDialog(targetLabel, 
            "Could not import photo.");
      }
    }
    if (arg instanceof Image)
    {
      image = (Image)arg;        
    }

    if (image != null)
    {
      ImageIcon icon = new ImageIcon(getPhotoScaledToHeight(image, targetLabel.getHeight()));
      targetLabel.setIcon(icon);
    }
  }

  private Image getPhotoScaledToHeight(Image source, int height)
  {
    if (source == null)
    {
      return source;
    }
    double aspectRatio = (double)source.getWidth(null)
        / (double)source.getHeight(null);
    int width = (int)(aspectRatio * height);

    Image resizedImage =
        source.getScaledInstance(width, height,Image.SCALE_DEFAULT);
    return resizedImage;
  }
}
