package ngc.util.wuc;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.servlet.annotation.WebServlet;
import java.io.*;

import com.google.common.collect.Lists;

@WebServlet("/WucMappingGenerator")
public class WucMappingGenerator extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Send the fileLocation to a PubTypeOrganizer to seperate out pubs.
		try{
			PubTypeOrganizer pubOrganizer = new PubTypeOrganizer().seperatePubTypesForDirectory(req.getParameter("pubs"));

			// Create an IpdFile object for each 941 file found.
			List<IpdModel> ipdFilesList = Lists.newArrayList();
			for (String fileName : pubOrganizer.getIpdFileNameList()) {
				ipdFilesList.add(new IpdModel(fileName).setParentDirectoryPath(req.getParameter("pubs")));
			}

			// Make an ipdParser
			IpdParser ipdParser = new IpdParser();
			// For each idpdFile, send it to the parser and set its partNumbers
			for (IpdModel ipdFile : ipdFilesList) {
				ipdParser.setPartNumbersForFile(ipdFile);
			}

			// Create the WucBaseline and comparer
			Comparer comparer = new Comparer(new WucBaselineData(req.getParameter("baseline")));

			// For each IpdFile, send it to the WucBaseline and have the wucs be set
			for (IpdModel ipdFile : ipdFilesList) {
				ipdFile.setWucsThatMatchPartNumbers(comparer.findAllWucEntriesForFile(ipdFile));
			}

			// Create a WucMappingFileWriter
			WucMappingFileWriter wucMappingFileWriter = new WucMappingFileWriter(req.getParameter("csv"));
			// Send the ipdFiles to the wucMappingFileWriter to be written out to the
			// file
			for (IpdModel ipdFile : ipdFilesList) {
				wucMappingFileWriter.printEntriesForIpdFile(ipdFile);
			}
			wucMappingFileWriter.close();

			// Log Those files not Listed
			WucMappingGeneratorLogger logger = new WucMappingGeneratorLogger(req.getParameter("log"));
			logger.logFilesNotCoveredInWucMappingFor(pubOrganizer);
			logger.close();
		}
		catch(Exception e){
		//	System.out.println("Please input appropriate Directory Location and file locations " + e.getMessage());
			String message = " Please input appropriate Directory locations and/or File Locations ";
			    JOptionPane.showMessageDialog(new JFrame(), message, "Sorry, Please Try again",
			        JOptionPane.ERROR_MESSAGE);

		}
		finally{
			res.sendRedirect(req.getContextPath() + "/newMapping");
		}
	}

}
