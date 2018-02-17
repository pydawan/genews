package br.gov.go.agr.genews.servlets;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class ApplicationBootstrapServlet.
 * 
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
@WebServlet(
   description = "Servlet responsável pela configuração inicial da aplicação.",
   urlPatterns = { "/JediServlet" }
)
public class JediServlet extends HttpServlet {
   
   private static final long serialVersionUID = 1L;
   
   /**
    * @see HttpServlet#HttpServlet()
    */
   public JediServlet() {
      super();
      // TODO Auto-generated constructor stub
   }
   
   /**
    * @see Servlet#init(ServletConfig)
    */
   public void init(ServletConfig config) throws ServletException {
      System.setProperty("user.dir", "/home/thiago/workspace/genews");
      // System.setProperty("user.dir", "/home/supi/programas/apache-tomcat-7.0.54/webapps/genews");
   }
   
   /**
    * @see Servlet#destroy()
    */
   public void destroy() {
      // TODO Auto-generated method stub
   }
   
}
