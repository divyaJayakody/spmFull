/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spmFull;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.HeadlessException;
import spmsize.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author GTC
 */
public class spmFull extends javax.swing.JFrame {

    static void setIcon(ImageIcon imageIcon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

                 int TCsCount = 0;
                 int TCiCount= 0;
                 int TCtCount =0;      
                 int TCrCount =0;
                 int TwCount=0;
                 int CpsCount=0;
                 int CpCount=0; 
                 
                 int keywordsCount = 0;
                 int arithOCount= 0;
                 int relatOCount= 0;
                 int logicOCount= 0;
                 int assOCount= 0;
                 int bitwOCount= 0;
                 int manipCount= 0;
                 int miscOCount= 0;
                 int numberCount= 0;
                 int textCount =0;
                 int cppCount = 0;
                 int ccount = 0;
                 int specialCount =0;
                 
                 //nesting
                int incrementForIf = 0;
                int incrementForOPerators = 0;
                int incrementForIterative = 0;
                int incrementForCatch = 0;
                int incrementForSwitch = 0;
                int countCatch = 0;
                int countCase = 0;
                int iLoop = 0;
                String line;
                String codeLine[] = null;
                
                
                
             // This will reference one line at a time
             String line4 = null;
             String line3 =null;
             String line2 =null;
             String line1=null;
         
             
             String[] keywords = new String[] { "long","fibonacci","number", "void", "double", "int", "float", "String", "cout","cin",
                           "if", "for", "while","do-while","switch","case", "main","System","out","println","args","operator","bark","count","accessFiles","FileNotFoundException","FileReader","f","catch","e","std","this","Dog","Animal"};
             int keywordSize = keywords.length;
             
             String[] inheritsWords=new String[]{"extends","implements",":"};
             int inheritSize = inheritsWords.length;
       
             String[] cppKeyWords=new String[]{"&","*","new","delete","throw","throws"};
             int cppKeywordSize = cppKeyWords.length;
             
             String[] ariOperators = new String[] { "+", "-", "*", "%", "++", "--" };
             int arithOSize = ariOperators.length;
             //divider has been seperated

             String[] relOperators = new String[] { "==", "!=", ">", "<", ">=", "<=" };
             int relatOSize = relOperators.length;
             

             String[] logicOperators = new String[] { "&&", "||", "!" };
             int logicOSize = logicOperators.length;
             

             String[] assignOperators = new String[] { "=","+=", "-=", "*=", "/=", ">>>=", "|=", "&=", "<<=", ">>=", "%=",
                           "^=" };
             int assOSize = assignOperators.length;
             

             String[] bitwiseOperators = new String[] { "|" ,"^", "~", "<<", ">>", "<<<", ">>>" };
             int bitwOSize = bitwiseOperators.length;
             
             String[] manipulators = new String[] { "\n", "endl"};
             int manipSize = manipulators.length;
             
             String[] miscellaneous  = new String[] {  "->",".","::","~"};
             int miscOSize = miscellaneous.length;
 
             String[] printwQ  = new String[] {};
             
             
             ArrayList<String> printQ = new ArrayList<String>();
             // create object of table and table model
            DefaultTableModel model;
            



    /**
     * Creates new form dragDrop
     */
    public spmFull() {
        initComponents();
        modifyLabel();
        model = new DefaultTableModel();
        display.setModel(model);
        model.addColumn("Line");
        model.addColumn("Statement");
        model.addColumn("Key words");
        model.addColumn("Cs");
        model.addColumn("Ci");
        model.addColumn("Ct");
        model.addColumn("Tw");
        model.addColumn("Cps");
        model.addColumn("Cp");
        
        
        

        display.getColumn("Line").setMaxWidth(30);
        display.getColumn("Statement").setMaxWidth(250);
        display.getColumn("Key words").setMaxWidth(400);
        display.getColumn("Cs").setMaxWidth(30);
        display.getColumn("Ci").setMaxWidth(30);
        display.getColumn("Ct").setMaxWidth(30);
        display.getColumn("Tw").setMaxWidth(30);
        display.getColumn("Cps").setMaxWidth(30);
        display.getColumn("Cp").setMaxWidth(30);
    }

    public void modifyLabel(){
        
        /***drag and drop implementation ***/
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
                    
                    /***display file path***/
                    String fileName = f.getPath();
                    String path = f.getPath();
                    jLabel18.setText(path);
                    
                    /***display file name***/ 
                    String fname = f.getName();
                    int pos = fname.lastIndexOf(".");
                    if (pos > 0) {
                        fname = fname.substring(0, pos);
                        jLabel14.setText(fname);
                    }
                   
                  /***display file extension***/
                   int q = fileName.lastIndexOf('.');
                    if (q >= 0) {
                        String extension = fileName.substring(q+1);
                        jLabel16.setText(extension);
                    }
                    
                    /*** FileReader reads text files in the default encoding.***/
                    FileReader fileReader = new FileReader(fileName);
                    

                    
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    int lineCounter =0;
                    
                    /*** A line instance is  created and looped for keywords operators nested conditions.***/
                    while ((line = bufferedReader.readLine()) != null) {
                        
                 
                        lineCounter++;
                        int TCs =0;
                        int TCi =0;
                        int TCt =0;
                        
                        int TCr =0;
                        int Tw=0;
                        int Cps=0;
                        int Cp=0; 
                        
                        
                        //display line in the table model without alteration
                        String displayLine = line;
                         
                         //refining all files 
                        line = line.replaceAll("//.*$","").replaceAll("[(]"," ").replaceAll("[)]"," ").replaceAll("[;]","").replaceAll("\\[|\\]","").replaceAll("count"," count ").replaceAll("[.]"," . ");            
                        
                        //refining cpp files  
                        line = line.replaceAll("[::]"," :: ").replaceAll("[*]"," * ").replaceAll("[==]"," == ");
                        
                        ArrayList<String> printQ = new ArrayList<String>();
                        
                        
                       
                            /********Loop for C++ inheritance Keywords **********/
                        
                            Pattern pattern10 = Pattern.compile("implements(.*)"); 
                            Matcher matcher10 = pattern10.matcher(line);
                            
                            while (matcher10.find()) { 
                                                         
                                System.out.println("contains implementaions....................." + matcher10.group());
                                String s = matcher10.group(); 
                                s =s.replaceAll("[,]"," ").replaceAll("[{]"," ").replaceAll("[.]"," ");
                                System.out.println("contains implementaions....................." +s);
                                String[] implClass = s.split(" ");
 
                                for (int a = 0; a < implClass.length; a++) {
                                    System.out.println("contains implementaions....................." + implClass[a]);
                                
                                }
                                //keyword count
                                TCi = implClass.length;
                                
                                printQ.add(matcher10.group());
                            }
                               
                            
                             Pattern pattern11 = Pattern.compile("(?<=implements).*"); 
                            Matcher matcher11 = pattern11.matcher(line);
                            
                            while (matcher10.find()) { 
                                                         
                                System.out.println("contains parent classes....................." + matcher11.group());
                                String s = matcher10.group(); 
                                s =s.replaceAll("[,]"," ").replaceAll("[{]"," ").replaceAll("[.]"," ");
                                String[] extClass = s.split(" ");
                                
                                for (int a = 0; a < extClass.length; a++) {
                                    System.out.println("contains parent classes....................." + extClass[a]);
                                
                                }
                                //keyword count 
                                TCi= extClass.length ;                         
                                printQ.add(matcher10.group());
                            }
                        
                             /********Loops for  Size Complexity   **********/
                             
                             
                              /********Loop for keywords **********/
                            for (int k = 0; k < keywordSize; k++) {
                                 int a = 0;
                                 String[] split = line.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                   
                                        if (split[a].equals(keywords[k]) ) {
                                        System.out.println("containss keywords ....................." + keywords[k]);
                                        TCs++;
                                        keywordsCount++;
                                        printQ.add(split[a]);
                                        }
                                 
                                 }
                                 
                                 
                            }
                                                    
                             /********Loop for keywords **********/                                      
                        
                            Pattern pattern = Pattern.compile("[0-9]+"); 
                            Matcher matcher = pattern.matcher(line);
                            while (matcher.find()) {                            
                                System.out.println("contains numbers....................." + matcher.group());
                                TCs++;
                                numberCount++;
                                printQ.add(matcher.group());
                            }
                           
                            
                                        
                                 
                           /********Loop for text within double quotes**********/                                         
                            Pattern pattern1 = Pattern.compile("\\\"(.*?)\\\""); 
                            Matcher matcher1 = pattern1.matcher(line);                            
                           
                            while(matcher1.find() ) {
                                 if(matcher1.group(1)== null){
                               System.out.println("contains double quoted text ....................." + matcher1.group(1));
                                TCs++;
                                textCount+=1;
                                printQ.add(matcher1.group(1));
                               }        
                                
                            }
                          
                        
                        /********Loop for arith operators**********/                                     
                           for (int k = 0; k < arithOSize; k++) {
                                 int a = 0;
                                 String[] split = line.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(ariOperators[k])) {
                                               System.out.println("containss ariOperators ....................." + ariOperators[k]);
                                               TCs++;
                                               printQ.add(split[a]);
                                        }
                                 }
                           } 
                           
                           
                        /********Loop for relate  operators**********/   
                       
