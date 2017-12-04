package ngc.util.wuc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WucMappingGeneratorLogger {
   private final char NEW_LINE = '\n';
   private FileWriter fileWriter;
   
   public WucMappingGeneratorLogger(String outputFileName){
      File logFile = new File(outputFileName);
      if (logFile.exists()) {
         logFile.delete();
      }
      try {
         logFile.createNewFile();
         fileWriter = new FileWriter(logFile);
      } catch (IOException e) {
         if (fileWriter != null) {
            try {
               fileWriter.close();
            } catch (IOException e1) {
               e1.printStackTrace();
            }
         }
         e.printStackTrace();
      }
      setUpFirstRowFor(logFile);
   }

   private void setUpFirstRowFor(File logFile) {
      try {
         fileWriter.write("FileName,ParentDirectory");
         fileWriter.write(NEW_LINE);
      } catch (IOException e) {
         e.printStackTrace();
      }
      
   }

   public void logFilesNotCoveredInWucMappingFor(PubTypeOrganizer pubTypeOrganizer) {
      List<String> fileNamesToBeLogged = pubTypeOrganizer.getInstallAndRemoveFilesWithoutAssociatedIpdFile();
      String parentDirectory = pubTypeOrganizer.getDirectoryName();
      for(String fileName: fileNamesToBeLogged){
         try {
            fileWriter.write(fileName + "," + parentDirectory);
            fileWriter.write(NEW_LINE);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
   
   public void close(){
      try {
         fileWriter.close();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

}
