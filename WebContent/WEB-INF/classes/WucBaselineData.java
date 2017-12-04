package ngc.util.wuc;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class WucBaselineData {
   private HashMap<String, List<WucPosEntry>> partNumberToWucEntryMap;
   

   public WucBaselineData(String fileLocation){
      partNumberToWucEntryMap = Maps.newHashMap();
      generateForFile(fileLocation);
   }
   
   private void generateForFile(String fileLocation){
      TabDelimitedWucBaselineParser parser = new TabDelimitedWucBaselineParser();
      this.partNumberToWucEntryMap = parser.createPartNumberToWucHashMapFor(fileLocation);
   }
   
   public List<WucPosEntry> getWucListByPartNumber(String partNumber){
      List<WucPosEntry> wucList = partNumberToWucEntryMap.get(partNumber);
      if(wucList != null){
         return wucList;
      } else {
         return Lists.newArrayList();
      }
   }
}
