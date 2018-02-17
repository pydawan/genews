package br.gov.go.agr.genews.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.gov.go.agr.genews.models.Usuario;

/**
 * Servlet implementation class AddUsers
 */
@WebServlet(
   description = "Servlet responsável por popular a tabela de usuários.",
   urlPatterns = { "/addusers" }
)
public class AddUsers extends HttpServlet {
   
   private static final long serialVersionUID = 1L;
   
   /**
    * @see HttpServlet#HttpServlet()
    */
   public AddUsers() {
      super();
   }
   
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      Usuario usuario = new Usuario();
      usuario.setNome("Thiago");
      usuario.setSobrenome("Monteiro");
      usuario.setEmail("thiago.amm.agr@gmail.com");
      usuario.setLogin("admin");
      usuario.setSenha("Thi88**");
      usuario.setAtivo(true);
      usuario.setAdmin(true);
      usuario.save();
      usuario = new Usuario();
      usuario.setNome("Gerência de Comunicação");
      usuario.setSobrenome("AGR");
      usuario.setEmail("comunicacaoagr@gmail.com");
      usuario.setLogin("gecom");
      usuario.setSenha("comunicaagr");
      usuario.setAtivo(true);
      usuario.setAdmin(false);
      usuario.save();
   }
   
}
