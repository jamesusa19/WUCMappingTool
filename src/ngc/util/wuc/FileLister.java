package ngc.util.wuc;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;

public class FileLister {
   private final String directory;

   public FileLister(String directoryToBeListed){
      this.directory = directoryToBeListed;
   }

   public List<String> getNamesOfFiles() {
      File folder = new File(directory); 
      List<String> listOfFileNames = Lists.newArrayList();
      File[] arrayOfFileNames = folder.listFiles();
      for(File file: arrayOfFileNames){
         if(file.isFile()){
            listOfFileNames.add(file.getName());
         }
      }
      return listOfFileNames;
   }
}
