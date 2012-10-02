/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alteraimagem;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Arrays;
import javax.imageio.*;
import javax.swing.*;

/**
 *
 * @author Valentim
 */

public class AlteraIMG extends JPanel{
    public static BufferedImage image, imageBak;
    
    
    public static void main( String[] args ) throws IOException {
        JFrame f = new JFrame("Load Image Sample");
        JFrame p = new JFrame("Load Image");
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        p.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        f.add(new AlteraIMG());
        //Adiciona Panel com imagem original
        JLabel label2=new JLabel(new ImageIcon(imageBak),SwingConstants.LEFT);
        p.add(label2); 
        p.pack();
        p.setSize(imageBak.getWidth(),imageBak.getHeight());
        p.setVisible(true);
        //Adiciona Panel com imagem modificada
        JLabel label=new JLabel(new ImageIcon(image),SwingConstants.RIGHT);
        f.add(label);
        f.setLocation(200,0);
        f.pack();
        f.setSize(image.getWidth(),image.getHeight());
        f.setVisible(true);
    }

    
    public AlteraIMG() {
        int width, height, bands;
        
        String arquivo = JOptionPane.showInputDialog(null, "Digite o nome (com diretório) do arquivo de imagem \nCaso não seja digitado, será aberto o padrão");
        if (arquivo.equals(""))
            arquivo = "Tulips.jpg";

        //Faz a leitura do arquivo de imagem
        try { image = ImageIO.read(new File(arquivo)); }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Não foi possível abrir o arquivo: " + arquivo);
        }
        if (image == null)
            throw new RuntimeException("Arquivo de imagem inválido: " + arquivo);

        //Faz a leitura do arquivo de imagem para Backup
        try { imageBak = ImageIO.read(new File(arquivo)); }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Não foi possível abrir o arquivo: " + arquivo);
        }
        if (imageBak == null)
            throw new RuntimeException("Arquivo de imagem inválido: " + arquivo);
                
        // Obtem a altura, a largura e o número de bandas da imagem
    	height = image.getHeight();
        width = image.getWidth();
        bands = image.getSampleModel().getNumBands();
        
        
        //Código para leitura dos pixels da imagem e armazenamento em uma matriz unidimensional
        WritableRaster inputRaster = image.getRaster();
        int pixel[] = new int[3];       //Armazena apenans um pixel RGB
        double pixelMod[] = new double[3];    //Armazena apenans o pixel RGB modificado

        String scontrol = JOptionPane.showInputDialog(null, "Escolha o que fazer na imagem: \n\n1=Converter RGB-YIQ-RGB \n2=Bandas Individuais e Monocromático \n3=Negativo \n4=Controle de Brilho Aditivo \n5=Controle de Brilho Multiplicativo \n6=Filtro Média/Mediana \n7=Soma de imagens");
        int CONTROLE = Integer.parseInt(scontrol);
        

