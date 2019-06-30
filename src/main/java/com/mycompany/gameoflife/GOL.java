package com.mycompany.gameoflife;
// timer usa o timer task para funcionar
import java.util.Timer;
import java.util.TimerTask;
// doublebuffer para a tela não ficar piscando ao ser executada, o objetivo é não fazer as coisas na tela principal e sim nas outras e depois executar
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.SwingUtilities;



/**
 *
 * @author Henri
 */
public class GOL extends javax.swing.JFrame {
      final int wid = 200;
      final int hei = 100;
         
    //enquanto a celula ta viva não é usado o nextMove, até q a celula morra
    boolean[][] currentMove = new boolean[hei][wid]; //booleano do movimento atual da celula
    boolean [][] nextMove = new boolean [hei][wid]; //booleano para o próximo movimento da célula
    boolean play; // para o timer iniciar o jogo
    
    // variaveis globais
    Image offScrImg;
    Graphics offScrGraph;
    
     
    public GOL() {
        initComponents();
        
        //a partir do tamanho da imagem é criado o grafico
        offScrImg = createImage(jPanel1.getWidth() , jPanel1.getHeight());
        offScrGraph = offScrImg.getGraphics();
        
        //timer
        Timer time = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                if(play){
                    for(int i = 0; i < hei; i++){
                        for(int j = 0; j < wid; j++){
                            nextMove[i][j] = decide(i,j);
                        }
                    }
                    for(int i = 0; i < hei; i++){
                        for(int j = 0; j < wid; j++){
                            currentMove[i][j] = nextMove[i][j];
                        }
                    }
                    repain();
                }
            }
        };
        time.scheduleAtFixedRate(task, 0, 100);
        repain();
    }
    
    private boolean decide(int i, int j){
        int neighbors = 0;
        
        //os ifs abaixo checam se existem vizinhos da celula (cada uma tem possibilidade de ter 8 vizinhos)
        if(j > 0){
            if(currentMove[i][j-1]) neighbors++;
            if(i>0) if(currentMove[i-1][j-1]) neighbors++;
            if(i<hei-1) if(currentMove[i+1][j-1]) neighbors++;
        }
        if(j < wid-1){
            if(currentMove[i][j+1]) neighbors++;
            if(i>0) if(currentMove[i-1][j+1]) neighbors++;
            if(i<hei-1) if(currentMove[i+1][j+1]) neighbors++;
        }
        if(i>0) if(currentMove[i-1][j]) neighbors++;
        if(i<hei-1) if(currentMove[i+1][j]) neighbors++;
        if(neighbors == 3) // se existem 3 vizinhos a celula é uma celula viva
            return true;
        if(currentMove[i][j] && neighbors == 2) 
            return true;
        return false;
    }
    
    
    
    private void repain(){
        offScrGraph.setColor(jPanel1.getBackground());
        offScrGraph.fillRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());
        for(int i = 0 ; i < hei ; i++){
            for(int j = 0 ; j < wid; j++){
                if(currentMove[i][j]){
                    offScrGraph.setColor(Color.MAGENTA);
                    int x = j * jPanel1.getWidth()/wid;
                    int y = i * jPanel1.getHeight()/hei;
                    offScrGraph.fillRect(x, y, jPanel1.getWidth()/wid, jPanel1.getHeight()/hei);
                }
            }
        }
        //desenha as linhas 
        offScrGraph.setColor(Color.BLACK);
        for(int i = 1; i < hei;i++){
            int y = i * jPanel1.getHeight()/hei;
            offScrGraph.drawLine(0, y, jPanel1.getWidth(), y);
        }
        for(int j = 1; j < wid;j++){
            int x = j * jPanel1.getWidth()/wid;
            offScrGraph.drawLine(x, 0, x, jPanel1.getHeight());
        }
        jPanel1.getGraphics().drawImage(offScrImg, 0, 0, jPanel1);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textArea1 = new java.awt.TextArea();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );

        jButton1.setText("Play");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 418, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         play = !play;
        if(play) jButton1.setText("Pause");
        else jButton1.setText("Play");
        repain();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        currentMove = new boolean[hei][wid];
        repain();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
         int j = wid * evt.getX() / jPanel1.getWidth();
        int i = hei * evt.getY() / jPanel1.getHeight();
        if(SwingUtilities.isLeftMouseButton(evt)){
            currentMove[i][j] = true;
        }else currentMove[i][j] = false;
        repain();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        offScrImg = createImage(jPanel1.getWidth(), jPanel1.getHeight());
        offScrGraph = offScrImg.getGraphics();
        repain();
    }//GEN-LAST:event_jPanel1ComponentResized

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GOL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GOL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GOL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GOL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GOL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
