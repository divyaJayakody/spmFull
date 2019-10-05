/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import spmsize.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 *
 * @author GTC
 */
public class dragContainer2 extends javax.swing.JFrame {

    static void setIcon(ImageIcon imageIcon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

                 int Cs = 0;
                 int keywordsCount = 0;
                 int arithOCount= 0;
                 int relatOCount= 0;
                 int logicOCount= 0;
                 int assOCount= 0;
                 int bitwOCount= 0;
                 int manipCount= 0;
                 int miscOCount= 0;
                 
                
             // This will reference one line at a time
             String line = null;
             String[] keywords = new String[] { "import", "void", "double", "int", "float", "String", "println", "cout",
                           "if", "for", "while", "switch", "case"};
             int keywordSize = keywords.length;
       
             
             String[] ariOperators = new String[] { "+", "-", "*", "/", "%", "++", "--" };
             int arithOSize = ariOperators.length;
             

             String[] relOperators = new String[] { "==", "!=", ">", "<", ">=", "<=" };
             int relatOSize = relOperators.length;
             

             String[] logicOperators = new String[] { "&&", "||", "!" };
             int logicOSize = logicOperators.length;
             

             String[] assignOperators = new String[] { "+=", "-=", "*=", "/=", ">>>=", "|=", "&=", "<<=", ">>=", "%=",
                           "^=" };
             int assOSize = assignOperators.length;
             

             String[] bitwiseOperators = new String[] { "|", "^", "~", "<<", ">>", "<<<", ">>>" };
             int bitwOSize = bitwiseOperators.length;
             
             String[] manipulators = new String[] { "\n", "endl"};
             int manipSize = manipulators.length;
             
             String[] miscellaneous  = new String[] { ",", "->",".","::"};
             int miscOSize = miscellaneous.length;
 
    
    /**
     * Creates new form dragDrop
     */
    public dragContainer2() {
        initComponents();
        modifyLabel();
    }

    public void modifyLabel(){
        TransferHandler th = new TransferHandler(){
            
        public boolean canImport(JComponent comp,DataFlavor[] transferFlavors){
            return true;
        } 
       
        public boolean importData(JComponent comp,Transferable t){
            try {
                List<File> files = (List<File>)  t.getTransferData(DataFlavor.javaFileListFlavor);
                for (File file : files){
                System.out.println(file.getName());
                //rest of the code
                if(files.size()==1){
                    File f = files.get(0);
                    dragContainer.setIcon(new ImageIcon(f.getPath()));
                    String fileName = f.getPath();
                    
           
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader = new FileReader(fileName);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    int count = 1;
                    int linekeys=0;
                    while ((line = bufferedReader.readLine()) != null) {
                        
                        
                          HashSet<Character> match = new HashSet<>(Arrays.asList('(',')','=',';','{','}','[',']','+','-','*','/','&','!','%','^','|','<','>'));
                          System.out.println(line);
                          for(int i =0; i < line.length(); i++) {                              
                                if (match.contains(line.charAt(i))){                                                                  
                                    linekeys++;
                                    
                                }
                            }
                          System.out.println(linekeys);
                        
                       
                          
                           //loop for miscellaneous operators
               
                           
                            
                            
                           System.out.println(count + " - " + line + " ");
                           System.out.println("Cs value   : " + Cs);
                           count++;
                           
                    }
                    
                    //setting values to display
                            keywordBox.setText(Integer.toString(keywordsCount));
                            arithOBox.setText(Integer.toString(arithOCount));
                            relatOBox.setText(Integer.toString(relatOCount));
                            logicOBox.setText(Integer.toString(logicOCount));
                            assignOBox.setText(Integer.toString(assOCount));
                            bitwOBox.setText(Integer.toString(bitwOCount));
                            manipOBox.setText(Integer.toString(manipCount));
                            miscOBox.setText(Integer.toString(miscOCount));
                            totalCsBox.setText(Integer.toString(Cs));
                            
                            
                    System.out.println("Comprehensive Cs value   : " + Cs);

                    // Always close files.
                    bufferedReader.close();
                
                
                
                
                }
                
                }
                
            } catch (UnsupportedFlavorException ex) {
                Logger.getLogger(dragContainer2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(dragContainer2.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
            
        
    };
        dragContainer.setTransferHandler(th);
        
                
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dragContainer = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        keywordBox = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        arithOBox = new javax.swing.JLabel();
        relatOBox = new javax.swing.JLabel();
        logicOBox = new javax.swing.JLabel();
        bitwOBox = new javax.swing.JLabel();
        miscOBox = new javax.swing.JLabel();
        assignOBox = new javax.swing.JLabel();
        manipOBox = new javax.swing.JLabel();
        stringOBox = new javax.swing.JLabel();
        otherBox = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        totalCsBox = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Code Complexity Measurement  Tool - Size");
        setBackground(new java.awt.Color(51, 51, 51));

        dragContainer.setBorder(javax.swing.BorderFactory.createLineBorder(null));

        jLabel1.setText(" Drag and drop your java codes(.java, .txt formats are supported)");

        jLabel2.setText(">>>>>>>>>>>  Size Complexity  -  Summary <<<<<<<<<<");
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabel3.setText("Key Word Count ----------------------");

        jLabel5.setText("Arithmatic Operators----------------");

        keywordBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Relational Operators-----------------");

        jLabel9.setText("Logical Operators---------------------");

        jLabel11.setText("Bitwise Operators---------------------");

        jLabel13.setText("Miscellaneous Operators-------------");

        jLabel15.setText("Assignment Operators----------------");

        jLabel17.setText("Manipulators---------------------------");

        jLabel19.setText("Text within double quotes-----------");

        jLabel21.setText("Class,Objects..etc--------------------");

        arithOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        relatOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        logicOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bitwOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        miscOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        assignOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        manipOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        stringOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        otherBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("TOTAL COMPLEXITY ");

        totalCsBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel11)
                                .addComponent(jLabel7)
                                .addComponent(jLabel5)
                                .addComponent(jLabel9)
                                .addComponent(jLabel15)
                                .addComponent(jLabel19)
                                .addComponent(jLabel17)
                                .addComponent(jLabel3)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21))
                            .addGap(50, 50, 50)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(keywordBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(arithOBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(relatOBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(logicOBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bitwOBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(miscOBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(assignOBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(manipOBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stringOBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(totalCsBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(otherBox, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                            .addComponent(dragContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dragContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(keywordBox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arithOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(relatOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logicOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bitwOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(miscOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(assignOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manipOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stringOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(otherBox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(totalCsBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(dragContainer2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dragContainer2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dragContainer2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dragContainer2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dragContainer2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arithOBox;
    private javax.swing.JLabel assignOBox;
    private javax.swing.JLabel bitwOBox;
    private javax.swing.JLabel dragContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel keywordBox;
    private javax.swing.JLabel logicOBox;
    private javax.swing.JLabel manipOBox;
    private javax.swing.JLabel miscOBox;
    private javax.swing.JLabel otherBox;
    private javax.swing.JLabel relatOBox;
    private javax.swing.JLabel stringOBox;
    private javax.swing.JLabel totalCsBox;
    // End of variables declaration//GEN-END:variables
}
