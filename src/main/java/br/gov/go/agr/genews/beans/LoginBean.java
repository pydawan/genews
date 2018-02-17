package br.gov.go.agr.genews.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.gov.go.agr.genews.models.Usuario;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
@SessionScoped
@ManagedBean
public class LoginBean implements Serializable {
   
   private static final long serialVersionUID = 1L;
   private Usuario usuario;
   private String login;
   private String senha;
   
   @PostConstruct
   public void init() {
      usuario = new Usuario();
      login = "";
      senha = "";
   }
   
   public String entrar() {
      login = usuario.getLogin();
      senha = usuario.getSenha();
      if (isLoginValido(login, senha)) {
         login = String.format("login='%s'", login);
         senha = String.format("senha='%s'", senha);
         usuario = Usuario.objects.<Usuario>filter(login, senha).first();
         usuario.setLogado(true);
         return "admin";
      }
      return "login";
   }
   
   public String sair() {
      FacesContext fc = FacesContext.getCurrentInstance();
      HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
      session.invalidate();
      usuario = new Usuario();
      return "login";
   }
   
   public boolean isLoginOrSenhaVazios() {
      String login = usuario.getLogin();
      String senha = usuario.getSenha();
      login = login == null ? "" : login.trim();
      senha = senha == null ? "" : senha.trim();
      if (login.isEmpty() || senha.isEmpty()) {
         return true;
      }
      return false;
   }
   
   public boolean isLoginValido(String login, String senha) {
      // 1 - Campos login e senha vazios - login invalido.
      if (isLoginOrSenhaVazios()) {
         return false;
      }
      login = usuario.getLogin();
      login = String.format("login='%s'", login);
      senha = usuario.getSenha();
      senha = String.format("senha='%s'", senha);
      // 2 - Existe usuário cadastrado com o login e senha informados?
      // return !Usuario.objects.filter(login, senha).isEmpty();
      return Usuario.objects.filter(login, senha).isNotEmpty();
   }
   
   public Usuario getUsuario() {
      return usuario;
   }
   
   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }
   
}
