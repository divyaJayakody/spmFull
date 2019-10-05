/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spmsize;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author GTC
 */
public class dragContainer extends javax.swing.JFrame {

    static void setIcon(ImageIcon imageIcon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

                 int TCs = 0;
                 int TCi= 0;
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
    public dragContainer() {
        initComponents();
        modifyLabel();
        model = new DefaultTableModel();
        display.setModel(model);
        model.addColumn("Line");
        model.addColumn("Statement");
        model.addColumn("Key words");
        model.addColumn("Cs");
        model.addColumn("Ci");

        display.getColumn("Line").setMaxWidth(30);
        display.getColumn("Statement").setMaxWidth(250);
        display.getColumn("Key words").setMaxWidth(400);
        display.getColumn("Cs").setMaxWidth(30);
        display.getColumn("Ci").setMaxWidth(30);
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
                        int Cs =0;
                        int Ci =0;
                        
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
                               
                                Ci = implClass.length;
                                for (int a = 0; a < implClass.length; a++) {
                                    System.out.println("contains implementaions....................." + implClass[a]);
                                
                                }
                                TCi++;
                                ccount++;
                                printQ.add(matcher10.group());
                            }
                               
                            
                             Pattern pattern11 = Pattern.compile("(?<=implements).*"); 
                            Matcher matcher11 = pattern11.matcher(line);
                            
                            while (matcher10.find()) { 
                                                         
                                System.out.println("contains parent classes....................." + matcher11.group());
                                String s = matcher10.group(); 
                                s =s.replaceAll("[,]"," ").replaceAll("[{]"," ").replaceAll("[.]"," ");
                                String[] extClass = s.split(" ");
                                Ci = extClass.length;
                                for (int a = 0; a < extClass.length; a++) {
                                    System.out.println("contains parent classes....................." + extClass[a]);
                                
                                }
                                TCi++;
                                ccount++;
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
                                        Cs++;
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
                                Cs++;
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
                               
                                Cs+=1;
                                TCs+=1;
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
                                               Cs++;
                                               TCs++;
                                               arithOCount++; 
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
                                               Cs++;
                                               TCs++;
                                               relatOCount++; 
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
                                               Cs++;
                                               TCs++;
                                               logicOCount++; 
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
                                               Cs++;
                                               TCs++;
                                               assOCount++;
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
                                               Cs++;
                                               TCs++;
                                               bitwOCount++;
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
                                               Cs+=2;
                                               TCs+=2;
                                               cppCount++;
                                               printQ.add(split[a]);
                                        }
                                 
                                }
                           }        
                
                           
                            
                        /********Loop for manip operators**********/   
                           for (int i = 0; i < manipSize; i++) {
                                 if (line.contains(manipulators[i])) {
                                        System.out.println("containss manipulators ....................." + manipulators[i]);
                                        Cs++;
                                        manipCount++;
                                        TCs++;
                                        printQ.add(manipulators[i]);
                                 }
                           }
                          
                        /********Loop for misc operators**********/     
                           for (int i = 0; i < miscOSize; i++) {
                                 if (line.contains(miscellaneous[i])) {
                                        System.out.println("containss misc operators ....................." + miscellaneous[i]);
                                        Cs++;
                                        TCs++;
                                        miscOCount++;
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
                             //check for "Catch"
                           }else if(line.contains(condition1[3])){
                               incrementForCatch++;
                               System.out.println("Count value is " + incrementForCatch );
                               
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
                           }

                       }
          
                           
                           
                          
                           System.out.println("Line wise Ci value   : " + Ci);
                           System.out.println("Line wise Cs value   : " + Cs);
                           System.out.println("Total Cs value   : " + TCs);
                           
                           //convert array list to String
                           String printlist = String.join(", ", printQ);
                           System.out.println("complete line   : " +printlist);
                           printQ.clear();
                           
                           int line1 = lineCounter;
                           String statement1 = displayLine;
                           String keywords1 = printlist;
                           int Cs1 = Cs;
                           int Ci1 = Ci;
                           Cs = 0;
                           Ci = 0;
                           
                           //setting values to table
                           model.addRow(new Object[]{ 
                              line1,statement1 ,keywords1,Cs1,Ci1 
                            });
                           
                           
            }              //display. 
              
                    //setting values to display
                            keywordBox.setText(Integer.toString(keywordsCount));
                            arithOBox.setText(Integer.toString(arithOCount));
                            relatOBox.setText(Integer.toString(relatOCount));
                            logicOBox.setText(Integer.toString(logicOCount));
                            assignOBox.setText(Integer.toString(assOCount));
                            bitwOBox.setText(Integer.toString(bitwOCount));
                            manipOBox.setText(Integer.toString(manipCount));
                            miscOBox.setText(Integer.toString(miscOCount));
                            numberBox.setText(Integer.toString(numberCount));
                            textOBox.setText(Integer.toString(textCount));
                            specialBox.setText(Integer.toString(specialCount));
                            totalCsBox.setText(Integer.toString(TCs));
                            
                            
                    System.out.println("Comprehensive Cs value   : " + TCs);

                    // Always close files.
                    bufferedReader.close();
                
                
                
                
                }
                
                }
                
            } catch (UnsupportedFlavorException ex) {
                Logger.getLogger(dragContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(dragContainer.class.getName()).log(Level.SEVERE, null, ex);
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
        textOBox = new javax.swing.JLabel();
        numberBox = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        totalCsBox = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        display = new javax.swing.JTable();
        specialBox = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

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

        jLabel2.setText(">>>>>>>>>>>  Size Complexity  -  Summary <<<<<<<<<<");
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabel3.setText("Key Word Count ----------------------");
        jLabel3.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel3.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel3.setPreferredSize(new java.awt.Dimension(4, 10));

        jLabel5.setText("Arithmatic Operators----------------");
        jLabel5.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel5.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel5.setPreferredSize(new java.awt.Dimension(4, 10));

        keywordBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        keywordBox.setMaximumSize(new java.awt.Dimension(4, 6));
        keywordBox.setMinimumSize(new java.awt.Dimension(4, 6));
        keywordBox.setPreferredSize(new java.awt.Dimension(4, 6));

        jLabel7.setText("Relational Operators-----------------");
        jLabel7.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel7.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel7.setPreferredSize(new java.awt.Dimension(4, 10));

        jLabel9.setText("Logical Operators---------------------");
        jLabel9.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel9.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel9.setPreferredSize(new java.awt.Dimension(4, 10));

        jLabel11.setText("Bitwise Operators---------------------");
        jLabel11.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel11.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel11.setPreferredSize(new java.awt.Dimension(4, 10));

        jLabel13.setText("Miscellaneous Operators-------------");
        jLabel13.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel13.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel13.setPreferredSize(new java.awt.Dimension(4, 10));

        jLabel15.setText("Assignment Operators----------------");
        jLabel15.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel15.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel15.setPreferredSize(new java.awt.Dimension(4, 10));

        jLabel17.setText("Manipulators---------------------------");
        jLabel17.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel17.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel17.setPreferredSize(new java.awt.Dimension(4, 10));

        jLabel19.setText("Text within double quotes-----------");
        jLabel19.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel19.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel19.setPreferredSize(new java.awt.Dimension(4, 10));

        jLabel21.setText("Numbers -------------------------------");
        jLabel21.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel21.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel21.setPreferredSize(new java.awt.Dimension(4, 10));

        arithOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        arithOBox.setMaximumSize(new java.awt.Dimension(4, 6));
        arithOBox.setMinimumSize(new java.awt.Dimension(4, 6));
        arithOBox.setPreferredSize(new java.awt.Dimension(4, 6));

        relatOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        relatOBox.setMaximumSize(new java.awt.Dimension(4, 6));
        relatOBox.setMinimumSize(new java.awt.Dimension(4, 6));
        relatOBox.setPreferredSize(new java.awt.Dimension(4, 6));

        logicOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        logicOBox.setMaximumSize(new java.awt.Dimension(4, 6));
        logicOBox.setMinimumSize(new java.awt.Dimension(4, 6));
        logicOBox.setPreferredSize(new java.awt.Dimension(4, 6));

        bitwOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        bitwOBox.setMaximumSize(new java.awt.Dimension(4, 6));
        bitwOBox.setMinimumSize(new java.awt.Dimension(4, 6));
        bitwOBox.setPreferredSize(new java.awt.Dimension(4, 6));

        miscOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        miscOBox.setMaximumSize(new java.awt.Dimension(4, 6));
        miscOBox.setMinimumSize(new java.awt.Dimension(4, 6));
        miscOBox.setPreferredSize(new java.awt.Dimension(4, 6));

        assignOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        assignOBox.setMaximumSize(new java.awt.Dimension(4, 6));
        assignOBox.setMinimumSize(new java.awt.Dimension(4, 6));
        assignOBox.setPreferredSize(new java.awt.Dimension(4, 6));

        manipOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        manipOBox.setMaximumSize(new java.awt.Dimension(4, 6));
        manipOBox.setMinimumSize(new java.awt.Dimension(4, 6));
        manipOBox.setPreferredSize(new java.awt.Dimension(4, 6));

        textOBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        textOBox.setMaximumSize(new java.awt.Dimension(4, 6));
        textOBox.setMinimumSize(new java.awt.Dimension(4, 6));
        textOBox.setPreferredSize(new java.awt.Dimension(4, 6));

        numberBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        numberBox.setMaximumSize(new java.awt.Dimension(4, 6));
        numberBox.setMinimumSize(new java.awt.Dimension(4, 6));
        numberBox.setPreferredSize(new java.awt.Dimension(4, 6));

        jLabel6.setText("TOTAL COMPLEXITY ");
        jLabel6.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel6.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel6.setPreferredSize(new java.awt.Dimension(4, 10));

        totalCsBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        totalCsBox.setMaximumSize(new java.awt.Dimension(4, 6));
        totalCsBox.setMinimumSize(new java.awt.Dimension(4, 6));
        totalCsBox.setPreferredSize(new java.awt.Dimension(4, 6));

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

        specialBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        specialBox.setMaximumSize(new java.awt.Dimension(4, 6));
        specialBox.setMinimumSize(new java.awt.Dimension(4, 6));
        specialBox.setPreferredSize(new java.awt.Dimension(4, 6));

        jLabel22.setText("Special Characters ------------------");
        jLabel22.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel22.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel22.setPreferredSize(new java.awt.Dimension(4, 10));

        jLabel8.setText("File Name:");

        jLabel10.setText("File Type:");

        jLabel12.setText("File Path:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

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
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(keywordBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(arithOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(relatOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(logicOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bitwOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(miscOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numberBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(assignOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(manipOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(specialBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5))
                            .addComponent(totalCsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(keywordBox, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arithOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relatOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logicOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bitwOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(miscOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(assignOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(manipOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textOBox, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numberBox, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(specialBox, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalCsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(dragContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dragContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dragContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dragContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dragContainer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arithOBox;
    private javax.swing.JLabel assignOBox;
    private javax.swing.JLabel bitwOBox;
    private javax.swing.JTable display;
    private javax.swing.JLabel dragContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel keywordBox;
    private javax.swing.JLabel logicOBox;
    private javax.swing.JLabel manipOBox;
    private javax.swing.JLabel miscOBox;
    private javax.swing.JLabel numberBox;
    private javax.swing.JLabel relatOBox;
    private javax.swing.JLabel specialBox;
    private javax.swing.JLabel textOBox;
    private javax.swing.JLabel totalCsBox;
    // End of variables declaration//GEN-END:variables
}
