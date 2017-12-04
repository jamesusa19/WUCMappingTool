package ngc.util.wuc;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.common.collect.Lists;

public class IpdParser {
   private Document document;

   public IpdParser (){
   }
   
   public IpdModel setPartNumbersForFile(IpdModel ipdFile){
      File file = createFileFor(ipdFile);
      createDocumentFor(file);
      ipdFile.setPartNumbers(getPartsNumbers());
      return ipdFile;
   }

   private File createFileFor(IpdModel ipdFile) {
      if(ipdFile.getParentDirectoryPath() != null){
         return new File(ipdFile.getParentDirectoryPath() + "/" +ipdFile.getFileName());
      } else {
         return new File(ipdFile.getFileName());
      }
   }

   private void createDocumentFor(File file) {
      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         document = builder.parse(file);
         } catch (Exception e){
            System.out.println("There was an exception while setting part numbers: \n" + e);
         }
   }
   
   public List<String> getPartsNumbers() {
      NodeList partNodes = getPartsFromFile();
      List<String> partNumbers = populatePartsList(partNodes);
      return partNumbers;
   }
   
   protected NodeList getPartsFromFile() {
      return document.getElementsByTagName("csn");
   }

   private List<String> populatePartsList(NodeList partNodes) {
      int index = 0;
      List<String> partNumbers = Lists.newArrayList();
      while (index < partNodes.getLength()){
         Element item = (Element) partNodes.item(index);
         partNumbers.add((item.getElementsByTagName("pnr")).item(0).getTextContent());
         index++;
      }
      return partNumbers;
   }
   
}
