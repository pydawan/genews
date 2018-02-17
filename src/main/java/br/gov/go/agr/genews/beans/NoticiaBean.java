package br.gov.go.agr.genews.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.gov.go.agr.genews.models.Noticia;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
@SessionScoped
@ManagedBean
public class NoticiaBean implements java.io.Serializable {
   
   private static final long serialVersionUID = 1L;
   private Noticia noticia;
   
   @PostConstruct
   public void init() {
      noticia = new Noticia();
   }
   
   public Noticia getNoticia() {
      return noticia;
   }
   
   public void setNoticia(Noticia noticia) {
      this.noticia = noticia;
   }
   
   public void adicionar(ActionEvent actionEvent) {
      noticia = new Noticia();
   }
   
   public void editar(Noticia noticia) {
      this.noticia = noticia;
   }
   
   public void confirmarRemocao(Noticia noticia) {
      this.noticia = noticia;
   }
   
   public void remover(ActionEvent actionEvent) {
      noticia.delete();
   }
   
   public void salvar(ActionEvent actionEvent) {
      noticia.save();
   }
   
   public List<Noticia> getList() {
      return Noticia.objects.all();
   }
   
}
