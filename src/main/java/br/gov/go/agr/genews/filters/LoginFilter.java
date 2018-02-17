package br.gov.go.agr.genews.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.gov.go.agr.genews.beans.LoginBean;

/**
 * Servlet Filter implementation class UsuarioLogadoFilter.
 * 
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
@WebFilter(
   description = "Filtro que libera ou restringe o acesso a recursos da aplicação " + "caso o usuário esteja ou não logado.",
   urlPatterns = { "/UsuarioLogadoFilter" }
)
public class LoginFilter implements Filter {
   
   /**
    * Default constructor.
    */
   public LoginFilter() {
      // TODO Auto-generated constructor stub
   }
   
   /**
    * @see Filter#destroy()
    */
   public void destroy() {
      // TODO Auto-generated method stub
   }
   
   /**
    * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
    */
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      HttpSession session = httpRequest.getSession();
      LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
      if (loginBean == null || loginBean.getUsuario() == null || !loginBean.getUsuario().isLogado()) {
         // Limpa cache
         httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
         httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
         httpResponse.setDateHeader("Expires", 0); // Proxies.
         // Fim limpa cache
         String path = httpRequest.getContextPath();
         path = path + "/index.xhtml";
         httpResponse.sendRedirect(path);
      } else {
         chain.doFilter(request, response);
      }
   }
   
   /**
    * @see Filter#init(FilterConfig)
    */
   public void init(FilterConfig fConfig) throws ServletException {
      // TODO Auto-generated method stub
   }
   
}
