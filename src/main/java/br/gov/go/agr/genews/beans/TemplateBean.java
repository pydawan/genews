package br.gov.go.agr.genews.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import br.gov.go.agr.genews.models.Newsletter;
import br.gov.go.agr.genews.models.Noticia;
import br.gov.go.agr.genews.models.TemplateNewsletter;
import br.gov.go.agr.genews.models.Usuario;
import br.gov.go.agr.genews.template.Template;
import br.gov.go.agr.genews.template.TemplateContext;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
@SessionScoped
@ManagedBean
public class TemplateBean implements java.io.Serializable {
   
   private static final long serialVersionUID = 1L;
   private TemplateNewsletter templateNewsletter;
   private Newsletter newsletter;
   private List<Noticia> noticias;
   private ExternalContext context;
   private Map<String, Object> session;
   private LoginBean loginBean;
   private Usuario usuario;
   private String newsletterId;
   private String intranet;
   private String html;
   
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
   
   public List<Noticia> getNoticias() {
      return noticias;
   }
   
   public void setNoticias(List<Noticia> noticias) {
      this.noticias = noticias;
   }
   
   public List<Newsletter> getList() {
      return TemplateNewsletter.objects.<Newsletter>all().orderBy("-id");
   }
   
   public void adicionar(ActionEvent actionEvent) {
      templateNewsletter = new TemplateNewsletter();
      templateNewsletter.setAutor(usuario);
      newsletter = Newsletter.objects.earliest();
      templateNewsletter.setNewsletter(newsletter);
      noticias = new ArrayList<Noticia>();
      noticias.add(new Noticia());
      noticias.add(new Noticia());
      noticias.add(new Noticia());
      noticias.add(new Noticia());
      noticias.add(new Noticia());
      templateNewsletter.setNoticias(noticias);
      if (newsletter != null) {
         newsletterId = "" + newsletter.getId();
      }
   }
   
   public void editar(TemplateNewsletter template) {
      noticias = Noticia.objects.filter("template_id = " + template.getId());
      template.setNoticias(noticias);
      this.templateNewsletter = template;
      newsletterId = "" + template.getNewsletter().getId();
   }
   
   public void confirmarRemocao(TemplateNewsletter template) {
      this.templateNewsletter = template;
   }
   
   public void remover(ActionEvent actionEvent) {
      templateNewsletter.delete();
   }
   
   public void salvar(ActionEvent actionEvent) {
      int newsletterId = templateNewsletter.getNewsletter().getId();
      TemplateNewsletter ultimoTemplate = TemplateNewsletter.objects.<TemplateNewsletter>filter("newsletter_id = " + newsletterId).latest();
      if (templateNewsletter.getId() == 0) {
         // Se existe último template
         if (ultimoTemplate != null) {
            // incrementa a edição
            templateNewsletter.setEdicao(ultimoTemplate.getEdicao() + 1);
         } else {
            // senão é a primeira edição.
            templateNewsletter.setEdicao(1);
         }
      }
      templateNewsletter.setAutor(usuario);
      templateNewsletter.save();
      for (Noticia noticia : templateNewsletter.getNoticias()) {
         noticia.save();
      }
   }
   
   public void limpar(ActionEvent actionEvent) {
      newsletter = Newsletter.objects.earliest();
      if (templateNewsletter.getNoticias() != null) {
         for (Noticia noticia : templateNewsletter.getNoticias()) {
            noticia.setTitulo("");
            noticia.setConteudo("");
            noticia.setUrl("");
            noticia.setIntranet(false);
         }
      }
   }
   
   public String getNewsletterId() {
      return newsletterId;
   }
   
   public void setNewsletterId(String newsletterId) {
      this.newsletterId = newsletterId;
   }
   
   public void getSelectedNewsletter(AjaxBehaviorEvent event) {
      Object value = ((UIOutput) event.getSource()).getValue();
      Newsletter newsletter = (Newsletter) value;
      templateNewsletter.setNewsletter(newsletter);
   }
   
   public String getHtml() {
      TemplateContext c = new TemplateContext();
      c.put("template", templateNewsletter);
      c.put("edicao", String.format("%03d", templateNewsletter.getEdicao()));
      Template template = new Template();
      html = template.render("base", c);
      Document doc = Jsoup.parse(html);
      doc.outputSettings().indentAmount(4);
      doc.outputSettings().prettyPrint(true);
      html = doc.html();
      return html;
   }
   
   public TemplateNewsletter getTemplate() {
      return templateNewsletter;
   }
   
   public void setTemplate(TemplateNewsletter template) {
      this.templateNewsletter = template;
   }
   
   public String getIntranet() {
      return intranet;
   }
   
   public void setIntranet(String intranet) {
      this.intranet = intranet;
   }
   
   public void visualizar(TemplateNewsletter template) {
      this.templateNewsletter = template;
      noticias = Noticia.objects.filter("template_id = " + template.getId());
      this.templateNewsletter.setNoticias(noticias);
   }
   
}
