package br.gov.go.agr.genews.models;

import jedi.db.models.BooleanField;
import jedi.db.models.CascadeType;
import jedi.db.models.CharField;
import jedi.db.models.ForeignKeyField;
import jedi.db.models.Manager;
import jedi.db.models.Model;
import jedi.db.models.TextField;
import jedi.db.models.URLField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @version v1.0.0
 */
@Getter
@Setter
@Accessors(fluent = true, chain = true)
@ToString(includeFieldNames = true)
public class Noticia extends Model {
   
   private static final long serialVersionUID = 1L;
   
   @CharField(max_length = 100)
   private String titulo;
   
   @TextField
   private String conteudo;
   
   @ForeignKeyField(cascade_type = CascadeType.NONE)
   private TemplateNewsletter template;
   
   @URLField
   private String url;
   
   @BooleanField(default_value = false)
   private boolean intranet;
   
   public static Manager objects = new Manager(Noticia.class);
   
   public Noticia(String titulo, String conteudo, TemplateNewsletter template, String url) {
      this.titulo = titulo;
      this.conteudo = conteudo;
      this.template = template;
      this.url = url;
   }
   
   public Noticia() {
      this("", "", new TemplateNewsletter(), "");
   }
   
   public static Noticia of(String titulo, String conteudo, TemplateNewsletter template, String url) {
      return new Noticia(titulo, conteudo, template, url);
   }
   
   public static Noticia of() {
      return new Noticia();
   }
   
   public String getTitulo() {
      return titulo;
   }
   
   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }
   
   public String getConteudo() {
      return conteudo;
   }
   
   public void setConteudo(String conteudo) {
      this.conteudo = conteudo;
   }
   
   public TemplateNewsletter getTemplate() {
      return template;
   }
   
   public void setTemplate(TemplateNewsletter template) {
      this.template = template;
   }
   
   public String getUrl() {
      return url;
   }
   
   public void setUrl(String url) {
      this.url = url;
   }
   
   public boolean getIntranet() {
      return intranet;
   }
   
   public void setIntranet(boolean intranet) {
      this.intranet = intranet;
   }
   
}
