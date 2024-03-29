/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * appPDI.java
 *
 * Created on 29/10/2011, 10:53:21
 */
package alteraimagem;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.text.MaskFormatter;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Neto
 */
public class appPDI extends javax.swing.JPanel {
    private BufferedImage image, image2, imageOriginal;
    private JImagePanel quadroImg = null;
    private String arquivo;
    private MaskFormatter maskNum;
    
    
    
    //Faz a leitura do arquivo de imagem e guarda uma como original
    public void getImagem(String arquivo)throws IOException{
        try { image = ImageIO.read(new File(arquivo)); }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Não foi possível abrir o arquivo: \n" + arquivo);
        }
        if (image == null)
            JOptionPane.showMessageDialog(this, "Arquivo de imagem inválido: \n" + arquivo);
        
        //Salva imagem original para comparação do resultado dos friltros aplicados
        if (image != null)
            imageOriginal = image;
    }

    //Faz a leitura do arquivo de imagem
    public void getImagem2 (String arquivo) throws IOException{
        try { image2 = ImageIO.read(new File(arquivo)); }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Não foi possível abrir o arquivo: \n" + arquivo);
        }
        if (image2 == null)
            JOptionPane.showMessageDialog(this, "Arquivo de imagem inválido: \n" + arquivo);
    }
    
    
    
    //Exibe janela de seleção de arquivos no disco rígido para encontrar imagem
    public String getArquivo(){
        JFileChooser chooser = new JFileChooser();  
        chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "bmp", "gif"));  
        chooser.setAcceptAllFileFilterUsed(false);  
        int returnVal = chooser.showOpenDialog(this);  
        
        if(returnVal == JFileChooser.APPROVE_OPTION){  
            return chooser.getSelectedFile().getAbsolutePath();
        }else{
            chooser.setVisible(false);
            return "";
        } 
        
    }
    
    
    
    //Ativa botões que permitem operações em RGB
    public void AtivaRGB(){
        btnRgbYiq.setEnabled(true);
        btnYiqRgb.setEnabled(false);
        btnMedia.setEnabled(true);
        btnMediana.setEnabled(true);
        btnBrilhoAdd.setEnabled(true);
        btnBrilhoMul.setEnabled(true);
        txtBrilhoAdd.setEnabled(true);
        txtBrilhoMul.setEnabled(true);
        btnBandaR.setEnabled(true);
        btnBandaG.setEnabled(true);
        btnBandaB.setEnabled(true);
        btnBandaRMono.setEnabled(true);
        btnBandaGMono.setEnabled(true);
        btnBandaBMono.setEnabled(true);
        btnSomaImg.setEnabled(true);
        btnNegativo.setEnabled(true);
    }
    
    //Ativa botões que permitem operações em YIQ
    public void AtivaYIQ(){
        btnRgbYiq.setEnabled(false);
        btnYiqRgb.setEnabled(true);
        btnMedia.setEnabled(false);
        btnMediana.setEnabled(false);
        btnBrilhoAdd.setEnabled(true);
        btnBrilhoMul.setEnabled(true);
        txtBrilhoAdd.setEnabled(true);
        txtBrilhoMul.setEnabled(true);
        btnBandaR.setEnabled(false);
        btnBandaG.setEnabled(false);
        btnBandaB.setEnabled(false);
        btnBandaRMono.setEnabled(false);
        btnBandaGMono.setEnabled(false);
        btnBandaBMono.setEnabled(false);
        btnSomaImg.setEnabled(false);
        btnNegativo.setEnabled(true);
    }
    
    
       
    
    
    
    
    /** Creates new form appPDI */
    public appPDI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBrilhoMul = new javax.swing.JButton();
        btnSomaImg = new javax.swing.JButton();
        btnBandaBMono = new javax.swing.JButton();
        btnBrilhoAdd = new javax.swing.JButton();
        btnBandaRMono = new javax.swing.JButton();
        btnBandaGMono = new javax.swing.JButton();
        btnBandaG = new javax.swing.JButton();
        btnBandaB = new javax.swing.JButton();
        btnMediana = new javax.swing.JButton();
        btnBandaR = new javax.swing.JButton();
        try{
            maskNum = new MaskFormatter("#,#");
        }catch (Exception erro){
            JOptionPane.showMessageDialog(null,"Erro ao definir formato do Ano"+erro.toString(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
        txtBrilhoMul = new JFormattedTextField(maskNum);
        try{
            maskNum = new MaskFormatter("####");
        }catch (Exception erro){
            JOptionPane.showMessageDialog(null,"Erro ao definir formato do Ano"+erro.toString(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
        txtBrilhoAdd = new JFormattedTextField(maskNum);
        btnMedia = new javax.swing.JButton();
        btnRgbYiq = new javax.swing.JButton();
        btnNegativo = new javax.swing.JButton();
        btnYiqRgb = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setName("jPaneMain"); // NOI18N
        setPreferredSize(new java.awt.Dimension(684, 499));

        btnBrilhoMul.setText("Brilho Multiplicativo");
        btnBrilhoMul.setEnabled(false);

        btnSomaImg.setText("Somar Duas Imagens");
        btnSomaImg.setEnabled(false);
        btnSomaImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSomaImgActionPerformed(evt);
            }
        });

        btnBandaBMono.setText("Monocromática B");
        btnBandaBMono.setEnabled(false);

        btnBrilhoAdd.setText("Brilho Aditivo");
        btnBrilhoAdd.setEnabled(false);

        btnBandaRMono.setText("Monocromática R");
        btnBandaRMono.setEnabled(false);

        btnBandaGMono.setText("Monocromática G");
        btnBandaGMono.setEnabled(false);

        btnBandaG.setText("Banda G");
        btnBandaG.setEnabled(false);

        btnBandaB.setText("Banda B");
        btnBandaB.setEnabled(false);

        btnMediana.setText("Mediana");
        btnMediana.setEnabled(false);

        btnBandaR.setText("Banda R");
        btnBandaR.setEnabled(false);

        btnMedia.setText("Média");
        btnMedia.setEnabled(false);

        btnRgbYiq.setText("RGB - YIQ");
        btnRgbYiq.setEnabled(false);
        btnRgbYiq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRgbYiqActionPerformed(evt);
            }
        });

        btnNegativo.setText("Negativo");
        btnNegativo.setEnabled(false);

        btnYiqRgb.setText("YIQ - RGB");
        btnYiqRgb.setEnabled(false);
        btnYiqRgb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYiqRgbActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnAbrir.setText("Abrir");
        btnAbrir.setName("btnAbrir"); // NOI18N
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBandaB, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnBandaG, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBrilhoMul)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnBandaGMono, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBandaBMono, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtBrilhoMul, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnNegativo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSomaImg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBandaR, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBandaRMono, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBrilhoAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMediana, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBrilhoAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRgbYiq, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnYiqRgb, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAbrir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(btnAbrir)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpar))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnYiqRgb)
                            .addComponent(btnRgbYiq))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMedia)
                            .addComponent(btnMediana))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBrilhoAdd)
                            .addComponent(txtBrilhoAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBrilhoMul)
                            .addComponent(txtBrilhoMul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBandaRMono)
                            .addComponent(btnBandaR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBandaG)
                            .addComponent(btnBandaGMono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBandaBMono)
                            .addComponent(btnBandaB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNegativo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSomaImg))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        arquivo = getArquivo();
        if (!arquivo.equals("")){
            AtivaRGB();
            try {
                getImagem(arquivo);
            } catch (IOException ex){
            }
        }
        
        
                if (image != null){
/*            Graphics g1 = jPanel1.getGraphics();
            Image img1 = image.getScaledInstance(392, 477, BufferedImage.SCALE_SMOOTH);
            g1.drawImage(img1,0,0,392,477,null);
            
            Graphics g2 = jPanel2.getGraphics();
            g2.drawImage(img1,0,0,266,172,null);
            jPanel1.repaint();
            jPanel2.repaint(); */
            
            
//            quadroImg = new JImagePanel(image);
//            quadroImg.setImage(image);
//            jPanel1.setImage(image);
            //jPanel1.setBackground(Color.white);
            //Graphics g1 = jPanel1.getGraphics();
           // g1.drawImage(image, 0, 0, null);
            ///ImageIcon img = new ImageIcon(quadroImg.getImage());
            ///JLabel label = new JLabel(img);
            ///jPanel1.add(label, BorderLayout.NORTH);
            //jPanel1.paintComponents(g1);
            //g1.drawImage(quadroImg.getImage(), 0, 0, null);
 //           jPanel1.repaint();
            
            
            //g1.drawImage(image, 0, 0, null);
            //jPanel1.paintComponents(g1);
            
            //jPanel1.add(quadroImg);
            //jPanel1.repaint(); 
            
/*            PainelGrafico img1= new PainelGrafico(arquivo);
            JPanel ex = new JPanel();
            ex.add(img1);
            ex.repaint(); */
            
            
            //Component add = getContentPane().add(jPanel1);
            
/*            jPanel1.setLayout(new BorderLayout());
            ImageIcon img = new ImageIcon(image);
            JLabel labelimg = new JLabel(img);
            jPanel1.add(labelimg,BorderLayout.CENTER);
            
            /*            jPanel1.paintComponent(image) = new JPanel(){
            protected void paintcomponent(Graphics g){
            super.paintComponent(Graphics g);
            image.paintIcon(this,20,20);
            }  */
            }
             
            
            
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        jPanel1.removeAll();
        jPanel1.repaint();
        jPanel2.removeAll();
        jPanel2.repaint();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSomaImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSomaImgActionPerformed
        arquivo = getArquivo();
        if (!arquivo.equals("")){
            AtivaRGB();
            try {
                getImagem2(arquivo);
            } catch (IOException ex){
            }
        }
    }//GEN-LAST:event_btnSomaImgActionPerformed

    private void btnRgbYiqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRgbYiqActionPerformed
        AtivaYIQ();
    }//GEN-LAST:event_btnRgbYiqActionPerformed

    private void btnYiqRgbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYiqRgbActionPerformed
        AtivaRGB();
    }//GEN-LAST:event_btnYiqRgbActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new appPDI().setVisible(true);
            }
        });
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnBandaB;
    private javax.swing.JButton btnBandaBMono;
    private javax.swing.JButton btnBandaG;
    private javax.swing.JButton btnBandaGMono;
    private javax.swing.JButton btnBandaR;
    private javax.swing.JButton btnBandaRMono;
    private javax.swing.JButton btnBrilhoAdd;
    private javax.swing.JButton btnBrilhoMul;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnMedia;
    private javax.swing.JButton btnMediana;
    private javax.swing.JButton btnNegativo;
    private javax.swing.JButton btnRgbYiq;
    private javax.swing.JButton btnSomaImg;
    private javax.swing.JButton btnYiqRgb;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtBrilhoAdd;
    private javax.swing.JTextField txtBrilhoMul;
    // End of variables declaration//GEN-END:variables
}