                            for (int k = 0; k < relatOSize; k++) {
                                 int a = 0;
                                 String[] split = line.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(relOperators[k])) {
                                               System.out.println("containss relOperators ....................." +relOperators[k]);
                                               TCs++;
                                               printQ.add(split[a]);
                                        }
                                 }
                           } 
                            
                        /********Loop for logic operators**********/   

                               for (int k = 0; k < logicOSize; k++) {
                                 int a = 0;
                                 String[] split = line.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(logicOperators[k])) {
                                               System.out.println("containss logicOperators ....................." +logicOperators[k]);
                                               TCs++;
                                               printQ.add(split[a]);
                                        }
                                 }
                           } 
                           
                        /********Loop for assign operators**********/   
                           for (int k = 0; k < assOSize; k++){
                               int a = 0;
                                 String[] split = line.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(assignOperators[k])) {
                                               System.out.println("containss assignOperators ....................." + assignOperators[k]);
                                               TCs++;
                                               printQ.add(split[a]);
                                               
                                        }
                                 }    
                           }
                          
                         
                        /********Loop for bit wise operators**********/   
                           for (int k = 0; k < bitwOSize; k++) {
                                 int a = 0;
                                 String[] split = line.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(bitwiseOperators[k])) {
                                               System.out.println("containss bitwiseOperators ....................." + bitwiseOperators[k]);
                                               TCs++;
                                               printQ.add(split[a]);
                                        }
                                 
                                }
                           }        
                
                           
                           
                        /********Loop for cpp  keyword **********/   
                           for (int k = 0; k < cppKeywordSize; k++) {
                                 int a = 0;
                                 String[] split = line.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(cppKeyWords[k])) {
                                               System.out.println("containss cppOperators ....................." + cppKeyWords[k]);
                                               TCs+=2;
                                               printQ.add(split[a]);
                                        }
                                 
                                }
                           }        
                
                           
                            
                        /********Loop for manip operators**********/   
                           for (int i = 0; i < manipSize; i++) {
                                 if (line.contains(manipulators[i])) {
                                        System.out.println("containss manipulators ....................." + manipulators[i]);
                                        TCs++;
                                        printQ.add(manipulators[i]);
                                 }
                           }
                          
                        /********Loop for misc operators**********/     
                           for (int i = 0; i < miscOSize; i++) {
                                 if (line.contains(miscellaneous[i])) {
                                        System.out.println("containss misc operators ....................." + miscellaneous[i]);
                                        TCs++;
                                        printQ.add(miscellaneous[i]);
                                 }
                           }
                           
                           
                    /********Program for measuring nested complexity**********/           
                    ArrayList<String>arrList = new ArrayList<>(Arrays.asList(line)) ;
        
                    String condition1[] = {"if", "for", "while", "catch", "switch"};

                    for(iLoop = 0; iLoop < arrList.size(); iLoop++){
                           line = arrList.get(iLoop);

                           //ignores comment lines
                           while(line.contains("//")){
                               line = arrList.get(iLoop = iLoop + 1);
                           }
                           //check for "if"
                           if(line.contains(condition1[0])){
                                String tempString; 
                                int i = 0 ;
                                while(i <= line.length() - 2){
                                    tempString = String.valueOf(line.charAt(i) + String.valueOf(line.charAt(i+1)));
                                    if(tempString.equals("if")){
                                        incrementForIf++;
                                    }
                                    if(tempString.equals("&&") ||tempString.equals("||")){
                                        //System.out.println("Come .. to && or ||" );
                                        incrementForIf++;
                                        i += 2;
                                    }else if(line.charAt(i) == '&' || line.charAt(i) == '|'){
                                        //System.out.println("Come .. to else if " );
                                        incrementForIf++;
                                        i++;
                                    }else
                                        i++;

                                }
                                System.out.println("Count value of if condition is" + incrementForIf );
                               
                                TCt +=incrementForIf ;
                                
                                
                            //check for "for or while"
                           }else if(line.contains(condition1[1]) || line.contains(condition1[2])){
                               String tempString;
                               String newLine = line;
                               int i = 0;

                               while(i <= newLine.length() - 2){
                                   tempString = String.valueOf(newLine.charAt(i) + String.valueOf(newLine.charAt(i+1)));
                                   if(newLine.contains(condition1[1]) || newLine.contains(condition1[2])){
                                       newLine = newLine.replaceFirst("for", "");
                                       newLine = newLine.replaceFirst("while", "");
                                       incrementForIterative += 2;
                                       i+=2;
                                   }
                                   if(tempString.equals("&&")|| tempString.equals("||")){
                                       incrementForIterative += 2; 
                                       i+=2;
                                   }else if(newLine.charAt(i) == '&' || newLine.charAt(i) == '|'){ //go to above if n doesnt come to here check it....
                                        incrementForIterative +=2;
                                        i++;
                                   }else
                                        i++;


                                }
                                    System.out.println("Count value of incrementForIterative is " + incrementForIterative );
                                    
                                    TCt +=incrementForIterative;
                             //check for "Catch"
                           }else if(line.contains(condition1[3])){
                               incrementForCatch++;
                               System.out.println("Count value is " + incrementForCatch );
                                TCt +=incrementForCatch;
                               
                            //check for switch   
                           }else if(line.contains(condition1[4])){
                               int n = 0;
                               while(!line.contains("default")){
                                   line = arrList.get(iLoop = iLoop + 1);
                                   if(!line.contains("//") &&line.contains("case")){ 
                                       n++;
                                   }
                               }
                               incrementForSwitch = n;
                               System.out.println("Count value is " + incrementForSwitch );
                               TCt +=incrementForSwitch;
                           }

                       }
                           
                           //System.out.println("Line wise Cr value   : " + Cr);
                           System.out.println("Line wise Ct value   : " + TCt);
                           System.out.println("Line wise Ci value   : " + TCi);
                           //total of Ct +Ci =  Tw
                           
                           Tw = TCi+TCt;
                           
                           System.out.println("Line wise Tw value   : " + Tw);
                           System.out.println("Line wise Cs value   : " + TCs);
                           
                           
                           Cps = TCs*Tw;
                           
                           
                          //total of Cs * Tw =  Cps
                            System.out.println("Total Cs value   : " + Cps);
                            
                           //if recursion is present
                           //else this
                           Cp = Cps;
                            
                            
                           
                           //convert array list to String
                           String printlist = String.join(", ", printQ);
                           System.out.println("complete line   : " +printlist);
                           printQ.clear();
                           
                           int line1 = lineCounter;
                           String statement1 = displayLine;
                           String keywords1 = printlist;
                           int printCs = TCs;
                           int printCi = TCi;
                           int printCt = TCt;
                           int printTw= Tw;
                           int printCps = Cps;
                           int printCp = Cp;
                           
                            TCsCount += TCs;
                            TCiCount += TCi;
                            TCtCount +=TCt;      
                            TCrCount +=TCr;
                            TwCount +=Tw;
                            CpsCount +=Cps;
                            CpCount +=Cp; 
                           
                           //
                           
                           //setting values to table
                           model.addRow(new Object[]{ 
                              line1,statement1 ,keywords1,printCs,printCi,printCt,printTw,printCps,printCp
                            });
                           
                           
            }              //display. 
              
                    //setting values to display
                    
                    
                    
                    
                           
                            lineCsBox.setText(Integer.toString(TCsCount));
                            lineCiBox.setText(Integer.toString(TCiCount));
                            lineCtBox.setText(Integer.toString(TCtCount));
                            totalCrBox.setText(Integer.toString(TCrCount));
                            lineTwBox.setText(Integer.toString(TwCount));
                            totalCpsBox.setText(Integer.toString(CpsCount));
                            totalCpBox.setText(Integer.toString(CpCount));
                            
                            
                            
                            
                    //System.out.println("Comprehensive Cs value   : " + TCs);

                    // Always close files.
                    bufferedReader.close();
                
                
                
                
                }
                
                }
                
            } catch (UnsupportedFlavorException ex) {
                Logger.getLogger(spmFull.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(spmFull.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        dragContainer = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lineCsBox = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        display = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lineCiBox = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lineCtBox = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lineTwBox = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        totalCpsBox = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        totalCrBox = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        totalCpBox = new javax.swing.JLabel();
        breport2 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "line No", "Statement", "keywords", "Cs"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Code Complexity Measurement  Tool - Size");
        setBackground(new java.awt.Color(51, 51, 51));

        dragContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText(" Drag and drop your java /c++ codes(.java, .txt formats are supported)");

        jLabel2.setText(">>>>>>>>>>> Code Complexity  -  Summary <<<<<<<<<<");
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabel6.setText("Total Size Complxity");
        jLabel6.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel6.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel6.setPreferredSize(new java.awt.Dimension(4, 10));

        lineCsBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lineCsBox.setMaximumSize(new java.awt.Dimension(4, 6));
        lineCsBox.setMinimumSize(new java.awt.Dimension(4, 6));
        lineCsBox.setPreferredSize(new java.awt.Dimension(4, 6));

        display.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "lineNoBox", "statementBox", "keyWordBox", "CsBox"
            }
        ));
        display.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(display);
        if (display.getColumnModel().getColumnCount() > 0) {
            display.getColumnModel().getColumn(0).setPreferredWidth(10);
            display.getColumnModel().getColumn(1).setPreferredWidth(40);
            display.getColumnModel().getColumn(2).setPreferredWidth(50);
            display.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        jLabel8.setText("File Name:");

        jLabel10.setText("File Type:");

        jLabel12.setText("File Path:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel20.setText("Total Inheritance Complxity");
        jLabel20.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel20.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel20.setPreferredSize(new java.awt.Dimension(4, 10));

        lineCiBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lineCiBox.setMaximumSize(new java.awt.Dimension(4, 6));
        lineCiBox.setMinimumSize(new java.awt.Dimension(4, 6));
        lineCiBox.setPreferredSize(new java.awt.Dimension(4, 6));

        jLabel23.setText("Tota Control Structure & Nesting Complxity");
        jLabel23.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel23.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel23.setPreferredSize(new java.awt.Dimension(4, 10));

        lineCtBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lineCtBox.setMaximumSize(new java.awt.Dimension(4, 6));
        lineCtBox.setMinimumSize(new java.awt.Dimension(4, 6));
        lineCtBox.setPreferredSize(new java.awt.Dimension(4, 6));

        jLabel24.setText("Total Weight");
        jLabel24.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel24.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel24.setPreferredSize(new java.awt.Dimension(4, 10));

        lineTwBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lineTwBox.setMaximumSize(new java.awt.Dimension(4, 6));
        lineTwBox.setMinimumSize(new java.awt.Dimension(4, 6));
        lineTwBox.setPreferredSize(new java.awt.Dimension(4, 6));

        jLabel25.setText(" Complexity of a program statement");
        jLabel25.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel25.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel25.setPreferredSize(new java.awt.Dimension(4, 10));

        totalCpsBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        totalCpsBox.setMaximumSize(new java.awt.Dimension(4, 6));
        totalCpsBox.setMinimumSize(new java.awt.Dimension(4, 6));
        totalCpsBox.setPreferredSize(new java.awt.Dimension(4, 6));

        jLabel26.setText("Recursion Complexity");
        jLabel26.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel26.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel26.setPreferredSize(new java.awt.Dimension(4, 10));

        totalCrBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        totalCrBox.setMaximumSize(new java.awt.Dimension(4, 6));
        totalCrBox.setMinimumSize(new java.awt.Dimension(4, 6));
        totalCrBox.setPreferredSize(new java.awt.Dimension(4, 6));

        jLabel27.setText("TOTAL PROGRAM COMPLEXITY");
        jLabel27.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel27.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel27.setPreferredSize(new java.awt.Dimension(4, 10));

        totalCpBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        totalCpBox.setMaximumSize(new java.awt.Dimension(4, 6));
        totalCpBox.setMinimumSize(new java.awt.Dimension(4, 6));
        totalCpBox.setPreferredSize(new java.awt.Dimension(4, 6));

        breport2.setText("Report");
        breport2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                breport2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(totalCpBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(totalCrBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(totalCpsBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lineCsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addGap(21, 21, 21)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(dragContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel10))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(7, 7, 7)
                                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGap(8, 8, 8)))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(lineCiBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lineTwBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(33, 33, 33)
                                    .addComponent(lineCtBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(breport2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dragContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel10)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lineCiBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lineCtBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lineTwBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lineCsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalCpsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalCrBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalCpBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(65, 65, 65)
                        .addComponent(breport2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void breport2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_breport2ActionPerformed
        // TODO add your handling code here:
        String inherit=lineCiBox.getText();
        String nesting=lineCtBox.getText();
        String totweight=lineTwBox.getText();
        String totsize=lineCsBox.getText();
        String totstatement=totalCpsBox.getText();
        String recursive=totalCrBox.getText();
        String totalcomplex=totalCpBox.getText();
        //        String value2=surnamebox.getText();
        //        String value3=idbox.getText();
        //        String value4=designationbox.getText();
        //        String value5=dobbox.getText();
        //        String value6=hiredbox.getText();

        /*JFileChooser dialog =new JFileChooser();
        dialog.setSelectedFile(new File("Code Complexity"+".pdf"));
        int dialogresult=dialog.showSaveDialog(null);
        if(dialogresult==JFileChooser.APPROVE_OPTION){

            String filepath=dialog.getSelectedFile().getPath();

            try{

                Document document=new Document();
                PdfWriter.getInstance(document,new FileOutputStream(filepath));

                document.open();
                document.add(new Paragraph("Code Complexity",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD)));
                document.add(new Paragraph(new Date().toString()));
                document.add(new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD)));
                document.add(new Paragraph("Code Complexity due to inheritance",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Code complexity : "+inherit,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                //document.add(new Paragraph("Code complexity for C++ : "+value2,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Code Complexity due to Control structure",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Code complexity: "+nesting,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Code Complexity due to Size",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Code complexity : "+totsize,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Total Weight",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Code complexity : "+totweight,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Complexity of program statement",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Code complexity : "+totstatement,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Complexity due to recursive",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Code complexity : "+recursive,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Total Complexity",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Total Complexity  : "+totalcomplex,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("---------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.close();
                JOptionPane.showMessageDialog(null, "Report was successfully generated");

            }catch(DocumentException | HeadlessException | FileNotFoundException | NumberFormatException e){
                JOptionPane.showMessageDialog(null, e);
            }finally{
                try{

                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "sssssssssssssss");
                }
            }

        }*/
    }//GEN-LAST:event_breport2ActionPerformed

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
            java.util.logging.Logger.getLogger(spmFull.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(spmFull.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(spmFull.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(spmFull.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new spmFull().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton breport2;
    private javax.swing.JTable display;
    private javax.swing.JLabel dragContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lineCiBox;
    private javax.swing.JLabel lineCsBox;
    private javax.swing.JLabel lineCtBox;
    private javax.swing.JLabel lineTwBox;
    private javax.swing.JLabel totalCpBox;
    private javax.swing.JLabel totalCpsBox;
    private javax.swing.JLabel totalCrBox;
    // End of variables declaration//GEN-END:variables
}