////////////////////////////////////////////////////////////////////////////////RGB-YIQ
if (CONTROLE == 1){
        //Converte os valores RGB para YIQ
        double[][] pixelAUX = new double[height*width][3];
        int k = 0;
        for(int h=0;h<height;h++)
            for(int w=0;w<width;w++){
                inputRaster.getPixel(w, h, pixel);
                pixelAUX[k][0] = (pixel[0]*0.299) + (pixel[1]*0.587) + (pixel[2]*0.114);    //Y
                pixelAUX[k][1] = (pixel[0]*0.596) - (pixel[1]*0.274) - (pixel[2]*0.322);    //I
                pixelAUX[k][2] = (pixel[0]*0.211) - (pixel[1]*0.523) + (pixel[2]*0.312);    //Q
                k++;
            }
        //Converte os valores YIQ para RGB
        k = 0;
        for(int h=0;h<height;h++)
            for(int w=0;w<width;w++){
                pixelMod[0] = pixelAUX[k][0] + (pixelAUX[k][1]*0.956) + (pixelAUX[k][2]*0.621);     //R
                pixelMod[1] = pixelAUX[k][0] - (pixelAUX[k][1]*0.272) - (pixelAUX[k][2]*0.647);     //G
                pixelMod[2] = pixelAUX[k][0] - (pixelAUX[k][1]*1.106) + (pixelAUX[k][2]*1.703);     //B
                k++;
                for(int band=0;band<bands;band++){
                    if (pixelMod[band] > 255)
                        pixelMod[band] = 255;
                    if (pixelMod[band] < 0)
                        pixelMod[band] = 0;
                }
                inputRaster.setPixel(w, h, pixelMod);
            }
}        
////////////////////////////////////////////////////////////////////////////////RGB-YIQ

        
////////////////////////////////////////////////////////////////////////////////BANDA INDIVIDUAL
if (CONTROLE == 2){
        String sop = JOptionPane.showInputDialog(null, "Digite uma opção de banda: \n0=R \n1=G \n2=B \n3=Monocromática em R \n4=Monocromática em G \n5=Monocromática em B");
        int op = Integer.parseInt(sop);
        //Atera o valor de cada componente de cada pixel deixando em zero as bandas que não forem escolhidas ou deixando o valor de 2 igual ao dabanda escolhida para mono
        for(int h=0;h<height;h++)
            for(int w=0;w<width;w++){
                inputRaster.getPixel(w, h, pixel);
                
                switch (op){
                    case 1: pixelMod[0] = 0;
                            break;
                    case 2: pixelMod[0] = 0;
                            break;
                    case 4: pixelMod[0] = pixel[1];
                            break;
                    case 5: pixelMod[0] = pixel[2];
                            break;
                    default: pixelMod[0] = pixel[0];
                    }
                
                switch (op){
                    case 0: pixelMod[1] = 0;
                            break;
                    case 2: pixelMod[1] = 0;
                            break;
                    case 3: pixelMod[1] = pixel[0];
                            break;
                    case 5: pixelMod[1] = pixel[2];
                            break;
                    default: pixelMod[1] = pixel[1];
                    }
                
                switch (op){
                    case 0: pixelMod[2] = 0;
                            break;
                    case 1: pixelMod[2] = 0;
                            break;
                    case 3: pixelMod[2] = pixel[0];
                            break;
                    case 4: pixelMod[2] = pixel[1];
                            break;
                    default: pixelMod[2] = pixel[2];
                    }
                
                inputRaster.setPixel(w, h, pixelMod);
            }
}        
////////////////////////////////////////////////////////////////////////////////BANDA INDIVIDUAL



