package ngc.util.wuc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;

public class WucMappingFileWriter {
   private final char NEW_LINE = '\n';
   private final char COMMA = ',';
   public static final String FILE_EXTENSION = ".csv";
   private FileWriter fileWriter;

   public WucMappingFileWriter(String fileName) {
      File wucMappingFile = new File(fileName);
      if (wucMappingFile.exists()) {
         wucMappingFile.delete();
      }
      try {
         wucMappingFile.createNewFile();
         fileWriter = new FileWriter(wucMappingFile);
      } catch (IOException e) {
         close();
         e.printStackTrace();
      }
      setUpFirstRowFor(wucMappingFile);

   }

   private void setUpFirstRowFor(File wucMappingFile) {
      List<String> columnHeaders = Lists.newArrayList("WUC", "POS", "WUCPubId", "WUCLaunchPoints");
      writeValues(columnHeaders);
   }

   public void writeValues(List<String> entry) {
      try {
         for (int i = 0; i < entry.size(); i++) {
            fileWriter.write(entry.get(i));
            if (i < entry.size() - 1) {
               fileWriter.write(COMMA);
            }
         }
         fileWriter.write(NEW_LINE);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void printEntriesForIpdFile(IpdModel ipdFile) {
      String removeProcedureFileName = PubFileNameUtils.replaceIpdDesignatorWithRemoveDesignator(ipdFile.getFileName());
      String installProcedureFileName = PubFileNameUtils
            .replaceIpdDesignatorWithInstallDesignator(ipdFile.getFileName());
      for (WucPosEntry wucPosEntry : ipdFile.getWucsThatMatchPartNumbers()) {
         writeToFile(ipdFile, removeProcedureFileName, wucPosEntry);
         writeToFile(ipdFile, installProcedureFileName, wucPosEntry);
      }
   }

   public void writeToFile(IpdModel ipdFile, String procedureFileName, WucPosEntry wucPosEntry) {
      String procedureName = PubFileNameUtils.removeVersionAndExtension(procedureFileName);
      List<String> entry = Lists.newArrayList(wucPosEntry.getWuc(), wucPosEntry.getPosition(),
            getParentDirectoryNameFor(ipdFile), procedureName);
      writeValues(entry);
   }

   private String getParentDirectoryNameFor(IpdModel ipdFile) {
      String ipdParentDirectoryPath = ipdFile.getParentDirectoryPath();
      if (ipdParentDirectoryPath.contains("/")) {
         return ipdParentDirectoryPath.substring(ipdParentDirectoryPath.lastIndexOf("/") + 1,
               ipdParentDirectoryPath.length());
      } else if (ipdParentDirectoryPath.contains("\\")) {
         return ipdParentDirectoryPath.substring(ipdParentDirectoryPath.lastIndexOf("\\") + 1,
               ipdParentDirectoryPath.length());
      } else {
         return ipdParentDirectoryPath;
      }
   }

   public void close() {
      try {
         fileWriter.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}
