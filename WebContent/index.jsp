<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ngc.util.wuc.*,java.util.List,com.google.common.collect.Lists" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
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
    out.print("<h1>Generating Csv File</h1>");
    for (IpdModel ipdFile : ipdFilesList) {
       wucMappingFileWriter.printEntriesForIpdFile(ipdFile);
    }
    wucMappingFileWriter.close();

    // Log Those files not Listed
    WucMappingGeneratorLogger logger = new WucMappingGeneratorLogger("TestAssets/wucGeneratorLog.txt");
    out.println("<h1>Generating Log File<h3>");
    logger.logFilesNotCoveredInWucMappingFor(pubOrganizer);
    logger.close();
	%>
</body>
</html>