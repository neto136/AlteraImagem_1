/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alteraimagem;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
/**
 *
 * @author Neto
 */
public class Imagem {
    private BufferedImage image, image2, imageOriginal;
    
    public Imagem(String arquivo)throws IOException{
        //Faz a leitura do arquivo de imagem
        try { image = ImageIO.read(new File(arquivo)); }
        catch (IOException e) {
            throw new RuntimeException("Não foi possível abrir o arquivo: " + arquivo);
        }
        if (image == null)
            throw new RuntimeException("Arquivo de imagem inválido: " + arquivo);
        
        //Salva imagem original para comparação do resultado dos friltros aplicados
        if (image != null)
            imageOriginal = image;
    }
    
    public void Imagem2 (String arquivo) throws IOException{
        //Faz a leitura do arquivo de imagem
        try { image2 = ImageIO.read(new File(arquivo)); }
        catch (IOException e) {
            throw new RuntimeException("Não foi possível abrir o arquivo: " + arquivo);
        }
        if (image2 == null)
            throw new RuntimeException("Arquivo de imagem inválido: " + arquivo);
    }
    
}
