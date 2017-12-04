package ngc.util.wuc;

import java.util.List;

import com.google.common.collect.Lists;

public class IpdModel {
   private String parentDirectoryPath;
   private String fileName;
   private List<String> partNumbers;
   private List<WucPosEntry> wucsThatMatchPartNumbers;
   
   public IpdModel(String fileName){
      this.fileName = fileName;
      this.partNumbers = Lists.newArrayList();
      this.wucsThatMatchPartNumbers = Lists.newArrayList();
   }

   public String getFileName() {
      return this.fileName;
   }
   
   public void setPartNumbers(List<String> partNumbers) {
      this.partNumbers = partNumbers;
   }

   public List<String> getPartNumbers() {
      return this.partNumbers;
   }

   public String getParentDirectoryPath() {
      return parentDirectoryPath;
   }

   public IpdModel setParentDirectoryPath(String parentDirectoryPath) {
      this.parentDirectoryPath = parentDirectoryPath;
      return this;
   }

   public List<WucPosEntry> getWucsThatMatchPartNumbers() {
      return wucsThatMatchPartNumbers;
   }

   public void setWucsThatMatchPartNumbers(List<WucPosEntry> wucs) {
      this.wucsThatMatchPartNumbers = wucs;
   }

}
