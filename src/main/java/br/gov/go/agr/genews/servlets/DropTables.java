package br.gov.go.agr.genews.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jedi.db.engine.JediEngine;

/**
 * Servlet implementation class DropTables
 * 
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
@WebServlet(
   description = "Servlet responsável por excluir as tabelas do banco de dados",
   urlPatterns = { "/droptables" }
)
public class DropTables extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   /**
    * @see HttpServlet#HttpServlet()
    */
   public DropTables() {
      super();
   }
   
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      JediEngine.droptables();
   }
   
}
