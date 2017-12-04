package ngc.util.wuc;

import java.util.List;

import com.google.common.collect.Lists;

public class PubTypeOrganizer {
   private List<String> ipdFileNameList = Lists.newArrayList();
   private List<String> installAndRemoveFileNamesList = Lists.newArrayList();
   private String directory = "";

   public PubTypeOrganizer seperatePubTypesForDirectory(String directory) {
      this.directory = directory;
      FileLister lister = new FileLister(directory);
      List<String> fileNames = lister.getNamesOfFiles();
      for(String fileName: fileNames){
         if(fileName.contains("941")){
            ipdFileNameList.add(fileName);
         } else if(fileName.contains("520") || fileName.contains("720")){
            installAndRemoveFileNamesList.add(fileName); 
         }
      }
      return this;
   }
   
   public List<String> getIpdFileNameList(){
      return ipdFileNameList;
   }

   public List<String> getInstallAndRemoveFileNameList() {
      return installAndRemoveFileNamesList;
   }

   public List<String> getInstallAndRemoveFilesWithoutAssociatedIpdFile() {
      List<String> fileNamesWithoutAssociatedIPD = Lists.newArrayList();
      List<String> ipdFilesWithoutVersions = getIpdFileNamesWithoutVersions();
      for(String fileName: installAndRemoveFileNamesList){
         String fileNameWithoutVersionAs941 = turnInstallOrRemoveIntoIpdFileName(fileName);
         if(!ipdFilesWithoutVersions.contains(fileNameWithoutVersionAs941)){
            fileNamesWithoutAssociatedIPD.add(fileName);
         }
      }
      return fileNamesWithoutAssociatedIPD;
   }

   private List<String> getIpdFileNamesWithoutVersions() {
      List<String> ipdFileNamesWithoutVersions = Lists.newArrayList();
      for(String name: ipdFileNameList){
         ipdFileNamesWithoutVersions.add(PubFileNameUtils.removeVersionAndExtension(name));
      }
      return ipdFileNamesWithoutVersions;
   }

   private String turnInstallOrRemoveIntoIpdFileName(String fileName) {
      fileName = PubFileNameUtils.removeVersionAndExtension(fileName);
      fileName = PubFileNameUtils.replaceInstallOrRemoveDesignatorWithIpdDesignator(fileName);
      return fileName;
   }

   public String getDirectoryName() {
      return directory;
   }
}