////////////////////////////////////////////////////////////////////////////////NEGATIVO
if (CONTROLE == 3){
        WritableRaster inputRaster2 = imageBak.getRaster();
        double[][] pixelAUX = new double[height*width][3];
        int k = 0;

        //Atera o valor de cada componente RGB de cada pixel para fazer o negativo da imagem (255 - valor)
        for(int h=0;h<height;h++)
            for(int w=0;w<width;w++){
                inputRaster.getPixel(w, h, pixel);
                for(int band=0;band<bands;band++)
                    pixelMod[band] = 255 - pixel[band];
                inputRaster.setPixel(w, h, pixelMod);
                
                //Aproveita o laço para já salvar os valores convertidos para YIQ
                pixelAUX[k][0] = (pixel[0]*0.299) + (pixel[1]*0.587) + (pixel[2]*0.114);    //Y
                pixelAUX[k][1] = (pixel[0]*0.596) - (pixel[1]*0.274) - (pixel[2]*0.322);    //I
                pixelAUX[k][2] = (pixel[0]*0.211) - (pixel[1]*0.523) + (pixel[2]*0.312);    //Q
                k++;
            }
 
        //Converte os valores YIQ para RGB e salva no arquivo de backup da imagem (deixa Y em negativo)
        k = 0;
        for(int h=0;h<height;h++)
            for(int w=0;w<width;w++){
                pixelMod[0] = (255 - pixelAUX[k][0]) + (pixelAUX[k][1]*0.956) + (pixelAUX[k][2]*0.621);     //R
                pixelMod[1] = (255 - pixelAUX[k][0]) - (pixelAUX[k][1]*0.272) - (pixelAUX[k][2]*0.647);     //G
                pixelMod[2] = (255 - pixelAUX[k][0]) - (pixelAUX[k][1]*1.106) + (pixelAUX[k][2]*1.703);     //B
                k++;
                for(int band=0;band<bands;band++){
                    if (pixelMod[band] > 255)
                        pixelMod[band] = 255;
                    if (pixelMod[band] < 0)
                        pixelMod[band] = 0;
                }
                inputRaster2.setPixel(w, h, pixelMod);
            }
}        
////////////////////////////////////////////////////////////////////////////////NEGATIVO

        
////////////////////////////////////////////////////////////////////////////////BRILHO ADITIVO
if (CONTROLE == 4){
        WritableRaster inputRaster2 = imageBak.getRaster();
        double[][] pixelAUX = new double[height*width][3];
        int k = 0;
        
        String sop = JOptionPane.showInputDialog(null, "Digite um valor para controle de brilho aditivo");
        int op = Integer.parseInt(sop);
        
        //Atera o valor de cada componente de cada pixel adicionando o valor inserido
        for(int h=0;h<height;h++)
            for(int w=0;w<width;w++){
                inputRaster.getPixel(w, h, pixel);
                for(int band=0;band<bands;band++){
                    pixelMod[band] = pixel[band] + op;
                    if (pixelMod[band] < 0)
                        pixelMod[band] = 0;
                    if (pixelMod[band] > 255)
                        pixelMod[band] = 255;
                    }
                inputRaster.setPixel(w, h, pixelMod);
                
                //Aproveita o laço para já salvar os valores convertidos para YIQ
                pixelAUX[k][0] = (pixel[0]*0.299) + (pixel[1]*0.587) + (pixel[2]*0.114);    //Y
                pixelAUX[k][1] = (pixel[0]*0.596) - (pixel[1]*0.274) - (pixel[2]*0.322);    //I
                pixelAUX[k][2] = (pixel[0]*0.211) - (pixel[1]*0.523) + (pixel[2]*0.312);    //Q
                k++;
            }
        
        //Converte os valores YIQ para RGB e salva no arquivo de backup da imagem (adiciona valor em Y)
        k = 0;
        for(int h=0;h<height;h++)
            for(int w=0;w<width;w++){
                pixelMod[0] = (op + pixelAUX[k][0]) + (pixelAUX[k][1]*0.956) + (pixelAUX[k][2]*0.621);     //R
                pixelMod[1] = (op + pixelAUX[k][0]) - (pixelAUX[k][1]*0.272) - (pixelAUX[k][2]*0.647);     //G
                pixelMod[2] = (op + pixelAUX[k][0]) - (pixelAUX[k][1]*1.106) + (pixelAUX[k][2]*1.703);     //B
                k++;
                for(int band=0;band<bands;band++){
                    if (pixelMod[band] > 255)
                        pixelMod[band] = 255;
                    if (pixelMod[band] < 0)
                        pixelMod[band] = 0;
                }
                inputRaster2.setPixel(w, h, pixelMod);
            }
}        
////////////////////////////////////////////////////////////////////////////////BRILHO ADITIVO

        
////////////////////////////////////////////////////////////////////////////////BRILHO MULTIPLICATIVO
if (CONTROLE == 5){
        WritableRaster inputRaster2 = imageBak.getRaster();
        double[][] pixelAUX = new double[height*width][3];
        int k = 0;
        
        String sop = JOptionPane.showInputDialog(null, "Digite um valor para controle de brilho multiplicativo");
        double op = Double.parseDouble(sop);
        
        //Atera o valor de cada componente de cada pixel multiplicando pelo valor inserido
        for(int h=0;h<height;h++)
            for(int w=0;w<width;w++){
                inputRaster.getPixel(w, h, pixel);
                for(int band=0;band<bands;band++){
                    pixelMod[band] = pixel[band] * op;
                    if (pixelMod[band] < 0)
                        pixelMod[band] = 0;
                    if (pixelMod[band] > 255)
                        pixelMod[band] = 255;
                    }
                inputRaster.setPixel(w, h, pixelMod);
                
                //Aproveita o laço para já salvar os valores convertidos para YIQ
                pixelAUX[k][0] = (pixel[0]*0.299) + (pixel[1]*0.587) + (pixel[2]*0.114);    //Y
                pixelAUX[k][1] = (pixel[0]*0.596) - (pixel[1]*0.274) - (pixel[2]*0.322);    //I
                pixelAUX[k][2] = (pixel[0]*0.211) - (pixel[1]*0.523) + (pixel[2]*0.312);    //Q
                k++;
            }
        
        //Converte os valores YIQ para RGB e salva no arquivo de backup da imagem (multiplicando valor em Y)
        k = 0;
        for(int h=0;h<height;h++)
            for(int w=0;w<width;w++){
                pixelMod[0] = (op * pixelAUX[k][0]) + (pixelAUX[k][1]*0.956) + (pixelAUX[k][2]*0.621);     //R
                pixelMod[1] = (op * pixelAUX[k][0]) - (pixelAUX[k][1]*0.272) - (pixelAUX[k][2]*0.647);     //G
                pixelMod[2] = (op * pixelAUX[k][0]) - (pixelAUX[k][1]*1.106) + (pixelAUX[k][2]*1.703);     //B
                k++;
                for(int band=0;band<bands;band++){
                    if (pixelMod[band] > 255)
                        pixelMod[band] = 255;
                    if (pixelMod[band] < 0)
                        pixelMod[band] = 0;
                }
                inputRaster2.setPixel(w, h, pixelMod);
            }        
}        
////////////////////////////////////////////////////////////////////////////////BRILHO MULTIPLICATIVO


