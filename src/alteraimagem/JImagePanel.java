/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alteraimagem;
import java.awt.Color;
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.Paint;  
import java.awt.TexturePaint;  
import java.awt.geom.Rectangle2D;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import javax.imageio.ImageIO;  
import javax.swing.JPanel;  
/**
 *
 * @author Neto
 */
public class JImagePanel extends JPanel{
    private BufferedImage image = null;  
    private FillType fillType = FillType.ASPECT_RATIO_CENTER;  
  
    public JImagePanel() {  
        image = null;  
    }     
    
    public JImagePanel(BufferedImage img) {  
        setImage(img);  
    }  
      
    public JImagePanel(File imgSrc) throws IOException {  
        this(ImageIO.read(imgSrc));  
    }  
  
    public JImagePanel(String fileName) throws IOException {  
        this(new File(fileName));  
    }  
  
    public final void setImage(BufferedImage img) {  
        if (img == null)  
            throw new NullPointerException("Sem imagem para processar!");  
        this.image = img;  
        invalidate();  
    }  
  
    public void setImage(File img) throws IOException {  
        setImage(ImageIO.read(img));  
    }  
  
    public void setImage(String fileName) throws IOException {  
        setImage(new File(fileName));  
    }  
  
    public BufferedImage getImage() {  
        return image;  
    }  
  
    @Override
    protected void paintComponent(Graphics g) { 
        g.setColor(Color.yellow);
        super.paintComponent(g);  
        Graphics2D g2d = (Graphics2D) g.create();  
        fillType.drawImage(this, g2d, image);          
        g2d.dispose();  
    }  
  
    public FillType getFillType() {  
        return fillType;  
    }  
  
    public void setFillType(FillType fillType) {  
        if (fillType == null)  
            throw new IllegalArgumentException("Invalid fill type!");  
        this.fillType = fillType;  
        invalidate();  
    }  
  
    public static enum FillType {  
        RESIZE {  
            public void drawImage(JPanel panel, Graphics2D g2d, BufferedImage image) {  
                g2d.drawImage(image, 0, 0, panel.getWidth(), panel.getHeight(),  
                        null);  
            }  
        },  
          
        CENTER {  
            public void drawImage(JPanel panel, Graphics2D g2d, BufferedImage image) {  
                int left = (panel.getHeight() - image.getHeight()) / 2;  
                int top = (panel.getWidth() - image.getWidth()) / 2;  
                g2d.drawImage(image, top, left, null);  
            }  
        },  
  
        SIDE_BY_SIDE {  
            public void drawImage(JPanel panel, Graphics2D g2d, BufferedImage image) {  
                Paint p = new TexturePaint(image, new Rectangle2D.Float(0, 0, image.getWidth(), image.getHeight()));  
                g2d.setPaint(p);  
                g2d.fillRect(0, 0, panel.getWidth(), panel.getHeight());  
            }  
        },  
        
        ASPECT_RATIO {  
        public void drawImage(JPanel panel, Graphics2D g2d, BufferedImage image) {  
            int larguraImg = image.getWidth(), alturaImg = image.getHeight();  
            int largura = 0, altura = 0;  
            double aspectoImg = larguraImg / alturaImg;  
            double aspectoPanel = panel.getWidth() / panel.getHeight();  
              
            if (larguraImg > panel.getWidth() || alturaImg > panel.getHeight()) {  
                if (aspectoImg == aspectoPanel) {  
                    largura = panel.getWidth();  
                    altura = panel.getHeight();  
                }  
                if (aspectoImg != aspectoPanel) {  
                    if ((larguraImg / panel.getWidth()) > (alturaImg / panel.getHeight())) {  
                        largura = panel.getWidth();  
                        altura = (largura * alturaImg) / larguraImg;  
                    }  
                    if ((larguraImg / panel.getWidth()) < (alturaImg / panel.getHeight())) {  
                        altura = panel.getHeight();  
                        largura = (altura * larguraImg) / alturaImg;  
                    }  
                }  
            }  
            else {  
                largura = image.getWidth();  
                altura = image.getHeight();  
            }  
            g2d.drawImage(image, 0, 0, largura, altura, null);  
        }  
    },
      
    ASPECT_RATIO_CENTER {  
        public void drawImage(JPanel panel, Graphics2D g2d, BufferedImage image) {  
            int larguraImg = image.getWidth(), alturaImg = image.getHeight();  
            int largura = 0, altura = 0;  
            double aspectoImg = larguraImg / alturaImg;  
            double aspectoPanel = panel.getWidth() / panel.getHeight();  
              
            if (larguraImg > panel.getWidth() || alturaImg > panel.getHeight()) {  
                if (aspectoImg == aspectoPanel) {  
                    largura = panel.getWidth();  
                    altura = panel.getHeight();  
                }  
                if (aspectoImg != aspectoPanel) {  
                    if ((larguraImg / panel.getWidth()) > (alturaImg / panel.getHeight())) {  
                        largura = panel.getWidth();  
                        altura = (largura * alturaImg) / larguraImg;  
                    }  
                    if ((larguraImg / panel.getWidth()) < (alturaImg / panel.getHeight())) {  
                        altura = panel.getHeight();  
                        largura = (altura * larguraImg) / alturaImg;  
                    }  
                }  
            }  
            else {  
                largura = image.getWidth();  
                altura = image.getHeight();  
            }  
              
            // centraliza a imagem no JPanel...  
            int left = (panel.getHeight() - altura) / 2;  
            int top = (panel.getWidth() - largura) / 2;  
              
            g2d.drawImage(image, top, left, largura, altura, null);  
        }  
    };  
        public abstract void drawImage(JPanel panel, Graphics2D g2d, BufferedImage image);  
    }  
}
