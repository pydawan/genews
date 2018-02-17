package br.gov.go.agr.genews.beans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.gov.go.agr.genews.models.Newsletter;
import br.gov.go.agr.genews.models.Usuario;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
@SessionScoped
@ManagedBean
public class NewsletterBean implements java.io.Serializable {
   
   private static final long serialVersionUID = 1L;
   private Newsletter newsletter;
   private ExternalContext context;
   private Map<String, Object> session;
   private LoginBean loginBean;
   private Usuario usuario;
   
   @PostConstruct
   public void init() {
      context = FacesContext.getCurrentInstance().getExternalContext();
      session = context.getSessionMap();
      loginBean = (LoginBean) session.get("loginBean");
      usuario = (Usuario) loginBean.getUsuario();
   }
   
   public Newsletter getNewsletter() {
      return newsletter;
   }
   
   public void setNewsletter(Newsletter newsletter) {
      this.newsletter = newsletter;
   }
   
   public void adicionar(ActionEvent actionEvent) {
      newsletter = new Newsletter();
   }
   
   public void editar(Newsletter newsletter) {
      this.newsletter = newsletter;
   }
   
   public void confirmarRemocao(Newsletter newsletter) {
      this.newsletter = newsletter;
   }
   
   public void remover(ActionEvent actionEvent) {
      newsletter.delete();
   }
   
   public void salvar(ActionEvent actionEvent) {
      newsletter.setAutor(usuario);
      newsletter.save();
   }
   
   public void limpar(ActionEvent actionEvent) {
      newsletter.setNome("");
      newsletter.setEmail("");
      newsletter.setRemetente("");
   }
   
   public List<Newsletter> getList() {
      return Newsletter.objects.<Newsletter>all().orderBy("-id");
   }
   
}