////////////////////////////////////////////////////////////////////////////////FILTROS
if (CONTROLE == 6){
        String sop = JOptionPane.showInputDialog(null, "Escolha o filtro a ser aplicado: \n0=Média \n1=Mediana");
        int op = Integer.parseInt(sop);
        String sn = JOptionPane.showInputDialog(null, "Digite a ordem da matriz: ");
        int n = Integer.parseInt(sn);
        
        if (n%2 == 0)   //Garante que a matriz terá ordem ímpar para facilitar o processamento
            n++;
        int i = n/2;    //Registra apenas o número de linhas/colunas que irão cercar o pixel central

        int pixels[][] = new int[n*n][3];   //Armazena apenans um pixel RGB
 
        ///////////////////////MÉDIA
        if (op == 0){ 
            int R,G,B;
            for(int h=0;h<height;h++)
                for(int w=0;w<width;w++){
                    //Verifica se o pixel atual está na borda para deixá-lo preto
                    if ( (h <= i) || (h >= height-i) || (w <= i) || (w >= width-i) ){
                        pixelMod[0] = 0;
                        pixelMod[1] = 0;
                        pixelMod[2] = 0;
                    }else{
                        int k = 0;
                        //Armazena todos os pixels adjacentes ao atual
                        for (int aux1=h-i;aux1<=h+i;aux1++)
                            for (int aux2=w-i;aux2<=w+i;aux2++){
                                inputRaster.getPixel(aux2,aux1,pixels[k++]); //Armazena no índice 'k' e passa para o seguinte
                            }
                        //Cálcula o valor médio de cada banda da faixa selecionada acima e registra no array de saída
                        R=0; G=0; B=0;
                        for (k=0;k<n*n;k++){
                            R += pixels[k][0];
                            G += pixels[k][1];
                            B += pixels[k][2];
                        }
                        pixelMod[0] = R/(n*n);
                        pixelMod[1] = G/(n*n);
                        pixelMod[2] = B/(n*n);
                    }
                    inputRaster.setPixel(w,h,pixelMod);
                }
        }//if (op == 0)

        
        ///////////////////////MEDIANA
        if (op == 1){
            int vetorRGB[] = new int[n*n];      //Armazena os valores de uma única banda por vez
            for(int h=0;h<height;h++)
                for(int w=0;w<width;w++){
                    //Verifica se o pixel atual está na borda para deixá-lo preto
                    if ( (h <= i) || (h >= height-i) || (w <= i) || (w >= width-i) ){
                        pixelMod[0] = 0;
                        pixelMod[1] = 0;
                        pixelMod[2] = 0;
                    }else{
                        int k = 0;
                        //Armazena todos os pixels adjacentes ao atual
                        for (int aux1=h-i;aux1<=h+i;aux1++)
                            for (int aux2=w-i;aux2<=w+i;aux2++){
                                inputRaster.getPixel(aux2,aux1,pixels[k++]); //Armazena no índice 'k' e passa para o seguinte
                            }
                        
                        //Cálcula filtro da mediana na banda R
                        for (k=0;k<n*n;k++)
                            vetorRGB[k] = pixels[k][0]; //Armazena no vetor todos valores de R dos pixels adjacentes
                        Arrays.sort(vetorRGB);          //Ordena o array de forma crescente de valores
                        pixelMod[0] = vetorRGB[(n*n)/2];
                        
                        //Cálcula filtro da mediana na banda G
                        for (k=0;k<n*n;k++)
                            vetorRGB[k] = pixels[k][1]; //Armazena no vetor todos valores de G dos pixels adjacentes
                        Arrays.sort(vetorRGB);          //Ordena o array de forma crescente de valores
                        pixelMod[1] = vetorRGB[(n*n)/2];
                        
                        //Cálcula filtro da mediana na banda B
                        for (k=0;k<n*n;k++)
                            vetorRGB[k] = pixels[k][2]; //Armazena no vetor todos valores de B dos pixels adjacentes
                        Arrays.sort(vetorRGB);          //Ordena o array de forma crescente de valores
                        pixelMod[2] = vetorRGB[(n*n)/2];                        
                    }
                    inputRaster.setPixel(w,h,pixelMod);
                }
        }//if (op == 1)
        
        
}//if (CONTROLE == 6)
////////////////////////////////////////////////////////////////////////////////FILTROS


