package ngc.util.wuc;

import java.util.List;

import com.google.common.collect.Lists;

public class WucMappingGenerator {

   public static void main(String[] args) {
      // Send the fileLocation to a PubTypeOrganizer to seperate out pubs.
      PubTypeOrganizer pubOrganizer = new PubTypeOrganizer().seperatePubTypesForDirectory(args[0]);

      // Create an IpdFile object for each 941 file found.
      List<IpdModel> ipdFilesList = Lists.newArrayList();
      for (String fileName : pubOrganizer.getIpdFileNameList()) {
         ipdFilesList.add(new IpdModel(fileName).setParentDirectoryPath(args[0]));
      }

      // Make an ipdParser
      IpdParser ipdParser = new IpdParser();
      // For each idpdFile, send it to the parser and set its partNumbers
      for (IpdModel ipdFile : ipdFilesList) {
         ipdParser.setPartNumbersForFile(ipdFile);
      }

      // Create the WucBaseline and comparer
      Comparer comparer = new Comparer(new WucBaselineData(args[1]));

      // For each IpdFile, send it to the WucBaseline and have the wucs be set
      for (IpdModel ipdFile : ipdFilesList) {
         ipdFile.setWucsThatMatchPartNumbers(comparer.findAllWucEntriesForFile(ipdFile));
      }

      // Create a WucMappingFileWriter
      WucMappingFileWriter wucMappingFileWriter = new WucMappingFileWriter(args[2]);
      // Send the ipdFiles to the wucMappingFileWriter to be written out to the
      // file
      for (IpdModel ipdFile : ipdFilesList) {
         wucMappingFileWriter.printEntriesForIpdFile(ipdFile);
      }
      wucMappingFileWriter.close();

      // Log Those files not Listed
      WucMappingGeneratorLogger logger = new WucMappingGeneratorLogger(args[3]);
      logger.logFilesNotCoveredInWucMappingFor(pubOrganizer);
      logger.close();
   }

}
