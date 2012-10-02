/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alteraimagem;
import java.awt.Dimension;  
import java.awt.Graphics;  
import java.io.File;  
import javax.swing.ImageIcon;  
import javax.swing.JPanel; 
/**
 *
 * @author Neto
 */
public class PainelGrafico extends JPanel{
    private ImageIcon imageIcon;  
      
    public PainelGrafico(){  
        imageIcon = new ImageIcon("c:\\imagens\\basica.JPG");  
    }  
    public PainelGrafico(String caminho){  
        imageIcon = new ImageIcon(caminho);  
    }  
  
    public ImageIcon getImageIcon() {  
        return imageIcon;  
    }  
  
    public void setImageIcon(File arquivo) {  
          
        ImageIcon temp = new ImageIcon(arquivo.getPath());  
        this.imageIcon = temp;  
    }  
    public void paintComponent (Graphics g)  
    {  
        super.paintComponent(g);  
        imageIcon.paintIcon(this,g, 0, 0);  
    }  
    public Dimension getPreferredSize(){  
        return new Dimension( imageIcon.getIconWidth(),imageIcon.getIconHeight());  
    } 
}
