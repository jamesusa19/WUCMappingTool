package ngc.util.wuc;

import java.io.File;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

@WebServlet("/BrowseFolder")
public class BrowseFolderServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = fc.showOpenDialog(null);

		res.setContentType("text/plain");
	    res.setCharacterEncoding("UTF-8");
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
		    res.getWriter().write(selectedFile.getAbsolutePath());
		}

	}
}
