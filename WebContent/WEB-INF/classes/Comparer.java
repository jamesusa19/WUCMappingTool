/*
*******************************UNCLASSIFIED********************************
***************************************************************************
***                                                                     ***
***  U   U NN   N  CCCC L      AAA   SSSS  SSSS I FFFFF I EEEEE DDDD    ***
***  U   U N N  N C     L     A   A S     S     I F     I E     D   D   ***
***  U   U N  N N C     L     AAAAA  SSS   SSS  I FFFF  I EEEE  D   D   ***
***  U   U N   NN C     L     A   A     S     S I F     I E     D   D   ***
***   UUU  N    N  CCCC LLLLL A   A SSSS  SSSS  I F     I EEEEE DDDD    ***
***                                                                     ***
***************************************************************************
*******************************UNCLASSIFIED********************************
        Contractor Name: Northrop Grumman Corporation
        Contractor Address: 6635 E 21st St, Indianapolis, IN 46219        
 */

package ngc.util.wuc;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Compares WucBaselineData with ipdFile partNumbers to find partNumber matches.
 *
 */
public class Comparer {
   WucBaselineData wucBaseline;
   IpdParser ipdParser;
   
   public Comparer(WucBaselineData wucBaseline){
      this.wucBaseline = wucBaseline;
      this.ipdParser = new IpdParser();
   }
   
   /**
    * What the method does
    * 
    * @param ipdFile - 
    * @return List<WucPosEntry>
    */
   public List<WucPosEntry> findAllWucEntriesForFile(IpdModel ipdFile){
      List<String> partNumbersFromFile = ipdParser.setPartNumbersForFile(ipdFile).getPartNumbers();
      List<WucPosEntry> wucList = Lists.newArrayList();
      for(String partNumber: partNumbersFromFile){
         List<WucPosEntry> wucsPosEntriesForPartNumber = wucBaseline.getWucListByPartNumber(partNumber);
         if(wucsPosEntriesForPartNumber.size() != 0){
            for(WucPosEntry wucPosEntry: wucsPosEntriesForPartNumber){
               if(!wucList.contains(wucPosEntry)){
                  wucList.add(wucPosEntry);
               }
            }
         }
      }
      return wucList;
   }
   
}
