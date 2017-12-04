package ngc.util.wuc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class TabDelimitedWucBaselineParser {
   private final String wucColumnName = "WUC";
   private final String partNoColumnName = "PartNo";
   private final String positionCodeColumnName = "Position Code";
   private final String rowDelimiter = "\t";
   
   private int wucColumnNumber = -1;
   private int partNoColumnNumber = -1;
   private int positionCodeColumnNumber = -1;
   private HashMap<String, List<WucPosEntry>> partNumberToWucMap;
   
   
   public TabDelimitedWucBaselineParser(){
      this.partNumberToWucMap = Maps.newHashMap();
   }
   
   public HashMap<String, List<WucPosEntry>> createPartNumberToWucHashMapFor(String fileLocation){
      BufferedReader reader = makeBufferedReader(fileLocation);
      setColumnValues(reader);
      populatePartNumberToWucHash(reader);
      try {
         reader.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return this.partNumberToWucMap;
   }
   
   private BufferedReader makeBufferedReader(String fileLocation) {
      FileReader fileReader = null;
      try {
         fileReader = new FileReader(fileLocation);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      return new BufferedReader(fileReader);
   }
   
   private void setColumnValues(BufferedReader reader) {
      while (wucColumnNumber == -1 || partNoColumnNumber == -1 || positionCodeColumnNumber == -1) {
         try {
            String line = reader.readLine();
            assignColumnValuesIfWucOrPartNumberFoundInRow(line.split(rowDelimiter));
         } catch (IOException e) {
            e.printStackTrace();
            break;
         } 
      }
   }

   private void assignColumnValuesIfWucOrPartNumberFoundInRow(String[] rowValues) {
      for (int i = 0; i < rowValues.length; i++) {
         if (rowValues[i].equals(wucColumnName)) {
            setWucColumnNumber(i);
            
         } else if (rowValues[i].equals(partNoColumnName)) {
            setPartNoColumnNumber(i);
         } else if (rowValues[i].equals(positionCodeColumnName)){
            setPositionCodeColumnNumber(i);
         }
      }
   }
   
   private void setWucColumnNumber(int columnNumber) {
      this.wucColumnNumber = columnNumber;
   }
   
   private void setPartNoColumnNumber(int columnNumber){
      this.partNoColumnNumber = columnNumber;
   }
   
   private void setPositionCodeColumnNumber(int columnNumber){
      this.positionCodeColumnNumber = columnNumber;
   }
   
   private HashMap<String, List<WucPosEntry>> populatePartNumberToWucHash(BufferedReader bufferedReader) {
      try {
         String line = bufferedReader.readLine();
         while(line != null){
            String[] values = line.split(rowDelimiter);
            if(values.length > wucColumnNumber && values.length > partNoColumnNumber){
               addPartNumberWucEntryPairToMap(values);
            }
            line = bufferedReader.readLine();
         }
      } catch (IOException e){
         
      }
      return this.partNumberToWucMap;
   }

   private void addPartNumberWucEntryPairToMap(String[] values){
      List<WucPosEntry> list;
      if(partNumberToWucMap.get(values[partNoColumnNumber]) != null){
         list = partNumberToWucMap.get(values[partNoColumnNumber]);
         if(!listContainsWucPosEntry(list, new WucPosEntry(values[wucColumnNumber], values[positionCodeColumnNumber]))){
            list.add(new WucPosEntry(values[wucColumnNumber], values[positionCodeColumnNumber]));
         }
      } else {
         list = Lists.newArrayList();
         list.add(new WucPosEntry(values[wucColumnNumber], values[positionCodeColumnNumber]));
         partNumberToWucMap.put(values[partNoColumnNumber], list);
      }
   }

   private boolean listContainsWucPosEntry(List<WucPosEntry> list, WucPosEntry wucPosEntry) {
      for(WucPosEntry wucPosEntryInList: list){
         if(wucPosEntry.equals(wucPosEntryInList)){
            return true;
         }
      }
      return false;
   }
}
