import java.io.*;

public class FileRead {
       public static void main(String[] args) {
             int Cs = 0;
             // The name of the file to open.
             //E:\\SPMprojectFiles\\test.txt
             String fileName = "C:\\Users\\GTC\\Documents\\NetBeansProjects\\spmSize\\src\\test1.java";

             // This will reference one line at a time
             String line = null;
             String[] keywords = new String[] { "import", "void", "double", "int", "float", "String", "println", "cout",
                           "if", "for", "while", "switch", "case", "\n", "endl" };
             int keywordSize = keywords.length;
             System.out.println("keywordSize :" + keywordSize);

             String[] ariOperators = new String[] { "+", "-", "*", "/", "%", "++", "--" };
             int aoSize = ariOperators.length;
             System.out.println("aoSize :" + aoSize);

             String[] relOperators = new String[] { "==", "!=", ">", "<", ">=", "<=" };
             int roSize = relOperators.length;
             System.out.println("roSize :" + roSize);

             String[] logicOperators = new String[] { "&&", "||", "!" };
             int loSize = logicOperators.length;
             System.out.println("loSize :" + loSize);

             String[] assignOperators = new String[] { "+=", "-=", "*=", "/=", ">>>=", "|=", "&=", "<<=", ">>=", "%=",
                           "^=" };
             int asgnSize = assignOperators.length;
             System.out.println("asgnSize :" + asgnSize);

             String[] bitwiseOperators = new String[] { "|", "^", "~", "<<", ">>", "<<<", ">>>" };
             int bitSize = bitwiseOperators.length;
             System.out.println("bitSize :" + bitSize);

             try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader = new FileReader(fileName);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    int count = 1;
                    while ((line = bufferedReader.readLine()) != null) {

                           for (int i = 0; i < keywordSize; i++) {
                                 if (line.contains(keywords[i])) {
                                        System.out.println("containss keywords ....................." + keywords[i]);
                                        Cs++;
                                 }
                           }

                           for (int j = 0; j < aoSize; j++) {
                                 int a = 0;
                                 String[] split = line.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(ariOperators[j])) {
                                               System.out.println("containss ariOperators ....................." + ariOperators[j]);
                                               Cs++;
                                        }
                                 }
                           }
                           
                           for (int k = 0; k < roSize; k++) {
                                 int a = 0;
                                 String[] split = line.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(relOperators[k])) {
                                               System.out.println("containss relOperators ....................." + relOperators[k]);
                                               Cs++;
                                        }
                                 }
                           }

                           for (int k = 0; k < loSize; k++) {
                                 int a = 0;
                                 String[] split = line.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(logicOperators[k])) {
                                               System.out.println("containss logicOperators ....................." + logicOperators[k]);
                                               Cs++;
                                        }
                                 }
                           }

                           for (int k = 0; k < asgnSize; k++) {
                                 int a = 0;
                                 String[] split = line.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(assignOperators[k])) {
                                               System.out.println("containss assignOperators ....................." + assignOperators[k]);
                                               Cs++;
                                        }
                                 }
                           }

                           for (int k = 0; k < bitSize; k++) {
                                 int a = 0;
                                 String[] split = line.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(bitwiseOperators[k])) {
                                               System.out
                                                            .println("containss bitwiseOperators ....................." + bitwiseOperators[k]);
                                               Cs++;
                                        }
                                 }
                           }

                           /*
                           * for (int z = 0; z < operaters.length; z++) { int sizeFor = sizes[z].length();
                           * String operator = operaters[z]
                           * 
                            * for (int k = 0; k < sizeFor; k++) { int a = 0; String[] split =
                           * line.split(" ");
                           * 
                            * for (a = 0; a < split.length; a++) { if
                           * (split[a].equals(bitwiseOperators[k])) { System.out
                           * .println("containss bitwiseOperators ....................." +
                           * bitwiseOperators[k]); } }
                           * 
                            * } }
                           */

                           System.out.println(count + " - " + line + " ");
                           System.out.println("Cs value   : " + Cs);
                           count++;
                    }
                    System.out.println("Cs value   : " + Cs);

                    // Always close files.
                    bufferedReader.close();
             } catch (FileNotFoundException ex) {
                    System.out.println("Unable to open file '" + fileName + "'");
             } catch (IOException ex) {
                    System.out.println("Error reading file '" + fileName + "'");
                    // Or we could just do this:
                    // ex.printStackTrace();
             }
       }
}

//C:/Users/Dew/Desktop/test.txt
//FileRead
