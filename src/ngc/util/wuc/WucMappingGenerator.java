package ngc.util.wuc;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;

import com.google.common.collect.Lists;

@WebServlet("/WucMappingGenerator")
public class WucMappingGenerator extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
   protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      // Send the fileLocation to a PubTypeOrganizer to seperate out pubs.
      PubTypeOrganizer pubOrganizer = new PubTypeOrganizer().seperatePubTypesForDirectory("TestAssets");

      // Create an IpdFile object for each 941 file found.
      List<IpdModel> ipdFilesList = Lists.newArrayList();
      for (String fileName : pubOrganizer.getIpdFileNameList()) {
         ipdFilesList.add(new IpdModel(fileName).setParentDirectoryPath("TestAssets"));
      }

      // Make an ipdParser
      IpdParser ipdParser = new IpdParser();
      // For each idpdFile, send it to the parser and set its partNumbers
      for (IpdModel ipdFile : ipdFilesList) {
         ipdParser.setPartNumbersForFile(ipdFile);
      }

      // Create the WucBaseline and comparer
      Comparer comparer = new Comparer(new WucBaselineData("TestAssets/MQ-4C_UAV_WUCBaselineReportAsOf2017-06-12.txt"));

      // For each IpdFile, send it to the WucBaseline and have the wucs be set
      for (IpdModel ipdFile : ipdFilesList) {
         ipdFile.setWucsThatMatchPartNumbers(comparer.findAllWucEntriesForFile(ipdFile));
      }

      // Create a WucMappingFileWriter
      WucMappingFileWriter wucMappingFileWriter = new WucMappingFileWriter("TestAssets/testWucWriterFile.csv");
      // Send the ipdFiles to the wucMappingFileWriter to be written out to the
      // file
      for (IpdModel ipdFile : ipdFilesList) {
         wucMappingFileWriter.printEntriesForIpdFile(ipdFile);
      }
      wucMappingFileWriter.close();

      // Log Those files not Listed
      WucMappingGeneratorLogger logger = new WucMappingGeneratorLogger("TestAssets/wucGeneratorLog.txt");
      logger.logFilesNotCoveredInWucMappingFor(pubOrganizer);
      logger.close();
      res.sendRedirect(req.getContextPath() + "/index.jsp");
   }

}