////////////////////////////////////////////////////////////////////////////////SOMA 2 IMAGENS
if (CONTROLE == 7){
    
        String arquivo2 = JOptionPane.showInputDialog(null, "Digite o nome (com diretório) da segunda de imagem \nCaso não seja digitado, será aberto o padrão");
        if (arquivo2.equals(""))
            arquivo2 = "Koala.jpg";
        
        //Faz a leitura do segundo arquivo de imagem
        try { image = ImageIO.read(new File(arquivo2)); }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Não foi possível abrir o arquivo: " + arquivo2);
        }
        if (image == null)
            throw new RuntimeException("Arquivo de imagem inválido: " + arquivo2);
        
        //Atualiza os arquivos de imagem para o uso de 2 arquivos diferentes
        inputRaster = image.getRaster();
        WritableRaster inputRaster2 = imageBak.getRaster();

        //Soma cada banda RGB de cada pixel e divide por 2
        for(int h=0;h<height;h++)
            for(int w=0;w<width;w++){
                inputRaster.getPixel(w, h, pixel);
                inputRaster2.getPixel(w, h, pixelMod);
                for(int band=0;band<bands;band++){
                    pixelMod[band] = (pixelMod[band] + pixel[band])/2;
                    }
                inputRaster.setPixel(w, h, pixelMod);
            }
}        
////////////////////////////////////////////////////////////////////////////////SOMA 2 IMAGENS

        //Escreve matriz modificada na imagem de saída
        try { ImageIO.write(image,"JPG",new File("DARK.JPG")); }
            catch (IOException e) { e.printStackTrace(); }
}


}
