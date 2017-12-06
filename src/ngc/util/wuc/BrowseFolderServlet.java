package ngc.util.wuc;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/BrowseFolder")
public class BrowseFolderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/plain");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write("TODO: implement folder browsing");
	}
}
