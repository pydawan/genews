package br.gov.go.agr.genews.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jedi.db.engine.JediEngine;

/**
 * Servlet implementation class SyncDB
 * 
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
@WebServlet(
   description = "Servlet responsável por gerar as tabelas no banco de dados",
   urlPatterns = { "/syncdb" }
)
public class SyncDB extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   /**
    * @see HttpServlet#HttpServlet()
    */
   public SyncDB() {
      super();
      // TODO Auto-generated constructor stub
   }
   
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      JediEngine.syncdb();
   }
   
}
