package br.com.treinaweb.agenda.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlets extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6281431875467534355L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 PrintWriter out = resp.getWriter();
	 out.println("<html>");
	 out.println("<head>");
	 out.println("<title>Olá apartir do Servlet</title>");
	 out.println("</head>");
	 out.println("<body>");
	 out.println("<p> Olá mundo! Vim do Servlet!</p>");
	 out.println("</body>");
	 out.println("</html>");
	}
	
	

}
